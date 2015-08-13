package project.exception;

/**
 * This exception happens when color does not exist.
 */
public class ColorNotExistedException extends Exception {

	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = 5818804403065858938L;

	/**
	 * Constructor of the class.
	 * @param string color name
	 */
	public ColorNotExistedException(String string) {
		// TODO Auto-generated constructor stub
		super("This color ("+string+") does not exist");
	}
}
