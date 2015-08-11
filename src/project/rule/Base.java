package project.rule;

import project.Game;
import project.Player;

/**
 * This class extends base rule abstract class
 * @author Nuttakit
 * @version 1.0
 */

public class Base extends BaseRule{

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -5800234720766555002L;

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
