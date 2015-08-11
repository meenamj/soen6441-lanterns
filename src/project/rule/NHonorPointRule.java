package project.rule;

import project.Game;

/**
 * This class is an abstract class for the honor point rule type.
 * @author Nirav
 * @version 1.0
 */

public abstract class NHonorPointRule implements Rule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -7160378198238322039L;
	private int win_honor=0;

	/**
	 * This method set the winner based on their honor point
	 * @param win_honor Winner honor point
	 */
	public void setWinnerHonorValue(int win_honor){
		this.win_honor = win_honor;
	}
	
	/**
	 * This method get the winner based on their honor point
	 * @return win_honor Winner honor point
	 */
	public int getWinnerHonorValue(){
		return win_honor;
	}
	
	public abstract boolean rule(Game game);
}
