package gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.junit.Before;
import org.junit.Test;

public class DlgPointTest {
    private DlgPoint dialog;
    private OptionPane optionPane;
    
    @Before
    public void setUp() {
        dialog = new DlgPoint();
        optionPane = mock(OptionPane.class);
        dialog.setOptionPane(optionPane);
    }

    @Test
    public void testInitialization() {
        assertNotNull(dialog);
    }
    
    @Test
    public void testOKButtonClickWithEmptyFields() {
        dialog.getTxtX().setText("");
        dialog.getTxtY().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
    
    @Test
    public void testOKButtonClickWithEmptyXFields() {
        dialog.getTxtX().setText("");
        dialog.getTxtY().setText("20");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
    
    @Test
    public void testOKButtonClickWithEmptyYFields() {
        dialog.getTxtX().setText("10");
        dialog.getTxtY().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
    
    @Test
    public void testOKButtonClickWithFilledFields() {
        dialog.getTxtX().setText("10");
        dialog.getTxtY().setText("20");
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
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5');
        dialog.handleCoordinateInput(event);
        assertFalse(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithInvalidCharacter() {
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNegativeNumber() {
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '-');
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithBackspace() {
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DELETE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNonBackspaceOrDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtX(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        dialog.handleCoordinateInput(event);
        assertTrue(event.isConsumed());
    }
    
    private void verifyEmptyInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
	}
    
}
