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
		
	@Test
	public void testgetNumberOfLanternCardsOnHand() throws Exception {
				
		String[] playersNames = new String[] {"One", "Two", "Three"};
		Game g = new Game(playersNames);
		int actual = g.getNumberOfLanternCardsOnHand();
		int expected = 1;
		assertEquals(expected, actual);
	
	}
		
	}

