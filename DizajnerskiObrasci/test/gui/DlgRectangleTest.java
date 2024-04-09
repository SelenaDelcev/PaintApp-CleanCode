package gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import org.junit.Before;
import org.junit.Test;

public class DlgRectangleTest {
	private DlgRectangle dialog;
	private OptionPane optionPane;

	@Before
	public void setUp() {
		dialog = new DlgRectangle();
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
        dialog.getTxtHeight().setText("");
        dialog.getTxtWidth().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyCoordinates() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("");
        dialog.getTxtWidth().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyXCoordinate() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("");
        dialog.getTxtHeight().setText("");
        dialog.getTxtWidth().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithOnlyYCoordinate() {
        dialog.getTxtX().setText("");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("");
        dialog.getTxtWidth().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithSetHeight() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("50");
        dialog.getTxtWidth().setText("");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithSetWidth() {
        dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("");
        dialog.getTxtWidth().setText("80");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        assertTrue(dialog.isVisible());
        verifyEmptyInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithFilledFields() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("50");
        dialog.getTxtWidth().setText("80");
        dialog.getOkButton().doClick();
        assertTrue(dialog.isOK());
        assertFalse(dialog.isVisible());
    }
	
	@Test
    public void testOKButtonClickWithHeightLessThanZero() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("-50");
        dialog.getTxtWidth().setText("80");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        verifyNegativeInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithWidthLessThanZero() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("50");
        dialog.getTxtWidth().setText("-80");
        dialog.getOkButton().doClick();
        assertFalse(dialog.isOK());
        verifyNegativeInputInfoMessage();
    }
	
	@Test
    public void testOKButtonClickWithHeightAndWidthLessThanZero() {
		dialog.getTxtX().setText("100");
        dialog.getTxtY().setText("150");
        dialog.getTxtHeight().setText("-50");
        dialog.getTxtWidth().setText("-80");
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
    public void testCoordinateInputWithValidCharacterForHeight() {
        KeyEvent event = new KeyEvent(dialog.getTxtHeight(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5');
        dialog.handleInputValidation(event);
        assertFalse(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithValidCharacterForWidth() {
        KeyEvent event = new KeyEvent(dialog.getTxtWidth(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '5');
        dialog.handleInputValidation(event);
        assertFalse(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithInvalidCharacterForHeigth() {
        KeyEvent event = new KeyEvent(dialog.getTxtHeight(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithInvalidCharacterForWidth() {
        KeyEvent event = new KeyEvent(dialog.getTxtWidth(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
	
	@Test
    public void testCoordinateInputWithBackspace() {
        KeyEvent event = new KeyEvent(dialog.getTxtHeight(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }

    @Test
    public void testCoordinateInputWithDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtHeight(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DELETE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
    
    @Test
    public void testCoordinateInputWithNonBackspaceOrDelete() {
        KeyEvent event = new KeyEvent(dialog.getTxtHeight(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        dialog.handleInputValidation(event);
        assertTrue(event.isConsumed());
    }
	
	private void verifyEmptyInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	private void verifyNegativeInputInfoMessage() {
    	verify(optionPane).showMessageDialog(null, "Height and width must be greater than 0!", "Error", JOptionPane.WARNING_MESSAGE);
	}

}
