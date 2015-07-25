package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

/**
 * Lake tile is a card to place to the game After that player can get lantern
 * cards following the direction of the placed lake tile.
 * 
 * @author
 */
public class LakeTile implements Serializable {

	/**
	 * index
	 */
	private int index;
	/**
	 * four colors of lake tile
	 */
	private Queue<Color> colorOfFourSides;
	/**
	 * rotation of lake tile on board (4 type: 0, 90 , 180 , 270 degree)
	 */
	private Rotation rotation;
	/**
	 * if four sides on the lake tile is close to the other lake tile
	 */
	private boolean[] isAvailablePosition;
	/**
	 * platform is used to get favor token
	 */
	private boolean hasPlatform;
	
	/**
	 * Constructor of a lake tile Generate a lake tile with random color for
	 * each sides
	 * 
	 * @param true for this lake tiles is a start lake tile
	 */
	public LakeTile(int index, Color c1, Color c2, Color c3, Color c4,
			boolean platform) {
		Queue<Color> cos = new LinkedList<Color>();
		this.index = index;
		isAvailablePosition = new boolean[]{true,true,true,true};
		// start laketile
		if (index == 27) {
			setRotation(Rotation.D270);
		}
		cos.add(c1);
		cos.add(c2);
		cos.add(c3);
		cos.add(c4);
		this.colorOfFourSides = cos;
		this.hasPlatform = platform;
	}

	/*
	 * Get four color of lake tiles
	 * 
	 * @return four colors of a lake tile
	 */
	public Queue<Color> getColorOfFourSides() {
		return colorOfFourSides;
	}

	/*
	 * Set four color of lake tiles
	 * 
	 * @param four colors of a lake tile
	 */
	public void setColorOfFourSides(Queue<Color> colorOfFourSides) {
		this.colorOfFourSides = colorOfFourSides;
	}

	public boolean isPlatform() {
		return hasPlatform;
	}

	public void setPlatform(boolean hasPlatform) {
		this.hasPlatform = hasPlatform;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// Build-2

	/**
	 * get all positions of a lake tiles, return values will be 'true' or
	 * 'false'
	 * 
	 * @return boolean of four positions
	 */
	public boolean[] getAvailablePosition() {
		return isAvailablePosition;
	}
	
	/**
	 * To get the rotation of a lake tile
	 * @return rotation value
	 */
	public Rotation getRotation() {
		return rotation;
	}

	/**
	 * To rotate a lake tile at some angle
	 * @param rotation angle of rotation
	 */
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	
	public void changeRotation(Rotation rotation) {
		if(rotation.equals(Rotation.D0)){
			
		}else if(rotation.equals(Rotation.D90)){
			colorOfFourSides.add(colorOfFourSides.remove());
		}else if(rotation.equals(Rotation.D180)){
			colorOfFourSides.add(colorOfFourSides.remove());
			colorOfFourSides.add(colorOfFourSides.remove());
		}else if(rotation.equals(Rotation.D270)){
			colorOfFourSides.add(colorOfFourSides.remove());
			colorOfFourSides.add(colorOfFourSides.remove());
			colorOfFourSides.add(colorOfFourSides.remove());
		}
	}
}
