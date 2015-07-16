package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
/**
 * play area is a place for players to put a lake tile
 * and draw dedication tokens
 * @author nirav
 * @version 1.0
 */
public class PlayArea implements Serializable {
	/**
	 * the current number of favor tokens in play area
	 */
	private int numberOfFavorTokens;
	/**
	 * a group of lantern cards in play area
	 */
	private Supply supply;
	/**
	 * a start lake tile is placed in the play area since the game start
	 */
	private LakeTile startLakeTile;
	/**
	 * a group of lake tiles in a stack on play area
	 */
	private Stack<LakeTile> lakeTiles;
	/**
	 * one kind of a dedication token
	 */
	private Stack<SevenUniqueToken> sevenUniqueTokens;
	/**
	 * one kind of a dedication token
	 */
	private Stack<ThreePairToken> threePairTokens;
	/**
	 * one kind of a dedication token
	 */
	private Stack<FourOfAKindToken> fourOfAKindTokens;
	/**
	 * one kind of a dedication token
	 */
	private Stack<GenericToken> genericTokens;
	/**
	 * the total of each four of a kind, three pair and seven unique token in play area
	 */
	private int numberOfEachToken = 9;
	/**
	 * the total of generic token in play area
	 */
	private int numberOfGenericToken = 3;
	/**
	 * the total of lake tiles in play area
	 */
	private int numberOfLakeTiles = 35;
	/**
	 * Contructor of play area
	 * @param players list of players 
	 */
	public PlayArea(ArrayList<Player> players) {
		numberOfFavorTokens = 20;
		startLakeTile = new LakeTile(true);
		supply = new Supply(players.size());
		initializeLakeTiles(players.size());
		initializeDedicationTokens(players.size());
		putStartLakeTile(players);
	}
	/**
	 * put the flip lake tile of the game
	 * @param players list of players
	 */
	private void putStartLakeTile(ArrayList<Player> players) {
		Random r = new Random();
		int randomList = r.nextInt(players.size());
		Vector<Color> color = startLakeTile.getColorOfFourSides();
		for (int i = 0; i < players.size(); i++) {
			players.get((randomList + i) % players.size()).getLanternCards()
					.add(supply.lanternStacks.get(color.get(i)).pop());
		}
	}
	/**
	 * create the total lake tiles for players in the game before giving to the players
	 * @param numberOfPlayers the number of players
	 */
	private void initializeLakeTiles(int numberOfPlayers) {
		int numberOfLTToRemove = 0;
		startLakeTile = new LakeTile(true);
		// creation of Lake tiles
		lakeTiles = new Stack<LakeTile>();
		for (int i = 0; i < numberOfLakeTiles; i++) {
			lakeTiles.add(new LakeTile(false));
		}
		// shuffle lakeTiles
		Collections.shuffle(lakeTiles);

		// the number of available lakeTiles will depend on the number of players
		if (numberOfPlayers == 2) {
			numberOfLTToRemove = 13;
		} else if (numberOfPlayers == 3) {
			numberOfLTToRemove = 8;
		} else if (numberOfPlayers == 4) {
			numberOfLTToRemove = 3;
		}

		for (int i = 0; i < numberOfLTToRemove; i++) {
			lakeTiles.remove(0);
		}
	}

	/**
	 * create the total dedication token related to number of players
	 * @param numberOfPlayers number of players
	 */
	private void initializeDedicationTokens(int numberOfPlayers) {

		// creation of Dedication Token
		sevenUniqueTokens = new Stack<SevenUniqueToken>();
		threePairTokens = new Stack<ThreePairToken>();
		fourOfAKindTokens = new Stack<FourOfAKindToken>();
		genericTokens = new Stack<GenericToken>();

		for (int i = 0; i < numberOfEachToken; i++) {
			
			if (numberOfPlayers == 2 && DedicationToken.dotsList[i] > 2) {
				// for 2 players, do not add dots in any stacks
			} else if (numberOfPlayers == 3 && DedicationToken.dotsList[i] > 3) {
				// for 3 players, do not add Dedication token4 dots in any stacks
			} else {
				//add 3 kinds of dedication token in their stacks
				fourOfAKindTokens.add(new FourOfAKindToken(i));
				threePairTokens.add(new ThreePairToken(i));
				sevenUniqueTokens.add(new SevenUniqueToken(i));
			}
		}

		for (int i = 0; i < numberOfGenericToken; i++) {
			//add 4 generic token into generic token stack
			genericTokens.add(new GenericToken());
		}
	}
	/**
	 * Get a group of lantern stacks in play area
	 * @return supply (lantern stacks)
	 */
	public Supply getSupply() {
		return supply;
	}
	
