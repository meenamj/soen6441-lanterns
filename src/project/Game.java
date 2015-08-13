package project;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.SerializationUtils;

import project.disaster.*;
import project.exception.*;
import project.rule.*;
import project.strategy.*;
import project.strategy.Random;
import project.strategy.Strategy.Name;

/**
 * The game named Lanterns : Harvest Festival This class is used to run the game
 * 
 * @author Nuttakit
 * @version 2.0
 */

public class Game implements Serializable {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 4065252587270966918L;

	/**
	 * the list of players in the game
	 * 
	 * You can get the first player by element method
	 * <code>Player first_player = getPlayers().element();</code>
	 * also, pop method, however, after using this method, the player will not in the queue object.
	 * <code>Player first_player = getPlayers().pop();</code>
	 * So, when finishing the turn, the player is put into the last by push method
	 * <code>getPlayers().push(first_player)</code>
	 */
	private Queue<Player> players;
	
	/**
	 * the list of players' name in the game
	 */
	private String[] playersNames;
	
	/**
	 * the number of strategies
	 * 0: Greedy - try to make dedication as possible
	 * 1: Unfriendly - try not to help other players as possible
	 * 2: Random - random everything as possible (high overhead)
	 * 3: Basic - choose only first option on place lake tile as possible
	 * 4: Human - you can control everything by yourself
	 */
	private int[] strategies;
	
	/**
	 * the rule of the game
	 */
	private Rule rule;
	
	/**
	 * The disaster of the game.
	 * There are 3 kinds of disaster.
	 * 1: Tsunami - clear all lake tile on board
	 * to get the tsunami object.
	 * <code>Tsunami disaster = (Tsunami)disasters.get(Strategy.Tsunami)</code>
	 * 2: Passing Power Boat - clear some lake tile on board
	 * to get the passing power boat object.
	 * <code>PassingPowerBoat disaster = (PassingPowerBoat)disasters.get(Strategy.PassingPowerBoat)</code>
	 * 3: Lightning Strike - remove some dedication tokens on players randomly
	 * to get the lightning strike object.
	 * <code>LightningStrike disaster = (LightningStrike)disasters.get(Strategy.LightningStrike)</code>
	 */
	private ArrayList<Disaster> disasters;
	
	/**
	 * the play area which provided lantern cards, 
	 * lake tiles and dedication token
	 */
	private PlayArea playArea;

	/**
	 * Get a queue of player
	 * 
	 * @return the list of players
	 */
	public Queue<Player> getPlayers() 
	{
		return players;
	}

	/**
	 * Set a group of Player
	 * 
	 * @param players the list of players
	 */
	public void setPlayers(Queue<Player> players) 
	{
		this.players = players;
	}

	/**
	 * Get a play area
	 * 
	 * @return PlayArea the area to place the card stacks and token piles
	 */
	public PlayArea getPlayArea() 
	{
		return playArea;
	}

	/**
	 * Set a play area
	 * 
	 * @param playArea
	 *            the area to place the card stacks and token piles
	 */
	public void setPlayArea(PlayArea playArea) 
	{
		this.playArea = playArea;
	}

	/**
	 * constructor of the game
	 * 
	 * @param playersNames the name of players
	 * @param strategies the technique to play of each players
	 * there are 5 strategies such as Greedy, Unfriendly, Random, Base or Human
	 * @param rule how to win the game
	 * there are 3 rules;  lake tile stack is empty(basic rule)
	 * ,n lake tile on board and n honor to choose;
	 * @param disasters unexpected situations in the game
	 * there are 3 disasters; tsunami, passing power boat and lightning strike
	 * @throws NoNumberPlayersException used when there are not between 2-4 players
	 */
	public Game(String[] playersNames, int[] strategies, Rule rule, ArrayList<Disaster> disasters) throws NoNumberPlayersException  
	{
		this.disasters = disasters;
		this.playersNames = playersNames;
		this.strategies = strategies;
		setRule(rule);
		
		if (playersNames.length > 1 && playersNames.length < 5) 
		{
			startGame();
		} 
		else 
		{
			throw new NoNumberPlayersException(playersNames.length);
		}
	}
	
