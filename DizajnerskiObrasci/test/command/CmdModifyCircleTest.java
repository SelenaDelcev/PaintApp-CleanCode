package command;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class CmdModifyCircleTest {
	private CmdModifyCircle cmdModifyCircle;
	private Circle originalCircle;

	@Before
	public void setUp() {
		originalCircle = new Circle(new Point(10, 20), 80);
		Circle updatedCircle = new Circle(new Point(50, 50), 100, false, Color.RED, Color.PINK);
		cmdModifyCircle = new CmdModifyCircle(originalCircle, updatedCircle);
	}

	@Test
	public void executeExpectedTrue() {
		cmdModifyCircle.execute();
		assertEquals(50, originalCircle.getCenter().getX());
		assertEquals(50, originalCircle.getCenter().getY());
		assertEquals(100, originalCircle.getRadius());
		assertEquals(Color.RED, originalCircle.getEdgeColor());
		assertEquals(Color.PINK, originalCircle.getInnerColor());
	}

	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyCircle.execute();
		cmdModifyCircle.unexecute();
		assertEquals(10, originalCircle.getCenter().getX());
		assertEquals(20, originalCircle.getCenter().getY());
		assertEquals(80, originalCircle.getRadius());
		assertEquals(Color.BLACK, originalCircle.getEdgeColor());
		assertEquals(Color.BLACK, originalCircle.getInnerColor());
	}

	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalCircle.toString() + "\n";
		assertEquals(expectedString, cmdModifyCircle.toString());
	}

}
