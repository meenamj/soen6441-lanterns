package project.strategy;

import java.io.Serializable;

import project.Game;
/**
 * This class represent the interface to different types of player strategy
 * @author Nuttakit
 * @version 1.0
 */
public interface Strategy extends Serializable{
	public static int GREEDY_STRATEGY = 0;
	public static int UNFRIENDLY_STRATEGY = 1;
	public static int RANDOM_STRATEGY = 2;
	public static int BASIC_STRATEGY = 3;
	public static int HUMAN_STRATEGY = 4;
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
	public int inputOption(int number_options, Strategy.Name status, Game game);
}
