package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import project.strategy.Strategy;
import project.strategy.Strategy.Name;


/**
 * player is the person who play the game
 * 
 * @author Idris
 * @version 1.3
 */
public class Player implements Serializable {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -8362237489470234660L;

	/**
	 * player index
	 */
	private int index;
	
	/**
	 * name of a player
	 */
	private String name;
	
	/**
	 * strategy
	 */
	private Strategy strategy;
	/**
	 * the amount of favor tokens which player has now
	 */
	private int numberOfFavorTokens;
	
	/**
	 * the lantern cards which player has now
	 */
	private ArrayList<LanternCard> lanternCards;
	
	/**
	 * the lake tiles which player has now
	 */
	private ArrayList<LakeTile> lakeTiles;
	
	/**
	 * the dedication token which player has now
	 */
	private ArrayList<DedicationToken> dedicationTokens;
	
	/**
	 * The list of four of kind card a player has hand
	 */
	private ArrayList<Color> fourOfaKindList = new ArrayList<Color>();
	
	/**
	 * The list of three pair card a player has on hand
	 */
	private ArrayList<Color> threePairList = new ArrayList<Color>();

	/**
	 * Get name of player
	 * 
	 * @return name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of player
	 * 
	 * @param name of player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get name of player
	 * 
	 * @return name of player
	 */

	public int getIndex() {
		return index;
	}

	/**
	 * Set index of player
	 * @param index of player
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	
	public Strategy getStrategy(){
		return strategy;
	}
	
	/**
	 * Get number of favor token on player hand
	 * 
	 * @return number of favor token
	 */
	public int getNumberOfFavorTokens() {
		return numberOfFavorTokens;
	}

	/**
	 * Set number of favor token on player hand
	 * 
	 * @param numberOfFavorTokens
	 *            number of favor token
	 */
	public void setNumberOfFavorTokens(int numberOfFavorTokens) {
		this.numberOfFavorTokens = numberOfFavorTokens;
	}

	/**
	 * Get lantern cards on player hand
	 * 
	 * @return list of lantern cards
	 */
	public ArrayList<LanternCard> getLanternCards() {
		return lanternCards;
	}

	/**
	 * Set lantern cards on player hand
	 * 
	 * @param lanternCards
	 *            list of lantern cards
	 */
	public void setLanternCards(ArrayList<LanternCard> lanternCards) {
		this.lanternCards = lanternCards;
	}

	/**
	 * Get dedication tokens which player has
	 * 
	 * @return list of dedication tokens
	 */
	public ArrayList<DedicationToken> getDedicationTokens() {
		return dedicationTokens;
	}

	/**
	 * Set dedication tokens which player has
	 * 
	 * @param dedicationTokens
	 *            list of dedication tokens
	 */
	public void setDedicationTokens(ArrayList<DedicationToken> dedicationTokens) {
		this.dedicationTokens = dedicationTokens;
	}

	/**
	 * Get lake tiles on player hand
	 * 
	 * @return list of lake tiles
	 */
	public ArrayList<LakeTile> getLakeTiles() {
		return lakeTiles;
	}

	/**
	 * Set lake tiles on player hand
	 * 
	 * @param lakeTiles
	 *            list of lake tiles
	 */
	public void setLakeTiles(ArrayList<LakeTile> lakeTiles) {
		this.lakeTiles = lakeTiles;
	}

	/**
	 * Constructor of player used at the beginning of the game
	 * 
	 * @param name
	 *            the name of game players
	 */
	public Player(String name, Strategy strategy) {
		this.name = name;
		lanternCards = new ArrayList<LanternCard>();
		lakeTiles = new ArrayList<LakeTile>();
		dedicationTokens = new ArrayList<DedicationToken>();
		numberOfFavorTokens = 0;
		this.strategy = strategy;
	}

