package project;

/**
 * Position class is used to represent position of lake tiles with all information
 * on the game board based on x and y coordinates and to get and set position of lake tiles on the board 
 * @author n_bhut
 *
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * Constructor of position class
	 * @param x x-coordinate on the board
	 * @param y y-coordinate on the board
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y =y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getText(){
		return "("+x+","+y+")";
	}
}
