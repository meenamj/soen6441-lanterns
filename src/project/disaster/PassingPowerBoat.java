package project.disaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Random;

import project.Color;
import project.Game;
import project.LakeTile;
import project.Position;

/**
 * This class extends the Passing power boat abstract class
 * @author Nuttakit
 * @version 1.0
 *
 */
public class PassingPowerBoat extends PassingPowerBoatDisaster{

	/**
	 * it is used to keep the correct version
	 */
	private static final long serialVersionUID = 6545653312985307225L;

	/**
	 * constractor of passing power boat
	 * @param nplayer the number of player in the game
	 */
	public PassingPowerBoat(int nplayer) {
		super(nplayer);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * the list of removed laketiles on board
	 */
	private ArrayList<LakeTile> removed_laketile;
	/**
	 * the list of positions that laketiles are removed on board
	 */
	private ArrayList<Position> removed_position;

	/**
	 * this attack used to remove some lake tiles on board randomly
	 * without gap between them
	 * @param game the current game
	 * @return the dedication information of the disaster on the player hand
	 */
	@Override
	public String attack(Game game) {
		String text = "";
		removed_laketile = new ArrayList<LakeTile>();
		removed_position = new ArrayList<Position>();
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		Random r = new Random();
		int number_laketile_onboard = countLakeTileOnBoard(board);
		if (number_laketile_onboard == 0) {
			return "Passing Power Boat attacking but there is nothing on the board";
		} else {
			int number_remove_laketile = r.nextInt(number_laketile_onboard) + 1;
			for (int i = 0; i < number_remove_laketile; i++) {
				removeALakeTile(game);
			}
			text += showText();
			return "Passing Power Boat attacking on position ::\n" + text + "\n";
		}
	}

	/**
	 * This method remove lake tile after a disaster and make sure there are no
	 * gap in lake tiles on board
	 * 
	 * @param game
	 *            The current game
	 * @return The positions of lake tiles to be remove
	 * @throws Exception
	 */
	private void removeALakeTile(Game game) {
		LakeTile[][] board = game.getPlayArea().getLakeTilesOnBoard();
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				LakeTile laketile = board[x][y];
				if (laketile != null) {
					int connect_laketile = 4;
					if (x + 1 < board.length && board[x + 1][y] == null)
						connect_laketile -= 1;
					if (y + 1 < board.length && board[x][y + 1] == null)
						connect_laketile -= 1;
					if (x - 1 >= 0 && board[x - 1][y] == null)
						connect_laketile -= 1;
					if (y - 1 >= 0 && board[x][y - 1] == null)
						connect_laketile -= 1;
					if (connect_laketile == 1) {
						Position p = new Position(x, y);
						positions.add(p);
					}
				}
			}
		}
		if (countLakeTileOnBoard(board) != 0 && positions.size() == 0) {
			positions = getAllLakeTilePositionOnBoard(board);
		}
		Collections.shuffle(positions);
		Position pos = positions.get(0);
		int x = pos.getX();
		int y = pos.getY();
		removed_laketile.add(board[x][y]);
		removed_position.add(pos);
		board[x][y] = null;
	}

	/**
	 * This method counts the number of lake tile on the board
	 * 
	 * @param board
	 *            The board containing lake tiles
	 * @return The number of lake tiles on the board
	 */
	private int countLakeTileOnBoard(LakeTile[][] board) {
		int count = 0;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[x][y] != null)
					count += 1;
			}
		}
		return count;
	}

	/**
	 * This method get all the lake tile position on the board
	 * 
	 * @param board
	 *            The containing the player lake tile
	 * @return the position of the lake tiles
	 */
	private ArrayList<Position> getAllLakeTilePositionOnBoard(LakeTile[][] board) {
		ArrayList<Position> all_laketile_position = new ArrayList<Position>();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[x][y] != null) {
					Position position = new Position(x, y);
					all_laketile_position.add(position);
				}
			}
		}
		return all_laketile_position;
	}

	/**
	 * the show the information of removed lake tiles on board by text
	 * @return text of removed lake tiles
	 */
	private String showText() {
		String text = "";
		try {
			String line1 = "";
			String line2 = "";
			String line3 = "";
			for (LakeTile lake_tile : removed_laketile) {
				int i = removed_laketile.indexOf(lake_tile);
				Position pos = removed_position.get(i);
				Queue<Color> color_queue = lake_tile.getColorOfFourSides();
				ArrayList<Color> color_list = new ArrayList<Color>(color_queue);

				line1 += String.format("%12s", "");
				line1 += Color.getColorText(color_list.get(0), " ");

				line1 += String.format("%4s", "");
				
				
				line2 += String.format("%2s", lake_tile.getIndex());
				line2 += ":";
				line2 += String.format("%-7s","(" + pos.getX() + "," + pos.getY() + ")");
				line2 += Color.getColorText(color_list.get(3), " ");
				line2 += " ";
				if (lake_tile.isPlatform()) {
					line2 += "O";
				} else {
					line2 += "X";
				}
				line2 += " " + Color.getColorText(color_list.get(1), " ") + "  ";

				line3 += String.format("%12s", "");
				line3 += Color.getColorText(color_list.get(2), " ");
				line3 += String.format("%4s", "");

			}
			text += line1 + "\n";
			text += line2 + "\n";
			text += line3 + "\n";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			text = "No Data";
			e.printStackTrace();
		}
		return text;

	}

}
