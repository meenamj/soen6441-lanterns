package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * player is the person who play the game
 * 
 * @author Idris
 * @version 1.3
 */
public class Player implements Serializable {
	/**
	 * player index
	 */
	private int index;
	/**
	 * name of a player
	 */
	private String name;
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
	 * the current player
	 */
	
	/**
	 * The list of four of kind card a player has hand
	 */
	ArrayList<Color> fourOfaKindList = new ArrayList<Color>();
	
	/**
	 * The list of three pair card a player has on hand
	 */
	ArrayList<Color> threePairList = new ArrayList<Color>();

	Color c;
	
	private boolean isCurrentPlayer;

	public static Scanner scan;

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
	 * Get the current status of a player. (active player/ non-active player)
	 * 
	 * @return true, if this player is active
	 */
	public boolean isCurrentPlayer() {
		return isCurrentPlayer;
	}

	/**
	 * Set the current status of a player. (active player/ non-active player)
	 * 
	 * @param isCurrentPlayer
	 *            if this player is active
	 */
	public void setCurrentPlayer(boolean isCurrentPlayer) {
		this.isCurrentPlayer = isCurrentPlayer;
	}

	/**
	 * Constructor of player used at the beginning of the game
	 * 
	 * @param name
	 *            the name of game players
	 */
	public Player(String name) {
		this.name = name;
		lanternCards = new ArrayList<LanternCard>();
		lakeTiles = new ArrayList<LakeTile>();
		dedicationTokens = new ArrayList<DedicationToken>();
		numberOfFavorTokens = 0;
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
	 * @param dedication_token_stack to draw a three pair token on play area
	 * @param generic_token_stack to draw a generic token when three pair token stack are empty.
	 * @param supply to put a lantern card to the supply stacks after make dedication.
	 */
	public void makeThreePair(Stack<ThreePairToken> dedication_token_stack,Stack<GenericToken> generic_token_stack, Supply supply){
		if(isThreePair())
		{
			
			displayThreePair(dedication_token_stack, generic_token_stack, supply);
			
		}
		else
		{
			System.out.println("Player can not perform Three Pair dedication");
			System.out.println("");
		}
	}
		
	/**
	 * to make a dedication of the type "Seven Unique"
	 * @param dedication_token_stack to draw a seven unique token on play area.
	 * @param generic_token_stack to draw a generic token when seven unique token stack are empty.
	 * @param supply to put a lantern card to the supply stacks after make dedication.
	 */
	public void makeSevenUnique(Stack<SevenUniqueToken> dedication_token_stack,Stack<GenericToken> generic_token_stack, Supply supply){
		if(isSevenUnique())
		{ 
			//add lantern card back to supply
			for(Color c: Color.values())
			{
					if(numOfCardColor(c) >= 1)
					{	
						LanternCard l = removeSingleCard(c);
						supply.getLanternStack().get(l.getColor()).push(l);
						
					}	
			}
			//add dedication token
			if(!dedication_token_stack.empty()){
				dedicationTokens.add(dedication_token_stack.pop());
			}else if(!generic_token_stack.empty()){
				dedicationTokens.add(generic_token_stack.pop());
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
	 * @param dedication_token_stack to draw a four of a kind token on play area.
	 * @param generic_token_stack to draw a generic token when seven unique token stack are empty.
	 * @param supply to put a lantern card to the supply stacks after make dedication.
	 */
	public void makeFourOfAKind(Stack<FourOfAKindToken> dedication_token_stack,Stack<GenericToken> generic_token_stack, Supply supply)
	{
			if(isFourOfAKind())
			{
				displayFourOfAKindChoice(dedication_token_stack,generic_token_stack,supply);
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
	 * @param dedication_token_stack to draw a four of a kind token on play area.
	 * @param generic_token_stack to draw a generic token when seven unique token stack are empty.
	 * @param supply to put a lantern card to the supply stacks after make dedication.
	 */
	 
	public void displayFourOfAKindChoice(Stack<FourOfAKindToken> dedication_token_stack, Stack<GenericToken> generic_token_stack, Supply supply)
	{ 
		for(Color c : fourOfaKindList)
		{
			ArrayList<LanternCard> color_list = removeFourOfAKindCard(c); //remove after player makes a choice
			for(LanternCard lantern : color_list){
				//add lantern card back to supply
				supply.getLanternStack().get(lantern.getColor()).push(lantern);
			}
			//drawFourOfAKindStackOnPlayArea();
		}
		//add dedication token
		if(!dedication_token_stack.empty()){
			dedicationTokens.add(dedication_token_stack.pop());
		}else if(!generic_token_stack.empty()){
			dedicationTokens.add(generic_token_stack.pop());
		}
		fourOfaKindList = new ArrayList<Color>();
		System.out.println("Your four of a kind dedication has been made");
	}
	
	/**
	 * Displays three pair cards a player has
	 * @param dedication_token_stack to draw a four of a kind token on play area.
	 * @param generic_token_stack to draw a generic token when seven unique token stack are empty.
	 * @param supply to put a lantern card to the supply stacks after make dedication.
	 */
	public void displayThreePair(Stack<ThreePairToken> dedication_token_stack, Stack<GenericToken> generic_token_stack, Supply supply)
	{ 
		for(Color c : threePairList)
		{
			
			ArrayList<LanternCard> color_list = removeThreePairCard(c); //remove only when player makes a choice
			for(LanternCard lantern : color_list){
				//add lantern card back to supply
				supply.getLanternStack().get(lantern.getColor()).push(lantern);
			}
		}
		//drawThreePairStackOnPlayArea();
		//add dedication token
		if(!dedication_token_stack.empty()){
			dedicationTokens.add(dedication_token_stack.pop());
		}else if(!generic_token_stack.empty()){
			dedicationTokens.add(generic_token_stack.pop());
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
		for(LanternCard lantern :this.lanternCards)
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
			for(LanternCard lantern :this.lanternCards)
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

}
