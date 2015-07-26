package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import project.Color;
import project.FourOfAKindToken;
import project.LakeTile;
import project.LanternCard;
import project.PlayArea;
import project.Player;

public class PlayAreaTest {
	private Player py1,py2,py3,py4;
	private Queue<Player> fourPyList,threePyList,twoPyList;
	@Before
	 public void setUp(){
	  py1 = new Player("Tester1");
	  py2 = new Player("Tester2");
	  py3 = new Player("Tester3");
	  py4 = new Player("Tester4");
	  
	  py1.getLanternCards().add(new LanternCard(Color.RED));
	  py1.getLanternCards().add(new LanternCard(Color.RED));
	  py1.getLanternCards().add(new LanternCard(Color.RED));
	  py1.getLanternCards().add(new LanternCard(Color.RED));
	  
	  py2.getLanternCards().add(new LanternCard(Color.BLUE));
	  py2.getLanternCards().add(new LanternCard(Color.BLUE));
	  py2.getLanternCards().add(new LanternCard(Color.BLUE));

	  py3.getLanternCards().add(new LanternCard(Color.BLUE));
	  py3.getLanternCards().add(new LanternCard(Color.WHITE));
	  py3.getLanternCards().add(new LanternCard(Color.BLACK));
	  py3.getLanternCards().add(new LanternCard(Color.GREEN));
	  py3.getLanternCards().add(new LanternCard(Color.ORANGE));
	  py3.getLanternCards().add(new LanternCard(Color.RED));
	  py3.getLanternCards().add(new LanternCard(Color.PURPLE));
	  
	  py4.getLanternCards().add(new LanternCard(Color.RED));
	  py4.getLanternCards().add(new LanternCard(Color.RED));
	  py4.getLanternCards().add(new LanternCard(Color.BLUE));
	  py4.getLanternCards().add(new LanternCard(Color.BLUE));
	  py4.getLanternCards().add(new LanternCard(Color.PURPLE));
	  py4.getLanternCards().add(new LanternCard(Color.PURPLE));
	  
	  fourPyList = new LinkedList<Player>();
	  fourPyList.add(py1);
	  fourPyList.add(py2);
	  fourPyList.add(py3);
	  fourPyList.add(py4);
	  
	  threePyList = new LinkedList<Player>();
	  threePyList.add(py1);
	  threePyList.add(py2);
	  threePyList.add(py3);
	  
	  
	  twoPyList = new LinkedList<Player>();
	  twoPyList.add(py1);
	  twoPyList.add(py2); 
	}
	
