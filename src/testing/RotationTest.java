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
	/**
	 * Run the Rotation getRotation(int) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetRotation_1()
		throws Exception {
		int rotation = 0;

		Rotation result = Rotation.getRotation(rotation);

		// add additional test code here
		assertNotNull(result);
		assertEquals("D0", result.name());
		assertEquals("D0", result.toString());
		assertEquals(0, result.ordinal());
	}

	/**
	 * Run the Rotation getRotation(int) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetRotation_2()
		throws Exception {
		int rotation = 90;

		Rotation result = Rotation.getRotation(rotation);

		// add additional test code here
		assertNotNull(result);
		assertEquals("D90", result.name());
		assertEquals("D90", result.toString());
		assertEquals(1, result.ordinal());
	}

	/**
	 * Run the Rotation getRotation(int) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetRotation_3()
		throws Exception {
		int rotation = 180;

		Rotation result = Rotation.getRotation(rotation);

		// add additional test code here
		assertNotNull(result);
		assertEquals("D180", result.name());
		assertEquals("D180", result.toString());
		assertEquals(2, result.ordinal());
	}

	/**
	 * Run the Rotation getRotation(int) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetRotation_4()
		throws Exception {
		int rotation = 270;

		Rotation result = Rotation.getRotation(rotation);

		// add additional test code here
		assertNotNull(result);
		assertEquals("D270", result.name());
		assertEquals("D270", result.toString());
		assertEquals(3, result.ordinal());
	}

	/**
	 * Run the Rotation getRotation(int) method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test(expected = java.lang.Exception.class)
	public void testGetRotation_5()
		throws Exception {
		int rotation = 1;

		Rotation result = Rotation.getRotation(rotation);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Rotation random() method test.
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testRandom_1()
		throws Exception {

		Rotation result = Rotation.random();

		// add additional test code here
		assertNotNull(result);
		assertEquals("D180", result.name());
		assertEquals("D180", result.toString());
		assertEquals(2, result.ordinal());
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(RotationTest.class);
	}
}
