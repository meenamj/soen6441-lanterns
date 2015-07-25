package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * player is the person who play the game
 * 
 * @author Idris
 * @version 1.3
 */
public class Player implements Serializable {
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
	
	ArrayList<Color> fourOfaKindList = new ArrayList<Color>();
	/**
	 * The list of four of kind card a player has hand
	 */
	ArrayList<Color> threePairList = new ArrayList<Color>();
	/**
	 * The list of three pair card a player has on hand
	 */
	
	boolean canMakeDedication = false;
	/**
	 * 
	 */
	Color c;
	private boolean isCurrentPlayer;

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
	 * @param name
	 *            of player
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Make a dedication by dedication type
	 * @param dedicationType The type of dedication a player is willing to make
	 */
	public void makeDedication(int dedicationType)
	{
		switch (dedicationType) 
		{
			case 1:
				if(isFourOfAKind())
				{
					System.out.println("Player can perform Four Of a kind dedication");
					displayFourOfAKindChoice();
					
				}
				else
				{
					System.out.println("Player can not perform Four Of a kind dedication");
				}
				
				//check and get four of kind lantern card for player and give dedicated token
				break;
			case 2:
				if(isThreePair())
				{
					System.out.println("Player can perform three pair dedication");
					displayThreePair();
					
				}
				else
				{
					System.out.println("Player can not perform Three Pair dedication");
				}
				//check and get three pair lantern card for user and give dedicated token
				break;
			case 3:
				if(isSevenUnique())
				{
					System.out.println("Player can perform Seven Unique dedication");
				}
				else
				{
					System.out.println("Player can not perform Seven Unique dedication");
				}
				////check and get Seven Unique lantern card for user and give dedicated token
				break;
			default:
				System.out.println("You have select the incorrect dedication type");
				
		
		}
	}
	
	/**
	 * Check if a player has a four of kind lantern card
	 * @return true if player has a four of a kind card and false otherwise
	 */
	public boolean isFourOfAKind()
	{
		
		for(Color c: Color.values())
		{
				if(numOfCardColor(c) >= 4)
				{	
					fourOfaKindList.add(c);
					canMakeDedication = true;
				}
			
		}
		return canMakeDedication;
	}
	
	/**
	 *  Display the four of kind  options a player has
	 */
	
	public void displayFourOfAKindChoice()
	{ 
		for(Color c : fourOfaKindList)
		{
			System.out.print("Select which of a kind color you would like to dedicate");
			System.out.print(c.name());
			removeFourOfAKindCard(c); //remove after player makes a choice
 
		}
		
	}

	/**
	 * Displays three pair cards a player has
	 */
	public void displayThreePair()
	{ 
		for(Color c : threePairList)
		{
			System.out.print("Select which of a Three Pair color you would like to dedicate");
			System.out.print(c.name());
			
			removeThreePairCard(c); //remove only when player makes a choice
		}
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
	
	public void removeFourOfAKindCard(Color c)
	{
		int count = 1;
		while(count<=4)
		{
			for(LanternCard lantern :this.lanternCards)
			{
				if(lantern.getColor() == c)
				{
					lanternCards.remove(c);
				}
				
			}
			count++;
		}
	}
	public void removeThreePairCard(Color c)
	{
		int count = 1;
		while(count<=2)
		{
			for(LanternCard lantern :this.lanternCards)
			{
				if(lantern.getColor() == c)
				{
					lanternCards.remove(c);
				}
				
			}
			count++;
		}
	}
	
	public void removeSingleCard(Color c)
	{
		int count = 1;
		while(count == 1)
		{
			for(LanternCard lantern :this.lanternCards)
			{
				if(lantern.getColor() == c)
				{
					lanternCards.remove(c);
				}
				
			}
			count++;
		}
	}
	
	/**
	 * Check if a player has a three pair
	 * @return true if player has a three pair card and false otherwise
	 */
	public boolean isThreePair()
	{
		int count = 0;;
		for(Color c: Color.values())
		{
				if(numOfCardColor(c) >= 2)
				{	
					threePairList.add(c);
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
		int count = 0;;
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

}