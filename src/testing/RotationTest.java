package testing;

import org.junit.*;

import project.Rotation;
import static org.junit.Assert.*;

/**
 * The class <code>RotationTest</code> contains tests for the class <code>{@link Rotation}</code>.
 *
 * @author nirav
 */
public class RotationTest {
	@Test
	public void testGetRotation0()
		throws Exception {
		int rotation = 0;
		Rotation result = Rotation.getRotation(rotation);
		assertNotNull(result);
		assertEquals("D0", result.name());
		assertEquals("D0", result.toString());
		assertEquals(0, result.ordinal());
	}

	@Test
	public void testGetRotation90()
		throws Exception {
		int rotation = 90;
		Rotation result = Rotation.getRotation(rotation);
		assertNotNull(result);
		assertEquals("D90", result.name());
		assertEquals("D90", result.toString());
		assertEquals(1, result.ordinal());
	}

	@Test
	public void testGetRotation180()
		throws Exception {
		int rotation = 180;
		Rotation result = Rotation.getRotation(rotation);
		assertNotNull(result);
		assertEquals("D180", result.name());
		assertEquals("D180", result.toString());
		assertEquals(2, result.ordinal());
	}

	@Test
	public void testGetRotation270()
		throws Exception {
		int rotation = 270;
		Rotation result = Rotation.getRotation(rotation);
		assertNotNull(result);
		assertEquals("D270", result.name());
		assertEquals("D270", result.toString());
		assertEquals(3, result.ordinal());
	}

	@Test(expected = java.lang.Exception.class)
	public void testGetRotationNotNull()
		throws Exception {
		int rotation = 1;

		Rotation result = Rotation.getRotation(rotation);

		assertNotNull(result);
	}
	
	@Test
	public void testRandomRandom()
		throws Exception {
		Rotation result = Rotation.random();
		assertEquals("D180", result.name());
		assertEquals("D180", result.toString());
		assertEquals(2, result.ordinal());
	}
}
