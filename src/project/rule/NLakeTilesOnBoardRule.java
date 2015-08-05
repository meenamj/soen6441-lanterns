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
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		int num_lake_board = 0;
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if(board[i][j]!=null){
					num_lake_board+=1;
				}
			}
		}
		int start_laketile = 1;
		return num_player*final_round==(num_lake_board-start_laketile);
	}
	
	public void setFinalRound(int final_round){
		this.final_round = final_round;
	}
}
