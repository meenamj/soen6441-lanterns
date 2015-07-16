package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GameFileTest.class, GameTest.class, LakeTileTest.class,
		PlayAreaTest.class, SupplyTest.class })
public class AllTests {

}
