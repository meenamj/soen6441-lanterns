package project.strategy;

import java.util.Scanner;

import project.Game;



public abstract class HumanStrategy implements Strategy{
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -5446700812827463498L;
	Scanner inputscan;
	
	public HumanStrategy(){
		inputscan = new Scanner(System.in);
	}
	
	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
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
		String in = null;
		boolean validation = false;
		do
		{
			if (in != null) 
			{
				System.out.println(in + " is not in the option");
			}
			in = inputscan.next();
			for (int i = start_options; i < end_options; i++)
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
		return inputscan.next();
	}
}
