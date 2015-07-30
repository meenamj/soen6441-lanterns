package testing;

import org.junit.*;

import project.Position;
import static org.junit.Assert.*;

/**
 * The class <code>PositionTest</code> contains tests for the class <code>{@link Position}</code>.
 *
 * @author nirav
 */
public class PositionTest {
	/**
	 * Run the Position(int,int) constructor test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testPosition_1()
		throws Exception {
		int x = 1;
		int y = 1;

		Position result = new Position(x, y);

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.getX());
		assertEquals(1, result.getY());
		assertEquals("(1,1)", result.getText());
	}

	/**
	 * Run the String getText() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetText_1()
		throws Exception {
		Position fixture = new Position(1, 1);

		String result = fixture.getText();

		// add additional test code here
		assertEquals("(1,1)", result);
	}

	/**
	 * Run the int getX() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetX_1()
		throws Exception {
		Position fixture = new Position(1, 1);

		int result = fixture.getX();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int getY() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetY_1()
		throws Exception {
		Position fixture = new Position(1, 1);

		int result = fixture.getY();

		// add additional test code here
		assertEquals(1, result);
	}





	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(PositionTest.class);
	}
}