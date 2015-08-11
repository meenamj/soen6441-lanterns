package project.disaster;

import project.Game;

/**
 * This class implement the passing power boat disaster type
 * @author Nuttakit
 * @version 1.0
 */
public abstract class PassingPowerBoatDisaster implements Disaster {
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 2298762894237856734L;
	
	private int chance_percent = 0;
	/**
	 * This method set the chance of a power boat disaster base on the number of
	 * player
	 * 
	 * @param nplayer
	 *            Number of player
	 */
	protected PassingPowerBoatDisaster(int nplayer) {
		if (nplayer == 2) {
			chance_percent = 10;
		} else if (nplayer == 3) {
			chance_percent = 7;
		} else {
			chance_percent = 5;
		}
	}
	
	public int getChancePercent(){
		return chance_percent;
	}
	
	public abstract String attack(Game game);
}
