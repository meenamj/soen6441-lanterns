package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

/**
 * play area is a place for players to put a lake tile and draw dedication
 * tokens
 * 
 * @author none
 */
public class PlayArea implements Serializable {

	/**
	 * ArrayList<Players> into Queue<Players>
	 * <code> Queue<Player> players = new Queue<Player> </code>
	 */

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
	private LakeTile startLakeTile = new LakeTile(27, Color.BLUE, Color.RED,
			Color.WHITE, Color.BLACK, false);
	/**
	 * a group of lake tiles in a stack on play area
	 */
	private Stack<LakeTile> lakeTiles;
	private static LakeTile[] lakeTilesList = {
			new LakeTile(0, Color.GREEN, Color.BLUE, Color.BLACK, Color.ORANGE,
					false),
			new LakeTile(1, Color.WHITE, Color.BLACK, Color.ORANGE,
					Color.BLACK, false),
			new LakeTile(2, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED,
					false),
			new LakeTile(3, Color.ORANGE, Color.PURPLE, Color.ORANGE,
					Color.ORANGE, false),
			new LakeTile(4, Color.RED, Color.BLACK, Color.GREEN, Color.PURPLE,
					false),
			new LakeTile(5, Color.RED, Color.GREEN, Color.BLUE, Color.PURPLE,
					false),
			new LakeTile(6, Color.WHITE, Color.GREEN, Color.ORANGE,
					Color.GREEN, true),
			new LakeTile(7, Color.RED, Color.GREEN, Color.BLACK, Color.RED,
					false),
			new LakeTile(8, Color.WHITE, Color.BLUE, Color.BLACK, Color.BLUE,
					true),
			new LakeTile(9, Color.GREEN, Color.RED, Color.ORANGE, Color.WHITE,
					false),
			new LakeTile(10, Color.BLUE, Color.PURPLE, Color.WHITE, Color.RED,
					false),
			new LakeTile(11, Color.RED, Color.GREEN, Color.GREEN, Color.RED,
					true),
			new LakeTile(12, Color.BLACK, Color.PURPLE, Color.BLUE, Color.BLUE,
					false),
			new LakeTile(13, Color.WHITE, Color.WHITE, Color.RED, Color.GREEN,
					false),
			new LakeTile(14, Color.ORANGE, Color.PURPLE, Color.WHITE,
					Color.WHITE, true),
			new LakeTile(15, Color.PURPLE, Color.PURPLE, Color.RED,
					Color.GREEN, true),
			new LakeTile(16, Color.GREEN, Color.BLUE, Color.ORANGE, Color.BLUE,
					false),
			new LakeTile(17, Color.BLUE, Color.RED, Color.BLACK, Color.GREEN,
					false),
			new LakeTile(18, Color.BLUE, Color.BLACK, Color.WHITE, Color.GREEN,
					false),
			new LakeTile(19, Color.RED, Color.BLACK, Color.BLACK, Color.PURPLE,
					true),
			new LakeTile(20, Color.RED, Color.BLACK, Color.RED, Color.ORANGE,
					true),
			new LakeTile(21, Color.PURPLE, Color.BLUE, Color.PURPLE,
					Color.GREEN, true),
			new LakeTile(22, Color.ORANGE, Color.BLUE, Color.WHITE,
					Color.ORANGE, true),
			new LakeTile(23, Color.BLUE, Color.RED, Color.WHITE, Color.BLACK,
					false),
			new LakeTile(24, Color.BLUE, Color.WHITE, Color.GREEN,
					Color.PURPLE, false),
			new LakeTile(25, Color.ORANGE, Color.BLUE, Color.ORANGE,
					Color.BLUE, true),
			new LakeTile(26, Color.BLACK, Color.WHITE, Color.BLACK,
					Color.WHITE, true),
			// index 27 :: start lake tile
			new LakeTile(28, Color.PURPLE, Color.BLACK, Color.WHITE,
					Color.ORANGE, false),
			new LakeTile(29, Color.RED, Color.ORANGE, Color.PURPLE,
					Color.WHITE, false),
			new LakeTile(30, Color.PURPLE, Color.PURPLE, Color.BLACK,
					Color.PURPLE, false),
			new LakeTile(31, Color.ORANGE, Color.BLACK, Color.PURPLE,
					Color.RED, false),
			new LakeTile(32, Color.BLUE, Color.WHITE, Color.ORANGE, Color.RED,
					false),
			new LakeTile(33, Color.BLUE, Color.PURPLE, Color.BLACK,
					Color.ORANGE, false),
			new LakeTile(34, Color.RED, Color.WHITE, Color.BLACK, Color.ORANGE,
					false),
			new LakeTile(35, Color.WHITE, Color.GREEN, Color.PURPLE,
					Color.BLUE, false) };
	/**
	 * a list of lake tiles in a play area (Build-2)
	 */
	private LakeTile[][] lakeTilesOnBoard;
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
	 * the total of each four of a kind, three pair and seven unique token in
	 * play area
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
	 * Constructor of play area
	 * 
	 * @param players
	 *            list of players
	 */
	// update start lake tile on board
	public PlayArea(Queue<Player> players) {
		numberOfFavorTokens = 20;
		supply = new Supply(players.size());
		lakeTilesOnBoard = new LakeTile[65][65];
		initializeLakeTiles(players.size());
		lakeTilesOnBoard[32][32] = startLakeTile;
		initializeDedicationTokens(players.size());
		setUpLakeTile(players);
	}

