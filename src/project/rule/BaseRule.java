package project.rule;

import project.Game;
import project.Player;


public abstract class BaseRule implements Rule{
	@Override
	public boolean rule(Game game) {
		//get current player
		Player p = game.getPlayers().element();
		int num_laketile = p.getLakeTiles().size();
		return num_laketile==0;
	}
}
