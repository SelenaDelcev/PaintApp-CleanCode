package gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

public class DlgLineTest {
	private DlgLine dialog;
	private OptionPane optionPane;

	@Before
	public void setUp() {
		dialog = new DlgLine();
        optionPane = mock(OptionPane.class);
        dialog.setOptionPane(optionPane);
	}

	@Test
    public void testInitialization() {
        assertNotNull(dialog);
    }
	
	@Test
    public void testOKButtonClickWithEmptyFields() {
        dialog.getTxtXStartPoint().setText("");
        dialog.getTxtYStartPoint().setText("");
        dialog.getTxtXEndPoint().setText("");
        dialog.getTxtYEndPoint().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithEmptyXStartPointFields() {
        dialog.getTxtXStartPoint().setText("");
        dialog.getTxtYStartPoint().setText("20");
        dialog.getTxtXEndPoint().setText("50");
        dialog.getTxtYEndPoint().setText("100");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithEmptyYStartPointFields() {
        dialog.getTxtXStartPoint().setText("10");
        dialog.getTxtYStartPoint().setText("");
        dialog.getTxtXEndPoint().setText("50");
        dialog.getTxtYEndPoint().setText("100");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithEmptyEndPointFields() {
        dialog.getTxtXStartPoint().setText("10");
        dialog.getTxtYStartPoint().setText("20");
        dialog.getTxtXEndPoint().setText("");
        dialog.getTxtYEndPoint().setText("100");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithEmptyYEndPointFields() {
        dialog.getTxtXStartPoint().setText("10");
        dialog.getTxtYStartPoint().setText("20");
        dialog.getTxtXEndPoint().setText("50");
        dialog.getTxtYEndPoint().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithFilledFields() {
        dialog.getTxtXStartPoint().setText("10");
        dialog.getTxtYStartPoint().setText("20");
        dialog.getTxtXEndPoint().setText("50");
        dialog.getTxtYEndPoint().setText("100");
        dialog.getOkButton().doClick();
        assertTrue(dialog.isOK());
        assertFalse(dialog.isVisible());
    }
	
	@Test
    public void testCancelButtonClick() {
        dialog.getCancelButton().doClick();
        assertFalse(dialog.isVisible());
    }
    
    @Test
    public void testColorButtonClick() {
        dialog.getColorButton().doClick();
        assertTrue(dialog.isColorChosen());
    }
    
    @Test
    public void testCoordinateInputWithValidCharacter() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5');
        dialog.handleCoordinateInput(event);
        assertFalse(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithInvalidCharacter() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNegativeNumber() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '-');
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithBackspace() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DELETE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNonBackspaceOrDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithBackspaceAndEscapePressedSimultaneously() {
        KeyEvent backspaceEvent = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, KeyEvent.CHAR_UNDEFINED);
        KeyEvent escapeEvent = new KeyEvent(dialog.getTxtXStartPoint(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(backspaceEvent);
        dialog.handleCoordinateInput(escapeEvent); 
        assertTrue(backspaceEvent.isConsumed());
        assertTrue(escapeEvent.isConsumed());
    }
	
	private void verifyEmptyInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
	}

}
