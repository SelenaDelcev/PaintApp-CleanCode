package mvc;

import static org.junit.Assert.*;
import java.awt.event.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class DrawingControllerTest {
	private DrawingController controller;
	private DrawingFrame frame;
	private DrawingModel model;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		controller = new DrawingController(model, frame);
	}

	@Test
	public void testCreatePoint() {
		frame.getBtnPoint().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(frame, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100,
				1, false);
		controller.mouseClicked(mouseEvent);

		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Point);
		Point createdPoint = (Point) model.getShapes().get(0);
		assertEquals(100, createdPoint.getX());
		assertEquals(100, createdPoint.getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testCreateLine() {
		frame.getBtnLine().setSelected(true);
		MouseEvent startMouseEvent = new MouseEvent(frame, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100,
				100, 1, false);
		controller.mouseClicked(startMouseEvent);
		MouseEvent endMouseEvent = new MouseEvent(frame, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 200,
				200, 1, false);
		controller.mouseClicked(endMouseEvent);

		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Line);
		Line createdLine = (Line) model.getShapes().get(0);
		assertEquals(100, createdLine.getStartPoint().getX());
		assertEquals(100, createdLine.getStartPoint().getY());
		assertEquals(200, createdLine.getEndPoint().getX());
		assertEquals(200, createdLine.getEndPoint().getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testCreateRectangle() {
		frame.getBtnRectangle().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 10, 10, 1, false);
		controller.mouseClicked(mouseEvent);

		Rectangle createdRectangle = (Rectangle) model.getShapes().get(0);
		assertTrue(createdRectangle instanceof Rectangle);
		assertEquals(10, createdRectangle.getUpperLeftPoint().getX());
		assertEquals(10, createdRectangle.getUpperLeftPoint().getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testCreateCircle() {
		frame.getBtnCircle().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 50, 50, 1, false);
		controller.mouseClicked(mouseEvent);

		Circle createdCircle = (Circle) model.getShapes().get(0);
		assertTrue(createdCircle instanceof Circle);
		assertEquals(50, createdCircle.getCenter().getX());
		assertEquals(50, createdCircle.getCenter().getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testCreateDonut() {
		frame.getBtnDonut().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 50, 50, 1, false);
		controller.mouseClicked(mouseEvent);

		Donut createdDonut = (Donut) model.getShapes().get(0);
		assertTrue(createdDonut instanceof Donut);
		assertEquals(50, createdDonut.getCenter().getX());
		assertEquals(50, createdDonut.getCenter().getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testCreateHexagon() {
		frame.getBtnHexagon().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 50, 50, 1, false);
		controller.mouseClicked(mouseEvent);

		HexagonAdapter createdHexagon = (HexagonAdapter) model.getShapes().get(0);
		assertTrue(createdHexagon instanceof HexagonAdapter);
		assertEquals(50, createdHexagon.getHexagon().getX());
		assertEquals(50, createdHexagon.getHexagon().getY());
		assertTrue(frame.getBtnSaveDrawing().isEnabled());
		assertTrue(frame.getBtnSaveCommands().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}

	@Test
	public void testSelectOneShape() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		frame.getBtnSelect().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);

		assertTrue(rectangle.isSelected());
		assertTrue(frame.getBtnModification().isEnabled());
		assertTrue(frame.getBtnDelete().isEnabled());
	}

	@Test
	public void testSelectMoreShapes() {
		Line line = new Line(new Point(100, 100), new Point(200, 200));
		model.getSelectedShapes().add(line);
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		frame.getBtnSelect().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);

		assertTrue(rectangle.isSelected());
		assertFalse(frame.getBtnModification().isEnabled());
		assertTrue(frame.getBtnDelete().isEnabled());
	}

	@Test
	public void testDeselectShape() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		frame.getBtnSelect().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);
		assertTrue(rectangle.isSelected());
		MouseEvent mouseEventSecond = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED,
				System.currentTimeMillis(), 0, 75, 75, 1, false);
		controller.mouseClicked(mouseEventSecond);
		
		assertFalse(rectangle.isSelected());
		assertFalse(frame.getBtnModification().isEnabled());
		assertFalse(frame.getBtnDelete().isEnabled());
	}

	@Test
	public void testDeselectShapeOnWrongClick() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		frame.getBtnSelect().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);
		assertTrue(rectangle.isSelected());
		MouseEvent mouseEventSecond = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED,
				System.currentTimeMillis(), 0, 375, 375, 1, false);
		controller.mouseClicked(mouseEventSecond);
		
		assertTrue(rectangle.isSelected());
		assertTrue(frame.getBtnModification().isEnabled());
		assertTrue(frame.getBtnDelete().isEnabled());
	}

	@Test
	public void testClickWithoutSelectButton() {
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);
		assertEquals(0, model.getShapes().size());
	}

	@Test
	public void testClickSelectButtonWithoutShapes() {
		assertFalse(frame.getBtnSelect().isEnabled());
		frame.getBtnSelect().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 75, 75, 1, false);
		controller.mouseClicked(mouseEvent);
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void testEditShapeWhenOneShapeSelected() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		model.getSelectedShapes().add(rectangle);
		controller.editShape();
		assertEquals(50, rectangle.getUpperLeftPoint().getX());
		assertEquals(50, rectangle.getUpperLeftPoint().getY());
		assertEquals(150, rectangle.getWidth());
		assertEquals(150, rectangle.getHeight());
	}

	@Test
	public void testEditShapeWhenNoSelectedShape() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		controller.editShape();
	}

	@Test
	public void testModificationButtonWhenMoreShapesSelected() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		Line line = new Line(new Point(100, 100), new Point(200, 100));
		model.addShapeToListOfSelected(rectangle);
		model.addShapeToListOfSelected(line);
		assertFalse(frame.getBtnModification().isEnabled());
	}

	@Test
	public void testDeleteOneShape() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		model.getSelectedShapes().add(rectangle);
		controller.deleteShape();
		assertEquals(0, model.getShapes().size());
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void testDeleteMultipleShapes() {
		Rectangle rectangle1 = new Rectangle(new Point(50, 50), 100, 100, false);
		Rectangle rectangle2 = new Rectangle(new Point(150, 150), 100, 100, false);
		model.addShapeToList(rectangle1);
		model.addShapeToList(rectangle2);
		model.getSelectedShapes().add(rectangle1);
		model.getSelectedShapes().add(rectangle2);
		controller.deleteShape();
		assertEquals(0, model.getShapes().size());
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void testDeleteShapeWhenNoSelectShape() {
		assertFalse(frame.getBtnDelete().isEnabled());
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		controller.deleteShape();
		assertEquals(1, model.getShapes().size());
	}

	@Test
	public void testUndo() {
		frame.getBtnRectangle().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 10, 10, 1, false);
		controller.mouseClicked(mouseEvent);
		controller.undo();
		assertEquals(0, model.getShapes().size());
		assertTrue(frame.getBtnRedo().isEnabled());
		assertFalse(frame.getBtnUndo().isEnabled());
	}

	@Test
	public void testRedo() {
		frame.getBtnRectangle().setSelected(true);
		MouseEvent mouseEvent = new MouseEvent(new DrawingFrame(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
				0, 10, 10, 1, false);
		controller.mouseClicked(mouseEvent);
		controller.undo();
		controller.redo();
		assertEquals(1, model.getShapes().size());
		assertFalse(frame.getBtnRedo().isEnabled());
		assertTrue(frame.getBtnUndo().isEnabled());
	}

	@Test
	public void testFullBringToBack() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		model.getSelectedShapes().add(rectangle);
		controller.fullBringToBack();
		assertEquals(rectangle, model.getShapes().get(0));
	}

	@Test
	public void testBringToBackByOne() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		Circle circle = new Circle(new Point(150, 50), 50, false);
		model.addShapeToList(rectangle);
		model.addShapeToList(circle);
		model.getSelectedShapes().add(circle);
		controller.bringToBackByOne();
		assertEquals(circle, model.getShapes().get(0));
		assertEquals(rectangle, model.getShapes().get(1));
	}

	@Test
	public void testFullBringToFront() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		model.addShapeToList(rectangle);
		model.getSelectedShapes().add(rectangle);
		controller.fullBringToFront();
		assertEquals(rectangle, model.getShapes().get(model.getShapes().size() - 1));
	}

	@Test
	public void testBringToFrontByOne() {
		Rectangle rectangle = new Rectangle(new Point(50, 50), 100, 100, false);
		Circle circle = new Circle(new Point(150, 50), 50, false);
		model.addShapeToList(rectangle);
		model.addShapeToList(circle);
		model.getSelectedShapes().add(rectangle);
		controller.bringToFrontByOne();
		assertEquals(circle, model.getShapes().get(0));
		assertEquals(rectangle, model.getShapes().get(1));
	}

	@Test
	public void testLoadDrawing() {
		controller.loadDrawing();
		assertEquals(2, model.getShapes().size());
	}

	@Test
	public void testLoadLog() {
		controller.loadLog();
		assertEquals(5, model.getShapes().size());
	}

	@Test
	public void testSaveDrawing() {
		controller.saveDrawing();
	}

	@Test
	public void testSaveLog() {
		controller.saveLog();
	}
}
