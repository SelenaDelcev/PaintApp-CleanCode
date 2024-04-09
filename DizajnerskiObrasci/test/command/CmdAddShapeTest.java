package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdAddShapeTest {
	private CmdAddShape cmdAddShape;
	private DrawingModel model;
	private Point point;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
        point = new Point(4, 4);
        circle = new Circle(new Point(4, 4), 20);
        cmdAddShape = new CmdAddShape();
        cmdAddShape.setModel(model);
        cmdAddShape.setShape(point);
	}
	
	@Test
	public void testInitialization() {
	    assertEquals(point, cmdAddShape.getShape());
	    assertEquals(model, cmdAddShape.getModel());
	}

	@Test
	public void executeExpectedTrue() {
		cmdAddShape.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(point));
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdAddShape.execute();
		cmdAddShape.unexecute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(point));
	}
	
	@Test
	public void executeSurfaceShapeExpectedTrue() {
		cmdAddShape = new CmdAddShape(model, circle);
		cmdAddShape.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(circle));
	}

	@Test
	public void unexecuteSurfaceShapeExpectedTrue() {
		cmdAddShape = new CmdAddShape(model, circle);
		cmdAddShape.execute();
		cmdAddShape.unexecute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(circle));
	}
	
	@Test
	public void toStringExpectedEquals() {
	    String expectedString = "Adding: " + point.toString() + "\n";
	    assertEquals(expectedString, cmdAddShape.toString());
	}

}
