package project.disaster;

import project.Game;

/**
 * This class implement the lighting strike disaster type
 * @author Meena
 * @version 1.0
 */

public abstract class LightningStrikeDisaster implements Disaster {

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -1203306784472254204L;

	private int chance_percent = 0;

	/**
	 * This method set the chance of a Lightning strike disaster base on the
	 * number of player
	 * 
	 * @param nplayer
	 *            Number of player
	 */
	protected LightningStrikeDisaster(int nplayer) {
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