	/**
	 * constructor to create clone of Game class
	 * to clone this object, commons lang library
	 *  is necessary to use the method clone on the
	 *  static class SerializationUtils
	 * <code>SerializationUtils.clone(object);</code>
	 * @return Game the clone object of game
	 */
	public Game clone(){
		Game game  = SerializationUtils.clone(this);
		return game;
	}

	/**
	 * Start the game this method create player, lantern cards, lake tiles and
	 * dedication tokens on play area and give 3 lake tiles to each players
	 */
	public void startGame() 
	{
		createPlayers(playersNames);
		playArea = new PlayArea(players);
		// give lakeTiles to player
		for (Player player : players) 
		{
			for (int i = 0; i < 3; i++) 
			{
				player.getLakeTiles().add(playArea.getLakeTiles().pop());
			}
		}
	}

	/**
	 * Create players, players' strategies
	 * then add them into queue of players
	 * 
	 * @param names
	 *            create the game of players
	 * 
	 */
	private void createPlayers(String... names) 
	{
		Player player = null;
		players = new LinkedList<Player>();

		// create players according to number of players
		for (int i = 0; i < names.length; i++) 
		{
			if(strategies[i]==Strategy.GREEDY_STRATEGY){
				player = new Player(names[i], new Greed());
			}else if(strategies[i]==Strategy.UNFRIENDLY_STRATEGY){
				player = new Player(names[i], new Unfriendliness());
			}else if(strategies[i]==Strategy.RANDOM_STRATEGY){
				player = new Player(names[i], new Random());
			}else if(strategies[i]==Strategy.BASIC_STRATEGY){
				player = new Player(names[i], new Basic());
			}else if(strategies[i]==Strategy.HUMAN_STRATEGY){
				player = new Player(names[i], new Human());
			}
			// initialize all the stuff for the new player
			players.add(player);
		}
	}
	
	/**
	 * Setter of disasters
	 * @param d the list of disaster
	 */
	public void setDisasters(ArrayList<Disaster> d){
		this.disasters = d;
	}
	
	/**
	 * Getter of disaster
	 * @return the list of disasters
	 */
	public ArrayList<Disaster> getDisasters(){
		return disasters;
	}
	
	/**
	 * Setter of Rule
	 * @param r the rule of game
	 */
	public void setRule(Rule r){
		this.rule = r;
	}
	
	/**
	 * Getter of Rule
	 * @return the rule of game
	 */
	public Rule getRule(){
		return rule;
	}
	
	/**
	 * this main method is used to run the game to select the starting menu 
	 * 
	 * @param args [] the first input from command line
	 * @throws Exception used to when the game are error    
	 */
	public static void main(String args[]) throws Exception
	{
		Game game = Game.startOption();
		// Start Game
		game.play();
	}

	/**
	 * 
	 * @return the game object
	 * @throws NoNumberPlayersException when the number of players are not between 2-4
	 */
	public static Game startOption() throws NoNumberPlayersException {
		System.out.println("0. New Game");
		System.out.println("1. Download");
		System.out.println("2. Exit");
		int in = new Human().inputOption(3, Strategy.Name.START,null);
		Game game = null;
		if (in == 0) 
		{
			game =  putPlayerNamesOption();
		} 
		else if (in == 1)
		{
			game = loadGameOption();
			String error_text = game.getValidationError();
			if(!error_text.equals(""))
			{
				System.out.println(error_text);
				game = startOption();
			}
			else
			{
				game.updateStrategy();
				int nplayer = game.players.size();
				String current_rule_name = game.getRule().getClass().getSimpleName();
				System.out.println("the current rule is "+current_rule_name);
				Rule r = ruleMenu(nplayer);
				game.setRule(r);
			}
		} else
		{
			System.out.print("Goodbye");
			System.exit(0);
		}
		return game;
	}
	
