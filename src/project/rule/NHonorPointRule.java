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
		for(Player player:player_list){
			if(player.countHonorValue()>=win_honor){
				return true;
			}
		}
		return false;
	}
	
	public void setWinnerHonor(int win_honor){
		this.win_honor = win_honor;
	}
}
