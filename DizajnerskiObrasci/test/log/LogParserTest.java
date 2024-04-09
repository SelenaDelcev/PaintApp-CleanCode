package log;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;

public class LogParserTest {
	private DrawingModel model;
	private DrawingFrame frame;
	private BtnUpdate buttonsStateControl;
	private LogParser logParser;
	private Scanner scanner;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		buttonsStateControl = new BtnUpdate();
		logParser = new LogParser(model, controller, frame, buttonsStateControl);
		scanner = mock(Scanner.class);
	}

	@Test
	public void makeShapeFromLogPoint() {
		String lineLog = "Adding: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		String stringShape = "Point";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof Point);
		Point point = (Point) shape;
		assertEquals(77, point.getX());
		assertEquals(70, point.getY());
		assertEquals(new Color(0, 0, 0), point.getEdgeColor());
	}

	@Test
	public void makeShapeFromLogLine() {
		String lineLog = "Adding: Line [StartPoint=(123, 53), EndPoint=(343, 58), Color= RGB(0, 0, 0)]";
		String stringShape = "Line";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof Line);
		Line line = (Line) shape;
		assertEquals(123, line.getStartPoint().getX());
		assertEquals(53, line.getStartPoint().getY());
		assertEquals(343, line.getEndPoint().getX());
		assertEquals(58, line.getEndPoint().getY());
		assertEquals(new Color(0, 0, 0), line.getEdgeColor());
	}

	@Test
	public void makeShapeFromLogRectangle() {
		String lineLog = "Adding: Rectangle [UpperLeftPoint=(78, 183), Height=100, Width=50, OuterColor= RGB(153, 0, 153), InnerColor= RGB(102, 102, 0)]";
		String stringShape = "Rectangle";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof Rectangle);
		Rectangle rectangle = (Rectangle) shape;
		assertEquals(78, rectangle.getUpperLeftPoint().getX());
		assertEquals(183, rectangle.getUpperLeftPoint().getY());
		assertEquals(100, rectangle.getHeight());
		assertEquals(50, rectangle.getWidth());
		assertEquals(new Color(153, 0, 153), rectangle.getEdgeColor());
		assertEquals(new Color(102, 102, 0), rectangle.getInnerColor());
	}

	@Test
	public void makeShapeFromLogCircle() {
		String lineLog = "Adding: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		String stringShape = "Circle";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof Circle);
		Circle circle = (Circle) shape;
		assertEquals(278, circle.getCenter().getX());
		assertEquals(178, circle.getCenter().getY());
		assertEquals(100, circle.getRadius());
		assertEquals(new Color(0, 51, 255), circle.getEdgeColor());
		assertEquals(new Color(0, 204, 153), circle.getInnerColor());
	}

	@Test
	public void makeShapeFromLogDonut() {
		String lineLog = "Adding: Donut [Center=(407, 279), OuterRadius=100, InnerRadius=70, OuterColor= RGB(153, 0, 0), InnerColor= RGB(255, 153, 153)]";
		String stringShape = "Donut";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof Donut);
		Donut donut = (Donut) shape;
		assertEquals(407, donut.getCenter().getX());
		assertEquals(279, donut.getCenter().getY());
		assertEquals(100, donut.getRadius());
		assertEquals(70, donut.getInnerRadius());
		assertEquals(new Color(153, 0, 0), donut.getEdgeColor());
		assertEquals(new Color(255, 153, 153), donut.getInnerColor());
	}

	@Test
	public void makeShapeFromLogHexagon() {
		String lineLog = "Adding: Hexagon [Center=(191, 300), Radius=100, OuterColor= RGB(51, 51, 255), InnerColor= (0, 255, 0)]";
		String stringShape = "Hexagon";
		Shape shape = logParser.makeShapeFromLog(lineLog, stringShape, false);
		assertTrue(shape instanceof HexagonAdapter);
		HexagonAdapter hexagon = (HexagonAdapter) shape;
		assertEquals(191, hexagon.getHexagonCenter().getX());
		assertEquals(300, hexagon.getHexagonCenter().getY());
		assertEquals(100, hexagon.getHexagonRadius());
		assertEquals(new Color(51, 51, 255), hexagon.getHexagonBorderColor());
		assertEquals(new Color(0, 255, 0), hexagon.getHexagonInnerColor());
	}

	@Test
	public void executeLineLogSelectExistingShape() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		String lineLog = "Select: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		logParser.executeLineLog(lineLog);
		assertEquals(1, model.getSelectedShapes().size());
		assertTrue(model.getSelectedShapes().get(0) instanceof Circle);
		assertEquals(circle, model.getSelectedShapes().get(0));
	}

	@Test
	public void executeLineLogSelectNotExistingShape() {
		String lineLog = "Select: Square [UpperLeft=(100, 100), SideLength=50, OuterColor= RGB(255, 0, 0), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void executeLineLogDeselect() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		String lineLogSelect = "Select: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogDeselect = "Deselect: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		logParser.executeLineLog(lineLogDeselect);
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void executeLineLogModifyingCircle() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		String lineLogSelect = "Select: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)] To: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(278, circle.getCenter().getX());
		assertEquals(178, circle.getCenter().getY());
		assertEquals(100, circle.getRadius());
		assertEquals(new Color(0, 51, 255), circle.getEdgeColor());
		assertEquals(new Color(255, 255, 0), circle.getInnerColor());
	}

	@Test
	public void executeLineLogModifyingPoint() {
		Point point = new Point(77, 70);
		model.addShapeToList(point);
		String lineLogSelect = "Select: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Point [x= 77, y= 70, Color= RGB(0, 0, 0)] To: Point [x= 100, y= 100, Color= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(100, point.getX());
		assertEquals(100, point.getY());
		assertEquals(new Color(255, 255, 0), point.getEdgeColor());
	}

	@Test
	public void executeLineLogModifyingLine() {
		Line line = new Line(new Point(123, 53), new Point(343, 58));
		model.addShapeToList(line);
		String lineLogSelect = "Select: Line [StartPoint=(123, 53), EndPoint=(343, 58), Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Line [StartPoint=(123, 53), EndPoint=(343, 58), Color= RGB(0, 0, 0)] To: Line [StartPoint=(100, 100), EndPoint=(300, 300), Color= RGB(100, 240, 255)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(100, line.getStartPoint().getX());
		assertEquals(100, line.getStartPoint().getY());
		assertEquals(300, line.getEndPoint().getX());
		assertEquals(300, line.getEndPoint().getY());
		assertEquals(new Color(100, 240, 255), line.getEdgeColor());
	}

	@Test
	public void executeLineLogModifyingRectangle() {
		Rectangle rectangle = new Rectangle(new Point(78, 183), 100, 50, false, new Color(153, 0, 153),
				new Color(102, 102, 0));
		model.addShapeToList(rectangle);
		String lineLogSelect = "Select: Rectangle [UpperLeftPoint=(78, 183), Height=100, Width=50, OuterColor= RGB(153, 0, 153), InnerColor= RGB(102, 102, 0)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Rectangle [UpperLeftPoint=(78, 183), Height=100, Width=50, OuterColor= RGB(153, 0, 153), InnerColor= RGB(102, 102, 0)] To: Rectangle [UpperLeftPoint=(78, 183), Height=150, Width=150, OuterColor= RGB(150, 100, 150), InnerColor= RGB(212, 212, 100)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(78, rectangle.getUpperLeftPoint().getX());
		assertEquals(183, rectangle.getUpperLeftPoint().getY());
		assertEquals(150, rectangle.getHeight());
		assertEquals(150, rectangle.getWidth());
		assertEquals(new Color(150, 100, 150), rectangle.getEdgeColor());
		assertEquals(new Color(212, 212, 100), rectangle.getInnerColor());
	}

	@Test
	public void executeLineLogModifyingDonut() {
		Donut donut = new Donut(new Point(407, 279), 100, 70, false, new Color(153, 0, 0), new Color(255, 153, 153));
		model.addShapeToList(donut);
		String lineLogSelect = "Select: Donut [Center=(407, 279), OuterRadius=100, InnerRadius=70, OuterColor= RGB(153, 0, 0), InnerColor= RGB(255, 153, 153)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Donut [Center=(407, 279), OuterRadius=100, InnerRadius=70, OuterColor= RGB(153, 0, 0), InnerColor= RGB(255, 153, 153)] To: Donut [Center=(300, 250), OuterRadius=120, InnerRadius=60, OuterColor= RGB(153, 153, 0), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(300, donut.getCenter().getX());
		assertEquals(250, donut.getCenter().getY());
		assertEquals(120, donut.getRadius());
		assertEquals(60, donut.getInnerRadius());
		assertEquals(new Color(153, 153, 0), donut.getEdgeColor());
		assertEquals(new Color(255, 255, 0), donut.getInnerColor());
	}

	@Test
	public void executeLineLogModifyingHexagon() {
		HexagonAdapter hexagon = new HexagonAdapter(new Point(191, 300), 100, false, new Color(51, 51, 255),
				new Color(0, 255, 0));
		model.addShapeToList(hexagon);
		String lineLogSelect = "Select: Hexagon [Center=(191, 300), Radius=100, OuterColor= RGB(51, 51, 255), InnerColor= (0, 255, 0)]";
		logParser.executeLineLog(lineLogSelect);
		String lineLogModifying = "Modifying: Hexagon [Center=(191, 300), Radius=100, OuterColor= RGB(51, 51, 255), InnerColor= (0, 255, 0)] To: Hexagon [Center=(100, 200), Radius=150, OuterColor= RGB(151, 151, 0), InnerColor= (255, 255, 0)]";
		logParser.executeLineLog(lineLogModifying);
		assertEquals(100, hexagon.getHexagonCenter().getX());
		assertEquals(200, hexagon.getHexagonCenter().getY());
		assertEquals(150, hexagon.getHexagonRadius());
		assertEquals(new Color(151, 151, 0), hexagon.getHexagonBorderColor());
		assertEquals(new Color(255, 255, 0), hexagon.getHexagonInnerColor());
	}

	@Test
	public void executeLineLogAdding() {
		String lineLog = "Adding: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(1, model.getShapes().size());
	}

	@Test
	public void executeLineLogDeleting() {
		Rectangle rectangle = new Rectangle(new Point(78, 183), 100, 50, false, new Color(153, 0, 153),
				new Color(102, 102, 0));
		Line line = new Line(new Point(123, 53), new Point(343, 58));
		Point point = new Point(77, 70);
		model.addShapeToList(rectangle);
		model.addShapeToListOfSelected(line);
		model.addShapeToListOfSelected(point);
		String lineLogDeletingPoint = "Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogDeletingPoint);
		String lineLogDeletingLine = "Deleting: Line [StartPoint=(123, 53), EndPoint=(343, 58), Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogDeletingLine);
		assertFalse(model.getShapes().contains(point));
		assertFalse(model.getShapes().contains(line));
		assertTrue(model.getShapes().contains(rectangle));
		assertEquals(1, model.getShapes().size());
	}

	@Test
	public void executeLineLogUndo() {
		Point point = new Point(77, 70);
		model.addShapeToListOfSelected(point);
		String lineLogDeletingPoint = "Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogDeletingPoint);
		String lineLogUndoDeletingPoint = "Undo: Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogUndoDeletingPoint);
		assertTrue(model.getShapes().contains(point));
		assertEquals(1, model.getShapes().size());
	}

	@Test
	public void executeLineLogEmptyUndo() {
		String lineLogUndo = "Undo: ";
		logParser.executeLineLog(lineLogUndo);
		assertFalse(buttonsStateControl.isBtnUndo());
	}

	@Test
	public void executeLineLogRedo() {
		Point point = new Point(77, 70);
		model.addShapeToListOfSelected(point);
		String lineLogDeletingPoint = "Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogDeletingPoint);
		String lineLogUndoDeletingPoint = "Undo: Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogUndoDeletingPoint);
		String lineLogRedoDeletingPoint = "Redo: Deleting: Point [x= 77, y= 70, Color= RGB(0, 0, 0)]";
		logParser.executeLineLog(lineLogRedoDeletingPoint);
		assertFalse(model.getShapes().contains(point));
		assertEquals(0, model.getShapes().size());
	}

	@Test
	public void executeLineLogEmptyRedo() {
		String lineLogRedo = "Redo: ";
		logParser.executeLineLog(lineLogRedo);
		assertFalse(buttonsStateControl.isBtnRedo());
	}

	@Test
	public void executeLineLogBringToFront() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		Donut donut = new Donut(new Point(407, 279), 100, 70, false, new Color(153, 0, 0), new Color(255, 153, 153));
		model.addShapeToList(donut);
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(donut, model.getShapeFromIndex(1));
		model.addShapeToListOfSelected(circle);
		String lineLog = "BringToFront: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(donut, model.getShapeFromIndex(0));
	}

	@Test
	public void executeLineLogToFront() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		Donut donut = new Donut(new Point(407, 279), 100, 70, false, new Color(153, 0, 0), new Color(255, 153, 153));
		model.addShapeToList(donut);
		HexagonAdapter hexagon = new HexagonAdapter(new Point(191, 300), 100, false, new Color(51, 51, 255),
				new Color(0, 255, 0));
		model.addShapeToList(hexagon);
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(donut, model.getShapeFromIndex(1));
		assertEquals(hexagon, model.getShapeFromIndex(2));
		model.addShapeToListOfSelected(circle);
		String lineLog = "ToFront: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(circle, model.getShapeFromIndex(1));
		assertEquals(donut, model.getShapeFromIndex(0));
		assertEquals(hexagon, model.getShapeFromIndex(2));
	}

	@Test
	public void executeLineLogBringToBack() {
		Donut donut = new Donut(new Point(407, 279), 100, 70, false, new Color(153, 0, 0), new Color(255, 153, 153));
		model.addShapeToList(donut);
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		assertEquals(donut, model.getShapeFromIndex(0));
		assertEquals(circle, model.getShapeFromIndex(1));
		model.addShapeToListOfSelected(circle);
		String lineLog = "BringToBack: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(255, 255, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(donut, model.getShapeFromIndex(1));
	}

	@Test
	public void executeLineLogToBack() {
		Circle circle = new Circle(new Point(278, 178), 100, false, new Color(0, 51, 255), new Color(0, 204, 153));
		model.addShapeToList(circle);
		Donut donut = new Donut(new Point(407, 279), 100, 70, false, new Color(153, 0, 0), new Color(255, 153, 153));
		model.addShapeToList(donut);
		HexagonAdapter hexagon = new HexagonAdapter(new Point(191, 300), 100, false, new Color(51, 51, 255),
				new Color(0, 255, 0));
		model.addShapeToList(hexagon);
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(donut, model.getShapeFromIndex(1));
		assertEquals(hexagon, model.getShapeFromIndex(2));
		model.addShapeToListOfSelected(hexagon);
		String lineLog = "ToBack: Hexagon [Center=(191, 300), Radius=100, OuterColor= RGB(51, 51, 255), InnerColor= (0, 255, 0)]";
		logParser.executeLineLog(lineLog);
		assertEquals(circle, model.getShapeFromIndex(0));
		assertEquals(hexagon, model.getShapeFromIndex(1));
		assertEquals(donut, model.getShapeFromIndex(2));
	}

	@Test
    public void loadFileByLoadingTypeWholeDrawing() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        logParser.loadFileByLoadingType(0, scanner);
        verify(scanner, times(3)).hasNextLine();
        verify(scanner, times(1)).nextLine();
        assertFalse(frame.getBtnNext().isEnabled()); 
    }

	@Test
    public void loadFileByLoadingTypeEmptyLogFile() {
        when(scanner.hasNextLine()).thenReturn(false);
        logParser.loadFileByLoadingType(0, scanner);
        assertFalse(buttonsStateControl.isBtnUndo());
        assertFalse(buttonsStateControl.isBtnRedo());
    }

	@Test
    public void loadFileByLoadingTypeStepByStep() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        logParser.loadFileByLoadingType(1, scanner);
        assertTrue(frame.getBtnNext().isEnabled());
        logParser.handleNextButtonClick(scanner);
        logParser.handleNextButtonClick(scanner);
        verify(scanner, times(2)).nextLine();
    }

	@Test
	public void disableNextButtonAndCloseLogFile() {
		when(scanner.hasNextLine()).thenReturn(true, true, false);
        logParser.loadFileByLoadingType(1, scanner);
        assertTrue(frame.getBtnNext().isEnabled());
        logParser.disableNextButtonAndCloseLogFile(scanner);
        assertFalse(frame.getBtnNext().isEnabled());
	}

}