	/**
	 * to get messages of validation to show on the console
	 * in case the game can't load correctly
	 * @return error validation
	 */
	public String getValidationError(){
		String error_info = "";
		if(!playArea.getSupply().validate(getPlayers().size())){
			error_info += "number lantern card on stacks is over\n";
		}
		if(playArea.getNumberOfFavorTokens()>20){
			error_info += "number favor tokens are over 20\n";
		}
		if(!error_info.equals("")){
			error_info += "please check your file\n";
		}
		return error_info;
	}

	/**
	 * This method displays the number of player allowed
	 * and selected number of player, their names, their strategies,
	 * and a rule of the game
	 * @return game
	 * @throws NoNumberPlayersException use when player enters incorrect number of player 
	 */
	public static Game putPlayerNamesOption() throws NoNumberPlayersException  
	{
		String[] names = null;
		System.out.print("How many players? (select 2,3 or 4) : ");
		int nplayer = new Human().inputOption(2, 4);
		names = new String[nplayer];
		int[] strategies = new int[nplayer];
		for (int i = 0; i < nplayer; i++) 
		{
			System.out.println("Player[" + i + "] name:");
			names[i] = new Human().inputString();
			System.out.println("Which computer-based players you want?");
			System.out.println(Strategy.GREEDY_STRATEGY+". Greed\n");
			System.out.println(Strategy.UNFRIENDLY_STRATEGY+". Unfriend\n");
			System.out.println(Strategy.RANDOM_STRATEGY+". Random\n");
			System.out.println(Strategy.BASIC_STRATEGY+". Basic\n");
			System.out.println(Strategy.HUMAN_STRATEGY+". Human");
			strategies[i] = new Human().inputOption(5, Strategy.Name.START,null);
		}
		Rule rule = ruleMenu(nplayer);
		ArrayList<Disaster> disasters = disasterMenu(nplayer);
		return new Game(names, strategies, rule, disasters);
	}
	
	/**
	 * to ask if the game has disaster or not.
	 * @param nplayer the number of player in the game
	 * @return disasters in the game; tsunami, lightning strike and power boat
	 */
	public static ArrayList<Disaster> disasterMenu(int nplayer){
		Disaster disaster = null;
		System.out.println("Do you want Disaster? Y/N");
		boolean is_disaster = new Human().inputYesNo();
		ArrayList<Disaster> disasters = new ArrayList<Disaster>(); 
		if(is_disaster){
			disaster = new Tsunami(nplayer);
			disasters.add(disaster);
			disaster = new PassingPowerBoat(nplayer);
			disasters.add(disaster);
			disaster = new LightningStrike(nplayer);
			disasters.add(disaster);
		}
		return disasters;
	}
	
	/**
	 * show menu of the rules in the game
	 * @param nplayer the number of player in the game
	 * @return rules in the game; laketile stack overflow, n laketile on board, and n honor value
	 */
	public static Rule ruleMenu(int nplayer){
		Rule rule = null;
		System.out.println("Choose the rule of game ::");
		System.out.println("0. Base Rule\n" +
				"1. N Lake tiles on board Rule\n" +
				"2. N Honor Point Rule");
		int rule_choice = new Human().inputOption(3, Strategy.Name.START,null);
		if(rule_choice==Rule.BASE_RULE){
			rule = (Rule) new Base();
		}else if(rule_choice==Rule.N_LAKE_TILES_ON_BOARD_RULE){
			System.out.println("How many round do you want to play?");
			int max_laketile_stack = 0;
			if(nplayer==4){
				max_laketile_stack = 20;
			}else if(nplayer==3){
				max_laketile_stack = 18;
			}else{
				max_laketile_stack = 16;
			}
			int max_round = max_laketile_stack/nplayer;
			for(int i =0;i<max_round-2;i++){
				System.out.println("option"+i+"::"+(i+2));
			}
			int round = new Human().inputOption(max_round-2, Strategy.Name.START,null);
			rule = (Rule) new NLakeTilesOnBoard(round+2);
		}else if(rule_choice==Rule.N_HONOR_POINT_RULE){
			System.out.println("How many Honor point do you want to finish the game?");
			int sum_honor = 0;
			for(int i = 0; i<DedicationToken.DOTS_LIST.length;i++){
				if(DedicationToken.DOTS_LIST[i]<nplayer)
				{
					sum_honor +=FourOfAKindToken.HONOR_LIST[i];
					sum_honor +=ThreePairToken.HONOR_LIST[i];
					sum_honor +=SevenUniqueToken.HONOR_LIST[i];
				}
			}
			
			int average_honor = sum_honor/nplayer;
			for(int i = 0; i<average_honor-4; i++){
				System.out.println("option"+i+"::"+(i+4));
			}
			int win_honor = new Human().inputOption(average_honor-4, Strategy.Name.START,null);
			rule = new NHonorPoint(win_honor+4);
		}
		return rule;
	}
	
