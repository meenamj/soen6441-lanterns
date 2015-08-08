package project.disaster;

import java.io.Serializable;

import project.Game;

public interface Disaster extends Serializable{
	public boolean getDisaster();
	public String attack(Game game);
}
