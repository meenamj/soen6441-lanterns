package project;

import java.io.Serializable;
import java.util.*;

/**
 * The game named Lanterns : Harvest Festival This class is used to run the game
 * 
 * @author Nuttakit
 * @version 1.2
 */

public class Game implements Serializable {
	/**
	 * the number of player in the game
	 */
	private int numberOfPlayers;
	/**
	 * the list of players in the game
	 */
	private ArrayList<Player> players;
	/**
	 * the list of players' name in the game
	 */
	private String[] playersNames;
	/**
	 * the play area which provided lantern cards, lake tiles and dedication
	 * token
	 */
	private PlayArea playArea;

	/**
	 * 
	 * Get a group of player
	 * 
	 * @return the list of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Set a group of Player
	 * 
	 * @param players
	 *            the list of players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * Get a play area
	 * 
	 * @return PlayArea the area to place the card stacks and token piles
	 */
	public PlayArea getPlayArea() {
		return playArea;
	}

	/**
	 * Set a play area
	 * 
	 * @param playArea
	 *            the area to place the card stacks and token piles
	 */
	public void setPlayArea(PlayArea playArea) {
		this.playArea = playArea;
	}

	/**
	 * 
	 * constructor of the game
	 * 
	 * @param playersNames
	 *            the name of players
	 * @throws Exception
	 *             used when the players are more than 4 or less than 1
	 */
	public Game(String... playersNames) throws Exception {
		this.playersNames = playersNames;

		if (playersNames.length > 1 && playersNames.length < 5) {
			this.numberOfPlayers = playersNames.length;
		} else {
			throw new Exception();
		}
	}

	/**
	 * Start the game this method create player, lantern cards, lake tiles and
	 * dedication tokens on play area and give 3 lake tiles to each players
	 */
	public void startGame() {
		createPlayers(playersNames);
		playArea = new PlayArea(players);
		// give lakeTiles to player
		for (Player player : players) {
			for (int i = 0; i < 3; i++) {
				player.getLakeTiles().add(playArea.getLakeTiles().pop());
			}
		}
	}

	/**
	 * this method is used to create players and add them into arraylist of
	 * players
	 * 
	 * @param names
	 *            create the game of players
	 * 
	 */
	private void createPlayers(String... names) {
		Player player = null;
		players = new ArrayList<Player>();

		// create players according to numberOfPlayers attribute
		for (int i = 0; i < names.length; i++) {
			player = new Player(names[i]);
			// initialize all the stuff for the new player
			players.add(player);
		}
	}

	/**
	 * this main method is used to control and run the game
	 * 
	 * @param args
	 *            [] the first input from command line
	 * @throws Exception
	 *             used to when the game load or save are error
	 */
	public static void main(String args[]) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.println("1. New Game");
		System.out.println("2. Download");
		System.out.println("3. Exit");
		String in = null;
		do {
			in = scanner.next();
		} while (!in.equals("1") && !in.equals("2") && !in.equals("3"));
		Game game = option(scanner, in);

		game.showInformation();
		if (in.equals("2")) {
			System.out
					.print("Do you want to shuffle the laketile stack? Y/N : ");
			do {
				in = scanner.next();
			} while (!in.toUpperCase().equals("Y")
					&& !in.toUpperCase().equals("N"));
			if (in.equals("Y")) {
				Collections.shuffle(game.playArea.getLakeTiles());
				game.showInformation();
			}
		}

		System.out.print("Do you want to save? Y/N : ");
		do {
			in = scanner.next();
		} while (!in.toUpperCase().equals("Y") && !in.toUpperCase().equals("N"));

