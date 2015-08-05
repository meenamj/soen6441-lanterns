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
		return r.nextInt(number_options);
	}
}
