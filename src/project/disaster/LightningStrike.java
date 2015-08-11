package project.disaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import project.DedicationToken;
import project.Game;
import project.Player;

/**
 * This class extends the Lightning strike abstract class
 * @author Meena
 * @version 1.0
 *
 */

public class LightningStrike extends LightningStrikeDisaster{
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -6501053886209432961L;
	/**
	 * constructor of lightningStrike
	 * @param nplayer number of player in the game
	 */
	public LightningStrike(int nplayer) {
		super(nplayer);
		// TODO Auto-generated constructor stub
	}
	/**
	 * to check if disaster is occurred or not
	 * @return boolean get disaster or not get disaster in the game
	 */
	public boolean getDisaster() {
		Random random = new Random();
		int risk = random.nextInt(100);
		return getChancePercent() > risk;
	}

	/**
	 * this attack used to remove the dedication tokens on player hand randomly
	 * @param game the current game
	 * @return the dedication information of the disaster on the player hand
	 */
	@Override
	public String attack(Game game) {
		String text = "";
		ArrayList<Player> players = new ArrayList<Player>(game.getPlayers());
		int max_remove_dedication = 0;
		for (Player player : players) {
			int num_dedication = player.getDedicationTokens().size();
			if (num_dedication > max_remove_dedication) {
				max_remove_dedication = num_dedication;
			}
		}
		if (max_remove_dedication == 0) {
			text += "but no harm done";
		} else {
			Random random = new Random();
			ArrayList<DedicationToken> player_dedication = null;
			int num_remove_dedication = random.nextInt(max_remove_dedication) + 1;
			int counter = 0;
			for (Player player : players) {
				if (player.getDedicationTokens().size() >= num_remove_dedication) {
					player_dedication = player.getDedicationTokens();
					if (random.nextBoolean()) {
						counter++;
						Collections.shuffle(player_dedication);
						text += player.getName();
						text += " lost ";
						text += num_remove_dedication + " Dedication Tokens\n";
						for (int i = 0; i < num_remove_dedication; i++) {
							text += (i + 1)
									+ " -"
									+ player_dedication.get(0).getClass()
											.getSimpleName();
							text += " with ";
							text += player_dedication.get(0).getHonor();
							text += " honors\n";
							player_dedication.remove(0);
						}
						text += "\n";
					}
				}
			}
			if (counter == 0) {
				attack(game);
			} else {
				System.out.println("number of players ::" + counter);
				System.out.println("number of lost dedication tokens ::"
						+ num_remove_dedication);
			}
		}
		return "Lightning Strike is trying to attack players\n" + text;
	}

}
