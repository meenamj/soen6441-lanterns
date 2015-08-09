package project.disaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import project.DedicationToken;
import project.Game;
import project.Player;

public abstract class LightningStrikeDisaster implements Disaster{
	
	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = -1203306784472254204L;
	
	int chance = 0;
	/**
	 * This method set the chance of a Lightning strike disaster base on the number of player
	 * @param nplayer Number of player
	 */
	protected LightningStrikeDisaster(int nplayer) {
		if(nplayer==2)
		{
			chance = 10;
		}
		else if(nplayer==3)
		{
			chance = 7;
		}
		else
		{
			chance = 5;
		}
	}
	
	public boolean getDisaster(){
		Random random = new Random();
		int risk = random.nextInt(100);
		return chance>risk;
	}
	
	@Override
	public String attack(Game game){
		String text = "";
		ArrayList<Player> players = new ArrayList<Player>(game.getPlayers());
		int max_remove_dedication = 0;
		for(Player player : players){
			int num_dedication = player.getDedicationTokens().size();
			if(num_dedication>max_remove_dedication){
				max_remove_dedication = num_dedication;
			}
		}
		if(max_remove_dedication==0){
			return "Disaster has occurred, but no harm done";
		}
		Random random = new Random();
		ArrayList<DedicationToken> player_dedication = null;
		int num_remove_dedication = random.nextInt(max_remove_dedication)+1;
		int counter = 0;
		for(Player player : players){
			if(player.getDedicationTokens().size()>=num_remove_dedication){
				player_dedication=player.getDedicationTokens();
				if(random.nextBoolean()){
					counter++;
					Collections.shuffle(player_dedication);
					text += player.getName();
					text += " lost ";
					text += num_remove_dedication+" Dedication Tokens\n";
					for(int i = 0; i<num_remove_dedication; i++){
						text += (i+1)+" -"+player_dedication.get(0).getClass().getSimpleName();
						text += " with ";
						text += player_dedication.get(0).getHonor();
						text += " honors\n";
						player_dedication.remove(0);
					}
					text += "\n";
				}
			}
		}
		if(counter==0){
			attack(game);
		}else{
			System.out.println("number of players ::"+counter);
			System.out.println("number of lost dedication tokens ::"+ num_remove_dedication);
		}
		return "Lightning Strike is attacking players\n"+text;
	}

}
