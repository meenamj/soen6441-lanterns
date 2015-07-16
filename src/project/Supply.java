package project;

import java.io.Serializable;
import java.util.*;
/** 
 * This supply class will be used for seven lantern stacks creation
 * @author Meenakshi
 */
public class Supply implements Serializable{
	/**
	 * hashmap is used to keep all lantern stacks in supply
	 */
	public HashMap<Color, Stack<LanternCard>> lanternStacks = null;
	
	/**
	 * Constructor of supply
	 * this method is used to create number of each lantern card in the stacks related to number of players
	 * @param nPlayer the number of player
	 */
	public Supply(int nPlayer) {
		lanternStacks = new HashMap<Color, Stack<LanternCard>>();
		switch (nPlayer) {
		case 4:
			// for 4 players, add 8 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 8; i++) {
					s.add(new LanternCard(c));
				}
				lanternStacks.put(c, s);
			}
			break;
		case 3:
			// for 3 players, add 7 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 7; i++) {
					s.add(new LanternCard(c));
				}
				lanternStacks.put(c, s);
			}
			break;
		case 2:
			// for 2 players, add 5 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 5; i++) {
					s.add(new LanternCard(c));
				}
				lanternStacks.put(c, s);
			}
			break;
		default:
			System.out.print("Supply.java :: Error");
		}
	}
}