package project.rule;

import project.Game;
/**
 * This is an abstract class for the Number of Lake Tile rule.
 * @author Idris
 * @version 1.0
 */

public abstract class NLakeTilesOnBoardRule implements Rule{
	
	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = 3183260809817926291L;
	private int final_round;
	
	/**
	 * This method set the final round of the game based on the number of lake tile played.
	 * @param final_round final round of the game
	 */
	public void setFinalRound(int final_round){
		this.final_round = final_round;
	}
	
	/**
	 * This method get the final round of the game based on the number of lake tile played.
	 * @return final round of the game
	 */
	public int getFinalRound(){
		return final_round;
	}
	
	public abstract boolean rule(Game game);
}
