package project.exception;

/**
 * this exception happens when color does not exist
 */
public class ColorNotExistedException extends Exception {

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 5818804403065858938L;

	/**
	 * 
	 * @param string color name
	 */
	public ColorNotExistedException(String string) {
		// TODO Auto-generated constructor stub
		super("This color ("+string+") does not exist");
	}
}