	/**
	 * to type the file name
	 * @param game the Game
	 */
	public static void saveGameOption(Game game) 
	{
		System.out.println("Put File Name To Save");
		String filename = new Human().inputString();
		Game.saveGame(game, filename);
	}

	/**
	 * Save state of the game
	 * 
	 * @param g the Game
	 * @param fname the name of the saved file
	 */
	private static void saveGame(Game g, String fname) 
	{
		GameFile.save(g, fname);
	}

	/**
	 * This method load game from saved file
	 * @return  game
	 */
	public static Game loadGameOption()
	{
		Game game = null;
		System.out.println("Put File Name");
		String file_name = new Human().inputString();
		game = Game.loadGame(file_name);
		if (game == null)
		{
			System.out.println("Put Another File Name");
			game = loadGameOption();
		}
		return game;
	}

	/**
	 * Load state of the game
	 * 
	 * @param fname the name of the saved file
	 * @return Game the state of the game
	 */
	private static Game loadGame(String fname) 
	{
		return GameFile.load(fname);
	}

	/**
	 * get text of stuff such as lantern stacks, dedication stacks on play area
	 * and text of players' stuff.
	 * @return String the starting game information
	 * @throws ColorNotExistedException when the color does not exist
	 */
	private String getInformationText() throws ColorNotExistedException  
	{
		String text = "\nFour Of A Kind Token Stack";
		Stack<FourOfAKindToken> four_kind_stack = playArea.getFourOfAKindTokens();
		int four_kind_size = four_kind_stack.size();
		for (int i = 0; i < four_kind_size; i++) 
		{
			text += "\n";
			text += four_kind_stack.get(i).getHonor();
		}
		text += "\n";
		text += "\nSeven Unique Token Stack";
		Stack<SevenUniqueToken> seven_unique_stack = playArea.getSevenUniqueTokens();
		int seven_unique_size = seven_unique_stack.size();
		for (int i = 0; i < seven_unique_size; i++) 
		{
			text += "\n";
			text += seven_unique_stack.get(i).getHonor();
		}
		text += "\n";
		text += "\nThree Pair Token Stack";
		Stack<ThreePairToken> three_pair_stack = playArea.getThreePairTokens();
		int three_pair_size = three_pair_stack.size();
		for (int i = 0; i < three_pair_size; i++) 
		{
			text += "\n";
			text += three_pair_stack.get(i).getHonor();
		}
		text += "\n";
		text += "\nGeneric Token Stack";
		Stack<GenericToken> generic_stack = playArea.getGenericTokens();
		int generic_size = generic_stack.size();
		for (int i = 0; i < generic_size; i++) 
		{
			text += "\n";
			text += generic_stack.get(i).getHonor();
		}

		text += "\n\nLantern Card Supply :\n";
		for (Color c : Color.values()) 
		{
			Stack<LanternCard> lantern_stack = playArea.getSupply().get(c);
			int lantern_stack_size = lantern_stack.size();
			text += Color.getColorText(c, Symbol.BULLET);
			text += " : ";
			text += lantern_stack_size;
			text += "\n";
		}
		
		text += "\nAmount of Favor Token :";
		text += playArea.getNumberOfFavorTokens();
		text += "\n\nLake Tiles Stack\n";
		
		for (int i = 0; i < playArea.getLakeTiles().size(); i++)
		{
			LakeTile l = playArea.getLakeTiles().get(i);
			text += String.format("%4s", l.getIndex() + " :");
			for (Color c : l.getColorOfFourSides()) 
			{
				text += Color.getColorText(c, " ");
				text += " ";
			}
			
			if (l.isPlatform()) 
			{
				text += Symbol.PLATFORM;
				
			}
			text += "\n";
		}

		text += "\nStart Lake Tile\n";
		text += playArea.getStartLakeTile().getIndex() + " : ";
		for (Color c : playArea.getStartLakeTile().getColorOfFourSides()) 
		{
			text += Color.getColorText(c, Symbol.BULLET);
			text += " ";
		}
		
		if (this.playArea.getStartLakeTile().isPlatform())
		{
			text += Symbol.PLATFORM;
		}
		text += " \n\n";
		
		Player current_player = players.element();
		for (Player player : players) 
		{
			text += player.getInformationText(current_player);
			text += "\n";
		}
		return text;
	}

