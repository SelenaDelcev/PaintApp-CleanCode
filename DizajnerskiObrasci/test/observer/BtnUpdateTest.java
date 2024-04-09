package observer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class BtnUpdateTest {
    private BtnUpdate btnUpdate;
    private PropertyChangeSupport propertyChangeSupport;
    private PropertyChangeListener propertyChangeListener;

    @Before
    public void setUp() {
        propertyChangeSupport = mock(PropertyChangeSupport.class);
        btnUpdate = new BtnUpdate(propertyChangeSupport);
        propertyChangeListener = mock(PropertyChangeListener.class);
    }
    
    @Test
    public void testEmptyConstructorExpectedNotNull() {
    	BtnUpdate btnUpdateEmpty = new BtnUpdate();
        assertNotNull(btnUpdateEmpty.getPropertyChangeSupport());
    }
    
    @Test
    public void testBtnSaveCommands() {
        btnUpdate.setBtnSaveCommands(true);
        assertTrue(btnUpdate.isBtnSaveCommands());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnSaveCommands", false, true);
    }
    
    @Test
    public void testBtnSaveDrawing() {
        btnUpdate.setBtnSaveDrawing(true);
        assertTrue(btnUpdate.isBtnSaveDrawing());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnSaveDrawing", false, true);
    }
    
    @Test
    public void testBtnUndo() {
        btnUpdate.setBtnUndo(true);
        assertTrue(btnUpdate.isBtnUndo());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnUndo", false, true);
    }
    
    @Test
    public void testBtnRedo() {
        btnUpdate.setBtnRedo(true);
        assertTrue(btnUpdate.isBtnRedo());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnRedo", false, true);
    }

    @Test
    public void testBtnSelect() {
        btnUpdate.setBtnSelect(true);
        assertTrue(btnUpdate.isBtnSelect());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnSelect", false, true);
    }

    @Test
    public void testBtnModification() {
        btnUpdate.setBtnModification(true);
        assertTrue(btnUpdate.isBtnModification());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnModification", false, true);
    }
    
    @Test
    public void testBtnDelete() {
        btnUpdate.setBtnDelete(true);
        assertTrue(btnUpdate.isBtnDelete());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnDelete", false, true);
    }
    
    @Test
    public void testBtnBringToBack() {
        btnUpdate.setBtnBringToBack(true);
        assertTrue(btnUpdate.isBtnBringToBack());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnBringToBack", false, true);
    }
    
    @Test
    public void testBtnBringToFront() {
        btnUpdate.setBtnBringToFront(true);
        assertTrue(btnUpdate.isBtnBringToFront());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnBringToFront", false, true);
    }
    
    @Test
    public void testBtnToBack() {
        btnUpdate.setBtnToBack(true);
        assertTrue(btnUpdate.isBtnToBack());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnToBack", false, true);
    }
    
    @Test
    public void testBtnToFront() {
        btnUpdate.setBtnToFront(true);
        assertTrue(btnUpdate.isBtnToFront());
        Mockito.verify(propertyChangeSupport).firePropertyChange("btnToFront", false, true);
    }
    
    @Test
    public void testAddPropertyChangeListener() {
        btnUpdate.addPropertyChangeListener(propertyChangeListener);
        verify(propertyChangeSupport).addPropertyChangeListener(propertyChangeListener);
    }

    @Test
    public void testRemovePropertyChangeListener() {
        btnUpdate.addPropertyChangeListener(propertyChangeListener);
        btnUpdate.removePropertyChangeListener(propertyChangeListener);
        verify(propertyChangeSupport).removePropertyChangeListener(propertyChangeListener);
    }
    
}
