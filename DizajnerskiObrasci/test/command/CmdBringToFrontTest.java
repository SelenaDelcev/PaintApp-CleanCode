package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdBringToFrontTest {
	private CmdBringToFront cmdBringToFront;
	private DrawingModel model;
	private Point point;
	private Line line;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10);
		line = new Line(new Point(10, 10), new Point(40, 40));
		circle = new Circle(new Point(10, 10), 10);
		model.addShapeToList(point);
		model.addShapeToList(line);
		model.addShapeToList(circle);
		cmdBringToFront = new CmdBringToFront(model, point, 0);
	}

	@Test
	public void executeExpectedTrue() {
		cmdBringToFront.execute();
		assertEquals(point, model.getShapeFromIndex(2));
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(line, model.getShapeFromIndex(0));
	}
	
	@Test
	public void rexecuteExpectedTrue() {
		cmdBringToFront.execute();
		CmdBringToFront cmdBringToFront2 = new CmdBringToFront(model, line, 0);
		cmdBringToFront2.execute();
		assertEquals(line, model.getShapeFromIndex(2));
		assertEquals(point, model.getShapeFromIndex(1));
		assertEquals(circle, model.getShapeFromIndex(0));
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdBringToFront.execute();
		cmdBringToFront.unexecute();
		assertEquals(circle, model.getShapeFromIndex(2));
		assertEquals(line, model.getShapeFromIndex(1));
		assertEquals(point, model.getShapeFromIndex(0));
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expectedString = "BringToFront: " + point.toString() + "\n";
		assertEquals(expectedString, cmdBringToFront.toString());
	}
	
	/*
	 * Ovaj test proverava da li je došlo do greške i da li se hvata izuzetak
	 * IndexOutOfBoundsException
	 * 
	 * @Test public void testExecuteException() {
	 * assertThrows(IndexOutOfBoundsException.class, () -> { CmdBringToFront
	 * cmdBringToFront = new CmdBringToFront(model, point, -1);
	 * cmdBringToFront.execute(); }); }
	 */

}
