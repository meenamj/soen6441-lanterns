package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.Color;
import project.Supply;

public class SupplyTest {
	private int nplayer;
	private Supply s;
	@Test
	public void testSetupForFourPlayers() {
		nplayer = 4;
		s = new Supply(nplayer);
		assertEquals("Color black is not 8",8,s.getLanternStack().get(Color.BLACK).size());
		assertEquals("Color blue is not 8",8,s.getLanternStack().get(Color.BLUE).size());
		assertEquals("Color green is not 8",8,s.getLanternStack().get(Color.GREEN).size());
		assertEquals("Color orange is not 8",8,s.getLanternStack().get(Color.ORANGE).size());
		assertEquals("Color purple is not 8",8,s.getLanternStack().get(Color.PURPLE).size());
		assertEquals("Color red is not 8",8,s.getLanternStack().get(Color.RED).size());
		assertEquals("Color white is not 8",8,s.getLanternStack().get(Color.WHITE).size());
	}
	
	public void testSetupForThreePlayers() {
		nplayer = 3;
		s = new Supply(nplayer);
		assertEquals("Color black is not 7",7,s.getLanternStack().get(Color.BLACK).size());
		assertEquals("Color blue is not 7",7,s.getLanternStack().get(Color.BLUE).size());
		assertEquals("Color green is not 7",7,s.getLanternStack().get(Color.GREEN).size());
		assertEquals("Color orange is not 7",7,s.getLanternStack().get(Color.ORANGE).size());
		assertEquals("Color purple is not 7",7,s.getLanternStack().get(Color.PURPLE).size());
		assertEquals("Color red is not 7",7,s.getLanternStack().get(Color.RED).size());
		assertEquals("Color white is not 7",7,s.getLanternStack().get(Color.WHITE).size());
	}

	public void testSetupForTwoPlayers() {
		nplayer = 2;
		s = new Supply(nplayer);
		assertEquals("Color black is not 5",5,s.getLanternStack().get(Color.BLACK).size());
		assertEquals("Color blue is not 5",5,s.getLanternStack().get(Color.BLUE).size());
		assertEquals("Color green is not 5",5,s.getLanternStack().get(Color.GREEN).size());
		assertEquals("Color orange is not 5",5,s.getLanternStack().get(Color.ORANGE).size());
		assertEquals("Color purple is not 5",5,s.getLanternStack().get(Color.PURPLE).size());
		assertEquals("Color red is not 5",5,s.getLanternStack().get(Color.RED).size());
		assertEquals("Color white is not 5",5,s.getLanternStack().get(Color.WHITE).size());
	}

}
