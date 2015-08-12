package project.exception;

/**
 * this exception happens when number players not between 2-4
 */
public class NoNumberPlayersException extends Exception {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -7932736642754824635L;

	/**
	 * 
	 * @param num_player number of players 
	 */
	public NoNumberPlayersException(int num_player) {
		// TODO Auto-generated constructor stub
		super("This number of players ("+num_player+") does not exist");
	}
}
