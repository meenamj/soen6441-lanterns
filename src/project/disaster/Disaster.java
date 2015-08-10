package project.disaster;


import java.io.Serializable;


import project.Game;
/**
 * This class is an interface to all types of disaster
 * @author Nuttakit
 * @version 1.0
 *
 */
public interface Disaster extends Serializable{
	public static int TSUNAMI = 0;
	public static int PASSING_BOAT = 1;
	public static int LIGHTNING_STRIKE = 2;
	public boolean getDisaster();
	/**
	 * This method causes an attack on the current game played
	 * @param game
	 * @return current game
	 */
	public String attack(Game game);
}
