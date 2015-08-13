package project;

import java.io.Serializable;
import java.util.*;

import project.exception.ColorNotExistedException;

/** 
 * This supply class will be used for seven lantern stacks creation.
 * @author Meenakshi
 */
public class Supply extends HashMap<Color, Stack<LanternCard>>implements Serializable{
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 8764536661432159523L;

	/**
	 * Constructor of supply
	 * this method is used to create number of each lantern card in the stacks related to number of players
	 * @param num_player the number of player
	 */
	public Supply(int num_player) {
		switch (num_player) {
		case 4:
			// for 4 players, add 8 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 8; i++) {
					s.add(new LanternCard(c));
				}
				put(c, s);
			}
			break;
		case 3:
			// for 3 players, add 7 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 7; i++) {
					s.add(new LanternCard(c));
				}
				put(c, s);
			}
			break;
		case 2:
			// for 2 players, add 5 lantern cards of each color into the stacks
			for (Color c : Color.values()) {
				Stack<LanternCard> s = new Stack<LanternCard>();
				for (int i = 0; i < 5; i++) {
					s.add(new LanternCard(c));
				}
				put(c, s);
			}
			break;
		default:
			System.out.print("Supply.java :: Error");
		}
	}
	
	
	/**
	 * Prints number of the lantern cards (with color)
	 * available of each color in the current supply stack
	 * @return number of each lantern stack in the supply
	 * @throws ColorNotExistedException if the color does not exist
	 */
	public String getNumberColorListText() throws ColorNotExistedException{
		String text = "";
		for (Map.Entry<Color, Stack<LanternCard>> c : this.entrySet()){
			text += Color.getColorText(c.getKey()," ");
			text += c.getValue().size();
			text += " ";
		}
		text += "\n";
		return text;
	}
	
	public boolean validate(int num_player){
		for(Color c : Color.values()){
			Stack<LanternCard> stack = this.get(c);
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
