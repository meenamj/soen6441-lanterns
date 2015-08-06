package project.strategy;

import java.util.Random;
import java.util.Scanner;

import project.Game;

public abstract class RandomStrategy implements Strategy{
	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		Random r = new Random();
		//minus 3 to ignore 3 choices (exit, save and load game).
		if(status.equals(Name.MAINMENU))
			number_options-=3;
		int random_choose = r.nextInt(number_options);
		//not to choose choice 0
		if(status.equals(Name.MAINMENU))
			random_choose+=1;
		return random_choose;
	}
}
