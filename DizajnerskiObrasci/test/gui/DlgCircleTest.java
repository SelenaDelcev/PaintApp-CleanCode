package gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import org.junit.Before;
import org.junit.Test;

public class DlgCircleTest {
	private DlgCircle dialog;
	private OptionPane optionPane;

	@Before
	public void setUp() {
		dialog = new DlgCircle();
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
        dialog.getTxtRadius().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyCoordinates() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtRadius().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyXCoordinate() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("");
        dialog.getTxtRadius().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyYCoordinate() {
        dialog.getTxtX().setText("");
        dialog.getTxtY().setText("150");
        dialog.getTxtRadius().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithFilledFields() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtRadius().setText("80");
        dialog.getOkButton().doClick();
        assertTrue(dialog.isOK());
        assertFalse(dialog.isVisible());
    }
	
	@Test
    public void testOKButtonClickWithRadiusLessThanZero() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtRadius().setText("-80");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        verifyNegativeInputInfoMessage();
    }
	
	@Test
    public void testCancelButtonClick() {
        dialog.getCancelButton().doClick();
        assertFalse(dialog.isVisible());
    }
	
	@Test
	public void testOuterColorButton() {
	    dialog.getBtnOuterColor().doClick();
	    assertTrue(dialog.isOuterColorChosen());
	    Color selectedColor = dialog.getOuterColor();
	    assertEquals(selectedColor.getRGB(), dialog.getBtnOuterColor().getBackground().getRGB());
	}
	
	@Test
	public void testInnerColorButton() {
	    dialog.getBtnInnerColor().doClick();
	    assertTrue(dialog.isInnerColorChosen());
	    Color selectedColor = dialog.getInnerColor();
	    assertEquals(selectedColor.getRGB(), dialog.getBtnInnerColor().getBackground().getRGB());
	}
	
	@Test
    public void testCoordinateInputWithValidCharacterForRadius() {
        KeyEvent event = new KeyEvent(dialog.getTxtRadius(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5');
        dialog.handleInputValidation(event);
        assertFalse(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithInvalidCharacterForRadius() {
        KeyEvent event = new KeyEvent(dialog.getTxtRadius(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithBackspace() {
        KeyEvent event = new KeyEvent(dialog.getTxtRadius(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtRadius(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DELETE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNonBackspaceOrDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtRadius(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
	
	private void verifyEmptyInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	private void verifyNegativeInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "Radius must be greater than 0!", "Error", JOptionPane.WARNING_MESSAGE);
	}

}
