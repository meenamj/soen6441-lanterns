package project.strategy;

import java.util.ArrayList;
import java.util.Queue;

import project.Game;
import project.Player;
import project.exception.RotationNotExistedException;
/**
 * This class represent the abstract class for greedy player strategy class, <br>
 * the greedy player always makes the move which will bring the best immediate <br> 
 * return (i.e. always try to place a Lake Tile which will help arrange a <br>
 * dedication, and always make a dedication as soon as possible). 
 * @author Nirav
 * 
 */
public abstract class GreedyStrategy implements Strategy{
	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = 8668713217327493214L;
	private ArrayList<Integer> solution = new ArrayList<Integer>();
	private int[] exchangeOptions = new int[2];
	
	/**
	 * This method determine the strategy of the player in a game and what option they have selected.
	 * @param number_options option selected by player
	 * @param status what strategy is currently being used by player
	 * @param game The current game played
	 * @return The option selected by player
	 */
	public int inputOption(int number_options, Strategy.Name status, Game game)
	{
		Game gameClone = game.clone();
		
		Queue<Player> players = gameClone.getPlayers();
		ArrayList<Player> playerList = new ArrayList<Player>(players);
        Player player = playerList.get(0);
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
						
						if(canMakeDedication(players)){
							in = 2;
						}
						else if(canExchangeLanternCard(players, gameClone)){
							exchangeOptions = performExchange(player,gameClone);
							in = 1;
						}
						else {
							try {
								
								solution = simulateGamePlay(game);
								in = 3;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					else if(status == Name.CHOOSE_LANTERN_HAND){
						in = exchangeOptions[0];
					}
					else if(status == Name.CHOOSE_LANTERN_SUPPLY){
						in = exchangeOptions[1];
					}
					else if(status == Name.MAKE_DEDICATION){
						int choice = selectDedication(players);
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
	
	/**
	 * This method is to get the best greedy player's solution, <br>
	 * which can earn, maximum lantern cards, and favor tokens, <br>
	 * by checking for all possible solutions to place a lake tile. 
	 * @param game instance of the game class
	 * @return best solution to place a lake tile
	 * @throws RotationNotExistedException occurs if rotation not exist
	 */
	protected abstract ArrayList<Integer> simulateGamePlay(Game game) throws RotationNotExistedException;
	
	/**
	 * This method is to check if a player can make a dedication, <br>
	 * based on the number of lantern cards.
	 * @param players list of all the players
	 * @return true if dedication is possible to make, false otherwise
	 */
	protected abstract boolean canMakeDedication(Queue<Player> players);
	
	/**
	 * This method is to select which dedication to make.
	 * @param players
	 * @return choice selected 
	 */
	protected abstract int selectDedication(Queue<Player> players);
	
	/**
	 * This method is to check if a player can exchange a lantern card <br>
	 * in order to perform a dedication.
	 * @param players list of players in the game
	 * @param game clone instance of game class
	 * @return true if exchange is possible, false otherwise
	 */
	protected abstract boolean canExchangeLanternCard(Queue<Player> players, Game game);
	
	/**
	 * This method is to perform exchange and get the lantern cards a player wants <br>
	 * to make a dedication.
	 * @param player current player
	 * @param game clone instance of game class
	 * @return inputs need to be provided in order to perform an exchange
	 */
	protected abstract int[] performExchange(Player player,Game game);
}