	/**
	 * Options to be displayed
	 * 
	 * @return in input selected by user
	 */
	public int Menu() {
		System.out.println("Select the one of the options(0-3):");
		System.out.println(" 0. Exit");
		System.out.println(" 1. Exchange a Lantern Card (optional) ");
		System.out.println(" 2. Make a Dedication (optional) ");
		System.out.println(" 3. Place a Lake Tile (mandatory) ");
		System.out.println(" 4. Save Game ");
		System.out.println(" 5. Load Game ");
		Player current_player = getPlayers().element();
		return current_player.getStrategy().inputOption(6, Strategy.Name.MAINMENU,this);
	}

	/**
	 * Show base information for each player turn in the game
	 * and prepare the 3 laketiles for each players during the game
	 * @throws ColorNotExistedException when color of dedication token,
	 *  lantern or lake tile does not exist 
	 * @throws RotationNotExistedException when any rotations on lake tile does not exists
	 */
	public void play() throws ColorNotExistedException, RotationNotExistedException  
	{
		boolean quit = false;
		System.out.println(getInformationText());
		do {
			Player current_player = players.element();
			System.out.println("Player - " + current_player.getName()
					+ " will start to play :\n");
			if (!playArea.getLakeTiles().empty()
					&& current_player.getNumberOfLakeTile() < 3)
			{
				System.out.println("Draw New LakeTile");
				LakeTile new_laketile = playArea.getLakeTiles().pop();
				System.out.println(new_laketile.getTextLakeTile());
				current_player.getLakeTiles().add(new_laketile);
			}
			System.out.print(playArea.getLakeTileBoardText());

			System.out.println("\nNumber of Favor Tokens::"
					+ current_player.getNumberOfFavorTokens());
			System.out.println("\nValue Dedication Token : "
					+ current_player.countHonorValue());
			// /code
			System.out.println("\nLantern Cards");
			
			for(Color c : Color.values())
			{
				System.out.print(Color.getColorText(c, " ")+current_player.numOfCardColor(c));
			}
			
			System.out.println("\n\n");
			// //
			gameCoreOption(current_player);

		} while (!quit);
		System.out.println("Good Bye");
	}

