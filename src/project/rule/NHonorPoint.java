package project.rule;
/**
 * This class represent the honor point rule type and extends the abstract class of the Honor Point rule type
 * @author Nirav
 * @version 1.0
 */

public class NHonorPoint extends NHonorPointRule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 1113739222228659334L;
	/**
	 * This method set the winner based on their honor point
	 * @param win_honor Winner honor point
	 */
	public NHonorPoint(int win_honor){
		setWinnerHonor(win_honor);
	}
}
