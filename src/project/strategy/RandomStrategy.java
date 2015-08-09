package project.strategy;

import java.util.Random;

import project.Game;
import project.Player;

public abstract class RandomStrategy implements Strategy{
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -6960493685653641656L;

	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		Player player = game.getPlayers().element();
		Random r = new Random();
		//minus 3 to ignore 3 choices (exit, save and load game).
		//if lantern card more than 12 choose make dedication or exchange lantern
		if(status.equals(Name.MAINMENU))
			if(player.getLanternCards().size()>12){
				number_options = 2;
			}else{
				number_options-=3;
			}
		int random_choose = r.nextInt(number_options);
		//plus 1 to ignore choice 0
		if(status.equals(Name.MAINMENU))
			random_choose+=1;
		System.out.println(random_choose);
		return random_choose;
	}
}
