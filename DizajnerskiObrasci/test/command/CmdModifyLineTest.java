package command;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class CmdModifyLineTest {
	private CmdModifyLine cmdModifyLine;
	private Line originalLine;

	@Before
	public void setUp() {
		originalLine = new Line(new Point(10, 20), new Point(30, 40));
		Line updatedLine = new Line(new Point(50, 60), new Point(150, 250), false, Color.RED);
		cmdModifyLine = new CmdModifyLine(originalLine, updatedLine);
	}

	@Test
	public void executeExpectedTrue() {
		cmdModifyLine.execute();
		assertEquals(50, originalLine.getStartPoint().getX());
		assertEquals(60, originalLine.getStartPoint().getY());
		assertEquals(150, originalLine.getEndPoint().getX());
		assertEquals(250, originalLine.getEndPoint().getY());
		assertEquals(Color.RED, originalLine.getEdgeColor());
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdModifyLine.execute();
		cmdModifyLine.unexecute();
		assertEquals(10, originalLine.getStartPoint().getX());
		assertEquals(20, originalLine.getStartPoint().getY());
		assertEquals(30, originalLine.getEndPoint().getX());
		assertEquals(40, originalLine.getEndPoint().getY());
		assertEquals(Color.BLACK, originalLine.getEdgeColor());
	}

	@Test
	public void toStringExpectedEquals() {
		String expectedString = "Modifying: " + originalLine.toString() + "\n";
		assertEquals(expectedString, cmdModifyLine.toString());
	}

}
