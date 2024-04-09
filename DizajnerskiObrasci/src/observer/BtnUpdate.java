package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import lombok.Getter;

@Getter
public class BtnUpdate {
	private PropertyChangeSupport propertyChangeSupport;
	private boolean btnSelect;
	private boolean btnModification;
	private boolean btnDelete;
	private boolean btnBringToBack;
	private boolean btnBringToFront;
	private boolean btnToBack;
	private boolean btnToFront;
	private boolean btnUndo;
	private boolean btnRedo;
	private boolean btnSaveDrawing;
	private boolean btnSaveCommands;

	public BtnUpdate() {
		super();
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public BtnUpdate(PropertyChangeSupport propertyChangeSupport) {
        super();
        this.propertyChangeSupport = propertyChangeSupport;
    }

	public void setBtnSaveCommands(boolean btnSaveCommads) {
		propertyChangeSupport.firePropertyChange("btnSaveCommands", this.btnSaveCommands, btnSaveCommads);
		this.btnSaveCommands = btnSaveCommads;
	}

	public void setBtnSaveDrawing(boolean btnSaveDrawing) {
		propertyChangeSupport.firePropertyChange("btnSaveDrawing", this.btnSaveDrawing, btnSaveDrawing);
		this.btnSaveDrawing = btnSaveDrawing;
	}

	public void setBtnUndo(boolean btnUndo) {
		propertyChangeSupport.firePropertyChange("btnUndo", this.btnUndo, btnUndo);
		this.btnUndo = btnUndo;
	}

	public void setBtnRedo(boolean btnRedo) {
		propertyChangeSupport.firePropertyChange("btnRedo", this.btnRedo, btnRedo);
		this.btnRedo = btnRedo;
	}

	public void setBtnSelect(boolean btnSelect) {
		propertyChangeSupport.firePropertyChange("btnSelect", this.btnSelect, btnSelect);
		this.btnSelect = btnSelect;
	}

	public void setBtnModification(boolean btnModification) {
		propertyChangeSupport.firePropertyChange("btnModification", this.btnModification, btnModification);
		this.btnModification = btnModification;
	}

	public void setBtnDelete(boolean btnDelete) {
		propertyChangeSupport.firePropertyChange("btnDelete", this.btnDelete, btnDelete);
		this.btnDelete = btnDelete;
	}

	public void setBtnBringToBack(boolean btnBringToBack) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.btnBringToBack, btnBringToBack);
		this.btnBringToBack = btnBringToBack;
	}

	public void setBtnBringToFront(boolean btnBringToFront) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.btnBringToFront, btnBringToFront);
		this.btnBringToFront = btnBringToFront;
	}

	public void setBtnToBack(boolean btnToBack) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.btnToBack, btnToBack);
		this.btnToBack = btnToBack;
	}

	public void setBtnToFront(boolean btnToFront) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.btnToFront, btnToFront);
		this.btnToFront = btnToFront;
	}

	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

}
