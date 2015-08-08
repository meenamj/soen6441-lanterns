package project.disaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import project.DedicationToken;
import project.Game;
import project.Player;

public class LightningStrikeDisaster implements Disaster{
	
	int chance = 0;
	
	protected LightningStrikeDisaster(int nplayer) {
		if(nplayer==2)
		{
			chance = 20;
		}
		else if(nplayer==3)
		{
			chance = 15;
		}
		else
		{
			chance = 10;
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
		if(max_remove_dedication==0)return "";
		Random random = new Random();
		ArrayList<DedicationToken> player_dedication = null;
		int num_remove_dedication = random.nextInt(max_remove_dedication);
		for(Player player : players){
			if(player.getDedicationTokens().size()>=num_remove_dedication){
				player_dedication=player.getDedicationTokens();
				if(random.nextBoolean()){
					Collections.shuffle(player_dedication);
					text += player.getName();
					text += " lost ";
					for(int i = 0; i<num_remove_dedication; i++){
						System.out.println("num_remove_dedication::"+num_remove_dedication);
						System.out.println("player_dedication.size()::"+player_dedication.size());
						text += player_dedication.get(0).getClass().getSimpleName();
						text += " with ";
						text += player_dedication.get(0).getHonor();
						text += " honors ";
						player_dedication.remove(0);
					}
					text += "\n";
				}
			}
		}
		return "Lightning Strike is attacking players\n"+text;
	}

}
