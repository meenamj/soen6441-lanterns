package project.disaster;

import java.util.Random;

public abstract class TsunamiDisaster implements Disaster{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -8342469732431843801L;
	int chance = 0;
	
	/**
	 * This method set the chance of a Tsunami disaster base on the number of player
	 * @param nplayer Number of player
	 */
	protected TsunamiDisaster(int nplayer) {
		if(nplayer==2)
		{
			chance = 10;
		}
		else if(nplayer==3)
		{
			chance = 7;
		}
		else
		{
			chance = 5;
		}
		
	}
	
	public boolean getDisaster(){
		Random random = new Random();
		int risk = random.nextInt(100);
		return chance>risk;
	}
}
