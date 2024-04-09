package command;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class CmdModifyDonutTest {
	private CmdModifyDonut cmdModifyDonut;
	private Donut originalDonut;

	@Before
	public void setUp() {
		originalDonut = new Donut(new Point(10, 20), 80, 40);
		Donut updatedDonut = new Donut(new Point(50, 50), 100, 50, false, Color.RED, Color.PINK);
		cmdModifyDonut = new CmdModifyDonut(originalDonut, updatedDonut);
	}

	@Test
	public void executeExpectedTrue() {
		cmdModifyDonut.execute();
		assertEquals(50, originalDonut.getCenter().getX());
		assertEquals(50, originalDonut.getCenter().getY());
		assertEquals(100, originalDonut.getRadius());
		assertEquals(50, originalDonut.getInnerRadius());
		assertEquals(Color.RED, originalDonut.getEdgeColor());
		assertEquals(Color.PINK, originalDonut.getInnerColor());
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyDonut.execute();
		cmdModifyDonut.unexecute();
		assertEquals(10, originalDonut.getCenter().getX());
		assertEquals(20, originalDonut.getCenter().getY());
		assertEquals(80, originalDonut.getRadius());
		assertEquals(40, originalDonut.getInnerRadius());
		assertEquals(Color.BLACK, originalDonut.getEdgeColor());
		assertEquals(Color.BLACK, originalDonut.getInnerColor());
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalDonut.toString() + "\n";
		assertEquals(expectedString, cmdModifyDonut.toString());
	}

}
