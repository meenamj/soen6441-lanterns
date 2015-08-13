package project.strategy;

import java.util.ArrayList;
import java.util.Queue;

import project.Game;
import project.Player;

/**
 * This class represent the abstract class for the unfriendly player strategy class.<br>
 * the unfriendly player always makes a move which will bring the most harm to one <br>
 * or more opponents (i.e. wherever possible, always place a Lake Tile in such a <br>
 * way as to minimize bonuses going to other players, and/or attempt to prevent <br>
 * other players from collecting the Lantern Cards they need to make a dedication).
 * @author Nirav
 * @version 3.0
 */
public abstract class UnfriendlyStrategy implements Strategy{
	/**
	 * It is used to keep the correct version.
	 */
	private static final long serialVersionUID = -2977512618052097832L;
	
	/**
	 * Solution contains values of inputs selected after selecting a option to place
	 * a lake tile.
	 */
	private ArrayList<Integer> solution = new ArrayList<Integer>();
	
	/**
	 * ExchangeOptions contains values to be selected as a player lantern card, <br>
	 * and a card from a supply lantern card stack to replace with.
	 */
	private int[] exchangeOptions = new int[2];
	
	/**
	 * This method determine the strategy of the player in a game <br>
	 * and what option they have selected.
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
	 * This method is to check for all possible solutions, and pick the best one to <br>
	 * place a lake tile, which can earn the greedy player, maximum lantern cards, <br>
	 * and favor tokens.
	 * @param game instance of the game class
	 * @return best solution to place a lake tile
	 */
	protected abstract ArrayList<Integer> simulateGamePlay(Game game);
	
	/**
	 * This method is to check if a player can make a dedication, based on the number <br>
	 * of lantern cards.
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
	 * This method is to check if a player can exchange a lantern card in order to <br>
	 * perform a dedication.
	 * @param players list of players in the game
	 * @param game clone instance of game class
	 * @return true if exchange is possible, false otherwise
	 */
	protected abstract boolean canExchangeLanternCard(Queue<Player> players, Game game);
	
	/**
	 * This method is to perform exchange and get the lantern cards a player wants <br>
	 * to make a dedication
	 * @param player current player
	 * @param game clone instance of game class
	 * @return inputs need to be provided in order to perform an exchange
	 */
	protected abstract int[] performExchange(Player player,Game game);
}
