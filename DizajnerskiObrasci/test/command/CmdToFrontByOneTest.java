package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

public class CmdToFrontByOneTest {
	private CmdToFrontByOne cmdToFrontByOne;
	private DrawingModel model;
	private Line line;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
		line = new Line(new Point(10, 10), new Point(40, 40));
		circle = new Circle(new Point(10, 10), 10);
		model.addShapeToList(line);
		model.addShapeToList(circle);
		cmdToFrontByOne = new CmdToFrontByOne(model, line, 0);
	}

	@Test
	public void executeExpectedTrue() {
		cmdToFrontByOne.execute();
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(line, model.getShapeFromIndex(1));
	}

	@Test
	public void unexecuteExpectedTrue() {
		cmdToFrontByOne.execute();
		cmdToFrontByOne.unexecute();
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(line, model.getShapeFromIndex(0));
	}

	@Test
	public void toStringExpectedEquals() {
		String expectedString = "ToFront: " + line.toString() + "\n";
		assertEquals(expectedString, cmdToFrontByOne.toString());
	}

	/*
	 * Ovaj test proverava da li je došlo do greške i da li se hvata izuzetak
	 * IndexOutOfBoundsException
	 * 
	 * @Test public void testExecuteException() {
	 * assertThrows(IndexOutOfBoundsException.class, () -> { CmdToFrontByOne
	 * cmdToFrontByOne = new CmdToFrontByOne(model, line, -1);
	 * cmdToFrontByOne.execute(); }); }
	 */

}