	/**
	 * put the flip lake tile of the game
	 * 
	 * @param players
	 *            list of players
	 */
	// update Queue
	private void setUpLakeTile(Queue<Player> players) {
		Random r = new Random();
		int randomRedLantern = r.nextInt(players.size());
		Queue<Color> color = startLakeTile.getColorOfFourSides();
		// change the current player who get red lantern card
		color.add(color.remove());
		for (int i = 0; i < randomRedLantern; i++) {
			players.add(players.remove());
		}
		int current_number_player = 0;
		// add index to player
		for (int i = 0; i < players.size(); i++) {
			Player p = players.remove();
			p.setIndex(i);
			players.add(p);
		}

		for (Color lantern_color : color) {
			if (current_number_player == players.size()) {
				break;
			} else {
				current_number_player++;
			}
			Player p = players.remove();
			p.getLanternCards().add(
					supply.lanternStacks.get(lantern_color).pop());
			players.add(p);
		}
		// rotate the laketile to the correct position on laketile board
	}

	/**
	 * create the total lake tiles for players in the game before giving to the
	 * players
	 * 
	 * @param numberOfPlayers
	 *            the number of players
	 */
	private void initializeLakeTiles(int numberOfPlayers) {
		int numberOfLTToRemove = 0;
		boolean[] position = { true, true, true, true };
		// Add start lake tile to the on board list
		// lakeTilesOnBoard.add(startLakeTile);
		// creation of Lake tiles
		lakeTiles = new Stack<LakeTile>();
		for (int i = 0; i < lakeTilesList.length; i++) {
			lakeTiles.add(lakeTilesList[i]);
		}

		// shuffle lakeTiles
		Collections.shuffle(lakeTiles);

		// the number of available lakeTiles will depend on the number of
		// players
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
	 * 
	 * @param numberOfPlayers
	 *            number of players
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
				// for 3 players, do not add Dedication token4 dots in any
				// stacks
			} else {
				// add 3 kinds of dedication token in their stacks
				fourOfAKindTokens.add(new FourOfAKindToken(i));
				threePairTokens.add(new ThreePairToken(i));
				sevenUniqueTokens.add(new SevenUniqueToken(i));
			}
		}

		for (int i = 0; i < numberOfGenericToken; i++) {
			// add 4 generic token into generic token stack
			genericTokens.add(new GenericToken());
		}
	}

	/**
	 * Get a group of lantern stacks in play area
	 * 
	 * @return supply (lantern stacks)
	 */
	public Supply getSupply() {
		return supply;
	}

	/**
	 * Set a group of lantern stacks in play area
	 * 
	 * @param supply
	 *            a group lantern stacks
	 */
	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	/**
	 * Get a start lake tile which is used at the beginning of the game
	 * 
	 * @return a start lake tile
	 */
	public LakeTile getStartLakeTile() {
		return startLakeTile;
	}

	/**
	 * Set a start lake tile which is used at the beginning
	 * 
	 * @param startLakeTile
	 *            the beginning of lake tile on play area
	 */
	public void setStartLakeTile(LakeTile startLakeTile) {
		this.startLakeTile = startLakeTile;
	}

	/**
	 * Get a lake tile stack in a play area
	 * 
	 * @return a lake tile stack
	 */
	public Stack<LakeTile> getLakeTiles() {
		return lakeTiles;
	}

	/**
	 * Set a lake tile stack in a play area
	 * 
	 * @param lakeTiles
	 *            a lake tile stack
	 */
	public void setLakeTiles(Stack<LakeTile> lakeTiles) {
		this.lakeTiles = lakeTiles;
	}

	/**
	 * Get a seven unique token stack in a play area
	 * 
	 * @return a seven unique token stack
	 */
	public Stack<SevenUniqueToken> getSevenUniqueTokens() {
		return sevenUniqueTokens;
	}

	/**
	 * Set a seven unique token stack in a play area
	 * 
	 * @param sevenUniqueTokens
	 *            a seven unique token stack
	 */
	public void setSevenUniqueTokens(Stack<SevenUniqueToken> sevenUniqueTokens) {
		this.sevenUniqueTokens = sevenUniqueTokens;
	}

	/**
	 * Get a three pair token stack in a play area
	 * 
	 * @return a three pair token stack
	 */
	public Stack<ThreePairToken> getThreePairTokens() {
		return threePairTokens;
	}

	/**
	 * Set a three pair token stack in a play area
	 * 
	 * @param threePairTokens
	 *            a three pair token stack
	 */
	public void setThreePairTokens(Stack<ThreePairToken> threePairTokens) {
		this.threePairTokens = threePairTokens;
	}

	/**
	 * Get a four of a kind token stack in a play area
	 * 
	 * @return a four of a kind token stack
	 */
	public Stack<FourOfAKindToken> getFourOfAKindTokens() {
		return fourOfAKindTokens;
	}

	/**
	 * Set a four of a kind token stack in a play area
	 * 
	 * @param fourOfAKindTokens
	 *            a four of a kind token stack
	 */
	public void setFourOfAKindTokens(Stack<FourOfAKindToken> fourOfAKindTokens) {
		this.fourOfAKindTokens = fourOfAKindTokens;
	}

	/**
	 * Get a generic token stack in a play area
	 * 
	 * @return a generic token stack
	 */
	public Stack<GenericToken> getGenericTokens() {
		return genericTokens;
	}

	/**
	 * Set a generic token stack in a play area
	 * 
	 * @param genericTokens
	 *            a generic token stack
	 */
	public void setGenericToken(Stack<GenericToken> genericTokens) {
		this.genericTokens = genericTokens;
	}

	/**
	 * Get a current number of favor tokens in a play area
	 * 
	 * @return a current number of favor tokens
	 */
	public int getNumberOfFavorTokens() {
		return numberOfFavorTokens;
	}

	/**
	 * Set a current number of favor tokens in a play area
	 * 
	 * @param numberOfFavorTokens
	 *            a current number of favor tokens
	 */
	public void setNumberOfFavorTokens(int numberOfFavorTokens) {
		this.numberOfFavorTokens = numberOfFavorTokens;
	}

	// Methods for build-2 starts
	// 1) count the number of the lantern card the player has
	// 2) Display all the open positions like (index, color, platform,
	// true/false , facing player)
	// 3) show the lake tile in his hand like (Color-facing player, color-facing
	// player,color-facing player,
	// color-facing player)
	// 4) get all the tiles and information around a tile user has put
	// 5) distribute bonuses based on the tile a user put

	/**
	 * To get all the lake tiles available on the board
	 * 
	 * @return list of lake tiles found on board
	 */
	public LakeTile[][] getLakeTilesOnBoard() {
		return lakeTilesOnBoard;
	}

	/**
	 * get all open positions on the board
	 * 
	 * @throws Exception
	 */
	public void showLakeTileBoard() throws Exception {
		System.out.println("-- Lake Tile Board --");

		int[] size = getBoardSize();
		for (int y = size[1]; y <= size[3]; y++) {
			for (int x = size[0]; x <= size[2]; x++) {
				LakeTile l = lakeTilesOnBoard[x][y];
				if (l == null) {
					for (int i = 0; i < 17; i++) {
						System.out.print(Symbol.NOT);
					}

				} else {
					System.out.print("[(" + x + "," + y + ")");
					System.out.printf("%2s:", l.getIndex());
					ArrayList<Color> laketile_colors = new ArrayList<Color>(
							l.getColorOfFourSides());
					System.out.print(Color.getColorText(laketile_colors.get(0),
							Symbol.UP) + " ");
					System.out.print(Color.getColorText(laketile_colors.get(1),
							Symbol.LEFT) + " ");
					System.out.print(Color.getColorText(laketile_colors.get(2),
							Symbol.DOWN) + " ");
					System.out.print(Color.getColorText(laketile_colors.get(3),
							Symbol.RIGHT) + " ");
					if (l.isPlatform()) {
						System.out.print(Symbol.PLATFORM);
					} else {
						System.out.print(Symbol.NOT);
					}
					System.out.print("]");
				}
			}
			System.out.println("");
		}
	}

	private int[] getBoardSize() {
		int[] size = new int[4];
		// check least x position
		size[0] = lakeTilesOnBoard.length;
		for (int y = 0; y < lakeTilesOnBoard.length; y++) {
			for (int x = 0; x < lakeTilesOnBoard[y].length; x++) {
				if (lakeTilesOnBoard[x][y] != null && x < size[0]) {
					size[0] = x;
				}
			}
		}
		// check least y position
		size[1] = lakeTilesOnBoard.length;
		for (int x = 0; x < lakeTilesOnBoard.length; x++) {
			for (int y = 0; y < lakeTilesOnBoard[x].length; y++) {
				if (lakeTilesOnBoard[x][y] != null & y < size[1]) {
					size[1] = y;
				}
			}
		}
		// check most x position
		for (int y = 0; y < lakeTilesOnBoard.length; y++) {
			for (int x = lakeTilesOnBoard[y].length - 1; x >= 0; x--) {
				if (lakeTilesOnBoard[x][y] != null & x > size[2]) {
					size[2] = x;
				}
			}
		}
		// check least y position
		for (int x = 0; x < lakeTilesOnBoard.length; x++) {
			for (int y = lakeTilesOnBoard[x].length - 1; y >= 0; y--) {
				if (lakeTilesOnBoard[x][y] != null & y > size[3]) {
					size[3] = y;
				}
			}
		}
		return size;
	}

	/**
	 * this method checks for all the positions available to put on the board
	 * 
	 * @return coordinates of available positions to put a lake tile on the
	 *         board
	 */
	public ArrayList<Position> showIndexAvailableToPutLakeTileOnBoard() {
		ArrayList<Position> index_list = new ArrayList<Position>();
		for (int i = 0; i < lakeTilesOnBoard.length; i++) {
			for (int j = 0; j < lakeTilesOnBoard[i].length; j++) {
				// check all the available lake tile on board with a condition
				boolean flag = false;
				if (j + 1 < lakeTilesOnBoard[i].length)// is the index(j+1)
														// greater than the
														// array index?
					if (lakeTilesOnBoard[i][j + 1] != null) {// check the
																// available
						flag = true;
					}
				if (i + 1 < lakeTilesOnBoard.length)// is the index(i+1) greater
													// than the array index?
					if (lakeTilesOnBoard[i + 1][j] != null) {
						flag = true;
					}
				if (j - 1 > 0)// is the index(j-1) less than the array index?
					if (lakeTilesOnBoard[i][j - 1] != null) {
						flag = true;
					}
				if (i - 1 > 0)// is the index(i-1) less than the array index?
					if (lakeTilesOnBoard[i - 1][j] != null) {
						flag = true;
					}
				// is there a lake tile on this position?
				if (lakeTilesOnBoard[i][j] instanceof LakeTile) {
					flag = false;
				}
				// add index
				if (flag) {
					Position index = new Position(i, j);
					index_list.add(index);
				}
			}
		}
		return index_list;
	}

	public HashMap<Rotation, Vector<Object>> showAdjacentColor(Position pos)
			throws Exception {
		HashMap<Rotation, Vector<Object>> color_store = new HashMap<Rotation, Vector<Object>>();
		boolean isPlatform = false;
		if (pos.getX() - 1 > 0
				&& lakeTilesOnBoard[pos.getX() - 1][pos.getY()] != null) {
			Queue color = lakeTilesOnBoard[pos.getX() - 1][pos.getY()]
					.getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[pos.getX() - 1][pos.getY()]
					.isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(1);
			System.out.print("(" + Symbol.LEFT + Color.getColorText(c, " ")
					+ ") ");
			// left
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_store.put(Rotation.D270, color_and_platform);
		}
		if (pos.getY() - 1 > 0
				&& lakeTilesOnBoard[pos.getX()][pos.getY() - 1] != null) {
			Queue color = lakeTilesOnBoard[pos.getX()][pos.getY() - 1]
					.getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[pos.getX()][pos.getY() - 1]
					.isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(2);
			System.out.print("(" + Symbol.UP + Color.getColorText(c, " ")
					+ ") ");
			// up
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_store.put(Rotation.D0, color_and_platform);
		}
		if (pos.getX() + 1 < lakeTilesOnBoard.length
				&& lakeTilesOnBoard[pos.getX() + 1][pos.getY()] != null) {
			Queue color = lakeTilesOnBoard[pos.getX() + 1][pos.getY()]
					.getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[pos.getX() + 1][pos.getY()]
					.isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(3);
			System.out.print("(" + Symbol.RIGHT + Color.getColorText(c, " ")
					+ ") ");
			// right
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_store.put(Rotation.D90, color_and_platform);
		}
		if (pos.getY() + 1 < lakeTilesOnBoard[0].length
				&& lakeTilesOnBoard[pos.getX()][pos.getY() + 1] != null) {
			Queue color = lakeTilesOnBoard[pos.getX()][pos.getY() + 1]
					.getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[pos.getX()][pos.getY() + 1]
					.isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(0);
			System.out.print("(" + Symbol.DOWN + Color.getColorText(c, " ")
					+ ") ");
			// down
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_store.put(Rotation.D180, color_and_platform);
		}

		System.out.println();
		return color_store;
	}

}
