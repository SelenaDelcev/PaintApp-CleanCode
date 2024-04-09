package command;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import geometry.Point;

public class CmdModifyPointTest {
	private CmdModifyPoint cmdModifyPoint;
	private Point originalPoint;

	@Before
	public void setUp() {
		originalPoint = new Point(1, 1);
		Point updatedPoint = new Point(10, 10, false, Color.RED);
		cmdModifyPoint = new CmdModifyPoint(originalPoint, updatedPoint);
	}

	@Test
	public void executeExpectedTrue() {
		cmdModifyPoint.execute();
		assertEquals(10, originalPoint.getX());
		assertEquals(10, originalPoint.getY());
		assertEquals(Color.RED, originalPoint.getEdgeColor());
	}

	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyPoint.execute();
		cmdModifyPoint.unexecute();
		assertEquals(1, originalPoint.getX());
		assertEquals(1, originalPoint.getY());
		assertEquals(Color.BLACK, originalPoint.getEdgeColor());
	}

	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalPoint.toString() + "\n";
		assertEquals(expectedString, cmdModifyPoint.toString());
	}

}
