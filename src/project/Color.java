package project;

import java.util.*;

/**
 * This class is used to show the color of lantern cards, lake tiles for playing the game
 * @author avneet
 * @version 1.0
 */

public enum Color {
	ORANGE, GREEN, PURPLE, WHITE, BLUE, RED, BLACK;
	/**
	 * This list is used when created random color
	 * with an unmodifiable view of the specific collection
	 */
	private static final List<Color> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));
	/**
	 * size of color
	 */
	private static final int SIZE = VALUES.size();
	/**
	 * this object is used to random the color 
	 */
	private static final Random RANDOM = new Random();
	
	/**
	 * random color of lake tiles
	 * @return Color 
	 */
	public static Color randomColor() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
