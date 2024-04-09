package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdDeleteOneShapeTest {
	private CmdDeleteOneShape cmdDeleteOneShape;
	private DrawingModel model;
	private Point point;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
        point = new Point(4, 4);
        circle = new Circle(new Point(4, 4), 20);
        model.addShapeToList(point);
        cmdDeleteOneShape = new CmdDeleteOneShape();
        cmdDeleteOneShape.setModel(model);
        cmdDeleteOneShape.setShape(point);
	}

	@Test
	public void testInitialization() {
	    assertEquals(point, cmdDeleteOneShape.getShape());
	    assertEquals(model, cmdDeleteOneShape.getModel());
	}
	
	@Test
	public void executeExpectedFalse() {
		cmdDeleteOneShape.execute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(point));
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdDeleteOneShape.execute();
		cmdDeleteOneShape.unexecute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(point));
	}
	
	@Test
	public void executeSurfaceShapeExpectedTrue() {
		cmdDeleteOneShape = new CmdDeleteOneShape(model, circle);
		cmdDeleteOneShape.execute();
		assertEquals(1, model.getShapes().size());
		assertFalse(model.getShapes().contains(circle));
	}

	@Test
	public void unexecuteSurfaceShapeExpectedTrue() {
		cmdDeleteOneShape = new CmdDeleteOneShape(model, circle);
		cmdDeleteOneShape.execute();
		cmdDeleteOneShape.unexecute();
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().contains(circle));
	}
	
	@Test
	public void toStringExpectedEquals() {
	    String expectedString = "Deleting: " + point.toString() + "\n";
	    assertEquals(expectedString, cmdDeleteOneShape.toString());
	}

}
