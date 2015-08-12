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
import java.util.Stack;
import java.util.Map.Entry;
import java.util.Random;

import project.Color;
import project.Game;
import project.LakeTile;
import project.LanternCard;
import project.PlayArea;
import project.Player;
import project.Position;
import project.Rotation;
import project.Supply;
/**
 * This class represent the concrete class for the unfriendly player strategy type,
 * Unfriendly player selects an option to place a lake tile, which does not give any 
 * Opponent player a lantern card to make a dedication. 
 * @author Nirav
 * @version 1.0
 */
public class Unfriendliness extends UnfriendlyStrategy{
    
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 7175461946886588653L;
	private static final int fourOfaKind = 0;
	private static final int threePair = 1;
	private static final int sevenUnique = 2;
	int lakeTiletochoose = 0;
    
    /**
     * to check if the player can make a dedication or not for the
     * current stack of lantern cards
     * @param players Queue of all the players
     * @return isDedicatoinPossible true if make a dedication is possible, false otherwise
     */
    protected boolean canMakeDedication(Queue<Player> players){
        boolean isDedicatoinPossible;
        ArrayList<Player> playerList = new ArrayList<Player>(players);
        Player player = playerList.get(0);
        if(player.isFourOfAKind() || player.isThreePair() || player.isSevenUnique()){
            //System.out.println("Dedication is possible to make");
        	isDedicatoinPossible = true;
        }
        else{
           // System.out.println("Dedication is not possible");
        	isDedicatoinPossible=false;
        }
        return isDedicatoinPossible;
    }
    
    /**
     * To check which dedication is possible to make, and return the choice 
     * based on the possibilities
     * @param players queue of all the players
     * @return choice number based on the possible dedication 
     */
    protected int selectDedication(Queue<Player> players){
        int choice=0;
        ArrayList<Player> playerList = new ArrayList<Player>(players);
        Player player = playerList.get(0);
        if(player.isSevenUnique()){
            choice = sevenUnique;
        }
        else if(player.isFourOfAKind()){
            choice = fourOfaKind;
        }
        else if(player.isThreePair()){
            choice = threePair;
        }
        return choice;
    }
    
    /**
     * To check if lantern card exchange is possible for a player
     * @param players queue of all the players
     * @param game instance of the game class
     * @return true if the exchange is possible, false otherwise 
     */
    protected boolean canExchangeLanternCard(Queue<Player> players, Game game){
        boolean isExchangePossible;
        int[] exchnageOptions = new int[2];
        ArrayList<Player> playerList = new ArrayList<Player>(players);
        Player player = playerList.get(0);
        
        exchnageOptions = performExchange(player,game);

        if(player.getNumberOfFavorTokens() > 2 && (exchnageOptions[0] < 9 && exchnageOptions[1] < 9)){
            //System.out.println("Exchange a lantern card is possible");
        	isExchangePossible = true;
        }
        else{
           // System.out.println("Can not Exchange lantern card");
        	isExchangePossible=false;
        }
        return isExchangePossible;
        
    }
    
    /**
     * to perform an exchange of a lantern card, to get all the lantern cards on a player hand
     * and put it in a HashMap
     * @param player current player
     * @param game clone of the game instance
     * @return pair of solution to be selected for the exchange of a lantern card
     */
    protected int[] performExchange(Player player,Game game){
        boolean DESC = false;
        int[] exchnageOptions = new int[2];
        
        int ORANGE = player.numOfCardColor(Color.ORANGE);
        int GREEN = player.numOfCardColor(Color.GREEN);
        int PURPLE = player.numOfCardColor(Color.PURPLE);
        int WHITE = player.numOfCardColor(Color.WHITE);
        int BLUE = player.numOfCardColor(Color.BLUE);
        int RED = player.numOfCardColor(Color.RED);
        int BLACK = player.numOfCardColor(Color.BLACK);
        
      //pair of lantern cards and number of each available on the player hand
         HashMap<Color,Integer> numOfLanternColor = new HashMap<Color,Integer>();
          // Put elements to the map
         numOfLanternColor.put(Color.ORANGE, ORANGE);
         numOfLanternColor.put(Color.GREEN, GREEN);
         numOfLanternColor.put(Color.PURPLE, PURPLE);
         numOfLanternColor.put(Color.WHITE, WHITE);
         numOfLanternColor.put(Color.BLUE, BLUE);
         numOfLanternColor.put(Color.RED, RED);
         numOfLanternColor.put(Color.BLACK, BLACK);
         
            
         Map<Color,Integer> sortedMap = sortByComparator(numOfLanternColor, DESC);
         exchnageOptions = ExchangeLanternCard(sortedMap,player,game);

         return exchnageOptions;
    }
    
