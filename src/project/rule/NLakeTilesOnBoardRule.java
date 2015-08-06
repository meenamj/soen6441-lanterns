package project.rule;

import project.Game;
import project.LakeTile;
import project.Player;

public abstract class NLakeTilesOnBoardRule implements Rule{
	private int final_round;
	
	@Override
	public boolean rule(Game game) {
		//get current player
		int num_player = game.getPlayers().size();
		int current_laketile_stack = game.getPlayArea().getLakeTiles().size();
		int start_laketile_stack = 0;
		if(num_player==4){
			start_laketile_stack = 20;
		}else if(num_player==3){
			start_laketile_stack = 18;
		}else if(num_player==2){
			start_laketile_stack = 16;
		}
		int num_draw = start_laketile_stack-current_laketile_stack;
		//plus 1 because first round players do not draw stack
		int current_round = (num_draw/num_player)+1;
		
		return current_round==final_round;
	}
	
	public void setFinalRound(int final_round){
		this.final_round = final_round;
	}
}
