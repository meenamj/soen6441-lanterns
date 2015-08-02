package project;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;
/** 
 * This supply class will be used for seven lantern stacks creation
 * @author Meenakshi
 */
public class Supply implements Serializable{
	/**
	 * HashMap is used to keep all lantern stacks in supply
	 * color represents key and the value represents stack of LanternCard instances related to the key color
	 */
	private HashMap<Color, Stack<LanternCard>> lanternStacks = null;
	
	/**
	 * Constructor of supply
	 * this method is used to create number of each lantern card in the stacks related to number of players
	 * @param nPlayer the number of player
	 */
	public Supply(int num_player) {
		lanternStacks = new HashMap<Color, Stack<LanternCard>>();
		switch (num_player) {
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
	
	/**
	 * To fetch all the lantern cards from the supply
	 * @return lantern card stack
	 */
	public HashMap<Color, Stack<LanternCard>> getLanternStacks(){
		return lanternStacks;
	}
	
	/**
	 * to put lantern cards back in the supply
	 * @param lanternStacks lantern card stack to be added in the supply
	 */
	public void setLanternStacks(HashMap<Color, Stack<LanternCard>> lanternStacks){
		this.lanternStacks = lanternStacks;
	}
	
	/**
	 * Prints number of the lantern cards (with color)
	 * available of each color in the current supply stack
	 * @throws Exception if the color does not exist
	 */
	public void getNumberColorListText() throws Exception{
		for (Entry<Color, Stack<LanternCard>> c : lanternStacks.entrySet()){
			System.out.print(Color.getColorText(c.getKey()," ")+c.getValue().size()+" ");
		}
		System.out.println();
	}
	
	public boolean validate(int num_player){
		for(Color c : Color.values()){
			Stack<LanternCard> stack = lanternStacks.get(c);
			if(num_player==4){
				if(stack.size()>8){
					return false;
				}
			}
			if(num_player==3){
				if(stack.size()>7){
					return false;
				}
			}
			if(num_player==2){
				if(stack.size()>5){
					return false;
				}
			}
		}
		return true;
	}
}
