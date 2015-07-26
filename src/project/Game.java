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
	 * @throws Exception 
	 */
	private void showInformation() throws Exception {
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
		System.out.println(Color.getColorText(c, "\u2022") + " : " +
				this.playArea.getSupply().lanternStacks.get(c).size());
		}
		System.out.println("Amount of Favor Token :"+ this.playArea.getNumberOfFavorTokens());
		System.out.println("\nLake Tiles Stack");
		for (int i = 0; i < this.playArea.getLakeTiles().size(); i++) {
			LakeTile l = this.playArea.getLakeTiles().get(i);
			System.out.print(l.getIndex()+" : \t");
			for (Color c: l.getColorOfFourSides()){
				System.out.print(Color.getColorText(c, "\u2022") + " ");
			}
			System.out.print(" --- Platform : ");
			if(l.isPlatform()){
				System.out.println("\u273f");
			}else{
				System.out.println();
			}
		}

		System.out.print("\nStart Lake Tile\n");
		System.out.print(playArea.getStartLakeTile().getIndex()+" : ");
		for ( Color c : playArea.getStartLakeTile().getColorOfFourSides()){
			System.out.print(Color.getColorText(c, "\u2022")+" ");
		}
		if(this.playArea.getStartLakeTile().isPlatform()){
			System.out.print("\u273f");
		}
		System.out.print(" \n\n");

		for (Player player : players) {
			showPlayerInformation(player);
		}
		
		//Start Game
		GamePlay();
	}
	
	public void showPlayerInformation(Player player) throws Exception{
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
		System.out.print(Color.getColorText(Color.BLACK, " ")+black+" ");
		System.out.print(Color.getColorText(Color.BLUE, " ")+blue+" ");
		System.out.print(Color.getColorText(Color.GREEN, " ")+green+" ");
		System.out.print(Color.getColorText(Color.RED, " ")+red+" ");
		System.out.print(Color.getColorText(Color.PURPLE, " ")+purple+" ");
		System.out.print(Color.getColorText(Color.WHITE, " ")+white+" ");
		System.out.println(Color.getColorText(Color.ORANGE, " ")+orange+" ");
		
		
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
			ArrayList<Color> laketile_colors  = new ArrayList<Color>(player.getLakeTiles().get(j).getColorOfFourSides());
			System.out.print(Color.getColorText(laketile_colors.get(0), "\u2191")+" ");//up
			System.out.print(Color.getColorText(laketile_colors.get(1), "\u2192")+" ");//right
			System.out.print(Color.getColorText(laketile_colors.get(2), "\u2193")+" ");//down
			System.out.print(Color.getColorText(laketile_colors.get(3), "\u2190")+" ");//left
			
			System.out.print("Platform : ");
			if(player.getLakeTiles().get(j).isPlatform()){
				System.out.print("\u273f");
			}
			System.out.println();
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
	 * @throws Exception 
	 */
	public void GamePlay() throws Exception{
		int input = 0;
		Scanner inputscan = new Scanner(System.in);
		boolean quit = false;
		do{
			Player current_player = players.element();
			System.out.println("Player - "+ current_player.getName()+" will start to play :");
			
			//check number of lake tile
			while(current_player.getNumberOfLakeTile()<3){
				System.out.println("Draw New LakeTile");
				LakeTile new_laketile = playArea.getLakeTiles().pop();
				current_player.getLakeTiles().add(new_laketile);
			}
			playArea.showLakeTileBoard();
			
			
			///code
			System.out.println("Lantern Cards");
			int black = 0;
			int blue = 0;
			int green = 0;
			int red = 0;
			int purple = 0;
			int white = 0;
			int orange = 0;
			for (int j = 0; j < current_player.getLanternCards().size(); j++) {
				if(current_player.getLanternCards().get(j).getColor() == Color.BLACK){
					black+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.BLUE){
					blue+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.GREEN){
					green+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.RED){
					red+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.PURPLE){
					purple+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.WHITE){
					white+=1;
				}else if(current_player.getLanternCards().get(j).getColor() == Color.ORANGE){
					orange+=1;
				}
			}
			System.out.print(Color.getColorText(Color.BLACK, " ")+black+" ");
			System.out.print(Color.getColorText(Color.BLUE, " ")+blue+" ");
			System.out.print(Color.getColorText(Color.GREEN, " ")+green+" ");
			System.out.print(Color.getColorText(Color.RED, " ")+red+" ");
			System.out.print(Color.getColorText(Color.PURPLE, " ")+purple+" ");
			System.out.print(Color.getColorText(Color.WHITE, " ")+white+" ");
			System.out.println(Color.getColorText(Color.ORANGE, " ")+orange+" ");
			
			////
			input=Menu();

			switch (input) {
			case 1:
				//remove lantern card from player's hand and add that card to supply stack
				playerLanternCard(current_player);
				//remove lantern card from supply stack and add it to player's hand
				supplyLanternCard(current_player);
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
				
				//show player position
				for(Player player : players){
					System.out.print(player.getName());
					if(player.getIndex()==0){
						System.out.print("\u2191 ");
					}else if(player.getIndex()==1){
						System.out.print("\u2192 ");
					}if(player.getIndex()==2){
						System.out.print("\u2193 ");
					}if(player.getIndex()==3){
						System.out.print("\u2190 ");
					}
				}
				System.out.println();
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
				//get the laketile which player wants to put then remove the tile on their hand
				LakeTile active_laketile = current_player.getLakeTiles().remove(Integer.parseInt(in));
				ArrayList<Position> list = playArea.showIndexAvailableToPutLakeTileOnBoard();
				System.out.println("Available index :::");
				
				ArrayList<HashMap<Rotation,Color>> adjacent_color_list = new ArrayList<HashMap<Rotation,Color>>();
				for (int i=0; i<list.size(); i++){
					Position index = list.get(i);
					System.out.print("option "+i+" ::"+index.getText());
					
					//show adjacent color
					adjacent_color_list.add(playArea.showAdjacentColor(index));
					System.out.println();
				}
				System.out.print("which position you want to put laketile::");
				//input position and check
				flag = true;
				do {
					in = inputscan.next();
					for( int i =0 ; i < list.size(); i++){
						if(in.equals(""+i)){
							flag= false;
						}
					}
				} while (flag);
				placeALakeTile(list.get(Integer.parseInt(in)), active_laketile);
				HashMap<Rotation, Color> adjacent_colors = adjacent_color_list.get(Integer.parseInt(in));
				System.out.println();
				showPossibleRotation(active_laketile);
				
				do {
					in = inputscan.next();
				} while (!in.equals("0") && !in.equals("1") && !in.equals("2") && !in.equals("3"));
				int rotation = Integer.parseInt(in)*90;
				active_laketile.setRotation(Rotation.getRotation(rotation));
				//change the side of lake tile to put on board
				/// new
				active_laketile.changeRotation(active_laketile.getRotation());
				
				/*
				 * 
				 * matching not working... we are doing it.
				 * 
				 */
				ArrayList<Player> players_list = new ArrayList<Player>(players);
				HashMap<Color, Stack<LanternCard>> lanternStacks = playArea.getSupply().getLanternStack();
				LanternCard l = null;
				for(int i =0; i<players.size(); i++){
					Player getting_lanterncard_player = players_list.get(i);
					int index = getting_lanterncard_player.getIndex();
					System.out.println("index::"+index);
					ArrayList<Color> color_list = new ArrayList<Color>(active_laketile.getColorOfFourSides());
					if(index==0){
						System.out.println(adjacent_colors.get(Rotation.D0));
						
						Stack<LanternCard> lanternCard = lanternStacks.get(color_list.get(0));
						l = lanternCard.pop();
						getting_lanterncard_player.getLanternCards().add(l);
					}else if(index==1){
						Stack<LanternCard> lanternCard = lanternStacks.get(color_list.get(1));
						l = lanternCard.pop();
						getting_lanterncard_player.getLanternCards().add(l);
					}else if(index==2){
						Stack<LanternCard> lanternCard = lanternStacks.get(color_list.get(2));
						l = lanternCard.pop();
						getting_lanterncard_player.getLanternCards().add(l);
					}else if(index==3){
						Stack<LanternCard> lanternCard = lanternStacks.get(color_list.get(3));
						l = lanternCard.pop();
						getting_lanterncard_player.getLanternCards().add(l);
					}
				}
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
	 * Selection and removal of a lantern card form player's stack
	 * @param player active player object
	 */
	public void playerLanternCard(Player player){
		Scanner inputscan = new Scanner(System.in);
		String playersLanternCard;
		System.out.println("Choose a lantern card you want to exchange");
		ArrayList<LanternCard> lanternCards =  player.getLanternCards();

		System.out.println();
		int p = 0;
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
		try {
			if(black>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.BLACK, " ")+black+" ");
				p++;}
			if (blue>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.BLUE, " ")+blue+" ");
				p++;}
			if (green>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.GREEN, " ")+green+" ");
				p++;}
			if (red>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.RED, " ")+red+" ");
				p++;}
			if (purple>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.PURPLE, " ")+purple+" ");
				p++;}
			if (white>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.WHITE, " ")+white+" ");
				p++;}
			if (orange>0){
				System.out.println("Index : "+ p +" : "+Color.getColorText(Color.ORANGE, " ")+orange+" ");
				p++;}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		boolean flag = true;
		do {
			playersLanternCard = inputscan.next();
			int j = 0; int i=0;
			System.out.print(lanternCards.size());
			for (int f=0; f<lanternCards.size();f++){
				
				if(j <= p){
					if(playersLanternCard.equals(""+j)){
						flag= false;
						int d = player.getNumberOfFavorTokens();
						player.setNumberOfFavorTokens(d-2);
						LanternCard lancard = player.getLanternCards().remove(f);
						
						playArea.getSupply().lanternStacks.get(lancard.getColor()).add(lancard);
					}
					j++;
				}
				i++;
			}
		} while (flag);
	}
	
	/**
	 * Selection and removal of a lantern card form supply stack
	 * @param player active player object
	 */
	public void supplyLanternCard(Player player){
		Scanner inputscan = new Scanner(System.in);
		String supplyLanternCard;
		System.out.println("\nLantern Card Supply :");
		int i=0;
		for (Color c : Color.values()){
			try {
				if(this.playArea.getSupply().lanternStacks.get(c).size() > 0){
					System.out.println("Index :"+ i +" :"+Color.getColorText(c, "\u2022") + " : " +
							this.playArea.getSupply().lanternStacks.get(c).size());
					i++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean flag = true;
		do {
			supplyLanternCard = inputscan.next();
			int j=0; int s=0;
			for (Color c : Color.values()){
				try {
					if(this.playArea.getSupply().lanternStacks.get(c).size() > 0){
						if(supplyLanternCard.equals(""+j)){
							flag= false;
							LanternCard lancard = playArea.getSupply().lanternStacks.get(c).remove(s);
							player.getLanternCards().add(lancard);
						}
						j++;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				s++;
			}
		} while (flag);
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
	 * @throws Exception 
	 */
	public void showPlayerLakeTile() throws Exception{
		for (int j = 1; j < 5; j++) 
		{
			Player current_player = players.element();
			System.out.print(j + " : index :"
					+ current_player.getLakeTiles().get(0).getIndex()
					+ " ");
			int i = 1;
			for( Color c : current_player.getLakeTiles().get(0).getColorOfFourSides()){
				System.out.print(Color.getColorText(c, "\u2022") + "P"+(i++));
			}
			System.out.print("platform::");
			if(current_player.getLakeTiles().get(0).isPlatform()){
				System.out.println("\u273f");
			}
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
	public void placeALakeTile(Position pos , LakeTile lakeTile){
		int x = pos.getX();
		int y = pos.getY();
		playArea.getLakeTilesOnBoard()[x][y] = lakeTile;
	}
	
	public boolean isNumberOfLanternCardsOnHandsOver(){
		int lanternCards;
		lanternCards = getNumberOfLanternCardsOnHand();
		return lanternCards > 12;
	}
	
	public void showCurrentPlayerLakeTile() throws Exception{
		ArrayList<LakeTile> current_player_laketiles = players.element().getLakeTiles();
		for (LakeTile lake_tile : current_player_laketiles){
			int index = current_player_laketiles.indexOf(lake_tile);
			System.out.print("index : " + index + ":");
			System.out.printf("%2s -",lake_tile.getIndex());
			for(Color c : lake_tile.getColorOfFourSides()){
				System.out.print(Color.getColorText(c, "\u2022")+" ");
			}
			System.out.println("");
		}
	}
	
	public void showPossibleRotation(LakeTile l) throws Exception{
		int sideOfLakeTile = 4;
		System.out.println("How do you want to rotate the lake tile?");
		for(int i = 0; i < sideOfLakeTile; i++){
			System.out.print(i+":");
			ArrayList<Color> four_side_colors = new ArrayList<Color>(l.getColorOfFourSides());
			System.out.print(Color.getColorText(four_side_colors.get(0), "\u2191")+" ");
			System.out.print(Color.getColorText(four_side_colors.get(1), "\u2192")+" ");
			System.out.print(Color.getColorText(four_side_colors.get(2), "\u2193")+" ");
			System.out.print(Color.getColorText(four_side_colors.get(3), "\u2190")+" ");
			System.out.println();
			l.getColorOfFourSides().add(l.getColorOfFourSides().remove());
		}
	}
}
