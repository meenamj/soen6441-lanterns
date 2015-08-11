package project.rule;

import project.Game;

/**
 * This class serves as an abstract class for base rule
 * @author Nuttakit
 * @version 1.0
 */
public abstract class BaseRule implements Rule{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 4245433488393564238L;
	public abstract boolean rule(Game game);
}