	/**
	 * The active player may perform each of these actions once per turn
	 * @param current_player active player
	 * @throws ColorNotExistedException when any colors on lake tile does not exists
	 * @throws RotationNotExistedException when any rotations on lake tile does not exists
	 */
	public void gameCoreOption(Player current_player) throws ColorNotExistedException, RotationNotExistedException   
	{
		if (getRule().rule(this)) 
		{
			System.out.println(getTheWinner());
			System.exit(0);
		}
		int input = Menu();
		switch (input) 
		{
		case 1:

			current_player.exchangeLanCard(this);
			break;

		case 2:
			makeADedicationMenu(current_player);
			if (getRule().getClass().getSimpleName().equals("NHonorPointRule")
					&&getRule().rule(this)) 
			{
				System.out.println(getTheWinner());
				System.exit(0);
			}
			break;

		case 3:
			if(current_player.getLanternCards().size()>12)
			{
				String text = "\nYou have more than twelve lantern cards\n";
				text += "You need to make a dedication or discard a lantern card\n";
				System.out.print(text);
			}
			else
			{
				System.out.println(getPlayerPositionText());
				
				System.out.println("Place a lake tile selected");

				// show player position
				
				System.out.println();
				System.out.println(current_player.getCurrentPlayerLakeTileText());
				int input1 = current_player.getStrategy().inputOption(current_player.getLakeTiles().size(), Strategy.Name.SELECT_LAKE,this);
				LakeTile active_laketile = current_player.getLakeTiles().remove(input1);
				ArrayList<Position> list = playArea.getPositionAvailableLakeTileOnBoard();
				
				ArrayList<HashMap<Rotation, Vector<Object>>> adjacent_color_list = current_player.placeLakeTileMenu(playArea, active_laketile);
				
				System.out.print("which position you want to put laketile::");
				// input position and check
				
				int pos_laketile_opt = current_player.getStrategy().inputOption(list.size(), Strategy.Name.SELECT_BOARD_POSITION,this);
				HashMap<Rotation, Vector<Object>> adjacent_colors = current_player.getPossibleRotation(list, adjacent_color_list, playArea, active_laketile, pos_laketile_opt);
				
				System.out.println(current_player.getPossibleRotationText(active_laketile));
				
				int rotation_opt = current_player.getStrategy().inputOption(4, Strategy.Name.SELECT_LAKE_ROTATION,this);
				current_player.setRotationOnActiveLakeTile(active_laketile, rotation_opt);
				
				
				distributeLanternCard(active_laketile, playArea.getSupply());
				getBonusPlaceLakeTile(active_laketile, adjacent_colors);
				
				current_player.getInformationText(current_player);
				
				// change turn
				players.add(players.remove());
				
				// to get the winner with rules
				if (getRule().rule(this)) 
				{
					System.out.println(getTheWinner());
					System.exit(0);
				}
				
				// disaster feature before starting the next player turn
				// show lake tile board once on board before disasters occur
				boolean showBeforeDisaster = true;
				for(int i = 0; i< getDisasters().size(); i++)
				{
					Disaster disaster = getDisasters().get(i);
					boolean is_disaster = disaster.getDisaster();
					if (is_disaster){
						if(showBeforeDisaster){
							System.out.println("The Board before Disaster::");
							System.out.println(playArea.getLakeTileBoardText());
							showBeforeDisaster = false;
						}
						String text = disaster.attack(this);
						System.out.println(text);
					}
				}
			}
			break;
			
		case 4:
			saveGameOption(this);
			break;
		case 5:
			Game g = loadGameOption();
			String error_text = g.getValidationError();
			if(!error_text.equals(""))
			{
				System.out.println(error_text);
				System.out.println("Continue playing the current game");
			}else{
				g.updateStrategy();
				String current_rule_name = g.getRule().getClass().getSimpleName();
				System.out.println("The current rule is "+current_rule_name);
				rule = ruleMenu(g.players.size());
				g.play();
			}
			break;
		case 0:
			System.out.print("GoodBye");
			System.exit(0);
			break;

		default:
			System.out.println("Invalid input!!! Please do right selection...");
			break;
		}

	}



		
	/**
	 * This method distribute lantern card from supply to player
	 * @param active_laketile lake tile from the stack
	 * @param supply lantern card supply stack 
	 */

	public void distributeLanternCard(LakeTile active_laketile, Supply supply) 
	{
		ArrayList<Player> players_list = new ArrayList<Player>(players);
		for (int i = 0; i < players.size(); i++) 
		{
			Player getting_lanterncard_player = players_list.get(i);
			int index = getting_lanterncard_player.getIndex();
			ArrayList<Color> color_list = new ArrayList<Color>(
					active_laketile.getColorOfFourSides());
			if (index >= 0 && index < players.size()) 
			{
				Stack<LanternCard> lanternCard = supply
						.get(color_list.get(index));
				if (!lanternCard.empty())
				{
					LanternCard l = lanternCard.pop();
					getting_lanterncard_player.getLanternCards().add(l);
				}
			}
		}
	}
	
	
	/**
	 * This method get text of the player position with direction
	 * @return String show direction of player
	 */
	private String getPlayerPositionText() 
	{
		String text = "";
		for (Player player : players) 
		{
			text += player.getName();
			if (player.getIndex() == 0) {
				text += Symbol.UP;
			} else if (player.getIndex() == 1) {
				text += Symbol.RIGHT;
			} else if (player.getIndex() == 2) {
				text += Symbol.DOWN;
			} else if (player.getIndex() == 3) {
				text += Symbol.LEFT;
			}
			text += " ";
		}
		return text;
	}