    /**
     * Decide which lantern card to exchange 
     * @param sortedMap sorted HashMap of color and value pair
     * @param player current player
     * @param game clone of the game instance
     * @return pair of solution to be selected for the exchange of a lantern card
     */
    protected int[] ExchangeLanternCard( Map<Color, Integer> sortedMap, Player player, Game game){
        int[] numOfLanterns = new int[7];
        Color[] c = new Color[7];
        int[] exchnageOptions = new int[2];
        int counter = 0;
         for (Entry<Color, Integer> entry : sortedMap.entrySet())
         {
        	numOfLanterns[counter] = entry.getValue();
            c[counter] = entry.getKey();
            counter++;
         }

        if(player.getLanternCards().size() >= 7)
        {
        	exchnageOptions = findSevenOfKindPattern(player, game, numOfLanterns, c, exchnageOptions);
         }
         else if(player.getLanternCards().size() <= 4){
        	 exchnageOptions = findFourOfKindPattern(player, game, numOfLanterns, c, exchnageOptions);
         }
         else{
        	 exchnageOptions = findThreePairPattern(player, game, numOfLanterns, c, exchnageOptions);
         }
        return exchnageOptions;
    }
    
    /**
     * Decide which lantern card to exchange to make a three Pair dedication
     * @param player current player
     * @param game Clone of the game instance
     * @param colors lantern cards color
     * @param c color of lantern card
     * @param ExchnageOptions Lantern cards to be exchanges options
     */
	private int[] findThreePairPattern(Player player, Game game, int[] colors,Color[] c, int[] exchnageOptions) {
		 if(colors[0] == 2 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && player.getNumberOfFavorTokens() >= 2){
			 if(checkSupply(c[2],game)){
			 exchnageOptions[0] = ChoosePlayerLanternCard(c[3],game,player);
			 exchnageOptions[1] = ChooseSupplyLanternCard(c[3],c[2],game,player);
			 }
		 	 else{
		 		 exchnageOptions[0] = 9;
		     	 exchnageOptions[1] = 9;
		 	 }
		 }
		 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && player.getNumberOfFavorTokens() >= 4){
			 if(checkSupply(c[1],game)){
				 exchnageOptions[0] = ChoosePlayerLanternCard(c[4],game,player);
				 exchnageOptions[1] = ChooseSupplyLanternCard(c[4],c[1],game,player);
			 }
		 	 else{
		 		 exchnageOptions[0] = 9;
		     	 exchnageOptions[1] = 9;
		 	 }
		 }
		 else{
			 exchnageOptions[0] = 9;
			 exchnageOptions[1] = 9;
		 }
		 return exchnageOptions;
	}
    /**
     * Decide which lantern card to exchange to make a Four of a Kind dedication
     * @param player current player
     * @param game Clone of the game instance
     * @param colors lantern cards color
     * @param c color of lantern card
     * @param ExchnageOptions Lantern cards to be exchanges options
     */

	private int[] findFourOfKindPattern(Player player, Game game, int[] colors,
			Color[] c, int[] exchnageOptions) {
		if(colors[0] == 3 && colors[1] == 1 && player.getNumberOfFavorTokens() >= 2){
			 if(checkSupply(c[0],game)){
		    	 exchnageOptions[0] = ChoosePlayerLanternCard(c[1],game,player);
		    	 exchnageOptions[1] = ChooseSupplyLanternCard(c[1],c[0],game,player);
			 }
		 	 else{
		     	 exchnageOptions[0] = 9;
		         exchnageOptions[1] = 9;
		 	 }
		 }
		 else if(colors[0] == 2 && colors[1] == 2 && player.getNumberOfFavorTokens() >= 4){
			 if(checkSupply(c[0],game)){
		    	 exchnageOptions[0] = ChoosePlayerLanternCard(c[1],game,player);
		    	 exchnageOptions[1] = ChooseSupplyLanternCard(c[1],c[0],game,player);
			 }
		 	 else{
		 		 exchnageOptions[0] = 9;
		     	 exchnageOptions[1] = 9;
		 	 }
		 }
		 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && player.getNumberOfFavorTokens() >= 4){
			 if(checkSupply(c[0],game)){
		    	 exchnageOptions[0] = ChoosePlayerLanternCard(c[2],game,player);
		    	 exchnageOptions[1] = ChooseSupplyLanternCard(c[2],c[0],game,player);
			 }
		 	 else{
		 		 exchnageOptions[0] = 9;
		     	 exchnageOptions[1] = 9;
		 	 }
		 }
		 else{
			 exchnageOptions[0] = 9;
			 exchnageOptions[1] = 9;
		 }
		return exchnageOptions;
	}
    /**
     * Decide which lantern card to exchange to make a Seven Unique Dedication
     * @param player current player
     * @param game Clone of the game instance
     * @param colors lantern cards color
     * @param c color of lantern card
     * @param ExchnageOptions Lantern cards to be exchanges options
     */

	private int[] findSevenOfKindPattern(Player player, Game game, int[] colors,
			Color[] c, int[] exchnageOptions) {
		if(colors[0] == 2 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && player.getNumberOfFavorTokens() >= 4){
			if(checkSupply(c[5],game)){
				exchnageOptions[0] = ChoosePlayerLanternCard(c[0],game,player);
				exchnageOptions[1] = ChooseSupplyLanternCard(c[0],c[5],game,player);
			}
			else{
				exchnageOptions[0] = 9;
		    	exchnageOptions[1] = 9;
			}
		 }
		 else if(colors[0] == 2 && colors[1] == 1 && colors[2] == 1 && colors[3] == 1 && colors[4] == 1 && colors[5] == 1 && player.getNumberOfFavorTokens() >= 2){
			 if(checkSupply(c[6],game)){
		    	 exchnageOptions[0] = ChoosePlayerLanternCard(c[0],game,player);
		    	 exchnageOptions[1] = ChooseSupplyLanternCard(c[0],c[6],game,player);
			 }
		 	else{
		 		exchnageOptions[0] = 9;
		     	exchnageOptions[1] = 9;
		 	}
		 }
		 else if(colors[0] == 3 && colors[1] == 2 && colors[2] == 1 && colors[3] == 1 && player.getNumberOfFavorTokens() >= 2){
			 if(checkSupply(c[2],game)){
		    	 exchnageOptions[0] = ChoosePlayerLanternCard(c[0],game,player);
		    	 exchnageOptions[1] = ChooseSupplyLanternCard(c[0],c[2],game,player);
			 }
		 	 else{
		 		exchnageOptions[0] = 9;
		     	exchnageOptions[1] = 9;
		 	 }
		 }
		 else{
			 exchnageOptions[0] = 9;
			 exchnageOptions[1] = 9;
		 }
		return exchnageOptions;
	}
    
	/**
     * select a lantern card from player stack to exchange
     * @param playerCard color to exchange
     * @param game clone instance of game class
     * @param player current player
     * @return option number to input
     */
    protected int ChoosePlayerLanternCard(Color playerCard,Game game, Player player){
    	
    	int playerCardOptionNumber=0;
		ArrayList<LanternCard> lanternCards = player.getLanternCards();

		ArrayList<LanternCard> arrays = new ArrayList<LanternCard>();
		
		for (int i = 0, counter = 0; i < lanternCards.size(); i++) 
		{
			boolean existColor = false;
			for (LanternCard array : arrays) {
				if (array.getColor().equals(lanternCards.get(i).getColor())) 
				{
					existColor = true;
					break;
				}
			}
			if (!existColor) 
			{
				arrays.add(lanternCards.get(i));
				if(lanternCards.get(i).getColor() == playerCard){
					playerCardOptionNumber = counter;
				}
				counter++;
			}
		}
        return playerCardOptionNumber;
    }
    
    /**
     * select a lantern card to exchange from supply
     * @param playerCard a card color to put in the supply
     * @param supplyCard a card color to get from the supply
     * @param game clone instance of game class
     * @param player current_player
     * @return option number to input
     */
    protected int ChooseSupplyLanternCard(Color playerCard,Color supplyCard, Game game, Player player){
        Supply lanternSupply = game.getPlayArea().getSupply();
        HashMap<Color, Stack<LanternCard>> supply = lanternSupply;
		
        for (int i = 0; i < player.getLanternCards().size(); i++) 
	{
		if (player.getLanternCards().get(i).getColor() == playerCard) 
		{
	        Stack<LanternCard> lantern_stack = supply.get(playerCard);
			lantern_stack.add(player.getLanternCards().get(i));    
		}
	}
		
        ArrayList<Color> buffer = new ArrayList<Color>();
        int cardOptionNumber=0;
        int i = 0;
        for (Color color : Color.values()) 
        {
            try {
                if (supply.get(color).size() > 0) 
                {
                	
                    if(supplyCard == color)
                    {
                        cardOptionNumber = i;
                        buffer.add(color);
                    }
                    i++;
                }
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return cardOptionNumber;
    }
    
    /**
     * check lantern card supply to make sure the lantern card a player wants to exchange is
     * available in the supply stack otherwise return false
     * @param supplyCard color to search for in the supply stack
     * @param game clone instance of game class
     * @return flag true if the color is available , false otherwise
     */
    private boolean checkSupply(Color supplyCard, Game game){
    	boolean isCardAvailable = false;
    	Supply supply = game.getPlayArea().getSupply();
        for (int i=0; i<Color.values().length; i++) 
        {
            try {
            	if(supply.get(supplyCard).size() > 0){
            		isCardAvailable = true;
            	}
                else{
                	isCardAvailable = false;
                }

            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return isCardAvailable;
    }
    
    /**
     * check all possible solutions for each three steps of place a lake tile on the board and return
     * best solution to put a lake tile
     * @param game clone instance of game class 
     * @throws Exception this color does not exist exception
     */
    protected ArrayList<Integer> simulateGamePlay(Game game) throws Exception
    {
        ArrayList<Integer> ithSolution = new ArrayList<Integer>();
        ArrayList<Integer> jthSolution = new ArrayList<Integer>();
        ArrayList<Integer> kthSolution = new ArrayList<Integer>();
        ArrayList<Integer> solution = new ArrayList<Integer>(3);
        Queue<Player> realplayers = game.getPlayers();
        PlayArea realplayarea = game.getPlayArea();
        
        ArrayList<Player> realplayerList = new ArrayList<Player>(realplayers);
        Player realplayer = realplayerList.get(0);

        ArrayList<Position> realavailableList = realplayarea.getPositionAvailableLakeTileOnBoard();

        for(int i=0;i<realplayer.getLakeTiles().size();i++)
        {
            for(int j=0; j<realavailableList.size();j++)
            {
                for(int k=0;k<4;k++)
                {
                    Game gameObject = game.clone();
                    
                    Queue<Player> players = gameObject.getPlayers();
                    PlayArea playarea = gameObject.getPlayArea();
                    
                    ArrayList<Player> playerList = new ArrayList<Player>(players);
                    Player player = playerList.get(0);
                    LakeTile active_laketile = player.getLakeTiles().get(i);
                    
                    if(k>=1){
                        active_laketile.changeRotation(Rotation.D90);
                    }

                    if(checkdistributeLanternCard(active_laketile, playarea.getSupply(),gameObject)){
                    	ithSolution.add(i);
                        jthSolution.add(j);
                        kthSolution.add(k);
                    }
                }
            }
        }

        int index =0;
        if(ithSolution.size() >0){
        	index = randInt(1,ithSolution.size()-1);
        	
        	solution.add(ithSolution.get(index));
        	solution.add(jthSolution.get(index));
        	solution.add(kthSolution.get(index));
        	solution.add(index);
        }
        if(solution.size()==0){
            solution.add(0);
            solution.add(0);
            solution.add(0);
            solution.add(0);
        }

        //System.out.println("Best Solution is : I :" + solution.get(0)+" J : " +solution.get(1)+" K : "+solution.get(2)+" with value : " +solution.get(3));
        return solution;
    }
    
    /**
     * To pick random value from a given array
     * @param min minimum range
     * @param max Maximum range
     * @return random number
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum;
        if(max!=0){
        	randomNum = rand.nextInt((max - min) + 1) + min;
        }
        else{
        	randomNum=0;
        }

        return randomNum;
    }
    
    /**
     * This method distribute lantern card from supply to player
     * @param active_laketile active lake tile 
     * @param supply lantern card supply
     * @param game current game being played
     * @return boolean
     */
    public boolean checkdistributeLanternCard(LakeTile active_laketile, Supply supply, Game game) 
    {
        Queue<Player> players = game.getPlayers();
		int valueCounter =0;
		boolean isSolution = false;
		
		ArrayList<Player> players_list = new ArrayList<Player>(players);
		
		for (int i = 0; i < players.size(); i++) 
		{
			if(i!=0){
				Player getting_player = players_list.get(i);
				
				int index = getting_player.getIndex();
				
				ArrayList<Color> color_list = new ArrayList<Color>(active_laketile.getColorOfFourSides());
				
				if (index >= 0 && index < players.size()) 
				{
					Stack<LanternCard> lanternCard = supply.get(color_list.get(index));
					
					if (!lanternCard.empty())
					{
						LanternCard l = lanternCard.pop();
						getting_player.getLanternCards().add(l);
					}
				}
				if(getting_player.isFourOfAKind() || getting_player.isSevenUnique() || getting_player.isThreePair()){
					valueCounter = valueCounter +1;
				}
				else{
					
				}
			}
		}
		if(valueCounter == 0){
			isSolution = true;
		}
		return isSolution;
        
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
}
