package project.rule;

import java.util.ArrayList;
import java.util.Queue;

import project.Game;
import project.Player;

public abstract class NHonorPointRule implements Rule{
	private int win_honor=0;
	@Override
	public boolean rule(Game game) {
		//get current player
		Queue<Player> player_queue = game.getPlayers();
		ArrayList<Player> player_list = new ArrayList<Player>(player_queue);
		int laketile_stack_size = game.getPlayArea().getLakeTiles().size();
		if(laketile_stack_size==0){
			System.out.println("with n honor point rule");
			System.out.println("laketile stack is over");
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
