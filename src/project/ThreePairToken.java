package project;
/**
 * Three pair token is one of dedication token
 * when players have three pair lantern cards,
 * the player can exchange this card.
 * @author Idris
 * @version 1.1
 */
public class ThreePairToken extends DedicationToken {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -1143854564213189338L;
	/**
	 * HONOR_LIST is used for order the card in three pair token stack
	 */
	static final int[] HONOR_LIST = { 5, 5, 6, 6, 7, 7, 8, 8, 9 };
	/**
	 * Constructor of three pair token
	 * @param indexOfHonor the index of honor in three pair token stack
	 */
	public ThreePairToken(int indexOfHonor) {
		setHonor(HONOR_LIST[indexOfHonor]);
	}
}