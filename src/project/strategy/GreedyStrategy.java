package project.strategy;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import project.Game;
import project.PlayArea;
import project.Player;
import project.Position;

public abstract class GreedyStrategy implements Strategy{
	ArrayList<Integer> solution = new ArrayList<Integer>();
	/**
	 * This method check the input the user provides
	 * @param n_option user input
	 * @return integer value of the option selected
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		Game gameClone = game.clone();
		
		Queue<Player> players = gameClone.getPlayers();
		
		int in = 0;
		boolean validation = false;
		do
		{
			for (int i = 0; i < number_options; i++)
			{
				if (in ==  i)
				{
					validation = true;
					if(status == Name.MAINMENU){
						//PickALakeTile(players);
						if(canExchange(players)){
							//in = 1;
						}
						if(canMakeDedication(players)){
							in = 2;
						}
						else{
							try {
								//checkLakeTilewithAvailable( players,pa);
								solution = simulateGamePlay(game);
								in = 3;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					else if(status == Name.MAKE_DEDICATION){
						int choice = whichDedication(players);
						in = choice;
					}
					else if(status == Name.SELECT_LAKE){
						in =  solution.get(0);
					}
					else if(status == Name.SELECT_BOARD_POSITION){
						in = solution.get(1);
					}
					else if(status == Name.SELECT_LAKE_ROTATION){
						in = solution.get(2);
					}
				}
			}
		}
		while (!validation);
		System.out.println(in);
		return in;
	
	}
	
	//protected abstract void PickALakeTile(Queue<Player> players);
	//protected abstract Position checkLakeTilewithAvailable (Queue<Player> player, PlayArea playarea) throws Exception;
	protected abstract ArrayList<Integer> simulateGamePlay(Game game) throws Exception;
	protected abstract boolean canMakeDedication(Queue<Player> players);
	protected abstract int whichDedication(Queue<Player> players);
	protected abstract boolean canExchange(Queue<Player> players);

}
