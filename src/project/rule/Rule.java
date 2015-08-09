package project.rule;

import java.io.Serializable;

import project.Game;

public interface Rule extends Serializable{
	public boolean rule(Game game);
}
