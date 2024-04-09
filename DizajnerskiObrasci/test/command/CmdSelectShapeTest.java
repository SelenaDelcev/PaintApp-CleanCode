package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdSelectShapeTest {
	private CmdSelectShape cmdSelectShape;
	private DrawingModel model;
	private Point point;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
        point = new Point(4, 4);
        circle = new Circle(new Point(4, 4), 20);
        cmdSelectShape = new CmdSelectShape();
        cmdSelectShape.setModel(model);
        cmdSelectShape.setShape(point);
	}

	@Test
	public void testInitialization() {
	    assertEquals(point, cmdSelectShape.getShape());
	    assertEquals(model, cmdSelectShape.getModel());
	}
	
	@Test
	public void executeExpectedTrue() {
		cmdSelectShape.execute();
		assertTrue(point.isSelected());
	}

	@Test
	public void unexecuteWhenSelectedExpectedTrue() {
		cmdSelectShape.execute();
		cmdSelectShape.unexecute();
		assertFalse(point.isSelected());
	}
	
	@Test
	public void toStringExpectedEquals() {
		cmdSelectShape = new CmdSelectShape(model, circle);
	    String expectedString = "Select: " + circle.toString() + "\n";
	    assertEquals(expectedString, cmdSelectShape.toString());
	}

}
