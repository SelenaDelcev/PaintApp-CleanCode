package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdBringToBackTest {
	private CmdBringToBack cmdBringToBack;
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
		cmdBringToBack = new CmdBringToBack(model, circle, 2);
	}

	@Test
	public void executeExpectedTrue() {
		cmdBringToBack.execute();
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(point, model.getShapeFromIndex(1));
		assertEquals(line, model.getShapeFromIndex(2));
	}
	
	@Test
	public void rexecuteExpectedTrue() {
		cmdBringToBack.execute();
		CmdBringToBack cmdBringToBack2 = new CmdBringToBack(model, line, 2);
		cmdBringToBack2.execute();
		assertEquals(line, model.getShapeFromIndex(0));
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(point, model.getShapeFromIndex(2));
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdBringToBack.execute();
		cmdBringToBack.unexecute();
		assertEquals(circle, model.getShapeFromIndex(2));
		assertEquals(line, model.getShapeFromIndex(1));
		assertEquals(point, model.getShapeFromIndex(0));
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expectedString = "BringToBack: " + circle.toString() + "\n";
		assertEquals(expectedString, cmdBringToBack.toString());
	}
	
	/*
	 * Ovaj test proverava da li je došlo do greške i da li se hvata izuzetak
	 * IndexOutOfBoundsException
	 * 
	 * @Test public void testExecuteException() {
	 * assertThrows(IndexOutOfBoundsException.class, () -> { CmdBringToBack
	 * cmdBringToBack = new CmdBringToBack(model, circle, -1);
	 * cmdBringToBack.execute(); }); }
	 */

}