	/**
	 * This method display the menu for the types of dedication a player can make 
	 * @param current_player active player
	 */
	private void makeADedicationMenu(Player current_player)
	{
		int choice;
		System.out.println("What type of dedication do you want to make? ");
		System.out.println(" 0. Four of A Kind");
		System.out.println(" 1. Three Pair");
		System.out.println(" 2. Seven Unique");
		do 
		{
			choice = current_player.getStrategy().inputOption(3, Strategy.Name.MAKE_DEDICATION,this);
		} 
		while (choice < 0 && choice > 2);
		
		if (choice == 0) 
		{
			current_player.makeFourOfAKind(playArea);
		} 
		else if (choice == 1) 
		{
			current_player.makeThreePair(playArea);
		} 
		else if (choice == 2)
		{
			current_player.makeSevenUnique(playArea);
		}
	}

	/**
	 * This method give bonus lake tile if two color of the same are facing each other
	 * @param active_laketile lake tile on the play area
	 * @param adjacent_colors adjacent color
	 * @throws RotationNotExistedException when any rotations on lake tile does not exists
	 */
	public void getBonusPlaceLakeTile(LakeTile active_laketile, HashMap<Rotation, Vector<Object>> adjacent_colors) throws RotationNotExistedException 
	{
		// get bonus for adjacent and platform
		for (int i = 0; i < adjacent_colors.size(); i++) 
		{
			for (Entry<Rotation, Vector<Object>> c : adjacent_colors.entrySet()) 
			{
				Vector<Object> color_platform = (Vector<Object>) c.getValue();
				// up -adjacent lake tile color and down - (active color)
				if (c.getKey().equals(Rotation.D0)) 
				{
					getBonusDirection(Rotation.D0, active_laketile, color_platform);
				} else if (c.getKey().equals(Rotation.D90)) 
				{
					
					getBonusDirection(Rotation.D90, active_laketile, color_platform);
				} else if (c.getKey().equals(Rotation.D180)) 
				{
					getBonusDirection(Rotation.D180, active_laketile, color_platform);
					
				} 
				else if (c.getKey().equals(Rotation.D270)) 
				{
					getBonusDirection(Rotation.D270, active_laketile, color_platform);
					
				}
			}
		}
	}

	/**
	 * This method checks the direction to give bonus
	 * Comparing one color side of player's lake tile with adjacent color
	 * @param r Degree of rotation
	 * @param active_laketile lake tile on play area
	 * @param color_platform color of platform
	 * @throws RotationNotExistedException when any rotations on lake tile does not exists  
	 */
	private void getBonusDirection(Rotation r, LakeTile active_laketile,
			Vector<Object> color_platform) throws RotationNotExistedException
	{
		Player current_player = players.element();
		Color side_color = active_laketile.getSideOfColor(r);
		if (side_color == color_platform.get(0)) 
		{
			Supply supply = playArea.getSupply();
			Stack<LanternCard> lantern_stack = supply.get(color_platform.get(0));
			
			if (!lantern_stack.empty()) {
				current_player.getLanternCards().add(
						lantern_stack.pop());
			}
			int favor_token = playArea.getNumberOfFavorTokens();
			if (active_laketile.isPlatform()&&favor_token>0)
			{
				favor_token = favor_token -1 ;
				playArea.setNumberOfFavorTokens(favor_token);
				current_player.setNumberOfFavorTokens(current_player.getNumberOfFavorTokens() + 1);
			}
			
			if ((Boolean) color_platform.get(1)&&favor_token>0) 
			{
				favor_token = favor_token -1 ;
				playArea.setNumberOfFavorTokens(favor_token);
				current_player.setNumberOfFavorTokens(current_player.getNumberOfFavorTokens() + 1);
			}
		}
	}

