package project.disaster;

import java.util.Random;

public abstract class TsunamiDisaster implements Disaster{
	int chance = 0;
	protected TsunamiDisaster(int nplayer) {
		chance = nplayer*10;
	}
	public boolean getDisaster(){
		Random random = new Random();
		int risk = random.nextInt(100);
		return risk<chance;		
	}
}
