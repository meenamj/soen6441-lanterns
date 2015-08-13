package project.strategy;

import java.util.Scanner;

/**
 * This class is a concrete class for Human strategy player type.
 * @author None
 * @version 3.0
 */
public class Human extends HumanStrategy{

	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = -2966957739254565623L;
	/**
	 * This method is to get options players can select.
	 * @param start_options start number of option which players can select.
	 * @param end_options last number option which players can select.
	 * @return The option selected by player.
	 */
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
	/**
	 * This method is to get options players can select.
	 * @return true/false depends on questions.
	 */
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
	/**
	 * This method is for the players to put input string.
	 * @return text of input.
	 */
	public String inputString(){
		inputscan = new Scanner(System.in);
		return inputscan.next();
	}
}
