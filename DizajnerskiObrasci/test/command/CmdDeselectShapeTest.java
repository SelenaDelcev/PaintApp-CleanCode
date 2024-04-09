package command;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.Circle;
import geometry.Point;
import mvc.DrawingModel;

public class CmdDeselectShapeTest {
	private CmdDeselectShape cmdDeselectShape;
	private DrawingModel model;
	private Point point;
	private Circle circle;

	@Before
	public void setUp() {
		model = new DrawingModel();
        point = new Point(4, 4, true);
        circle = new Circle(new Point(4, 4), 20);
        cmdDeselectShape = new CmdDeselectShape();
        cmdDeselectShape.setModel(model);
        cmdDeselectShape.setShape(point);
	}

	@Test
	public void testInitialization() {
	    assertEquals(point, cmdDeselectShape.getShape());
	    assertEquals(model, cmdDeselectShape.getModel());
	}
	
	@Test
	public void executeExpectedFalse() {
		cmdDeselectShape.execute();
		assertFalse(point.isSelected());
	}
	
	@Test
	public void unexecuteExpectedTrue() {
		cmdDeselectShape.execute();
		cmdDeselectShape.unexecute();
		assertTrue(point.isSelected());
	}
	
	@Test
	public void toStringExpectedEquals() {
		cmdDeselectShape = new CmdDeselectShape(model, circle);
	    String expectedString = "Deselect: " + circle.toString() + "\n";
	    assertEquals(expectedString, cmdDeselectShape.toString());
	}

}
