package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import lombok.AllArgsConstructor;
import mvc.DrawingFrame;

@AllArgsConstructor
public class BtnUpdateObserver implements PropertyChangeListener {
	private DrawingFrame frame;

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("btnSelect")) {
			frame.getBtnSelect().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnDelete")) {
			frame.getBtnDelete().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnModification")) {
			frame.getBtnModification().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnToFront")) {
			frame.getBtnToFront().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnBringToFront")) {
			frame.getBtnBringToFront().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnToBack")) {
			frame.getBtnToBack().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnBringToBack")) {
			frame.getBtnBringToBack().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnUndo")) {
			frame.getBtnUndo().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnRedo")) {
			frame.getBtnRedo().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnSaveCommands")) {
			frame.getBtnSaveCommands().setEnabled((boolean) event.getNewValue());
		}
		if (event.getPropertyName().equals("btnSaveDrawing")) {
			frame.getBtnSaveDrawing().setEnabled((boolean) event.getNewValue());
		}
	}
}
