package project.disaster;

import java.util.Random;

public abstract class TsunamiDisaster implements Disaster{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -8342469732431843801L;
	int chance = 0;
	protected TsunamiDisaster(int nplayer) {
		if(nplayer==2)
		{
			chance = 20;
		}
		else if(nplayer==3)
		{
			chance = 15;
		}
		else
		{
			chance = 10;
		}
		
	}
	
	public boolean getDisaster(){
		Random random = new Random();
		int risk = random.nextInt(100);
		return chance>risk;
	}
}
