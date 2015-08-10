package project.rule;
/**
 * This class represent the Number of Lake Tile rule type.
 * @author Idris
 * @version 1.0
 *
 */
public class NLakeTilesOnBoard extends NLakeTilesOnBoardRule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 157377633433786705L;

	/**
	 * This method set the final round of the game based on the number of lake tile played
	 * @param final_round final round of the game
	 */
	public NLakeTilesOnBoard(int final_round){
		setFinalRound(final_round);
	}
}
