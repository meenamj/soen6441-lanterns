package project.strategy;

import java.util.Queue;
import java.util.Scanner;

import project.Game;
import project.PlayArea;
import project.Player;
import project.Position;

public abstract class GreedyStrategy implements Strategy{
	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		Queue players = game.getPlayers();
		PlayArea pa = game.getPlayArea();
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
					if(status == Name.MAINMENU){
						//PickALakeTile(players);
						try {
							//checkLakeTilewithAvailable( players,pa);
							simulateGamePlay( players,pa,game);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		while (!validation);
		return Integer.parseInt(in);
	
	}
	
	protected abstract void PickALakeTile(Queue<Player> players);
	protected abstract Position checkLakeTilewithAvailable (Queue<Player> player, PlayArea playarea) throws Exception;
	protected abstract void simulateGamePlay(Queue<Player> player, PlayArea playarea,Game game) throws Exception;

}
