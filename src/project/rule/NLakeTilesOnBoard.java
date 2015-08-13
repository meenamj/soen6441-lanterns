package project.rule;

import project.Game;

/**
 * This class represent the Number of Lake Tile rule type.
 * @author Idris
 * @version 1.0
 *
 */
public class NLakeTilesOnBoard extends NLakeTilesOnBoardRule{
	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = 157377633433786705L;

	@Override
	public boolean rule(Game game) {
		//get current player
		boolean is_winner = false;
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
		if(current_round>=getFinalRound()){
			System.out.println("with n lake tile on board rule");
			is_winner = true;
			try {
				System.out.println(game.getPlayArea().getLakeTileBoardText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return is_winner;
	}
	
	/**
	 * This method set the final round of the game based on the number of lake tile played
	 * @param final_round final round of the game
	 */
	public NLakeTilesOnBoard(int final_round){
		setFinalRound(final_round);
	}
}
