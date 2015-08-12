package project;

import java.util.*;

import project.exception.ColorNotExistedException;

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
	public static Color random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
	/**
	 * ANSI escapes sequence plug-in for eclipse to print the color on the console,
	 * this method get the color name in string format and return the ANSI color code for that color
	 * @param c color value
	 * @param text a text value need to be printed inside the color block
	 * @return ANSI value of the color
	 * @throws ColorNotExistedException if the color does not exist
	 */
	public static String getColorText(Color c, String text) throws ColorNotExistedException{
		if(c.equals(Color.ORANGE)){ 
			return "\u001b[1;37m\u001b[43m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.GREEN)){
			return "\u001b[1;37m\u001b[42m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.PURPLE)){
			return "\u001b[1;37m\u001b[45m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.WHITE)){
			return "\u001b[1;37m\u001b[47m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.BLUE)){
			return "\u001b[1;37m\u001b[44m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.RED)){
			return "\u001b[1;37m\u001b[41m"+text+"\u001b[47m\u001b[0m";
		}else if(c.equals(Color.BLACK)){
			return "\u001b[1;37m\u001b[40m"+text+"\u001b[47m\u001b[0m";
		}else{
			throw new ColorNotExistedException("this color does not exist");
		}
	}
}
