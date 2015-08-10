package project.strategy;

import java.util.Scanner;

import project.Game;

/**
 * This class represent the abstract class of the Human strategy player type
 * @author Idris
 * @version 1.0
 */

public abstract class HumanStrategy implements Strategy{
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -5446700812827463498L;
	
	/**
	 * scanner is used to get input option
	 * postscript :: scanner class is not implements serialize
	 * transient is used for prevent this case
	 */
	transient Scanner inputscan;
	
	/**
	 * This method determine the strategy of the player in a game and what option they have selected
	 * @param number_options option selected by player
	 * @param status what strategy is currently being used by player
	 * @param game The current game played
	 * @return The option selected by player
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		inputscan = new Scanner(System.in);
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
	
	public int inputOption(int start_options, int end_options)
	{
		inputscan = new Scanner(System.in);
		String in = null;
		boolean validation = false;
		do
		{
			if (in != null) 
			{
				System.out.println(in + " is not in the option");
			}
			in = inputscan.next();
			for (int i = start_options; i <= end_options; i++)
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
	
	public boolean inputYesNo(){
		inputscan = new Scanner(System.in);
		String in = null;
		do{
			if(in !=null){
				System.out.println(in+" is not the option");
			}
			in = inputscan.next();
			in = in.toUpperCase();
		}while(!in.equals("Y")&&!in.equals("N"));
		if(in.equals("Y")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String inputString(){
		inputscan = new Scanner(System.in);
		return inputscan.next();
	}
}
