package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.rule.Base;
import project.rule.NHonorPoint;
import project.rule.NLakeTilesOnBoard;
import project.rule.Rule;

public class RuleTest {

	@Test
	public void testBaseRuleNotNull() {
		Rule base = new Base();
		assertNotNull(base);
	}
	
	@Test
	public void testNHonorPointRuleNotNull() {
		Rule n_honor_point = new NHonorPoint(0);
		assertNotNull(n_honor_point);
	}
	
	@Test
	public void testNLakeTilesOnBoardRuleNotNull() {
		Rule n_laketiles_onboard = new NLakeTilesOnBoard(0);
		assertNotNull(n_laketiles_onboard);
	}
	
}
