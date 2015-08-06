package project.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import project.Color;
import project.Game;
import project.LakeTile;
import project.PlayArea;
import project.Player;
import project.Position;
import project.Rotation;

public class Greed extends GreedyStrategy{
	int lakeTiletochoose = 0;
	private static final Random RANDOM = new Random();
	
	/**
	 * Pick a lake tile with favor token from the players hand, if there is not
	 * a lake tile with favor token on the hand, then select a random tile. 
	 */
	protected void PickALakeTile(Queue<Player> players){
		
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		
		int numberOfLanternCards = player.getLanternCards().size();
		
		if(numberOfLanternCards >0  && numberOfLanternCards<3)
		{
			for(int i=0; i<player.getLakeTiles().size(); i++){
				boolean lakeTileWithPlatform = player.getLakeTiles().get(i).isPlatform();
				if(lakeTileWithPlatform){
					lakeTiletochoose = i;
					break;
				}
				else{
					lakeTiletochoose = RANDOM.nextInt(player.getLakeTiles().size());
				}
			}
			
		}
		System.out.println("Player selected "+ lakeTiletochoose);
	}
	
	/**
	 * 
	 */
	protected Position checkLakeTilewithAvailable(Queue<Player> players, PlayArea playarea) throws Exception{
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		Queue<Color> color = player.getLakeTiles().get(lakeTiletochoose).getColorOfFourSides();
		ArrayList<Color> list = new ArrayList<Color>(color);
		ArrayList<Position> availableList = playarea.getPositionAvailableLakeTileOnBoard();
		Position selectedIndex = null;
	
		for(int j=0; j< availableList.size(); j++)
		{
			Position index = availableList.get(j);
			for (int i = 0; i < list.size(); i++) 
			{
				HashMap<Rotation, Vector<Object>> color_platform = playarea.getAdjacentColor(index);
				Color c = list.get(i);

				for(Rotation r : Rotation.values())
				{
					if(color_platform.get(r) != null){
						Color colors = (Color) color_platform.get(r).get(0);
						if(c.name() == colors.name()){
							System.out.println("color found : "+color_platform.get(r).get(0));
							System.out.println("index selected :"+ j); 
							selectedIndex = index;
							break;
						}
					}
				}
			}
		}
		System.out.println("Player selected position :" + selectedIndex.getText());
		return selectedIndex;
	}
	
	/**
	 * to check if the player can make a dedication or not for the
	 * current situation
	 */
	protected boolean canMakeDedication(Player player){
		boolean flag;
		if(player.isFourOfAKind() || player.isThreePair() || player.isSevenUnique()){
			System.out.println("Dedication is possible to make");
			flag = true;
		}
		else{
			flag=false;
		}
		return flag;
	}
	
	/**
	 * check all possible solutions for each three steps of place a lake tile
	 * @throws Exception 
	 */
	protected void simulateGamePlay(Queue<Player> players, PlayArea playarea, Game game) throws Exception
	{
		
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		int lanterns = player.getLanternCards().size();
		ArrayList<Position> availableList = playarea.getPositionAvailableLakeTileOnBoard();
		
		for(int i=0;i<player.getLakeTiles().size();i++)
		{
			LakeTile active_laketile = player.getLakeTiles().remove(i);
			ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = player.placeLakeTileMenu(playarea, active_laketile);

			for(int j=0; j<availableList.size();j++)
			{
				System.out.println("positions : "+ availableList.get(j).getText());
				int pos_laketile_opt = j;
				HashMap<Rotation, Vector<Object>> adjacent_colors = player.getPossibleRotation(availableList, adjacent_color_list, playarea, active_laketile, pos_laketile_opt);

				for(int k=0;k<4;k++)
				{

					int rotation_opt = k;
					player.setRotationOnActiveLakeTile(active_laketile, rotation_opt);
					
					game.distributeLanternCard(active_laketile, playarea.getSupply());
					game.getBonusPlaceLakeTile(active_laketile, adjacent_colors);
					/*if(canMakeDedication(player)){
						System.out.println("This is the perfect lake tile to put");
						break;
					}*/
					
					System.out.print("player name is: "+player.getName());
					System.out.println("------ number of lanterns: "+lanterns);
					lanterns = player.getLanternCards().size();
					if(lanterns >10){
						System.out.println("more than 10");
						break;
					}
					int x = availableList.get(j).getX();
					int y = availableList.get(j).getY();
					game.getPlayArea().getLakeTilesOnBoard()[x][y] = null;
				
					//System.out.println();
				}
			}
		}
		System.out.println(playarea.getLakeTileBoardText());
		
	}	
}
