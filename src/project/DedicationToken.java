package project;

import java.io.Serializable;
/**
 * Dedication Token is a card to get the score
 * Player can change from the lantern card to dedication token
 * @author Idris
 * @version 1.1
 */

public class DedicationToken implements Serializable {
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 6535529241661589629L;
	//dots list used to remove the dedication tokens when there are 2-3 players
	static final int[] dotsList = {0,0,0,3,0,3,0,4,0};
	//the score of the game
	private int honor;
	//the number of dots in dedication tokens
	private int dots;
	/**
	 * Set honor
	 * @param honor the value of the dedication token
	 */
	public void setHonor(int honor){
		this.honor = honor;
	}
	/**
	 * Get Honor
	 * @return honor the value of the dedication token
	 */
	public int getHonor(){
		return this.honor;
	}
	/**
	 * Get Dots
	 * @return the number of dots
	 */
	public int getDots() {
		return dots;
	}
	/**
	 * Set the number of dots
	 * @param dots the number of dots
	 */
	public void setDots(int dots) {
		this.dots = dots;
	}
}