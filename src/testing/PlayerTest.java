package testing;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import project.Color;
import project.FourOfAKindToken;
import project.LanternCard;
import project.Player;

public class PlayerTest {
	private Player player;

	@Before
	public void setUp() {
		player = new Player("Tester1");
	}
	
	@Test
	public void testCountHonorValue(){
		FourOfAKindToken four = new FourOfAKindToken(0);
		player.getDedicationTokens().add(four);
		assertEquals(4,player.countHonorValue());
	}
}
