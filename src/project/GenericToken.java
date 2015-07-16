package project;
/**
 * Generic Token is one of dedication token
 * when players can change the four of a kind, three pair or seven unique token
 * ,but there is empty in the stack, the player can exchange this card
 * @author Idris
 * @version 1.0
 */
public class GenericToken extends DedicationToken {
	/**
	 * Contructor of generic token
	 */
	public GenericToken() {
		this.setHonor(4);
	}
}
