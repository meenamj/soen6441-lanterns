package project.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;
import java.util.Map.Entry;

import project.Color;
import project.Game;
import project.LakeTile;
import project.PlayArea;
import project.Player;
import project.Position;
import project.Rotation;
import project.Supply;

public class Greed extends GreedyStrategy{
	int lakeTiletochoose = 0;
	private static final Random RANDOM = new Random();
	
	
	/**
	 * to check if the player can make a dedication or not for the
	 * current situation
	 */
	protected boolean canMakeDedication(Queue<Player> players){
		boolean flag;
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		if(player.isFourOfAKind() || player.isThreePair() || player.isSevenUnique()){
			System.out.println("Dedication is possible to make");
			flag = true;
		}
		else{
			System.out.println("Dedication is not possible");
			flag=false;
		}
		return flag;
	}
	
	protected int whichDedication(Queue<Player> players){
		int choice=0;
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		if(player.isThreePair()){
			choice = 1;
		}
		else if(player.isFourOfAKind()){
			choice = 0;
		}
		else if(player.isSevenUnique()){
			choice = 2;
		}
		return choice;
	}
	
	protected boolean canExchange(Queue<Player> players){
		boolean flag;
		ArrayList<Player> playerList = new ArrayList<Player>(players);
		Player player = playerList.get(0);
		if(player.getNumberOfFavorTokens() > 2){
			System.out.println("Exchange is possible");
			performExchange(player);
			flag = true;
		}
		else{
			System.out.println("Can not Exchange lantern card");
			flag=false;
		}
		return flag;
		
	}
	
	private void performExchange(Player player){
		boolean DESC = false;
		
		int ORANGE = player.numOfCardColor(Color.ORANGE);
		int GREEN = player.numOfCardColor(Color.GREEN);
		int PURPLE = player.numOfCardColor(Color.PURPLE);
		int WHITE = player.numOfCardColor(Color.WHITE);
		int BLUE = player.numOfCardColor(Color.BLUE);
		int RED = player.numOfCardColor(Color.RED);
		int BLACK = player.numOfCardColor(Color.BLACK);
		
		 HashMap<Color,Integer> colors = new HashMap<Color,Integer>();
	      // Put elements to the map
		 colors.put(Color.ORANGE, ORANGE);
		 colors.put(Color.GREEN, GREEN);
		 colors.put(Color.PURPLE, PURPLE);
		 colors.put(Color.WHITE, WHITE);
		 colors.put(Color.BLUE, BLUE);
		 colors.put(Color.RED, RED);
		 colors.put(Color.BLACK, BLACK);
		 
			
		 Map<Color,Integer> sortedMap = sortByComparator(colors, DESC);
		 //System.out.println(sortedMap);
		 ExchangePlayerCard(sortedMap,player);
	}
	
