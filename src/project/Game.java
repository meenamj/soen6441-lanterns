package project;

import java.io.Serializable;
import java.util.*;

/**
 * The game named Lanterns : Harvest Festival This class is used to run the game
 * 
 * @author none
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
		Game game = null;
		if (in.equals("2")) {
			System.out.println("Put File Name");
			game = Game.loadGame(scanner.next());
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
		while(!this.playArea.getFourOfAKindTokens().isEmpty()) {
			System.out.println(this.playArea.getFourOfAKindTokens().pop()
					.getHonor());
		}
		System.out.println("\nSeven Unique Token Stack");
		while(!this.playArea.getSevenUniqueTokens().isEmpty()){
			System.out.println(this.playArea.getSevenUniqueTokens().pop()
					.getHonor());
		}

		System.out.println("\nThree Pair Token Stack");
		while(!this.playArea.getThreePairTokens().isEmpty()) {
			System.out.println(this.playArea.getThreePairTokens().pop()
					.getHonor());
		}

		System.out.println("\nGeneric Token Stack");
		while(!this.playArea.getGenericTokens().isEmpty()) {
			System.out.println(this.playArea.getGenericTokens().pop()
					.getHonor());
		}
		
		System.out.println("\nLake Tiles Stack");
		while(!this.playArea.getLakeTiles().isEmpty()) {
			LakeTile l = this.playArea.getLakeTiles().pop();
			System.out.println(l.getColorOfFourSides().get(0).name() + " "
					+ l.getColorOfFourSides().get(1).name() + " "
					+ l.getColorOfFourSides().get(2).name() + " "
					+ l.getColorOfFourSides().get(3).name() + " \n Platform : "
					+ l.isPlatform());
		}

		System.out.println("\nStart Lake Tile");
		System.out.print(this.playArea.getStartLakeTile().getColorOfFourSides()
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
			System.out.println("\nTheir Lantern Cards");
			for (int j = 0; j < this.players.get(i).getLanternCards().size(); j++) {
				System.out.println(this.players.get(i).getLanternCards().get(j)
						.getColor());
			}
			System.out.println("Their Lake Tiles :");
			for (int j = 0; j < this.players.get(i).getLakeTiles().size(); j++) {
				System.out.println(j
						+ 1
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