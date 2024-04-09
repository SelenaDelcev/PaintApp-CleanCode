package command;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class CmdModifyRectangleTest {
	private CmdModifyRectangle cmdModifyRectangle;
	private Rectangle originalRectangle;

	@Before
	public void setUp() {
		originalRectangle = new Rectangle(new Point(50, 50), 100, 150);
		Rectangle updatedRectangle = new Rectangle(new Point(50, 50), 200, 250, false, Color.RED, Color.PINK);
		cmdModifyRectangle = new CmdModifyRectangle(originalRectangle, updatedRectangle);
	}
	
	@Test
	public void executeExpectedTrue() {
		cmdModifyRectangle.execute();
		assertEquals(50, originalRectangle.getUpperLeftPoint().getX());
		assertEquals(50, originalRectangle.getUpperLeftPoint().getY());
		assertEquals(200, originalRectangle.getHeight());
		assertEquals(250, originalRectangle.getWidth());
		assertEquals(Color.RED, originalRectangle.getEdgeColor());
		assertEquals(Color.PINK, originalRectangle.getInnerColor());
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyRectangle.execute();
		cmdModifyRectangle.unexecute();
		assertEquals(50, originalRectangle.getUpperLeftPoint().getX());
		assertEquals(50, originalRectangle.getUpperLeftPoint().getY());
		assertEquals(100, originalRectangle.getHeight());
		assertEquals(150, originalRectangle.getWidth());
		assertEquals(Color.BLACK, originalRectangle.getEdgeColor());
		assertEquals(Color.BLACK, originalRectangle.getInnerColor());
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalRectangle.toString() + "\n";
		assertEquals(expectedString, cmdModifyRectangle.toString());
	}

}
