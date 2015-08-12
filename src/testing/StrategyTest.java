package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.strategy.Basic;
import project.strategy.Greed;
import project.strategy.Human;
import project.strategy.Random;
import project.strategy.Strategy;
import project.strategy.Unfriendliness;


public class StrategyTest {
	@Test
	public void testHumanStrategyNotNull() {
		Strategy human = new Human();
		assertNotNull(human);
	}
	@Test
	public void testGreedyStrategyNotNull() {
		Strategy greed = new Greed();
		assertNotNull(greed);
	}
	@Test
	public void testUnfriendlyStrategyNotNull() {
		Strategy unfriendliness = new Unfriendliness();
		assertNotNull(unfriendliness);
	}
	@Test
	public void testRandomStrategyNotNull() {
		Strategy random = new Random();
		assertNotNull(random);
	}
	@Test
	public void testBasicStrategyNotNull(){
		Strategy basic = new Basic();
		assertNotNull(basic);
	}
}
