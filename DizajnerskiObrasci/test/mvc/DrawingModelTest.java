package mvc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class DrawingModelTest {
	private DrawingModel model;
	private Point point;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(10, 10);
	}

	@Test
	public void addShapeExpectedEqual() {
		model.addShapeToList(point);
		assertEquals(1, model.getShapes().size());
		assertEquals(point, model.getShapeFromIndex(0));
	}
	
	@Test
	public void addShapeAtIndexExpectedEqual() {
		model.addShapeToList(point);
		model.addShapeAtIndex(new Circle(point , 10), 0);
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
	}
	
	@Test
	public void addShapeToListOfSelectedExpectedEqual() {
		model.addShapeToListOfSelected(point);
		assertEquals(1, model.getSelectedShapes().size());
	}
	
	@Test
	public void getShapeFromIndexExpectedEqual() {
		model.addShapeToList(point);
		model.addShapeToList(new Circle(point , 10));
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapeFromIndex(1) instanceof Circle);
	}
	
	@Test
	public void getIndexOfShapeExpectedEqual() {
		model.addShapeToList(point);
		model.addShapeToList(new Circle(point , 10));
		assertEquals(2, model.getShapes().size());
		assertEquals(0, model.getIndexOfShape(point));
	}
	
	@Test
	public void removeShapeExpectedEqual() {
		model.addShapeToList(point);
		model.deleteShapeFromList(point);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void removeShapeAtIndexExpectedEqual() {
		model.addShapeToList(point);
		model.addShapeToList(new Circle(point , 10));
		model.deleteShapeAtIndex(0);
		assertFalse(model.getShapeFromIndex(0) instanceof Point);
	}
	
	@Test
	public void removeShapeToListOfSelectedExpectedEqual() {
		model.addShapeToListOfSelected(point);
		assertEquals(1, model.getSelectedShapes().size());
		model.deleteShapeFromListOfSelected(point);
		assertEquals(0, model.getSelectedShapes().size());
	}

}
