package project.disaster;

import java.io.Serializable;

import project.Game;

public interface Disaster extends Serializable{
	public static int TSUNAMI = 0;
	public static int PASSING_BOAT = 1;
	public static int LIGHTNING_STRIKE = 2;
	public boolean getDisaster();
	public String attack(Game game);
}