	/**
	 * Set a group of lantern stacks in play area
	 * @param supply a group lantern stacks
	 */
	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	
	/**
	 * Get a start lake tile which is used at the beginning of the game
	 * @return a start lake tile
	 */
	public LakeTile getStartLakeTile() {
		return startLakeTile;
	}
	
	/**
	 * Set a start lake tile which is used at the beginning
	 * @param startLakeTile the beginning of lake tile on play area
	 */
	public void setStartLakeTile(LakeTile startLakeTile) {
		this.startLakeTile = startLakeTile;
	}

	/**
	 * Get a lake tile stack in a play area 
	 * @return a lake tile stack
	 */
	public Stack<LakeTile> getLakeTiles() {
		return lakeTiles;
	}
	
	/**
	 * Set a lake tile stack in a play area 
	 * @param lakeTiles a lake tile stack
	 */
	public void setLakeTiles(Stack<LakeTile> lakeTiles) {
		this.lakeTiles = lakeTiles;
	}
	
	/**
	 * Get a seven unique token stack in a play area 
	 * @return a seven unique token stack
	 */
	public Stack<SevenUniqueToken> getSevenUniqueTokens() {
		return sevenUniqueTokens;
	}

	/**
	 * Set a seven unique token stack in a play area 
	 * @param sevenUniqueTokens a seven unique token stack
	 */
	public void setSevenUniqueTokens(Stack<SevenUniqueToken> sevenUniqueTokens) {
		this.sevenUniqueTokens = sevenUniqueTokens;
	}

	/**
	 * Get a three pair token stack in a play area 
	 * @return a three pair token stack
	 */
	public Stack<ThreePairToken> getThreePairTokens() {
		return threePairTokens;
	}

	/**
	 * Set a three pair token stack in a play area
	 * @param threePairTokens a three pair token stack 
	 */
	public void setThreePairTokens(Stack<ThreePairToken> threePairTokens) {
		this.threePairTokens = threePairTokens;
	}
	
	/**
	 * Get a four of a kind token stack in a play area
	 * @return a four of a kind token stack 
	 */
	public Stack<FourOfAKindToken> getFourOfAKindTokens() {
		return fourOfAKindTokens;
	}

	/**
	 * Set a four of a kind token stack in a play area
	 * @param fourOfAKindTokens a four of a kind token stack 
	 */
	public void setFourOfAKindTokens(Stack<FourOfAKindToken> fourOfAKindTokens) {
		this.fourOfAKindTokens = fourOfAKindTokens;
	}

	/**
	 * Get a generic token stack in a play area
	 * @return a generic token stack 
	 */
	public Stack<GenericToken> getGenericTokens() {
		return genericTokens;
	}

	/**
	 * Set a generic token stack in a play area
	 * @param genericTokens a generic token stack 
	 */
	public void setGenericToken(Stack<GenericToken> genericTokens) {
		this.genericTokens = genericTokens;
	}

	/**
	 * Get a current number of favor tokens in a play area
	 * @return a current number of favor tokens  
	 */
	public int getNumberOfFavorTokens() {
		return numberOfFavorTokens;
	}

	/**
	 * Set a current number of favor tokens in a play area
	 * @param numberOfFavorTokens a current number of favor tokens 
	 */
	public void setNumberOfFavorTokens(int numberOfFavorTokens) {
		this.numberOfFavorTokens = numberOfFavorTokens;
	}

}
