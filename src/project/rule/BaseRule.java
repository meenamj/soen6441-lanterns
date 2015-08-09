package project.rule;

import project.Game;
import project.Player;


public abstract class BaseRule implements Rule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 4245433488393564238L;

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
