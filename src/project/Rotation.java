package project;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	public static Rotation getRotation(int rotation) throws Exception{
		if (rotation==0){
			return D0;
		}else if(rotation==90){
			return D90;
		}else if(rotation==180){
			return D180;
		}else if(rotation==270){
			return D270;
		}else{
			throw new Exception("not available for this degree");
		}
	}
}
