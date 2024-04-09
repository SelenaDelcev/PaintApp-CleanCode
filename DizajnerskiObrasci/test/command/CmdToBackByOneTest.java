package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdToBackByOneTest {
	private CmdToBackByOne cmdToBackByOne;
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
		cmdToBackByOne = new CmdToBackByOne(model, circle, 1);
	}

	@Test
	public void executeExpectedTrue() {
		cmdToBackByOne.execute();
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(line, model.getShapeFromIndex(1));
	}

	@Test
	public void unexecuteExpectedTrue() {
		cmdToBackByOne.execute();
		cmdToBackByOne.unexecute();
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(line, model.getShapeFromIndex(0));
	}

	@Test
	public void toStringExpectedEquals() {
		String expectedString = "ToBack: " + circle.toString() + "\n";
		assertEquals(expectedString, cmdToBackByOne.toString());
	}

	/*
	 * Ovaj test proverava da li je došlo do greške i da li se hvata izuzetak
	 * IndexOutOfBoundsException
	 * 
	 * @Test public void testExecuteException() {
	 * assertThrows(IndexOutOfBoundsException.class, () -> { CmdToBackByOne
	 * cmdToBackByOne = new CmdToBackByOne(model, circle, -1);
	 * cmdToBackByOne.execute(); }); }
	 */

}