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
	
	@Test
	public void testPosition() {
		int x = 1;
		int y = 1;

		Position result = new Position(x, y);
		assertNotNull(result);
		assertEquals(1, result.getX());
		assertEquals(1, result.getY());
		assertEquals("(1,1)", result.getText());
	}

	@Test
	public void testGetText(){
		Position fixture = new Position(1, 1);
		String result = fixture.getText();
		assertEquals("(1,1)", result);
	}

	@Test
	public void testGetX(){
		Position fixture = new Position(1, 1);
		int result = fixture.getX();
		assertEquals(1, result);
	}

	@Test
	public void testGetY(){
		Position fixture = new Position(1, 1);
		int result = fixture.getY();
		assertEquals(1, result);
	}
}