	/**
	 * Constructor of player used when the game loading
	 * 
	 * @param name
	 *            player name
	 * @param lanternCards
	 *            list of lantern cards which player has
	 * @param lakeTiles
	 *            list of lake tiles on player hand
	 * @param dedicationTokens
	 *            list of dedication tokens which player has
	 * @param numberOfFavorTokens
	 *            the number of favor token which player has
	 */
	public Player(String name, ArrayList<LanternCard> lanternCards,
			ArrayList<LakeTile> lakeTiles,
			ArrayList<DedicationToken> dedicationTokens, int numberOfFavorTokens) {
		this.name = name;
		this.lanternCards = lanternCards;
		this.lakeTiles = lakeTiles;
		this.dedicationTokens = dedicationTokens;
		this.numberOfFavorTokens = numberOfFavorTokens;
	}
	
	/**
	 * to make a dedication of the type "Three Pair"
	 * @param play_area to draw a three pair token on play area
	 */
	public void makeThreePair(PlayArea play_area){
		if(isThreePair())
		{
			displayThreePair(play_area);
		}
		else
		{
			System.out.println("Player can not perform Three Pair dedication");
			System.out.println("");
		}
	}
		
	/**
	 * to make a dedication of the type "Seven Unique"
	 * @param play_area to draw a seven unique token on play area.
	 */
	public void makeSevenUnique(PlayArea play_area){
		if(isSevenUnique())
		{ 
			Stack<SevenUniqueToken> seven_unique_stack = play_area.getSevenUniqueTokens();
			Stack<GenericToken> generic_stack = play_area.getGenericTokens();
			Supply supply = play_area.getSupply();
			//add lantern card back to supply
			for(Color c: Color.values())
			{
					if(numOfCardColor(c) >= 1)
					{	
						LanternCard l = removeSingleCard(c);
						supply.get(l.getColor()).push(l);
						
					}	
			}
			//add dedication token
			if(!seven_unique_stack.empty()){
				dedicationTokens.add(seven_unique_stack.pop());
			}else if(!generic_stack.empty()){
				dedicationTokens.add(generic_stack.pop());
			}
			//remove 7 unique color from lantern card
			System.out.println("Your seven unique dedication has been made");
		}
		else
		{
			System.out.println("Player can not perform Seven Unique dedication");
			System.out.println("");
		}
		////check and get Seven Unique lantern card for user and give dedicated token
	}

	/**
	 * to make a dedication of the type "Four Of A Kind"
	 * @param play_area to draw a four of a kind token on play area.
	 */
	public void makeFourOfAKind(PlayArea play_area)
	{
			if(isFourOfAKind())
			{
				displayFourOfAKindChoice(play_area);
			}
			else
			{
				System.out.println("Player can not perform Four Of a kind dedication");
				System.out.println("");
			}
			//check and get four of kind lantern card for player and give dedicated token
	}
	
	/**
	 * Check if a player has a four of kind lantern card
	 * @return true if player has a four of a kind card and false otherwise
	 */
	public boolean isFourOfAKind()
	{
		boolean canMakeDedication=false;
		int count=1;
		for(Color c: Color.values())
		{
				if(numOfCardColor(c) >= 4)
				{	if(count <=1)
					{
						fourOfaKindList.add(c);
						canMakeDedication = true;
						count++;
					}
				}
		}
		return canMakeDedication;
	}
	
	/**
	 * Display the four of kind  options a player has
	 * @param play_area to draw a four of a kind token on play area.
	 */
	 
	public void displayFourOfAKindChoice(PlayArea play_area)
	{ 
		Stack<FourOfAKindToken> four_kind_stack = play_area.getFourOfAKindTokens();
		Stack<GenericToken> generic_stack = play_area.getGenericTokens();
		Supply supply = play_area.getSupply();
		for(Color c : fourOfaKindList)
		{
			ArrayList<LanternCard> color_list = removeFourOfAKindCard(c); //remove after player makes a choice
			for(LanternCard lantern : color_list){
				//add lantern card back to supply
				supply.get(lantern.getColor()).push(lantern);
			}
			//drawFourOfAKindStackOnPlayArea();
		}
		//add dedication token
		if(!four_kind_stack.empty()){
			dedicationTokens.add(four_kind_stack.pop());
		}else if(!generic_stack.empty()){
			dedicationTokens.add(generic_stack.pop());
		}
		fourOfaKindList = new ArrayList<Color>();
		System.out.println("Your four of a kind dedication has been made");
	}
	