	/**
	 * perform sorting on the HashMap of Lantern cards
	 * @param unsortMap unsorted map
	 * @param order sorting order
	 * @return sorted map
	 */
	private static Map<Color, Integer> sortByComparator(Map<Color, Integer> unsortMap, final boolean order)
    	{

        List<Entry<Color, Integer>> list = new LinkedList<Entry<Color, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Color, Integer>>()
        {
            public int compare(Entry<Color, Integer> o1,
                    Entry<Color, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Color, Integer> sortedMap = new LinkedHashMap<Color, Integer>();
        for (Entry<Color, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
	
	/**
	 * Choose a lantern card from player stack
	 * @param sortedMap
	 */
	protected void ExchangePlayerCard( Map<Color, Integer> sortedMap, Player player){
		int[] colors = new int[7];
		Color[] c = new Color[7];
		int counter = 0;
		 for (Entry<Color, Integer> entry : sortedMap.entrySet())
	     {
	        //System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
	        colors[counter] = entry.getValue();
	        c[counter] = entry.getKey();
	        counter++;
	     }

		if(player.getLanternCards().size() >= 7)
		 {
			 if(colors[0] == 2 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && player.getNumberOfFavorTokens() >= 4){
				 System.out.println(c[0] + " to " + c[5]);
				 System.out.println(c[1] + " to " + c[6]);
			 }
			 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && colors[5] == 1 && player.getNumberOfFavorTokens() >= 2){
				 System.out.println(c[0] + " to " + c[6]);
			 }
			 else if(colors[0] == 3 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && player.getNumberOfFavorTokens() >= 2){
				 System.out.println(c[2] + " to " + c[0]);
			 }
			 else{
				 
			 }
		 }
		 else if(player.getLanternCards().size() <= 4){
			 if(colors[0] == 3 && colors[1] == 1 && player.getNumberOfFavorTokens() >= 2){
				 System.out.println(c[1] + " to " + c[0]);
			 }
			 else if(colors[0] == 2 && colors[1] == 2 && player.getNumberOfFavorTokens() >= 4){
				 System.out.println(c[0] + " to " + c[1]);
				 System.out.println(c[0] + " to " + c[1]);
			 }
			 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && player.getNumberOfFavorTokens() >= 4){
				 System.out.println(c[1] + " to " + c[0]);
				 System.out.println(c[2] + " to " + c[0]);
			 }
			 else{
				 
			 }
		 }
		 else{
			 if(colors[0] == 2 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && player.getNumberOfFavorTokens() >= 2){
				 System.out.println(c[2] + " to " + c[3]);
			 }
			 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && player.getNumberOfFavorTokens() >= 4){
				 System.out.println(c[2] + " to " + c[1]);
				 System.out.println(c[4] + " to " + c[3]);
			 }
			 else{
				 
			 }
		 }
	}
	
	/**
	 * check all possible solutions for each three steps of place a lake tile
	 * @throws Exception 
	 */
	protected ArrayList<Integer> simulateGamePlay(Game game) throws Exception
	{
		ArrayList<Integer> solution = new ArrayList<Integer>(4);
		int valueCounter=0;
		Queue<Player> realplayers = game.getPlayers();
		PlayArea realplayarea = game.getPlayArea();
		
		ArrayList<Player> realplayerList = new ArrayList<Player>(realplayers);
		Player realplayer = realplayerList.get(0);

		ArrayList<Position> realavailableList = realplayarea.getPositionAvailableLakeTileOnBoard();

		for(int i=0;i<realplayer.getLakeTiles().size();i++)
		{
			//System.out.println(" value of i : " + i);
			//System.out.println(" list :"+realavailableList.size());
			for(int j=0; j<realavailableList.size();j++)
			{
				//System.out.println(" value of j : " + j);
				for(int k=0;k<4;k++)
				{
					Game gameObject = game.clone();
					
					Queue<Player> players = gameObject.getPlayers();
					PlayArea playarea = gameObject.getPlayArea();
					
					ArrayList<Player> playerList = new ArrayList<Player>(players);
					Player player = playerList.get(0);
					
					ArrayList<Position> availableList = playarea.getPositionAvailableLakeTileOnBoard();

					
					LakeTile active_laketile = player.getLakeTiles().get(i);
					ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = player.checkPlaceLakeTile(playarea, active_laketile);

					int pos_laketile_opt = j;
					HashMap<Rotation, Vector<Object>> adjacent_colors = player.getPossibleRotation(availableList, adjacent_color_list, playarea, active_laketile, pos_laketile_opt);

					//System.out.println(" value of k : " + k);
					int temp = valueCounter;

					if(k>=1){
						active_laketile.changeRotation(Rotation.D90);
					}
					
					checkdistributeLanternCard(active_laketile, playarea.getSupply(),gameObject);
					valueCounter = checkgetBonusPlaceLakeTile(active_laketile, adjacent_colors,gameObject);
					
					//System.out.println("temp value : " + valueCounter);
					//System.out.println("value counter value : " + valueCounter);
					if(valueCounter > temp)
					{
						solution.clear();
						solution.add(i);
						solution.add(j);
						solution.add(k);
						solution.add(valueCounter);
					}
					//System.out.println("your lantern color :"+ lanterncolor);
					if(solution.size()==0){
						solution.add(0);
						solution.add(0);
						solution.add(0);
						solution.add(0);
					}
				}

			}
		}
		
		//System.out.println("Best Solution is : I :" + solution.get(0)+" J : " +solution.get(1)+" K : "+solution.get(2)+" with value : " +solution.get(3));
		return solution;
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
		int valueCounter=0;
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
			//System.out.println("extra favor token : "+ checkFavorToken);
			valueCounter++;
		}
		if(lanterncard != null){
			//System.out.println("extra lantern cards : " + lanterncard);
			valueCounter++;
		}
		//System.out.println("value counter in bonus direction : " + valueCounter);
		return valueCounter;
	}
}
