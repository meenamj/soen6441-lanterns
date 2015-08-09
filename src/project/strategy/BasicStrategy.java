package project.strategy;

import java.util.Random;

import project.Game;
import project.Player;

public abstract class BasicStrategy implements Strategy{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -5287120180128710069L;

	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		int input = 0;
		Random r = new Random();
		if(status.equals(Name.MAINMENU)){
			Player current_player= game.getPlayers().element();
			if(current_player.getLanternCards().size()>11){
				input = r.nextInt(2)+1;
			}else{
				input = 3;
			}
		}else if(status.equals(Name.MAKE_DEDICATION)){
			input = r.nextInt(number_options);
		}else if(status.equals(Name.CHOOSE_LANTERN_HAND)){
			input = r.nextInt(number_options);
		}else if(status.equals(Name.CHOOSE_LANTERN_SUPPLY)){
			input = r.nextInt(number_options);
		}
		System.out.println(input);
		return input;
	}

}
