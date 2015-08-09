package project;

import java.io.Serializable;
/**
 * Lantern card is a card which player can get after placed lake tile
 * @author Meenakshi
 * @version 1.1
 */
public class LanternCard implements Serializable{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -3356057491692327091L;
	/**
	 * color of lantern card
	 */
	private Color c;
	/**
	 * Constructor of lantern card
	 * @param c color of lantern card
	 */
	public LanternCard(Color c){
		setColor(c);
	}
	
	/**
	 * Set color of lantern card
	 * @param c color of lantern card
	 */
	public void setColor(Color c){
		this.c = c;
	}
	
	/**
	 * Get color of lantern card
	 * @return lantern card
	 */
	public Color getColor(){
		return c;
	}
	
	
}
