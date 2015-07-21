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
	boolean canMakeDedication = false;
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
	 * 
	 * @param dedicationType The type of dedication a player is willing to make
	 */
	public void makeDedication(String dedicationType)
	{
		switch (dedicationType) 
		{
			case "fourOFAKind":
				checkFourOfAKind();
				//check and get four of kind lantern card for user and give dedicated token
				break;
			case "threePair":
				checkThreePair();
				//check and get three pair lantern card for user and give dedicated token
				break;
			case "sevenUnique":
				checkSevenUnique();
				////check and get Seven Unique lantern card for user and give dedicated token
				break;
			default:
				System.out.println("You have select the incorrect dedication type");
				
		
		}
	}
	public boolean checkFourOfAKind()
	{
		if(lanternCards.contains("RED"));
		{
			
		}
		canMakeDedication = true;
		return canMakeDedication;
		
		
	}
	
	public boolean checkThreePair()
	{
		canMakeDedication = true;
		return canMakeDedication;
	}
	public boolean checkSevenUnique()
	{
		canMakeDedication = true;
		return canMakeDedication;
	}

}