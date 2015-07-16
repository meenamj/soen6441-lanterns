package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Game;

public class GameTest {

	@Test
	public void testTwoPlayersInGame() throws Exception {
		Game g = new Game("Player1","Player2");
		g.startGame();
		assertEquals("not 2 Players in the game ", 2, g.getPlayers().size());
	}
	
	@Test
	public void testThreePlayersInGame() throws Exception {
		Game g = new Game("Player1","Player2","Player3");
		g.startGame();
		assertEquals("not 3 Players in the game ", 3, g.getPlayers().size());
	}
	
	@Test
	public void testFourPlayersInGame() throws Exception {
		Game g = new Game("Player1","Player2","Player3","Player4");
		g.startGame();
		assertEquals("not 4 Players in the game ", 4, g.getPlayers().size());
	}
}
