package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
/**
 * this class is used to manage the game file\
 * 
 * @author Nuttakit
 * @version 1.1
 */
public final class GameFile {
	/**
	 * @param game the state of the game
	 * @param filename the file of the saved game
	 */
	public static void save(Game game, String filename) {

		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.println(filename + " has been saved");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	/**
	 * Load a state of game from text file
	 * @param filename the String of file name
	 * @return the game object
	 */
	public static Game load(String filename) {
		Game game = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			game = (Game) in.readObject();
			in.close();
			fileIn.close();
			System.out.println(filename + " has been loaded");
			return game;
		} catch (IOException i) {
			System.out.println("file not found");
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Game class not found");
			c.printStackTrace();
			return null;
		}

	}
	
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.println(":: Developer Console ::");
		System.out.println("input file name ::");
		String filename = scanner.next();
		Game game = load(filename);
		System.out.println("set number of favor token on play area ::");
		String favor_token = scanner.next();
		game.getPlayArea().setNumberOfFavorTokens(Integer.parseInt(favor_token));
		System.out.println(":: Saved ::");
		save(game, filename);
	}
}
