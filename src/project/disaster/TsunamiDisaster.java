package project.disaster;

import java.util.Random;

public abstract class TsunamiDisaster implements Disaster{
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
