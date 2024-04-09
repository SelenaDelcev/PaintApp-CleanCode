package strategy;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import geometry.*;
import mvc.DrawingFrame;
import mvc.DrawingModel;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

public class PaintingFileTest {
	private PaintingFile paintingFile;
	private DrawingModel model;
	private DrawingFrame frame;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		paintingFile = new PaintingFile(model, frame);
	}

	@Test
    public void saveDrawingSuccess() {
		String filePath = System.getProperty("user.home") + "\\Documents\\test_drawing_file.txt";
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(new Point(100, 100), 100, false, Color.RED, Color.PINK));
        shapes.add(new Rectangle(new Point(150, 150), 150, 150, false, Color.BLUE, Color.CYAN));
        model.setShapes(shapes);
        paintingFile.save(filePath);
        File testFile = new File(filePath);
        assertTrue(testFile.exists());
    }
	
	@Test
    public void saveDrawingFail() {
		String filePath = System.getProperty("user.home") + "\\Desktop\\test_drawing_file.txt";
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(new Point(100, 100), 100, false, Color.RED, Color.PINK));
        shapes.add(new Rectangle(new Point(150, 150), 150, 150, false, Color.BLUE, Color.CYAN));
        model.setShapes(shapes);
        paintingFile.save(filePath);
	}
	
	@Test
    public void loadDrawingSuccess() {
		String filePath = System.getProperty("user.home") + "\\Documents\\test_drawing_file.txt";
		paintingFile.load(filePath);
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getUndo().isEmpty());
		assertTrue(model.getRedo().isEmpty());
		assertTrue(frame.getBtnSelect().isEnabled());
		assertFalse(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}
	
	@Test
	public void loadDrawingFail() {
		String filePath = System.getProperty("user.home") + "\\Desktop\\test_drawing_file.txt";
		paintingFile.load(filePath);
	}
}