	/**
	 * This method get and displays the winner of the game
	 * @return string with the winner name, number of favor tokens and number of lantern
	 * cards
	 */
	public String getTheWinner() {
		int winner_honor = 0;
		int winner_favor_token = 0;
		int winner_lan_card = 0;
		ArrayList<Player> winner_honor_players = new ArrayList<Player>();
		ArrayList<Player> winner_favor_players = new ArrayList<Player>();
		ArrayList<Player> winners = new ArrayList<Player>();
		String winnerStr = "The winner";
		for (Player p : players) 
		{
			if (p.countHonorValue() == winner_honor)
			{
				winner_honor_players.add(p);
			}
			if (p.countHonorValue() > winner_honor) 
			{
				winner_honor_players = new ArrayList<Player>();
				winner_honor_players.add(p);
				winner_honor = p.countHonorValue();
			}
		}

		for (Player p : winner_honor_players)
		{
			if (p.getNumberOfFavorTokens() == winner_favor_token) 
			{
				winner_favor_players.add(p);
			}
			if (p.getNumberOfFavorTokens() > winner_favor_token)
			{
				winner_favor_players = new ArrayList<Player>();
				winner_favor_players.add(p);
				winner_favor_token = p.getNumberOfFavorTokens();
			}
		}

		for (Player p : winner_favor_players) 
		{
			int current_lan_size = p.getLanternCards().size();
			if (current_lan_size == winner_lan_card) {
				winners.add(p);
			}
			if (current_lan_size > winner_lan_card) 
			{
				winners = new ArrayList<Player>();
				winners.add(p);
				winner_lan_card = current_lan_size;
			}
		}
		
		if (winners.size() > 1) 
		{
			winnerStr += "s are";
		} 
		
		else 
		{
			winnerStr += " is";
		}
		
		for (int i = 0; i<winners.size();i++)
		{
			if(i==0)
			{
				winnerStr += (" "+winners.get(i).getName());
			}
			else if(i<winners.size()-1)
			{
				winnerStr += (", "+winners.get(i).getName());
			}
			else
			{
				winnerStr += (" and " + winners.get(i).getName());
			}
			
			if(i!=winners.size()-1){
				
			}
		}
		winnerStr +=" with " + winner_honor + " honor value, ";
		winnerStr +=winner_favor_token + " favor, and ";
		winnerStr +=winner_lan_card + " lantern card";
		return winnerStr;
	}
	
	/**
	 * This method is used for updating strategy after loading game
	 */
	public void updateStrategy(){
		System.out.println("Do you want to change strategy? Y/N");
		boolean is_changed_strategy = new Human().inputYesNo();
		if(is_changed_strategy){
			ArrayList<Player> player_list = new ArrayList<Player>(players);
			for(Player player : player_list){
				System.out.print(player.getName()+" has ");
				String strategy_name = player.getStrategy().getClass().getSimpleName();
				System.out.println(strategy_name);
				System.out.println("Which strategy do you want to change?");
				System.out.println(Strategy.GREEDY_STRATEGY+". Greed\n");
				System.out.println(Strategy.UNFRIENDLY_STRATEGY+". Unfriend\n");
				System.out.println(Strategy.RANDOM_STRATEGY+". Random\n");
				System.out.println(Strategy.BASIC_STRATEGY+". Basic\n");
				System.out.println(Strategy.HUMAN_STRATEGY+". Human");
				int input_num = new Human().inputOption(5, Name.START,this);
				if(input_num == Strategy.GREEDY_STRATEGY){
					player.setStrategy(new Greed());
				}else if(input_num == Strategy.UNFRIENDLY_STRATEGY){
					player.setStrategy(new Unfriendliness());
				}else if(input_num == Strategy.RANDOM_STRATEGY){
					player.setStrategy(new Random());
				}else if(input_num == Strategy.BASIC_STRATEGY){
					player.setStrategy(new Basic());
				}else if(input_num == Strategy.HUMAN_STRATEGY){
					player.setStrategy(new Human());
				}
			}
				
		}
	}
}
