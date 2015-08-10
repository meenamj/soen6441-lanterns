package project.rule;

import java.io.Serializable;

import project.Game;
/**
 * This class serves as an interface to different types of rule that can be applied to the game
 * @author Meena
 * @version 1.0
 */
public interface Rule extends Serializable{
	/**
	 * This method enables the modification of the game rule
	 * @param game the current game being played
	 * @return the rule selected
	 */
	public boolean rule(Game game);
}
