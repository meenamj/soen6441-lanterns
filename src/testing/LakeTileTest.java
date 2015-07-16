package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Color;
import project.LakeTile;

public class LakeTileTest {

	@Test
	public void testStartLakeTile() {
		LakeTile lt = new LakeTile(true, -1);
		assertTrue(lt.getColorOfFourSides().contains(Color.RED));
		assertTrue(!lt.isPlatform());
	}
	
	@Test
	public void testColorOfLakeTile() {
		LakeTile lt = new LakeTile(false, 0);
		assertEquals(4, lt.getColorOfFourSides().size());
	}
	
}
