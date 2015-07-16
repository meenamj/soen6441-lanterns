package testing;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import project.Color;
import project.Game;
import project.GameFile;

public class GameFileTest {
	private Game gSave,gLoad;
	private File f = null;
	@Before public void setUp() throws Exception{
		gSave = new Game("Player1","Player2");
		gSave.startGame();
	}
	
	@Test
	public void testSave() throws Exception {
		GameFile.save(gSave, "test.txt");
		f = new File("test.txt");
		assertTrue(f.exists()&& !f.isDirectory());
		f.delete();
	}
	
	@Test
	public void testLoad(){
		GameFile.save(gSave, "test.txt");
		gLoad = GameFile.load("test.txt");
		assertEquals("Player1", gLoad.getPlayers().get(0).getName());
	}

}
