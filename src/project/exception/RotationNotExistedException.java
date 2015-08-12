package project.exception;

/**
 * this exception happens when rotation does not exist
 *
 */
public class RotationNotExistedException extends Exception {
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 1873788941287743561L;

	public RotationNotExistedException(String string) {
		// TODO Auto-generated constructor stub
		super("This rotation ("+string+") does not exist");
	}
}
