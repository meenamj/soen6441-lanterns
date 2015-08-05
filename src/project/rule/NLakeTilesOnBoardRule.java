package project.rule;

import project.Game;
import project.Player;

public abstract class NLakeTilesOnBoardRule implements Rule{
	int final_round;
	
	@Override
	public boolean rule(Game game) {
		//get current player
		int num_player = game.getPlayers().size();
		int start_num_laketiles = 0;
		int current_num_laketiles = game.getPlayArea().getLakeTiles().size();

		if(num_player == 2){
			start_num_laketiles=20;
		}else if(num_player ==3){
			start_num_laketiles=18;
		}else{
			start_num_laketiles=16;
		}
		int spend_num_laketiles = (start_num_laketiles-current_num_laketiles);
		return num_player*final_round==spend_num_laketiles;
	}
}
