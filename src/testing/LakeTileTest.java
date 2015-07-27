package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Color;
import project.LakeTile;

public class LakeTileTest {

	@Test
	public void testStartLakeTile() {
		LakeTile lt = new LakeTile(-1, Color.BLACK, Color.BLUE, Color.RED, Color.WHITE, true);
		assertTrue(lt.getColorOfFourSides().contains(Color.RED));
		//assertTrue(!lt.isPlatform());
	}
	
	@Test
	public void testColorOfLakeTile() {
		LakeTile lt = new LakeTile(0,Color.BLACK,Color.RED, Color.ORANGE, Color.PURPLE ,false );
		assertEquals(4, lt.getColorOfFourSides().size());
	}
	
}

