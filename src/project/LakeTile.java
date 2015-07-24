package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

/**
 * Lake tile is a card to place to the game
 * After that player can get lantern cards
 * following the direction of the placed lake tile.
 * @author 
 */
public class LakeTile implements Serializable{
	
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
	private boolean[] position;
	/**
	 * platform is used to get favor token
	 */
	private boolean hasPlatform;
	/**
	 * random is used to random the red color which is only used in the getRandomColorNotRed method 
	 */
	private Random random = new Random();

	/**
	 * Get random color which is not red
	 * @return a color which is not red
	 */
	private Color getRandomColorNotRed() {
		Color randomColor;
		do {
			randomColor = Color.random();
		} while (randomColor == Color.RED);
		return randomColor;
	}
	
	/*
	 * Constructor of a lake tile
	 * Generate a lake tile with random color for each sides
	 * @param true for this lake tiles is a start lake tile
	 */
	public LakeTile(int index, Color c1, Color c2, Color c3, Color c4, boolean platform) {
		Queue<Color> cos = new LinkedList<Color>();
		this.index = index;
		//start laketile
		if(index == 27){
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
	 * @return four colors of a lake tile
	 */
	public Queue<Color> getColorOfFourSides() {
		return colorOfFourSides;
	}

	/*
	 * Set four color of lake tiles
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
	
	
	//Build-2 
	
	
	/**
	 * get all positions of a lake tiles, return values will be 'true' or 'false'
	 * @return boolean of top position 
	 */
	public boolean getTopPosition(){
		return position[0];
	}
	public boolean getLeftPosition(){
		return position[1];
	}
	public boolean getBottomPosition(){
		return position[2];
	}
	public boolean getRightPosition(){
		return position[3];
	}

	public Rotation getRotation() {
		return rotation;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}	
}