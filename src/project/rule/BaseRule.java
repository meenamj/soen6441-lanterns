package project.rule;

import project.Game;
import project.Player;


public abstract class BaseRule implements Rule{
	@Override
	public boolean rule(Game game) {
		//get current player
		boolean is_winner = false;
		Player p = game.getPlayers().element();
		int num_laketile = p.getLakeTiles().size();
		if(num_laketile==0){
			System.out.println("with base rule");
			is_winner = true;
		}
		return is_winner;
	}
}
