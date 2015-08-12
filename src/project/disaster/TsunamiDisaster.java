package project.disaster;

import java.util.Random;

import project.Game;
/**
 * This class implement the Tsunami disaster type
 * @author Jide
 *
 */

public abstract class TsunamiDisaster implements Disaster{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -8342469732431843801L;
	int chance_percent = 0;
	
	/**
	 * This method set the chance of a Tsunami disaster base on the number of player
	 * @param nplayer Number of player
	 */
	protected TsunamiDisaster(int nplayer) {
		if(nplayer==2)
		{
			chance_percent = 10;
		}
		else if(nplayer==3)
		{
			chance_percent = 7;
		}
		else
		{
			chance_percent = 5;
		}
		
	}
	
	/**
	 * to check if disaster is occurred or not
	 * @return boolean get disaster or not get disaster in the game
	 */
	public boolean getDisaster() {
		Random random = new Random();
		int risk = random.nextInt(100);
		return getChancePercent() > risk;
	}
	
	public int getChancePercent(){
		return chance_percent;
	}
	
	public abstract String attack(Game game);
}
