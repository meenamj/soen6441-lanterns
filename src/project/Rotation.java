package project;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import project.exception.RotationNotExistedException;
/**
 * This class is used to show the rotation of lake tiles on the game board.
 */
public enum Rotation {
	D0, D90, D180, D270;
	/**
	 * This list is used when created random color
	 * with an unmodifiable view of the specific collection
	 */
	private static final List<Rotation> VALUES = Collections
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
	public static Rotation random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
	/**
	 * To get the rotation value for the specified degree value
	 * @param rotation represents degree value in integer form
	 * @return required rotation value
	 * @throws RotationNotExistedException if the rotation is not from the degree list [0,90,180.270]
	 */
	public static Rotation getRotation(int rotation) throws RotationNotExistedException{
		if (rotation==0){
			return D0;
		}else if(rotation==90){
			return D90;
		}else if(rotation==180){
			return D180;
		}else if(rotation==270){
			return D270;
		}else{
			throw new RotationNotExistedException("Degree "+rotation);
		}
	}
}
