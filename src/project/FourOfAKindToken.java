package project;
/**
 * Four of a kind token is one of dedication token
 * when players have four lantern cards which have the same color,
 * the player can exchange this card
 * @author Idris
 * @version 1.1
 */
public class FourOfAKindToken extends DedicationToken {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 1170053206011471035L;
	
	//This honorList is used for order the card in four of a kind token stack
	static final int[] honorList = { 4, 5, 5, 5, 6, 6, 7, 7, 8 };
	/**
	 * Contructor of Four of a kind token
	 * @param indexOfHonor the index of honor in four of a kind token stack
	 */
	public FourOfAKindToken(int indexOfHonor) {
		setHonor(honorList[indexOfHonor]);
	}
}