	/**
	 * Displays three pair cards a player has
	 * @param play_area to draw a four of a kind token on play area.
	 */
	public void displayThreePair(PlayArea play_area)
	{ 
		Stack<ThreePairToken> three_pair_stack = play_area.getThreePairTokens();
		Stack<GenericToken> generic_stack = play_area.getGenericTokens();
		Supply supply = play_area.getSupply();
		
		for(Color c : threePairList)
		{
			
			ArrayList<LanternCard> color_list = removeThreePairCard(c); //remove only when player makes a choice
			for(LanternCard lantern : color_list){
				//add lantern card back to supply
				supply.get(lantern.getColor()).push(lantern);
			}
		}
		//drawThreePairStackOnPlayArea();
		//add dedication token
		if(!three_pair_stack.empty()){
			dedicationTokens.add(three_pair_stack.pop());
		}else if(!generic_stack.empty()){
			dedicationTokens.add(generic_stack.pop());
		}
		threePairList = new ArrayList<Color>();
		System.out.println("Your three pair dedication has been made");
	}
	
	/**
	 * Check the number of card color a player has
	 * @param c color of a card
	 * @return the number of card for each color
	 */
	public int numOfCardColor(Color c)
	{
		int cardNo = 0;
		for(LanternCard lantern : lanternCards)
		{
			if(lantern.getColor() == c)
			{
				cardNo++;	
			}
			
		}
		return cardNo;
	}
	
	/**
	 * This method removes lantern card of four of a kind from player's hand
	 * @param c color instance
	 * @return list of lantern cards
	 */
	public ArrayList<LanternCard> removeFourOfAKindCard(Color c)
	{
		int count = 1;
		ArrayList<LanternCard> lantern_list = new ArrayList<LanternCard>();
		while(count<=4)
		{
			for(LanternCard lantern : lanternCards)
			{
				
				if(lantern.getColor() == c)
				{
					lanternCards.remove(lantern);
					break;
				}
				
			}
			count++;
		}
		return lantern_list;
	}
	
	/**
	 * This method removes lantern card of three pair from player's hand and return it to supply
	 * @param c color of lantern card
	 * @return lantern card
	 */
	public ArrayList<LanternCard> removeThreePairCard(Color c)
	{
		int count = 1;
		ArrayList<LanternCard> lantern_list = new ArrayList<LanternCard>();
		while(count<=2)
		{
			for(LanternCard lantern :this.lanternCards)
			{
				if(lantern.getColor() == c)
				{
					lanternCards.remove(lantern);
					lantern_list.add(lantern);
					break;
				}
			}
			count++;
		}
		return lantern_list;
	}
	
	/**
	 * This method removes a single lantern card from payer's hand and return it to supply
	 * @param c color of lantern card
	 * @return lantern card
	 */
	public LanternCard removeSingleCard(Color c)
	{
		LanternCard l = null;
			for(LanternCard lantern :this.lanternCards)
			{
				if(lantern.getColor() == c)
				{
					lanternCards.remove(lantern);
					l=lantern;
					break;
				}
			}
			return l;
	}

	/**
	 * Check if a player has a three pair
	 * @return true if player has a three pair card and false otherwise
	 */
	public boolean isThreePair()
	{
		boolean canMakeDedication=false;
		int count = 0;
		for(Color c: Color.values())
		{
				if(numOfCardColor(c) >= 2)
				{
					if(count<=2)
					{	
						threePairList.add(c);
					}
					count++;
				}	
		}
		if(count >= 3)
		{
			canMakeDedication = true;
		}
		return canMakeDedication;
	}
	/**
	 * Check if a player has a seven Unique card
	 * @return true if player has seven unique card and false otherwise
	 */
	public boolean isSevenUnique()
	{
		boolean canMakeDedication=false;
		int count = 0;
		for(Color c: Color.values())
		{
				if(numOfCardColor(c) >= 1)
				{	
					count++;
				}	
		}
		if(count >= 7)
		{
			canMakeDedication = true;
		}
		return canMakeDedication;
	}

