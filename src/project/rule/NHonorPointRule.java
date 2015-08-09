package project.rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import project.Game;
import project.Player;

public abstract class NHonorPointRule implements Rule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -7160378198238322039L;
	private int win_honor=0;
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
			if(player.countHonorValue()>=win_honor){
				System.out.println("with n honor point rule");
				return true;
			}
		}
		return false;
	}
	
	public void setWinnerHonor(int win_honor){
		this.win_honor = win_honor;
	}
}
