package project;
/**
 * Three pair token is one of dedication token
 * when players have three pair lantern cards,
 * the player can exchange this card
 * @author Idris	
 * @version 1.0
 */
public class ThreePairToken extends DedicationToken {
	
	/**
	 * used for order the card in three pair token stack
	 */
	static final int[] honorList = { 5, 5, 6, 6, 7, 7, 8, 8, 9 };
	/**
	 * Contructor of three pair token
	 * @param indexOfHonor the index of honor in three pair token stack
	 */
	public ThreePairToken(int indexOfHonor) {
		setHonor(honorList[indexOfHonor]);
	}
}