	/**
	 * This method get the number of lake tiles
	 * @return number of lake tiles
	 */
	public int getNumberOfLakeTile() 
	{
		return this.lakeTiles.size();
	}

	/**
	 * This method counts the honor value
	 * @return return value
	 */
	public int countHonorValue(){
		int count=0;
		for(DedicationToken dedication :dedicationTokens)
			count+=dedication.getHonor();
		return count;
	}
	
	/**
	 * get player information such as name, active or inactive, lantern card Favor token and dedication token
	 * @param current_player the active player
	 * @return String information of player 
	 * @throws Exception if the color does not exist
	 */
	public String getInformationText(Player current_player) throws Exception
	{
		String text = "";
		
		text += "Player name : " + getName();
		if (this.equals(current_player))
		{
			text += ": (active)";
			// currentTurn = i+1;
		}
		
		text += "\n\n";
		text += "Lantern Cards ";
		
		for(Color c : Color.values())
		{
			text += Color.getColorText(c, " ");
			text += numOfCardColor(c);
			text += " ";
		}
		
		text += "\n\nNumber of Favor Tokens::";
		text += getNumberOfFavorTokens();
		text += "\n\nValue Dedication Token : ";
		text += countHonorValue();
		text += "\n\nLake Tiles :";
		for (int j = 0; j < getLakeTiles().size(); j++) 
		{
			LakeTile laketile =getLakeTiles().get(j);
			text += "\n";
			text += String.format("%5s", "No." + (j + 1));
			text += "-";
			text += String.format("%2s", getLakeTiles().get(j).getIndex());
			text += " ";
			ArrayList<Color> laketile_colors = new ArrayList<Color>(laketile.getColorOfFourSides());
			text += Color.getColorText(laketile_colors.get(0),Symbol.UP);
			text += " ";// up
			text += Color.getColorText(laketile_colors.get(1),Symbol.RIGHT);
			text += " ";// right
			text += Color.getColorText(laketile_colors.get(2),Symbol.DOWN);
			text += " ";// down
			text += Color.getColorText(laketile_colors.get(3),Symbol.LEFT);
			text += " ";// left

			if (getLakeTiles().get(j).isPlatform()) 
			{
				text += Symbol.PLATFORM;
			}
		}
		text += "\n\n";
		return text;
	}
	
	
	
	
	/**
	 * Selection and removal of a lantern card form supply stack
	 * 
	 * @param player
	 *            active player object1
	 */
	public void exchangeSupplyLanternCard(Game game)
	{
		Supply supply = game.getPlayArea().getSupply();
		System.out.println("\nLantern Card Supply :");
		int i = 0;
		ArrayList<Color> buffer = new ArrayList<Color>();
		for (Color c : Color.values()) 
		{
			try {
				if (supply.get(c).size() > 0) {
					System.out.println("Index :"
							+ i
							+ " :"
							+ Color.getColorText(c, Symbol.BULLET)
							+ " : "
							+ supply.get(c).size());
					buffer.add(c);
					i++;
				}

			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		int in = 0;
		boolean validation = false;
		do 
		{
			in = getStrategy().inputOption(buffer.size(), Name.CHOOSE_LANTERN_SUPPLY, game);
			for (i = 0; i < buffer.size(); i++) 
			{
				if (in == i) 
				{
					Stack<LanternCard> stack = supply.get(buffer.get(i));
					getLanternCards().add(stack.pop());
					validation = true;
				}
			}
		} while (!validation);

	}
	
	/**
	 * Exchange a lantern card option
	 * @param playArea play area
	 * @throws Exception exception
	 */
	public void exchangeLanCard(Game game) throws Exception 
	{
		if ((getNumberOfFavorTokens() < 2)
				|| (getLanternCards().size() == 0))
		{
			System.out.println("Sorry..you can not perform this action.");
			System.out.println("you do not have enough favor tokens or you " +
					"don't have a lantern card to exchange.");
		} 
		
		else 
		{
			// remove lantern card from player's hand and add that card
			// to supply stack
			exchangePlayerLanternCard(game);
			// remove lantern card from supply stack and add it to
			// player's hand
			exchangeSupplyLanternCard(game);
		}
	}
	

	/**
	 * Selection and removal of a lantern card form player's stack
	 * 
	 * @param player
	 *            active player object
	 * @throws Exception if the player does not exist
	 * 
	 */
	public void exchangePlayerLanternCard(Game game) throws Exception 
	{
		System.out.println("Choose a lantern card you want to exchange");
		ArrayList<LanternCard> lanternCards = getLanternCards();

		System.out.println();

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
				System.out.println("Index:"
						+ counter
						+ " : "
						+ Color.getColorText(lanternCards.get(i).getColor(),
								" ") + " ");
				counter++;
			}
		}

		int in = 0;
		boolean existCard = true;
		do {
			in = getStrategy().inputOption(arrays.size(), Name.CHOOSE_LANTERN_HAND, game);
			HashMap<Color, Stack<LanternCard>> supply = game.getPlayArea().getSupply();
			for (int i = 0; i < arrays.size(); i++) 
			{
				if (in == i) 
				{
					System.out.print("");
					Stack<LanternCard> lantern_stack = supply.get(arrays.get(i)
							.getColor());
					lantern_stack.add(arrays.get(i));
					lanternCards.remove(arrays.get(i));
					setNumberOfFavorTokens(getNumberOfFavorTokens()-2);
					existCard = false;
				}
			}
		} while (existCard);

	}

