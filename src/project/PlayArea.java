package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import java.util.Map.Entry;

import project.exception.ColorNotExistedException;

/**
 * Play area is a place for players to put a lake tile and draw dedication
 * tokens.
 * 
 * @author none
 */
public class PlayArea implements Serializable {

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -5043523585269610914L;
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
	/**
	 * index list of all lake tile except start lake tile in the game
	 */
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
	 * a list of lake tiles in a play area
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
	 * Constructor of play area
	 * get all stuff of starting play area depend on number of players
	 * 
	 * @param players list of players
	 */
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
					supply.get(lantern_color).pop());
			players.add(p);
		}
	}

	/**
	 * create lake tile stacks in the game
	 * before giving to the players
	 * 
	 * @param num_players
	 *            the number of players
	 */
	private void initializeLakeTiles(int num_players) {
		int numberOfLTToRemove = numLakeTileRemove(num_players);
		// Add start lake tile to the on board list
		// lakeTilesOnBoard.add(startLakeTile);
		// creation of Lake tiles
		lakeTiles = new Stack<LakeTile>();
		for (int i = 0; i < lakeTilesList.length; i++) {
			lakeTiles.add(lakeTilesList[i]);
		}

		// shuffle lakeTiles
		Collections.shuffle(lakeTiles);

		for (int i = 0; i < numberOfLTToRemove; i++) {
			lakeTiles.remove(0);
		}
	}

	/**
	 * remove laketile depend on the number of player
	 * @param numberOfPlayers
	 * @return number of lake tile to remove
	 */
	private int numLakeTileRemove(int numberOfPlayers) {
		int numberOfLTToRemove = 0;
		if (numberOfPlayers == 2) {
			numberOfLTToRemove = 13;
		} else if (numberOfPlayers == 3) {
			numberOfLTToRemove = 8;
		} else if (numberOfPlayers == 4) {
			numberOfLTToRemove = 3;
		}
		return numberOfLTToRemove;
	}

	/**
	 * create the total dedication token related to number of players
	 * 
	 * @param numberOfPlayers number of players
	 */
	private void initializeDedicationTokens(int numberOfPlayers) {

		// creation of Dedication Token
		sevenUniqueTokens = new Stack<SevenUniqueToken>();
		threePairTokens = new Stack<ThreePairToken>();
		fourOfAKindTokens = new Stack<FourOfAKindToken>();
		genericTokens = new Stack<GenericToken>();

		for (int i = 0; i < numberOfEachToken; i++) {

			if (numberOfPlayers == 2 && DedicationToken.DOTS_LIST[i] > 2) {
				// for 2 players, do not add dots in any stacks
			} else if (numberOfPlayers == 3 && DedicationToken.DOTS_LIST[i] > 3) {
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
	 * @param supply a group lantern stacks
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
	 * @param lakeTiles laketile stack
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
	 * @param sevenUniqueTokens a seven unique token stack
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
	 * @param threePairTokens a three pair token stack
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
	 * @param fourOfAKindTokens a four of a kind token stack
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
	 * @param genericTokens a generic token stack
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
	 * @param numberOfFavorTokens a current number of favor tokens
	 */
	public void setNumberOfFavorTokens(int numberOfFavorTokens) {
		this.numberOfFavorTokens = numberOfFavorTokens;
	}

	/**
	 * To get all the lake tiles available on the board
	 * 
	 * @return list of lake tiles found on board
	 */
	public LakeTile[][] getLakeTilesOnBoard() {
		return lakeTilesOnBoard;
	}

	/**
	 * display all open positions on the board based on the current arrangements of the lake tiles
	 * @return text of all lake tile on board
	 * @throws ColorNotExistedException when color of lake tile does not exist
	 */
	public String getLakeTileBoardText() throws ColorNotExistedException {
		ArrayList<String> index = new ArrayList<String>();
		ArrayList<String> line1 = new ArrayList<String>();
		ArrayList<String> line2 = new ArrayList<String>();
		ArrayList<String> line3 = new ArrayList<String>();
		int[] size = getBoardSize();
		for (int y = size[1]; y <= size[3]; y++) {
			String index_str = new String();
			String line1_str = new String();
			String line2_str = new String();
			String line3_str = new String();
			for (int x = size[0]; x <= size[2]; x++) {
				LakeTile l = lakeTilesOnBoard[x][y];
				if (l == null) {
					index_str += String.format("%11s", "");
					line1_str += String.format("%11s", "");
					line2_str += String.format("%11s", "");
					line3_str += String.format("%11s", "");
				} else {
					index_str += String.format("%2s", l.getIndex());
					index_str += ":";
					index_str += String.format("%-7s","(" + x + "," + y + ")");
					index_str += " ";
					ArrayList<Color> laketile_colors = new ArrayList<Color>(
							l.getColorOfFourSides());
					line1_str += "     ";
					line1_str += Color.getColorText(laketile_colors.get(0),Symbol.UP); 
					line1_str += "     ";
					line2_str += "   ";
					line2_str += Color.getColorText(laketile_colors.get(3),Symbol.LEFT); 
					line2_str += " ";
					if (l.isPlatform()) {
						line2_str += "0 ";
					} else {
						line2_str += "X ";
					}
					line2_str += Color.getColorText(laketile_colors.get(1),Symbol.RIGHT);
					line2_str += "   ";
					line3_str += "     ";
					line3_str += Color.getColorText(laketile_colors.get(2),Symbol.DOWN);
					line3_str += "     ";
				}
			}
			index.add(index_str+"\n");
			line1.add(line1_str+"\n");
			line2.add(line2_str+"\n");
			line3.add(line3_str+"\n");
		}
		String text = new String("");
		for(int i = 0; i< index.size();i++){
			text += index.get(i);
			text += line1.get(i);
			text += line2.get(i);
			text += line3.get(i);
		}
		return ":::Lake Tile Board:::\n"+text;
	}

	/**
	 * size of the game board is dynamic, so this method checks for the board size
	 * in both directions and displays only the required board grid
	 * 
	 * @return size of the board
	 */
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
	public ArrayList<Position> getPositionAvailableLakeTileOnBoard() {
		ArrayList<Position> index_list = new ArrayList<Position>();
		for (int i = 0; i < lakeTilesOnBoard.length; i++) {
			for (int j = 0; j < lakeTilesOnBoard[i].length; j++) {
				boolean lakeTileEmpty = isLakeTileEmpty(i, j);
				// add index
				if (lakeTileEmpty) {
					Position index = new Position(i, j);
					index_list.add(index);
				}
			}
		}
		if(index_list.size()==0){
			index_list.add(new Position(32,32));
		}
		return index_list;
	}
	
	/**
	 * 
	 * @param i the coordinator x on lake tile board
	 * @param j the coordinator y on lake tile board
	 * @return boolean lake tile on this position is empty or not
	 */
	private boolean isLakeTileEmpty(int i, int j) {
		boolean flag = false;
		if (j + 1 < lakeTilesOnBoard[i].length)
			if (lakeTilesOnBoard[i][j + 1] != null) {
				flag = true;
			}
		if (i + 1 < lakeTilesOnBoard.length)
			if (lakeTilesOnBoard[i + 1][j] != null) {
				flag = true;
			}
		if (j - 1 > 0)
			if (lakeTilesOnBoard[i][j - 1] != null) {
				flag = true;
			}
		if (i - 1 > 0)
			if (lakeTilesOnBoard[i - 1][j] != null) {
				flag = true;
			}
		if (lakeTilesOnBoard[i][j] instanceof LakeTile) {
			flag = false;
		}
		return flag;
	}

	/**
	 * takes the position of the lake tile as an input and checks for all adjacent lake tiles
	 * and get information of those lake tiles
	 * @param pos position of the current lake tile
	 * @return HashMap adjacent color and platform of lake tile on board around the lake tile position.
	 */
	public HashMap<Rotation, Vector<Object>> getAdjacentColor(Position pos) {
		HashMap<Rotation, Vector<Object>> color_platform_store = new HashMap<Rotation, Vector<Object>>();
		boolean isPlatform = false;
		int x = pos.getX();
		int y = pos.getY();
		if (pos.getX() - 1 > 0 && lakeTilesOnBoard[x-1][y] != null) {
			Queue<Color> color = lakeTilesOnBoard[x-1][y].getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[x-1][y].isPlatform();
			ArrayList<Color> list = new ArrayList<Color>(color);
			Color c = list.get(1);
			
			// left
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_platform_store.put(Rotation.D270, color_and_platform);
		}
		if (pos.getY() - 1 > 0 && lakeTilesOnBoard[x][y-1] != null) {
			Queue<Color> color = lakeTilesOnBoard[x][y-1].getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[x][y-1].isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(2);
			
			// up
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_platform_store.put(Rotation.D0, color_and_platform);
		}
		if (x + 1 < lakeTilesOnBoard.length && lakeTilesOnBoard[x+1][y] != null) {
			Queue<Color> color = lakeTilesOnBoard[x+1][y].getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[x+1][y].isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(3);
			
			// right
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_platform_store.put(Rotation.D90, color_and_platform);
		}
		if (y + 1 < lakeTilesOnBoard[0].length && lakeTilesOnBoard[x][y+1] != null) {
			Queue<Color> color = lakeTilesOnBoard[x][y+1].getColorOfFourSides();
			isPlatform = lakeTilesOnBoard[x][y+1].isPlatform();
			ArrayList<Color> l = new ArrayList<Color>(color);
			Color c = l.get(0);
			
			// down
			Vector<Object> color_and_platform = new Vector<Object>();
			color_and_platform.add(c);
			color_and_platform.add(isPlatform);
			color_platform_store.put(Rotation.D180, color_and_platform);
		}
		return color_platform_store;
	}
	
	/**
	 * show color of each sides of an available position
	 * 
	 * @param color_platform_store properties of the position on the laketile on board
	 * there are 4 rotations in the hashmap; 0,90,180 and 270 degrees;
	 * in each rotations store the color
	 * @return string of color and rotation in each sides
	 * @throws ColorNotExistedException when any colors on lake tile does not exists
	 */
	public String getAdjacentColorText(HashMap<Rotation, Vector<Object>> color_platform_store) throws ColorNotExistedException {
		String text = "";
		for (Entry<Rotation, Vector<Object>> color_platform_set : color_platform_store.entrySet()) {
			Rotation rotation = color_platform_set.getKey();
			Vector<Object> color_platform = color_platform_set.getValue();
			Color c = (Color)color_platform.get(0);
			if(rotation.equals(Rotation.D0))
			{
				text += "(" + Symbol.UP + Color.getColorText(c , " ") + ") ";
			}
			else if(rotation.equals(Rotation.D90))
			{
				text += "(" + Symbol.RIGHT + Color.getColorText(c, " ") + ") ";
			}
			else if(rotation.equals(Rotation.D180))
			{
				text += "(" + Symbol.DOWN + Color.getColorText(c, " ") + ") ";
			}
			else if(rotation.equals(Rotation.D270))
			{
				text += "(" + Symbol.LEFT + Color.getColorText(c, " ") + ") ";
			}
		}
		return text;
	}
	
	public int getNumberLakeTileOnBoard(){
		int num_laketile = 0;
		for (int y = 0; y < lakeTilesOnBoard.length; y++) {
			for (int x = 0; x < lakeTilesOnBoard[y].length; x++) {
				if(lakeTilesOnBoard[x][y]!=null)
				{
					num_laketile++;
				}
			}
		}
		return num_laketile;
	}
}
