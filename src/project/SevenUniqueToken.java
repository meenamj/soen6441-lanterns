package project;

import java.io.Serializable;

/**
 * Seven unique token is one of dedication token
 * when players have seven lantern cards which have the all different color,
 * the player can exchange this card
 * @author Idris
 * @version 1.0
 */
public class SevenUniqueToken extends DedicationToken {
	//This honorList is used for order the card in seven unique token stack
	static final int[] honorList = { 5, 6, 7, 7, 8, 8, 9, 9, 10 };
	/**
	 * Contructor of seven unique token
	 * @param indexOfHonor the index of honor in seven unique token stack
	 */
	public SevenUniqueToken(int indexOfHonor) {
		setHonor(honorList[indexOfHonor]);
	}
}
