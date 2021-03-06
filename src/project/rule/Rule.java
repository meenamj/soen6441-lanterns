package project.rule;

import java.io.Serializable;

import project.Game;
/**
 * This class serves as an interface to different types of rule that can be applied to the game.
 * @author Meena
 * @version 3.0
 */
public interface Rule extends Serializable{
	final static int BASE_RULE = 0 ;
	final static int N_LAKE_TILES_ON_BOARD_RULE = 1;
	final static int N_HONOR_POINT_RULE = 2;
	
	/**
	 * This method enables the modification of the game rule.
	 * @param game the current game being played
	 * @return the rule selected
	 */
	public boolean rule(Game game);
}