		if (in.toUpperCase().equals("Y")) {
			System.out.println("Put File Name To Save");
			Game.saveGame(game, scanner.next());
		} else {
			System.out.print("Goodbye");
		}
		System.exit(0);

	}

	public static Game option(Scanner scanner, String in) throws Exception{
		Game game = null;
		if (in.equals("2")) {
			System.out.println("Put File Name");
			game = Game.loadGame(scanner.next());
			if(game == null){
				System.out.println("Put Another File Name");
				game = option(scanner,in);
			}
		} else if (in.equals("1")) {
			String[] names = null;
			System.out.print("How many players? (select 2,3 or 4) : ");
			do {
				in = scanner.next();
			} while (!in.equals("2") && !in.equals("3") && !in.equals("4"));

			int nplayer = Integer.parseInt(in);
			names = new String[nplayer];
			for (int i = 0; i < nplayer; i++) {
				System.out.println("Player[" + i + "] name:");
				names[i] = new String(scanner.next());
			}
			game = new Game(names);
			game.startGame();
		} else {
			System.out.print("Goodbye");
			System.exit(0);
		}
		return game;
	}
	/**
	 * Save state of the game
	 * 
	 * @param g
	 *            the Game
	 * @param fname
	 *            the name of the saved file
	 */
	private static void saveGame(Game g, String fname) {
		GameFile.save(g, fname);
	}

	/**
	 * Load state of the game
	 * 
	 * @param fname
	 *            the name of the saved file
	 * @return Game the state of the game
	 */
	private static Game loadGame(String fname) {
		return GameFile.load(fname);
	}

	/**
	 * Show information of the card on play area and players hand
	 */
	private void showInformation() {
		System.out.println("\nFour Of A Kind Token Stack");
		for (int i = 0; i < this.playArea.getFourOfAKindTokens().size(); i++) {
			System.out.println(this.playArea.getFourOfAKindTokens().get(i)
					.getHonor());
		}
		System.out.println("\nSeven Unique Token Stack");
		for (int i = 0; i < this.playArea.getSevenUniqueTokens().size(); i++) {
			System.out.println(this.playArea.getSevenUniqueTokens().get(i)
					.getHonor());
		}

		System.out.println("\nThree Pair Token Stack");
		for (int i = 0; i < this.playArea.getThreePairTokens().size(); i++) {
			System.out.println(this.playArea.getThreePairTokens().get(i)
					.getHonor());
		}

		System.out.println("\nGeneric Token Stack");
		for (int i = 0; i < this.playArea.getGenericTokens().size(); i++) {
			System.out.println(this.playArea.getGenericTokens().get(i)
					.getHonor());
		}
		
		System.out.println("\nLantern Card Supply :");
		for (Color c : Color.values()){
		System.out.println(c.name() + " : " +
				this.playArea.getSupply().lanternStacks.get(c).size());
		}
		System.out.println("Amount of Favor Token :"+ this.playArea.getNumberOfFavorTokens());
		System.out.println("\nLake Tiles Stack");
		for (int i = 0; i < this.playArea.getLakeTiles().size(); i++) {
			LakeTile l = this.playArea.getLakeTiles().get(i);
			System.out.println(l.getIndex()+" : "+l.getColorOfFourSides().get(0).name() + " "
					+ l.getColorOfFourSides().get(1).name() + " "
					+ l.getColorOfFourSides().get(2).name() + " "
					+ l.getColorOfFourSides().get(3).name() + " \n Platform : "
					+ l.isPlatform());
		}

		System.out.println("\nStart Lake Tile");
		System.out.print(this.playArea.getStartLakeTile().getIndex()+" : "+this.playArea.getStartLakeTile().getColorOfFourSides()
				.get(0)
				+ " "
				+ this.playArea.getStartLakeTile().getColorOfFourSides().get(1)
				+ " "
				+ this.playArea.getStartLakeTile().getColorOfFourSides().get(2)
				+ " "
				+ this.playArea.getStartLakeTile().getColorOfFourSides().get(3)
				+ " \n");

		for (int i = 0; i < this.numberOfPlayers; i++) {
			System.out.print("Player" + i + " name : "
					+ this.players.get(i).getName());
			if (this.players.get(i).isCurrentPlayer()) {
				System.out.println(": (active)");
			} else {
				System.out.println();
			}
			System.out.println("Their Lantern Cards");
			int black = 0;
			int blue = 0;
			int green = 0;
			int red = 0;
			int purple = 0;
			int white = 0;
			int orange = 0;
			for (int j = 0; j < this.players.get(i).getLanternCards().size(); j++) {
				if(this.players.get(i).getLanternCards().get(j).getColor() == Color.BLACK){
					black+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.BLUE){
					blue+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.GREEN){
					green+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.RED){
					red+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.PURPLE){
					purple+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.WHITE){
					white+=1;
				}else if(this.players.get(i).getLanternCards().get(j).getColor() == Color.ORANGE){
					orange+=1;
				}
			}
			System.out.println("BLACK : "+black );
			System.out.println("BLUE : "+blue );
			System.out.println("GREEN : "+green );
			System.out.println("RED : "+red );
			System.out.println("PURPLE : "+purple );
			System.out.println("WHITE : "+white );
			System.out.println("ORANGE : "+orange);
			
			
			int total = 0;
			for (int j = 0; j < this.players.get(i).getDedicationTokens().size(); j++){
				total+=this.players.get(i).getDedicationTokens().get(j).getHonor();
			}
			System.out.println("\nValue Dedication Token : "+ total);
			System.out.println("\nTheir Lake Tiles :");
			for (int j = 0; j < this.players.get(i).getLakeTiles().size(); j++) {
				System.out.println(j
						+ 1
						+ " : index :"
						+ this.players.get(i).getLakeTiles().get(j)
								.getIndex()
						+ " "
						+ this.players.get(i).getLakeTiles().get(j)
								.getColorOfFourSides().get(0).name()
						+ " "
						+ this.players.get(i).getLakeTiles().get(j)
								.getColorOfFourSides().get(1).name()
						+ " "
						+ this.players.get(i).getLakeTiles().get(j)
								.getColorOfFourSides().get(2).name()
						+ " "
						+ this.players.get(i).getLakeTiles().get(j)
								.getColorOfFourSides().get(3).name()
						+ " "
						+ "\nPlatform : "
						+ this.players.get(i).getLakeTiles().get(j)
								.isPlatform());
			}
			System.out.println("");
		}

	}
}