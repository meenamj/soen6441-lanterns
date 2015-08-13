package project.exception;

/**
 * this exception happens when rotation does not exist
 *
 */
public class RotationNotExistedException extends Exception {
	
	/**
	 * It is used to keep the correct version
	 */
	private static final long serialVersionUID = 1873788941287743561L;

	/**
	 * Constructor of the class.
	 * @param string the degree of rotation or any messages
	 */
	public RotationNotExistedException(String string) {
		// TODO Auto-generated constructor stub
		super("This rotation ("+string+") does not exist");
	}
}
