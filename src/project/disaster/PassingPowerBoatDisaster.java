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
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		Random r = new Random();
		int number_laketile_onboard = countLakeTileOnBoard(board);
		System.out.println("number_laketile_onboard ::" +number_laketile_onboard);
		if(number_laketile_onboard==0)
		{
			return "";
		}
		else
		{
			int number_remove_laketile = r.nextInt(number_laketile_onboard)+1;
			for(int i=0; i<number_remove_laketile;i++){
				text += removeALakeTile(game);
			}
			return "Passing Power Boat attacking on position "+ text +"\n";
		}
	}
	
	
	private String removeALakeTile(Game game){
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		ArrayList<Position> positions = new ArrayList<Position>();
		Random random = new Random();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				LakeTile laketile = board[x][y];
				if(laketile != null){
					int connect_laketile=4;
					if(x+1<board.length&&board[x+1][y]==null)
						connect_laketile-=1;
					if(y+1<board.length&&board[x][y+1]==null)
						connect_laketile-=1;
					if(x-1>=0&&board[x-1][y]==null)
						connect_laketile-=1;
					if(y-1>=0&&board[x][y-1]==null)
						connect_laketile-=1;
					if(connect_laketile==1){
						Position p = new Position(x,y);
						positions.add(p);
					}
				}
			}
		}
		if(countLakeTileOnBoard(board)!=0&&positions.size()==0){
			positions = getAllLakeTilePositionOnBoard(board);
		}
		Collections.shuffle(positions);
		Position pos = positions.get(0);
		int x = pos.getX();
		int y = pos.getY();
		board[x][y] = null;
		return "("+x+","+y+") ";
	}
	

	private int countLakeTileOnBoard(LakeTile[][] board){
		int count = 0;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if(board[x][y]!=null)
					count+=1;
			}
		}
		return count;
	}
	
	private ArrayList<Position> getAllLakeTilePositionOnBoard(LakeTile[][] board){
		ArrayList<Position> all_laketile_position = new ArrayList<Position>();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if(board[x][y]!=null)
				{
					Position position = new Position(x,y);
					all_laketile_position.add(position);
				}
			}
		}
		return all_laketile_position;
	}
}
