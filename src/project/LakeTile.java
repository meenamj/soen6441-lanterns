package project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Lake tile is a card to place to the game
 * After that player can get lantern cards
 * following the direction of the placed lake tile.
 * @author Avneet
 */
public class LakeTile implements Serializable{
	/**
	 * four colors of lake tile
	 */
	private Vector<Color> colorOfFourSides;
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
			randomColor = Color.randomColor();
		} while (randomColor == Color.RED);

		return randomColor;
	}

	/*
	 * Constructor of a lake tile
	 * Generate a lake tile with random color for each sides
	 * @param true for this lake tiles is a start lake tile
	 */
	public LakeTile(boolean isStartLakeTile) {
		Vector<Color> cos = new Vector<Color>(0);
		if (isStartLakeTile) {
			cos.insertElementAt(Color.RED, 0);
			for (int i = 1; i < 4; i++) {
				cos.insertElementAt(getRandomColorNotRed(), i);
			}
		} else {
			setPlatform(random.nextBoolean());
			for (int i = 0; i < 4; i++) {
				cos.insertElementAt(getRandomColorNotRed(), i);
			}
		}
		setColorOfFourSides(cos);
	}

	/*
	 * Get four color of lake tiles
	 * @return four colors of a lake tile
	 */
	public Vector<Color> getColorOfFourSides() {
		return colorOfFourSides;
	}

	/*
	 * Set four color of lake tiles
	 * @param four colors of a lake tile
	 */
	public void setColorOfFourSides(Vector<Color> colorOfFourSides) {
		this.colorOfFourSides = colorOfFourSides;
	}

	public boolean isPlatform() {
		return hasPlatform;
	}

	public void setPlatform(boolean hasPlatform) {
		this.hasPlatform = hasPlatform;
	}
}
