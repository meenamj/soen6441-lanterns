package project.rule;

public class NHonorPoint extends NHonorPointRule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 1113739222228659334L;

	public NHonorPoint(int win_honor){
		setWinnerHonor(win_honor);
	}
}
