package project.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import java.util.Map.Entry;

import project.Color;
import project.Game;
import project.LakeTile;
import project.LanternCard;
import project.PlayArea;
import project.Player;
import project.Position;
import project.Rotation;
import project.Supply;

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
		
		ArrayList<Integer> solution = new ArrayList<Integer>(4);
		int valueCounter=1;
		
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		int lanterns = player.getLanternCards().size();
		ArrayList<Position> availableList = playarea.getPositionAvailableLakeTileOnBoard();
		//System.out.print("Total lake tiles a player has : "+player.getLakeTiles().size());
		for(int i=0;i<player.getLakeTiles().size();i++)
		{
			System.out.println(" value of i : " + i);
			LakeTile active_laketile = player.getLakeTiles().get(i);
			ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = player.placeLakeTileMenu(playarea, active_laketile);
			//System.out.print("Lake tile selected :");
			for(int l=0;l<4;l++){
				ArrayList<Color> color_list = new ArrayList<Color>(active_laketile.getColorOfFourSides());
				//System.out.print(color_list.get(l).name());
			}

			for(int j=0; j<availableList.size();j++)
			{
				System.out.println(" value of j : " + j);
				//System.out.println("positions : "+ availableList.get(j).getText());
				int pos_laketile_opt = j;
				HashMap<Rotation, Vector<Object>> adjacent_colors = player.getPossibleRotation(availableList, adjacent_color_list, playarea, active_laketile, pos_laketile_opt);

				
				HashMap<Rotation, Vector<Object>> color_platform = playarea.getAdjacentColor(availableList.get(j));
				//System.out.println(playarea.getAdjacentColorText(color_platform));
				for(int k=0;k<4;k++)
				{
					System.out.println(" value of k : " + k);
					int temp = valueCounter;
					int rotation_opt = k;
					player.setRotationOnActiveLakeTile(active_laketile, rotation_opt);
					
					String lanterncolor = checkdistributeLanternCard(active_laketile, playarea.getSupply(),game);
					valueCounter = checkgetBonusPlaceLakeTile(active_laketile, adjacent_colors,game);
					//System.out.println(" value of temp : " + temp);
					//System.out.println(" value of value counter : "+ valueCounter);
					if(valueCounter > temp)
					{
						solution.clear();
						solution.add(i);
						solution.add(j);
						solution.add(k);
						solution.add(valueCounter);
					}
					System.out.println("your lantern color :"+ lanterncolor);

					lanterns = player.getLanternCards().size();

				}
				int x = availableList.get(j).getX();
				int y = availableList.get(j).getY();
				game.getPlayArea().getLakeTilesOnBoard()[x][y] = null;
			}
		}
		System.out.println(playarea.getLakeTileBoardText());
		System.out.println("Best Solution is : I :" + solution.get(0)+" J : " +solution.get(1)+" K : "+solution.get(2)+" with value : " +solution.get(3));
	}
	
	/**
	 * This method distribute lantern card from supply to player
	 * @param active_laketile lake tile from the stack
	 * @param lanternStacks lantern card stack 
	 */

	public String checkdistributeLanternCard(LakeTile active_laketile, Supply supply, Game game) 
	{
		ArrayList<Color> color_list = new ArrayList<Color>(active_laketile.getColorOfFourSides());
		String lanternCard = color_list.get(0).name();
		
		return lanternCard;
	}
	
	/**
	 * This method give bonus lake tile if two color of the same are facing each other
	 * @param active_laketile lake tile on the play area
	 * @param adjacent_colors adjacent color
	 * @throws Exception exception
	 */
	public int checkgetBonusPlaceLakeTile(LakeTile active_laketile, HashMap<Rotation, Vector<Object>> adjacent_colors, Game game) throws Exception 
	{
		int valueCounter=5;
		// get bonus for adjacent and platform
		for (int i = 0; i < adjacent_colors.size(); i++) 
		{
			for (Entry<Rotation, Vector<Object>> c : adjacent_colors.entrySet())
			{
				Vector<Object> color_platform = (Vector<Object>) c.getValue();

				if (c.getKey().equals(Rotation.D0)) 
				{
					valueCounter = checkgetBonusDirection(Rotation.D0, active_laketile, color_platform,game);
				} else if (c.getKey().equals(Rotation.D90)) 
				{
					
					valueCounter = checkgetBonusDirection(Rotation.D90, active_laketile, color_platform,game);
				} else if (c.getKey().equals(Rotation.D180)) 
				{
					valueCounter = checkgetBonusDirection(Rotation.D180, active_laketile, color_platform,game);
				} 
				else if (c.getKey().equals(Rotation.D270)) 
				{
					valueCounter = checkgetBonusDirection(Rotation.D270, active_laketile, color_platform,game);
					
				}
			}
		}
		//System.out.println("value counter in bonus  : " + valueCounter);
		return valueCounter;
	}

	/**
	 * This method checks the direction to give bonus
	 * Comparing one color side of player's laketile with adjacent color
	 * @param r Degree of rotation
	 * @param active_laketile lake tile on play area
	 * @param color_platform color of platform
	 * @throws Exception exception
	 */
	private int checkgetBonusDirection(Rotation r, LakeTile active_laketile,Vector<Object> color_platform,Game game) throws Exception 
	{
		int valueCounter = 0;
		Queue<Player> players = game.getPlayers();
		int checkFavorToken = 0;
		String lanterncard = null;
		PlayArea playArea = game.getPlayArea();
		Player current_player = players.element();
		Color side_color = active_laketile.getSideOfColor(r);
		if (side_color == color_platform.get(0)) 
		{

			lanterncard = color_platform.get(0).toString();

			int favor_token = playArea.getNumberOfFavorTokens();
			if (active_laketile.isPlatform()&&favor_token>0)
			{
				checkFavorToken = current_player.getNumberOfFavorTokens() + 1;
			}
			
			if ((Boolean) color_platform.get(1)&&favor_token>0) 
			{
				checkFavorToken = current_player.getNumberOfFavorTokens() + 1;
			}
		}
		if(checkFavorToken > 0){
			System.out.println("extra favor token : "+ checkFavorToken);
			valueCounter++;
		}
		if(lanterncard != null){
			System.out.println("extra lantern cards : " + lanterncard);
			valueCounter++;
		}
		//System.out.println("value counter in bonus direction : " + valueCounter);
		return valueCounter;
	}
}
