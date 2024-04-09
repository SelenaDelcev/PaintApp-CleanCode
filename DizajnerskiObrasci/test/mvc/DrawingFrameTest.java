package mvc;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class DrawingFrameTest {
	private DrawingFrame frame;
	private DrawingController controller;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame = new DrawingFrame();
		frame.setController(controller);
	}
	
	@Test
	public void checkClickUndo() {
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnUndo().doClick();
		verify(controller).undo();
	}
	
	@Test
	public void checkClickRedo() {
		frame.getBtnRedo().setEnabled(true);
		frame.getBtnRedo().doClick();
		verify(controller).redo();
	}
	
	@Test
	public void checkClickToFront() {
		frame.getBtnToFront().setEnabled(true);
		frame.getBtnToFront().doClick();
		verify(controller).bringToFrontByOne();
	}
	
	@Test
	public void checkClickToBack() {
		frame.getBtnToBack().setEnabled(true);
		frame.getBtnToBack().doClick();
		verify(controller).bringToBackByOne();
	}
	
	@Test
	public void checkClickBringToFront() {
		frame.getBtnBringToFront().setEnabled(true);
		frame.getBtnBringToFront().doClick();
		verify(controller).fullBringToFront();
	}
	
	@Test
	public void checkClickBringToBack() {
		frame.getBtnBringToBack().setEnabled(true);
		frame.getBtnBringToBack().doClick();
		verify(controller).fullBringToBack();
	}
	
	@Test
	public void checkClickSaveCommands() {
		frame.getBtnSaveCommands().setEnabled(true);
		frame.getBtnSaveCommands().doClick();
		verify(controller).saveLog();
	}
	
	@Test
	public void checkClickLoadCommands() {
		frame.getBtnLoadCommands().setEnabled(true);
		frame.getBtnLoadCommands().doClick();
		verify(controller).loadLog();
	}
	
	@Test
	public void checkClickSaveDrawing() {
		frame.getBtnSaveDrawing().setEnabled(true);
		frame.getBtnSaveDrawing().doClick();
		verify(controller).saveDrawing();
	}
	
	@Test
	public void checkClickLoadDrawing() {
		frame.getBtnLoadDrawing().setEnabled(true);
		frame.getBtnLoadDrawing().doClick();
		verify(controller).loadDrawing();
	}
	
	@Test
	public void checkClickModify() {
		frame.getBtnModification().setEnabled(true);
		frame.getBtnModification().doClick();
		verify(controller).editShape();
	}
	
	@Test
	public void checkClickDelete() {
		frame.getBtnDelete().setEnabled(true);
		frame.getBtnDelete().doClick();
		verify(controller).deleteShape();
	}
	
}
