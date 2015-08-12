package project.strategy;

import java.io.Serializable;

import project.Game;

/**
 * According to strategy pattern, base details are encapsulated in the interface, and 
 * implementation details are buried in the different player strategy classes
 */
public interface Strategy extends Serializable{
	public static int GREEDY_STRATEGY = 0;
	public static int UNFRIENDLY_STRATEGY = 1;
	public static int RANDOM_STRATEGY = 2;
	public static int BASIC_STRATEGY = 3;
	public static int HUMAN_STRATEGY = 4;
	
	/**
	 * Name enumeration represents different menu choices
	 * START represents the menu where we select player types
	 * MAINMENU represents main options available for each player where they get their turn
	 * CHOOSE_LANTERN_HAND represents options to pick a lantern card from player stack while performing 
	 * 						a lantern card exchange
	 * CHOOSE_LANTERN_SUPPLY represents options to pick a lantern card from supply stack while performing
	 * 						a lantern card exchange
	 * MAKE_DEDICATION represents options to select a dedication a player wants to make
	 * SELECT_LAKE represents options while selecting a lake tile to place from a player hand
	 * SELECT_BOARD_POSITION represents options to select a position on the board to place a lake tile
	 * SELECT_LAKE_ROTATION represents options to select a rotation of the lake tile 
	 * 						before placing it on the board
	 *
	 */
	public enum Name{
		START, MAINMENU, CHOOSE_LANTERN_HAND, CHOOSE_LANTERN_SUPPLY
		, MAKE_DEDICATION, SELECT_LAKE, SELECT_BOARD_POSITION, SELECT_LAKE_ROTATION
	}
	/**
	 * This method determine the strategy of the player in a game and what option they have selected
	 * @param number_options option selected by player
	 * @param status what strategy is currently being used by player
	 * @param game The current game played
	 * @return The option selected by player
	 */
	public abstract int inputOption(int number_options, Strategy.Name status, Game game);
}
