package project.disaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import project.Game;
import project.LakeTile;
import project.Position;

public class PassingPowerBoatDisaster implements Disaster{
	int chance = 0;
	
	protected PassingPowerBoatDisaster(int nplayer) {
		chance = nplayer*10;
	}
	
	public boolean getDisaster(){
		Random random = new Random();
		int risk = random.nextInt(100);
		return risk<chance;		
	}
	
	@Override
	public String attack(Game game){
		String text = "";
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board.length; x++) {
				LakeTile laketile = board[x][y];
				if(laketile != null){
					int connect_laketile=4;
					if(board[x+1][y]==null)
						connect_laketile-=1;
					if(board[x][y+1]==null)
						connect_laketile-=1;
					if(board[x-1][y]==null)
						connect_laketile-=1;
					if(board[x+1][y-1]==null)
						connect_laketile-=1;
					if(connect_laketile==1){
						Position p = new Position(x,y);
						positions.add(p);
					}
				}
			}
		}
		Random random = new Random();
		int num_remove_laketile = random.nextInt(positions.size());
		Collections.shuffle(positions);
		for(int i=0; i<num_remove_laketile;i++){
			Position pos = positions.get(i);
			int x = pos.getX();
			int y = pos.getY();
			text += "("+x+","+y+") ";
			board[x][y] = null;
		}
		return "Passing Power Boat attacking on position\n"+text;
	}

}
