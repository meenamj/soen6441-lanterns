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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Scanner scan;
	/**
	 * the list of players in the game
	 */
	private Queue<Player> players;
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
	public Queue<Player> getPlayers() {
		return players;
	}

	/**
	 * Set a group of Player
	 * 
	 * @param players
	 *            the list of players
	 */
	public void setPlayers(Queue<Player> players) {
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
			startGame();
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
		players = new LinkedList<Player>();

		// create players according to number of players
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
			if (in.toUpperCase().equals("Y")) {
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
			System.out.print(l.getIndex()+" : ");
			for (Color c: l.getColorOfFourSides()){
				System.out.print(c.name() + " ");
			}
			System.out.println(" --- Platform : "+ l.isPlatform());
		}

		System.out.print("\nStart Lake Tile\n");
		System.out.print(playArea.getStartLakeTile().getIndex()+" : ");
		for ( Color c : playArea.getStartLakeTile().getColorOfFourSides()){
			System.out.print(c.name()+" ");
		}
		System.out.print(this.playArea.getStartLakeTile().isPlatform()
				+ " \n\n");

		for (Player player : players) {
			showPlayerInformation(player);
		}
		
		//Start Game
		GamePlay();
	}
	
	public void showPlayerInformation(Player player){
		System.out.print("Player name : "
				+ player.getName());
		if (players.element().equals(player)) {
			System.out.println(": (active)");
			//currentTurn = i+1;
		} else {
			System.out.println();
		}
		System.out.println("Lantern Cards");
		int black = 0;
		int blue = 0;
		int green = 0;
		int red = 0;
		int purple = 0;
		int white = 0;
		int orange = 0;
		for (int j = 0; j < player.getLanternCards().size(); j++) {
			if(player.getLanternCards().get(j).getColor() == Color.BLACK){
				black+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.BLUE){
				blue+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.GREEN){
				green+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.RED){
				red+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.PURPLE){
				purple+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.WHITE){
				white+=1;
			}else if(player.getLanternCards().get(j).getColor() == Color.ORANGE){
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
		for (int j = 0; j < player.getDedicationTokens().size(); j++){
			total+=player.getDedicationTokens().get(j).getHonor();
		}
		System.out.println("\nValue Dedication Token : "+ total);
		System.out.println("\nLake Tiles :");
		for (int j = 0; j < player.getLakeTiles().size(); j++) {
			System.out.print(j
					+ 1
					+ " : index :"
					+ player.getLakeTiles().get(j)
							.getIndex()
					+ " ");
			for (Color c : player.getLakeTiles().get(j).getColorOfFourSides()){
				System.out.print(c.name()+ " ");
			}
			System.out.println(" "
			+ "Platform : "
			+ player.getLakeTiles().get(j)
					.isPlatform());
		}
		System.out.println("");
	}
	//Build-2
	
	/**
	 * Options to be displayed
	 * @return in input selected by user
	 */
	public static int Menu(){
		@SuppressWarnings("resource")
		Scanner inputscan = new Scanner(System.in);
		String in;
		
		System.out.println("Select the one of the options(0-3):");
		System.out.println(" 0. Exit");
		System.out.println(" 1. Exchange a Lantern Card (optional) ");
		System.out.println(" 2. Make a Dedication (optional) ");
		System.out.println(" 3. Place a Lake Tile (mandatory) ");

		do {
			in = inputscan.next();
		} while (!in.equals("0") && !in.equals("1") && !in.equals("2") && !in.equals("3"));
		return Integer.parseInt(in);
	}
	
	/**
	 * Start Game play
	 */
	public void GamePlay(){
		int input = 0;
		Scanner inputscan = new Scanner(System.in);
		boolean quit = false;
		do{
			Player current_player = players.element();
			System.out.println("Player - "+ current_player.getName()+" will start to play :");
			playArea.showLakeTileBoard();
			input=Menu();

			switch (input) {
			case 1:
				System.out.println("Favor token part not Implemented yet...");
				break;

			case 2:
				System.out.println("Dedication tokens part not implemented yet");
				break;
				
			case 3:
				System.out.println("Place a lake tile selected");
				if(isNumberOfLanternCardsOnHandsOver()){
					System.out.println("You must make a dedication token or discard cards");
				}
				//**discard card or return to menu.
				showCurrentPlayerLakeTile();
				String in;
				boolean flag = true;
				do {
					in = inputscan.next();
					for( int i =0 ; i < current_player.getLakeTiles().size(); i++){
						if(in.equals(""+i)){
							flag= false;
						}
					}
				} while (flag);
				LakeTile active_laketile = current_player.getLakeTiles().get(Integer.parseInt(in));
				ArrayList<Double> list = playArea.showIndexAvailableToPutLakeTileOnBoard();
				System.out.print("Available index :::");
				for (double index : list) {
					System.out.print(index+" ");
				}
				/*
				 * 
				 * not working... we are doing it.
				 * 
				 */
				placeALakeTile();
				//change turn
				players.add(players.remove());
				
				break;

			case 0:
				quit = true;
				break;

			default:
				System.out.println("Invalid input!!! Please do right selection...");
				break;
			}
		}while(!quit);
	}
	
	/**
	 * get number of lantern cards a player has, to check it should not exceed 12
	 * @param playerID Id of the player
	 * @return count number of the lantern card a player has
	 */
	public int getNumberOfLanternCardsOnHand(){
		int count = players.element().getLanternCards().size();
		return count;
	}
	
	/**
	 * display the lake tile a player need to put next
	 * for example : 1. (Index, RED -P1, BLUE - P2, BLACK -P3, PURPLE- P4, Rotation)  
	 */
	public void showPlayerLakeTile(){
		for (int j = 1; j < 5; j++) 
		{
			Player current_player = players.element();
			System.out.print(j + " : index :"
					+ current_player.getLakeTiles().get(0).getIndex()
					+ " ");
			int i = 1;
			for( Color c : current_player.getLakeTiles().get(0).getColorOfFourSides()){
				System.out.print(c.name() + "P"+(i++));
			}
			System.out.print("Platform : "
					+ current_player.getLakeTiles().get(0).isPlatform());
					
			if(j==1){
				System.out.print( "Rotation : 0");
			}else if(j==2){
				System.out.print( "Rotation : 90");
			}else if(j==3){
				System.out.print( "Rotation : 180");
			}else if(j==4){
				System.out.print( "Rotation : 270");
			}
		}
	}
	
	/**
	 * To put a tile in the grid
	 * @param x position of x-coordinate
	 * @param y position of y-coordinate
	 * @param LakeTileIndex index of the lake, which need to put on the board
	 */
	public void putLakeTileOnGrid(int x, int y, LakeTile lakeTile){
			playArea.getLakeTilesOnBoard()[x][y] = lakeTile;
	}
	
	/**
	 * Get all the tiles around a tile provided
	 * @param x position of x-coordinates
	 * @param y position of y-coordinates
	 * 
	 */
	public ArrayList<LakeTile> getTilesAround(int x, int y){
		ArrayList<LakeTile> list_laketiles = new ArrayList<LakeTile>();
		list_laketiles.add(playArea.getLakeTilesOnBoard()[x][y+1]);
		list_laketiles.add(playArea.getLakeTilesOnBoard()[x+1][y]);
		list_laketiles.add(playArea.getLakeTilesOnBoard()[x][y-1]);
		list_laketiles.add(playArea.getLakeTilesOnBoard()[x-1][y]);
		return list_laketiles;
	}
	
	/**
	 * Execute this method, if the third option is selected by player.
	 */
	public void placeALakeTile(){
		/**
		 * 
		 * 
		 * not working .. nothing now
		 * 
		 */
		
			
		
	}
	
	public boolean isNumberOfLanternCardsOnHandsOver(){
		int lanternCards;
		lanternCards = getNumberOfLanternCardsOnHand();
		return lanternCards > 12;
	}
	
	public void showCurrentPlayerLakeTile(){
		ArrayList<LakeTile> current_player_laketiles = players.element().getLakeTiles();
		for (LakeTile lake_tile : current_player_laketiles){
			int index = current_player_laketiles.indexOf(lake_tile);
			System.out.print("index : " + index + "::");
			for(Color c : lake_tile.getColorOfFourSides()){
				System.out.print(c.name()+" ");
			}
			System.out.println("");
		}
	}
	

}
