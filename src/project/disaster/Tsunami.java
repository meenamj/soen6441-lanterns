package project.disaster;

import project.Game;
import project.LakeTile;

/**
 * This class extends the Tsunami abstract class
 * @author Avneet
 * @version 1.0
 *
 */

public class Tsunami extends TsunamiDisaster{

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 1939659623614440133L;

	/**
	 * constractor of passing power boat
	 * @param nplayer the number of player in the game
	 */
	public Tsunami(int nplayer) {
		super(nplayer);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * this attack used to remove all lake tiles on board 
	 * @param game the current game
	 * @return the dedication information of the disaster on the player hand
	 */
	@Override
	public String attack(Game game){
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}
		return "Tsunami is attacking our Lake Tiles on Board";
	}
}
