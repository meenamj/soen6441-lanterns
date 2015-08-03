package project.strategy;

import java.util.Scanner;

public abstract class GreedyStrategy implements Strategy{
	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status)
	{
		Scanner inputscan = new Scanner(System.in);
		String in = null;
		boolean validation = false;
		do
		{
			if (in != null) 
			{
				System.out.println(in + " is not in the option");
			}
			in = inputscan.next();
			for (int i = 0; i < number_options; i++)
			{
				if (in.equals("" + i)) 
				{
					validation = true;
				}
			}
		}
		while (!validation);
		return Integer.parseInt(in);
	}

}