	/**
	 * This method check if a player can place a lake tile and display position to place a lake tile
	 * @param current_player
	 * @throws Exception
	 */
	public ArrayList<HashMap<Rotation, Vector<Object>>> checkPlaceLakeTile(PlayArea play_area, LakeTile active_laketile) throws Exception 
	{
		
		// get the laketile which player wants to put then remove the
		// tile on their hand
		ArrayList<Position> list = play_area.getPositionAvailableLakeTileOnBoard();
		ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = new ArrayList<HashMap<Rotation, Vector<Object>>>();
		optionOnBoard(list, adjacent_color_list, play_area);
		return adjacent_color_list;
		
		
	}
	
	public ArrayList<HashMap<Rotation, Vector<Object>>> placeLakeTileMenu(PlayArea play_area, LakeTile active_laketile) throws Exception 
	{
		
		// get the laketile which player wants to put then remove the
		// tile on their hand
		ArrayList<Position> list = play_area.getPositionAvailableLakeTileOnBoard();
		ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = new ArrayList<HashMap<Rotation, Vector<Object>>>();
		optionOnBoardText(list, adjacent_color_list, play_area);
		return adjacent_color_list;
		
		
	}
	
	
	public void setRotationOnActiveLakeTile(LakeTile active_laketile,int rotation_input) throws Exception{
		//method2
		
		int rotation = rotation_input * 90;
		active_laketile.setRotation(Rotation.getRotation(rotation));
		// change the side of lake tile to put on board
		// / new
		active_laketile.changeRotation(active_laketile.getRotation());
	}
	
	public HashMap<Rotation, Vector<Object>> getPossibleRotation(ArrayList<Position> list,
			ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list,
			PlayArea play_area, LakeTile active_laketile,int pos_laketile_opt){
	////method1
			placeALakeTile(list.get(pos_laketile_opt), active_laketile, play_area);
			HashMap<Rotation, Vector<Object>> adjacent_colors = adjacent_color_list
					.get(pos_laketile_opt);
			return adjacent_colors;
	}
	
