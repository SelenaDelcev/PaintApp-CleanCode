package strategy;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;

public class LogFileTest {
	private LogFile logFile;
	private DrawingModel model;
	private DrawingFrame frame;

	@Before
	public void setUp() {
		model = new DrawingModel();
		frame = new DrawingFrame();
		DrawingController controller = new DrawingController(model, frame);
		BtnUpdate btnUpdate = new BtnUpdate();
		logFile = new LogFile(model, frame, controller, btnUpdate);
	}

	@Test
	public void saveFileSuccess() throws FileNotFoundException {
		String content = "Adding: Circle [Center=(278, 178), Radius=100, OuterColor= RGB(0, 51, 255), InnerColor= RGB(0, 204, 153)]";
		String filePath = System.getProperty("user.home") + "\\Documents\\test_log_file.txt";
		frame.getTextArea().setText(content);
		logFile.save(filePath);
		File testFile = new File(filePath);
		assertTrue(testFile.exists());
		Scanner scanner = new Scanner(testFile);
		StringBuilder fileContent = new StringBuilder();
		while (scanner.hasNextLine())
			fileContent.append(scanner.nextLine());
		assertEquals(content, fileContent.toString());
		scanner.close();
	}
	
	@Test
	public void saveFileFail() {
		String filePath = System.getProperty("user.home") + "\\Desktop\\test_log_file.txt";
		logFile.save(filePath);
	}
	
	@Test
	public void loadFileSuccess() {
		String filePath = System.getProperty("user.home") + "\\Documents\\test_log_file.txt";
		logFile.load(filePath);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getUndo().isEmpty());
		assertTrue(model.getRedo().isEmpty());
		assertFalse(frame.getBtnUndo().isEnabled());
		assertFalse(frame.getBtnRedo().isEnabled());
	}
	
	@Test
	public void loadFileFail() {
		String filePath = System.getProperty("user.home") + "\\Documents\\test.txt";
		logFile.load(filePath);
	}

}