	@Test
	public void testSetupForTwoPlayers() {
		PlayArea pa = new PlayArea(twoPyList);
		assertEquals("For two players, the amount of four of a kind tokens stack are not 6",6,pa.getFourOfAKindTokens().size());
		assertEquals("For two players, the amount of three pair tokens stack are not 6",6,pa.getThreePairTokens().size());
		assertEquals("For two players, the amount of seven unique tokens stack are not 6",6,pa.getSevenUniqueTokens().size());
		assertEquals("For two players, the amount of generic tokens stack are not 3",3 ,pa.getGenericTokens().size());
		Queue<Color> start_laketile_color = pa.getStartLakeTile().getColorOfFourSides();
		assertEquals("For two players, the amount of start lake tiles sides are not 4",4 , start_laketile_color.size());
		assertTrue("For two players, all sides of start laketile color is not Red", start_laketile_color.contains(Color.RED));
		assertEquals("For two players, the amount of lake tile stack before giving to players are not 22 but "+pa.getLakeTiles().size(),22 ,pa.getLakeTiles().size());
		assertEquals("For two players, the amount of favor token pile are not 20",20 ,pa.getNumberOfFavorTokens());
		
		assertEquals(8,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(7,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(6,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(4,pa.getFourOfAKindTokens().pop().getHonor());
		
		assertEquals(9,pa.getThreePairTokens().pop().getHonor());
		assertEquals(8,pa.getThreePairTokens().pop().getHonor());
		assertEquals(7,pa.getThreePairTokens().pop().getHonor());
		assertEquals(6,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		
		assertEquals(10,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(9,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(8,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(7,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(6,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(5,pa.getSevenUniqueTokens().pop().getHonor());
	}
	
	@Test
	public void testSetupForThreePlayers() {
		PlayArea pa = new PlayArea(threePyList);
		assertEquals("For three players, the amount of four of a kind tokens stack are not 8",8,pa.getFourOfAKindTokens().size());
		assertEquals("For three players, the amount of three pair tokens stack are not 8",8,pa.getThreePairTokens().size());
		assertEquals("For three players, the amount of seven unique tokens stack are not 8",8,pa.getSevenUniqueTokens().size());
		assertEquals("For three players, the amount of generic tokens stack are not 3",3 ,pa.getGenericTokens().size());
		Queue<Color> start_laketile_color = pa.getStartLakeTile().getColorOfFourSides();
		assertEquals("For three players, the amount of start lake tiles sides are not 4",4 , start_laketile_color.size());
		assertTrue("For three players, all sides of start laketile color is not Red", start_laketile_color.contains(Color.RED));
		assertEquals("For three players, the amount of lake tile stack before giving to players are not 27 but " + pa.getLakeTiles().size(),27 ,pa.getLakeTiles().size());
		assertEquals("For three players, the amount of favor token pile are not 20",20 ,pa.getNumberOfFavorTokens());
		
		assertEquals(8,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(7,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(6,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(6,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(4,pa.getFourOfAKindTokens().pop().getHonor());
		
		assertEquals(9,pa.getThreePairTokens().pop().getHonor());
		assertEquals(8,pa.getThreePairTokens().pop().getHonor());
		assertEquals(7,pa.getThreePairTokens().pop().getHonor());
		assertEquals(7,pa.getThreePairTokens().pop().getHonor());
		assertEquals(6,pa.getThreePairTokens().pop().getHonor());
		assertEquals(6,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		
		assertEquals(10,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(9,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(8,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(8,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(7,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(7,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(6,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(5,pa.getSevenUniqueTokens().pop().getHonor());
	}
	
	@Test
	public void testSetupForFourPlayers() {
		PlayArea pa = new PlayArea(fourPyList);
		assertEquals("For four players, the amount of four of a kind tokens stack are not 9",9,pa.getFourOfAKindTokens().size());
		assertEquals("For four players, the amount of three pair tokens stack are not 9",9,pa.getThreePairTokens().size());
		assertEquals("For four players, the amount of seven unique tokens stack are not 9",9,pa.getSevenUniqueTokens().size());
		assertEquals("For four players, the amount of generic tokens stack are not 3",3 ,pa.getGenericTokens().size());
		Queue<Color> start_laketile_color = pa.getStartLakeTile().getColorOfFourSides();
		assertEquals("For four players, the amount of start lake tiles sides are not 4",4 , start_laketile_color.size());
		assertTrue("For four players, all sides of start laketile color is not Red", start_laketile_color.contains(Color.RED));
		assertEquals("For four players, the amount of lake tile stack before giving to players are not 32 but " + pa.getLakeTiles().size(),32 ,pa.getLakeTiles().size());
		assertEquals("For four players, the amount of favor token pile are not 20",20 ,pa.getNumberOfFavorTokens());
		
		assertEquals(8,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(7,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(7,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(6,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(6,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(5,pa.getFourOfAKindTokens().pop().getHonor());
		assertEquals(4,pa.getFourOfAKindTokens().pop().getHonor());
		
		assertEquals(9,pa.getThreePairTokens().pop().getHonor());
		assertEquals(8,pa.getThreePairTokens().pop().getHonor());
		assertEquals(8,pa.getThreePairTokens().pop().getHonor());
		assertEquals(7,pa.getThreePairTokens().pop().getHonor());
		assertEquals(7,pa.getThreePairTokens().pop().getHonor());
		assertEquals(6,pa.getThreePairTokens().pop().getHonor());
		assertEquals(6,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		assertEquals(5,pa.getThreePairTokens().pop().getHonor());
		
		assertEquals(10,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(9,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(9,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(8,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(8,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(7,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(7,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(6,pa.getSevenUniqueTokens().pop().getHonor());
		assertEquals(5,pa.getSevenUniqueTokens().pop().getHonor());
		
	}
	
	@Test
	public void testPlatformOnBoard(){
		PlayArea pa = new PlayArea(twoPyList);
		LakeTile laketile1= new LakeTile(0, Color.WHITE, Color.ORANGE, Color., Color.RED,false);
		LakeTile laketile2= new LakeTile(1, Color.RED, Color.GREEN, Color.BLACK, Color.RED,false);
		LakeTile laketile3= new LakeTile(2, Color.RED, Color.GREEN, Color.BLACK, Color.RED,false);
		LakeTile laketile4= new LakeTile(3, Color.RED, Color.GREEN, Color.BLACK, Color.RED,false);
		pa.getLakeTilesOnBoard()[];
		twoPyList.g
	}
	
	@Test 
	public void testFourOfAKind()
	{
	    assertTrue(py1.isFourOfAKind());
	    assertFalse(py2.isFourOfAKind());
	}
	@Test
	public void testSevenOfAKind()
	{
	    assertTrue(py3.isSevenUnique());
	    assertFalse(py1.isSevenUnique());
	}
	@Test
	public void testThreePair()
	{
	    assertTrue(py4.isThreePair());
	    assertFalse(py3.isThreePair());
	}
}
