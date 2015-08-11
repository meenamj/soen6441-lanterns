package project.disaster;


import java.io.Serializable;


import project.Game;
/**
 * This class is an interface to all types of disaster
 * @author Nuttakit
 * @version 1.0
 */
public interface Disaster extends Serializable{
	public static final int TSUNAMI = 0;
	public static final int PASSING_BOAT = 1;
	public static final int LIGHTNING_STRIKE = 2;
	
	/**
	 * To compare the chance with random number.
	 * if the random number is greater than chance
	 * attack method will be used
	 * @return boolean
	 */
	public abstract boolean getDisaster();
	
	/**
	 * This method causes an attack on the current game played
	 * @param game the current game
	 * @return information of the result after disaster occurred
	 */
	public abstract String attack(Game game);
	
	/**
	 * To get the chance to use the attack method
	 * @return chance of disaster existance by percentage
	 */
	public abstract int getChancePercent();
}
