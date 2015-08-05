package project.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import project.Color;
import project.PlayArea;
import project.Player;
import project.Position;
import project.Rotation;

public class Greed extends GreedyStrategy{
	int lakeTiletochoose = 0;
	private static final Random RANDOM = new Random();
	protected void makeADedicationMenu(Queue players){
		
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		
		System.out.println("player name issss: "+ player.getName());
		int numberOfLanternCards = player.getLanternCards().size();
		if(numberOfLanternCards >0  && numberOfLanternCards<3)
		{
			System.out.println(player.getLakeTiles().size() +"number of lake tiles a player has");
			for(int i=0; i<player.getLakeTiles().size(); i++){
				boolean lakeTileWithPlatform = player.getLakeTiles().get(i).isPlatform();
				//System.out.println("platfrom : "+ player.getLakeTiles().get(i).isPlatform());
				if(lakeTileWithPlatform){
					System.out.print("found it!");
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
	
	protected Position simulateGame(Queue players, PlayArea playarea) throws Exception{
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		Position selectedIndex = null;
		Queue color = player.getLakeTiles().get(lakeTiletochoose).getColorOfFourSides();
		ArrayList<Color> list = new ArrayList<Color>(color);
		ArrayList<Position> availableList = playarea.getPositionAvailableLakeTileOnBoard();
	
		for(int j=0; j< availableList.size(); j++)
		{
			Position index = availableList.get(j);
			for (int i = 0; i < list.size(); i++) 
			{
				HashMap<Rotation, Vector<Object>> color_platform = playarea.getAdjacentColor(index);
				Color c = list.get(i);
				//System.out.println(c.name());
				//System.out.println("color_platformss : "+color_platform);
				for(Rotation r : Rotation.values())
				{
					//System.out.println(color_platform.get(r).get(0));
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
	
}
