package project.rule;

import java.util.ArrayList;
import java.util.Queue;

import project.Game;
import project.Player;

/**
 * This class represent the honor point rule type and extends the abstract class of the Honor Point rule type.
 * @author Nirav
 * @version 3.0
 */

public class NHonorPoint extends NHonorPointRule{
	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = 1113739222228659334L;
	
	/**
	 * This method set the winner based on their honor point
	 * @param win_honor Winner honor point
	 */
	public NHonorPoint(int win_honor){
		setWinnerHonorValue(win_honor);
	}
	
	/**
	 * @param game the current game
	 * @return true if the game ends
	 */
	@Override
	public boolean rule(Game game) {
		//get current player
		Queue<Player> player_queue = game.getPlayers();
		ArrayList<Player> player_list = new ArrayList<Player>(player_queue);
		int num_laketile = 0;
		// to check the number laketile of all players
		for (Player player : player_list) {
			if(player.getLakeTiles().size()> num_laketile)
			{
				num_laketile = player.getLakeTiles().size();
			}
		}
		
		if(num_laketile==0){
			System.out.println("with n honor point rule");
			System.out.println("laketile hand is over");
			return true;
		}
		
		for(Player player:player_list){
			if(player.countHonorValue()>=getWinnerHonorValue()){
				System.out.println("with n honor point rule");
				return true;
			}
		}
		return false;
	}
}
