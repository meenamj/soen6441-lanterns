package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import project.*;
import project.disaster.Disaster;
import project.disaster.LightningStrike;
import project.disaster.PassingPowerBoat;
import project.disaster.Tsunami;
import project.rule.*;
import project.strategy.*;

public class DisasterTest {
	Game game = null;
	
	@Before
	public void setUp() {
		String player0 = "Player0";
		String player1 = "Player1";
		String player2 = "Player2";
		String player3 = "Player3";
		String[] players = new String[4];
		players[0] = player0;
		players[1] = player1;
		players[2] = player2;
		players[3] = player3;
		int strategy0 = Strategy.RANDOM_STRATEGY;
		int strategy1 = Strategy.RANDOM_STRATEGY;
		int strategy2 = Strategy.RANDOM_STRATEGY;
		int strategy3 = Strategy.RANDOM_STRATEGY;
		int[] strategies = new int[4];
		strategies[0] = strategy0;
		strategies[1] = strategy1;
		strategies[2] = strategy2;
		strategies[3] = strategy3;
		Rule rule = new Base();
		Disaster tsunami = new Tsunami(players.length);
		Disaster passing_boat = new PassingPowerBoat(players.length);
		Disaster lightning_strike = new LightningStrike(players.length);
		ArrayList<Disaster> disasters = new ArrayList<Disaster>();
		disasters.add(tsunami);
		disasters.add(passing_boat);
		disasters.add(lightning_strike);
		
		try {
			game = new Game(players, strategies, rule, disasters);
			game.getPlayArea().getLakeTilesOnBoard();
			LakeTile l1 = new LakeTile(0, Color.GREEN, Color.BLUE, Color.BLACK, Color.RED, false);
			LakeTile l2 = new LakeTile(1, Color.WHITE, Color.BLUE, Color.WHITE, Color.ORANGE, false);
			LakeTile l3 = new LakeTile(3, Color.BLUE, Color.GREEN, Color.BLACK, Color.PURPLE, false);
			PlayArea play_area = game.getPlayArea();
			LakeTile[][] board = play_area.getLakeTilesOnBoard();
			board[31][32] = l1;
			board[30][32] = l2;
			board[32][31] = l3;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testNotNullTsunami(){
		Tsunami tsunami = (Tsunami)game.getDisasters().get(Disaster.TSUNAMI);
		assertNotNull(tsunami);
	}
	
	@Test
	public void testTsunami(){
		PlayArea play_area = game.getPlayArea();
		assertEquals("Number of Laketile on board should be 4", 4, play_area.getNumberLakeTileOnBoard());
		Disaster tsunami = game.getDisasters().get(Disaster.TSUNAMI);
		tsunami.attack(game);
		assertEquals("Number of Laketile on board should be 0", 0, play_area.getNumberLakeTileOnBoard());
	}
	
	@Test
	public void testNotNullPassingPowerBoat(){
		PassingPowerBoat passing_boat = (PassingPowerBoat) game.getDisasters().get(Disaster.PASSING_BOAT);
		assertNotNull(passing_boat);
	}
	
	@Test
	public void testPassingPowerBoat(){
		PlayArea play_area = game.getPlayArea();
		assertEquals("Number of Laketile on board should be 4", 4, play_area.getNumberLakeTileOnBoard());
		Disaster passing_boat = game.getDisasters().get(Disaster.PASSING_BOAT);
		passing_boat.attack(game);
		int number_laketile_after_disaster = play_area.getNumberLakeTileOnBoard();
		assertTrue("Number of Laketile on board should not be 4", number_laketile_after_disaster!=4);
	}
	
	@Test
	public void testNotNullLightningStrike(){
		LightningStrike lightning_strike = (LightningStrike) game.getDisasters().get(Disaster.LIGHTNING_STRIKE);
		assertNotNull(lightning_strike);
	}
	
	@Test
	public void testLightningStrike(){
		FourOfAKindToken four_8honor = new FourOfAKindToken(8);
		SevenUniqueToken seven_10honor = new SevenUniqueToken(8);
		ThreePairToken three_9honor = new ThreePairToken(8);
		Queue<Player> players = game.getPlayers();
		Player player0 = players.remove();
		ArrayList<DedicationToken> dedicationList = new ArrayList<DedicationToken>();
		dedicationList.add(four_8honor);
		dedicationList.add(seven_10honor);
		dedicationList.add(three_9honor);
		player0.setDedicationTokens(dedicationList);
		players.add(player0);
		
		Player player1 = players.remove();
		dedicationList = new ArrayList<DedicationToken>();
		dedicationList.add(four_8honor);
		dedicationList.add(seven_10honor);
		dedicationList.add(three_9honor);
		player1.setDedicationTokens(dedicationList);
		players.add(player1);
		
		Player player2 = players.remove();
		dedicationList = new ArrayList<DedicationToken>();
		dedicationList.add(four_8honor);
		dedicationList.add(seven_10honor);
		dedicationList.add(three_9honor);
		player2.setDedicationTokens(dedicationList);
		players.add(player2);
		
		Player player3 = players.remove();
		dedicationList = new ArrayList<DedicationToken>();
		dedicationList.add(four_8honor);
		dedi)cationList.add(seven_10honor);
		dedicationList.add(three_9honor);
		player3.setDedicationTokens(dedicationList);
		players.add(player3);
		
		Disaster lightning_strike = game.getDisasters().get(Disaster.LIGHTNING_STRIKE);
		lightning_strike.attack(game);
		int token_player0 = player0.getDedicationTokens().size();
		int token_player1 = player1.getDedicationTokens().size();
		int token_player2 = player2.getDedicationTokens().size();
		int token_player3 = player3.getDedicationTokens().size();
		int total_token = token_player0+token_player1+token_player2+token_player3;
		assertTrue("the total dedication tokens should not have 12",total_token!=12);
	}
}
