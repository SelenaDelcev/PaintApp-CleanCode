package command;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class CmdModifyHexagonTest {
	private CmdModifyHexagon cmdModifyHexagon;
	private HexagonAdapter originalHexagon;

	@Before
	public void setUp() {
		originalHexagon = new HexagonAdapter(new Point(10, 20), 80, false, Color.BLACK, Color.BLACK);
		HexagonAdapter updatedHexagon = new HexagonAdapter(new Point(50, 50), 100, false, Color.RED, Color.PINK);
		cmdModifyHexagon = new CmdModifyHexagon(originalHexagon, updatedHexagon);
	}

	@Test
	public void executeExpectedTrue() {
		cmdModifyHexagon.execute();
		assertEquals(50, originalHexagon.getHexagonCenter().getX());
		assertEquals(50, originalHexagon.getHexagonCenter().getY());
		assertEquals(100, originalHexagon.getHexagonRadius());
		assertEquals(Color.RED, originalHexagon.getHexagonBorderColor());
		assertEquals(Color.PINK, originalHexagon.getHexagonInnerColor());
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyHexagon.execute();
		cmdModifyHexagon.unexecute();
		assertEquals(10, originalHexagon.getHexagonCenter().getX());
		assertEquals(20, originalHexagon.getHexagonCenter().getY());
		assertEquals(80, originalHexagon.getHexagonRadius());
		assertEquals(Color.BLACK, originalHexagon.getHexagonBorderColor());
		assertEquals(Color.BLACK, originalHexagon.getHexagonInnerColor());
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalHexagon.toString() + "\n";
		assertEquals(expectedString, cmdModifyHexagon.toString());
	}

}