	/**
	 * text of all lake tiles of the current player
	 * @return information of all current lake tiles to put on the board
	 * @throws Exception exception
	 */
	public String getCurrentPlayerLakeTileText() throws Exception 
	{
		String text = "";
		String line1 = "";
		String line2 = "";
		String line3 = "";
		for (LakeTile lake_tile : getLakeTiles())
		{
			int index = getLakeTiles().indexOf(lake_tile);
			Queue<Color> color_queue = lake_tile.getColorOfFourSides();
			ArrayList<Color> color_list = new ArrayList<Color>(color_queue);
			
			line1 += String.format("%8s","");
			line1 += Color.getColorText(color_list.get(0), " ");
			line1 += String.format("%3s","");
			
			line2 += " "+index + ":";
			line2 += String.format("%2s ", lake_tile.getIndex());
			line2 += Color.getColorText(color_list.get(3), " ");
			line2 += " ";
			if (lake_tile.isPlatform()) 
			{
				line2 += "O";
			}else{
				line2 += "X";
			}
			line2 += " "+Color.getColorText(color_list.get(1), " ")+" ";
			
			line3 += String.format("%8s","");
			line3 += Color.getColorText(color_list.get(2), " ");
			line3 += String.format("%3s","");
			
		}
		text += line1+"\n";
		text += line2+"\n";
		text += line3+"\n";
		return text;
	}
	
	/**
	 * text of all the possible ways to rotate a lake tile
	 * @param l LakeTile
	 * @return String information of possible ways to rotate
	 * @throws Exception exception
	 */
	public String getPossibleRotationText(LakeTile l) throws Exception 
	{
		String text = "";
		int sideOfLakeTile = 4;
		text += "How do you want to rotate the lake tile?\n";
		String line1 = new String();
		String line2 = new String();
		String line3 = new String();
		
		for (int i = 0; i < sideOfLakeTile; i++)
		{
			ArrayList<Color> four_side_colors = new ArrayList<Color>(
					l.getColorOfFourSides());
			line1 += "      " + Color.getColorText(four_side_colors.get(0),Symbol.UP)+"   ";
			line2 += " "+i+": "+Color.getColorText(four_side_colors.get(3),Symbol.LEFT);
			if(l.isPlatform()){
				line2 += " O ";
			}else{
				line2 += " X ";
			}
			line2 += Color.getColorText(four_side_colors.get(1),Symbol.RIGHT)+" ";
			line3 += "      "+Color.getColorText(four_side_colors.get(2),Symbol.DOWN)+"   ";
			l.getColorOfFourSides().add(l.getColorOfFourSides().remove());
		}
		text+=line1+"\n";
		text+=line2+"\n";
		text+=line3+"\n";
		return text;
	}
	
	/**
	 * Execute this method, if the third option is selected by player.
	 * @param pos Position instance
	 * @param lakeTile LakeTile instance
	 */
	public void placeALakeTile(Position pos, LakeTile lakeTile, PlayArea playArea) 
	{
		int x = pos.getX();
		int y = pos.getY();
		playArea.getLakeTilesOnBoard()[x][y] = lakeTile;
	}
	
	/**
	 * This method display the options a player can place the lake tile
	 * @param list list of possible options
	 * @param adjacent_color_list Adjacent color
	 * @throws Exception
	 */

	public void optionOnBoard(ArrayList<Position> list,
			ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list,
			PlayArea play_area)
			throws Exception
	{
		
		for (int i = 0; i < list.size(); i++) 
		{
			Position index = list.get(i);

			// show information beside the possible lake tile
			HashMap<Rotation, Vector<Object>> color_platform = play_area.getAdjacentColor(index);
			adjacent_color_list.add(color_platform);

		}
		
	}
	public void optionOnBoardText(ArrayList<Position> list,
			ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list,
			PlayArea play_area)
			throws Exception
	{
		System.out.println("Available index :::");

			for (int i = 0; i < list.size(); i++) 
			{
				Position index = list.get(i);
				System.out.print("option " + i + " ::" + index.getText());
	
				// show information beside the possible lake tile
				HashMap<Rotation, Vector<Object>> color_platform = play_area.getAdjacentColor(index);
				System.out.println(play_area.getAdjacentColorText(color_platform));
				adjacent_color_list.add(color_platform);
				System.out.println();
			}
	}
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
}
