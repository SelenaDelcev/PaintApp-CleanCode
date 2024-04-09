package observer;

import static org.junit.Assert.*;
import java.beans.PropertyChangeEvent;
import org.junit.Before;
import org.junit.Test;
import mvc.DrawingFrame;

public class BtnUpdateObserverTest {
    private BtnUpdateObserver observer;
    private DrawingFrame frame;

    @Before
    public void setUp() {
        frame = new DrawingFrame();
        observer = new BtnUpdateObserver(frame);
    }

    @Test
    public void testBtnSelectEnabled() {
        assertFalse(frame.getBtnSelect().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnSelect", false, true));
        assertTrue(frame.getBtnSelect().isEnabled());
    }
    
    @Test
    public void testBtnDeleteEnabled() {
        assertFalse(frame.getBtnDelete().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnDelete", false, true));
        assertTrue(frame.getBtnDelete().isEnabled());
    }
    
    @Test
    public void testBtnModificationEnabled() {
        assertFalse(frame.getBtnModification().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnModification", false, true));
        assertTrue(frame.getBtnModification().isEnabled());
    }
    
    @Test
    public void testBtnToFrontEnabled() {
        assertFalse(frame.getBtnToFront().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnToFront", false, true));
        assertTrue(frame.getBtnToFront().isEnabled());
    }
    
    @Test
    public void testBtnBringToFrontEnabled() {
        assertFalse(frame.getBtnBringToFront().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnBringToFront", false, true));
        assertTrue(frame.getBtnBringToFront().isEnabled());
    }
    
    @Test
    public void testBtnToBackEnabled() {
        assertFalse(frame.getBtnToBack().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnToBack", false, true));
        assertTrue(frame.getBtnToBack().isEnabled());
    }
    
    @Test
    public void testBtnBringToBackEnabled() {
        assertFalse(frame.getBtnBringToBack().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnBringToBack", false, true));
        assertTrue(frame.getBtnBringToBack().isEnabled());
    }
    
    @Test
    public void testBtnUndoEnabled() {
        assertFalse(frame.getBtnUndo().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnUndo", false, true));
        assertTrue(frame.getBtnUndo().isEnabled());
    }
    
    @Test
    public void testBtnRedoEnabled() {
        assertFalse(frame.getBtnRedo().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnRedo", false, true));
        assertTrue(frame.getBtnRedo().isEnabled());
    }
    
    @Test
    public void testBtnSaveCommandsEnabled() {
        assertFalse(frame.getBtnSaveCommands().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnSaveCommands", false, true));
        assertTrue(frame.getBtnSaveCommands().isEnabled());
    }
    
    @Test
    public void testBtnSaveDrawingEnabled() {
        assertFalse(frame.getBtnSaveDrawing().isEnabled());
        observer.propertyChange(new PropertyChangeEvent(this, "btnSaveDrawing", false, true));
        assertTrue(frame.getBtnSaveDrawing().isEnabled());
    }

}
