package mvc;

import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeleteOneShape;
import command.CmdDeselectShape;
import command.CmdSelectShape;
import command.CmdToBackByOne;
import command.CmdToFrontByOne;
import command.Command;
import geometry.Shape;
import lombok.Getter;
import observer.BtnUpdate;
import observer.BtnUpdateObserver;

@Getter
public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape newShape;
	private Shape selectedShape;
	private Command command;
	private BtnUpdateObserver btnUpdateObserver;
	private BtnUpdate btnUpdate = new BtnUpdate();
	private ShapeAddHandler shapeAddHandler;
	private ShapeEditHandler shapeEditHandler;
	private FileHandler fileHandler;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		btnUpdateObserver = new BtnUpdateObserver(frame);
		btnUpdate.addPropertyChangeListener(btnUpdateObserver);
		this.shapeAddHandler = new ShapeAddHandler(this);
		this.shapeEditHandler = new ShapeEditHandler(this);
		this.fileHandler = new FileHandler(this);
	}

	//Setting buttons availability
	public void updateUndoRedoButtonsState() {
		updateButtonState(model.getUndo(), btnUpdate::setBtnUndo);
		updateButtonState(model.getRedo(), btnUpdate::setBtnRedo);
		updateSaveButtons();
		frame.repaint();
	}

	private void updateSaveButtons() {
		boolean isUndoEmpty = model.getUndo().isEmpty();
		boolean isRedoEmpty = model.getRedo().isEmpty();
		btnUpdate.setBtnSaveDrawing(isUndoEmpty || isRedoEmpty);
		btnUpdate.setBtnSaveCommands(isUndoEmpty || isRedoEmpty);
	}

	private void updateButtonState(List<?> list, Consumer<Boolean> buttonSetter) {
		buttonSetter.accept(!list.isEmpty());
	}

	//Setting buttons availability
	public void checkButtonState() {
		boolean hasShapes = !model.getShapes().isEmpty();
		boolean hasSelectedShapes = !model.getSelectedShapes().isEmpty();

		btnUpdate.setBtnSelect(hasShapes);
		btnUpdate.setBtnSaveDrawing(hasShapes);
		btnUpdate.setBtnSaveCommands(hasShapes);

		if (hasSelectedShapes) {
			if (model.getSelectedShapes().size() == 1) {
				btnUpdate.setBtnModification(true);
				btnUpdate.setBtnDelete(true);
				btnsUpdate();
			} else {
				setDefaultButtonState();
				btnUpdate.setBtnDelete(true);
			}
		} else {
			setDefaultButtonState();
			btnUpdate.setBtnDelete(false);
			btnUpdate.setBtnSaveDrawing(false);
		}

		if (frame.getTextArea().getText().isEmpty()) {
			btnUpdate.setBtnSaveDrawing(false);
			btnUpdate.setBtnSaveCommands(false);
		}
		frame.repaint();
	}

	private void setDefaultButtonState() {
		btnUpdate.setBtnModification(false);
		btnUpdate.setBtnBringToBack(false);
		btnUpdate.setBtnBringToFront(false);
		btnUpdate.setBtnToBack(false);
		btnUpdate.setBtnToFront(false);
	}

	//Setting buttons availability
	public void btnsUpdate() {
		if (model.getSelectedShapes().size() == 1) {
			int index = model.getShapes().indexOf(model.getSelectedShapes().get(0));
			int totalShapes = model.getShapes().size();
			boolean isFirstShape = (index == 0);
			boolean isLastShape = (totalShapes == index + 1);
			boolean isMiddleShape = (totalShapes > index + 1 && index > 0);

			btnUpdate.setBtnToBack(isMiddleShape || isLastShape);
			btnUpdate.setBtnBringToBack(isMiddleShape || isLastShape);
			btnUpdate.setBtnToFront(isFirstShape || isMiddleShape);
			btnUpdate.setBtnBringToFront(isFirstShape || isMiddleShape);
		} else
			setDefaultButtonState();
		frame.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		resetVariables();

		if (frame.getBtnSelect().isSelected() && model.getShapes() != null)
			handleShapeSelection(e);
		else if (frame.getBtnPoint().isSelected()) 
			newShape = shapeAddHandler.createPointShape(e);
		else if (frame.getBtnLine().isSelected())
			newShape = shapeAddHandler.createLineShape(e, newShape);
		else if (frame.getBtnRectangle().isSelected())
			newShape = shapeAddHandler.createRectangleShape(e, newShape);
		else if (frame.getBtnCircle().isSelected()) 
			newShape = shapeAddHandler.createCircleShape(e, newShape);
		else if (frame.getBtnDonut().isSelected()) 
			newShape = shapeAddHandler.createDonutShape(e, newShape);
		else if (frame.getBtnHexagon().isSelected()) 
			newShape = shapeAddHandler.createHexagonShape(e, newShape);
			
		if (newShape != null) {
			command = new CmdAddShape(model, newShape);
			setLogMessage(newShape, "Adding");
			executeCommand();
		}
		updateUIState();
	}

	private void resetVariables() {
		command = null;
		newShape = null;
		selectedShape = null;
	}

	public void handleShapeSelection(MouseEvent e) {
		Iterator<Shape> iterate = model.getShapes().iterator();
		while (iterate.hasNext()) {
			Shape shape = iterate.next();
			if (shape.contains(e.getX(), e.getY())) {
				if (shape.isSelected()) {
					command = new CmdDeselectShape(model, shape);
					setLogMessage(shape, "Deselect");
				} else {
					command = new CmdSelectShape(model, shape);
					setLogMessage(shape, "Select");
					selectedShape = shape;
				}
				executeCommand();
				checkButtonState();
				break;
			}
		}
	}

	public void setLogMessage(Shape shape, String action) {
		frame.getTextArea().append(action + ": " + shape.toString() + "\n");
	}

	private void executeCommand() {
		command.execute();
		model.getUndo().add(command);
	}

	protected void editShape() {
		command = null;

		if (model.getSelectedShapes().size() == 1) {
			Shape activeShape = model.getSelectedShapes().get(0);
			selectedShape = activeShape;
			command = shapeEditHandler.executeEditShape(activeShape, command, selectedShape);
			executeCommand();
		} else if (model.getSelectedShapes().isEmpty())
			showMessageNoShapeSelected();
		else 
			btnUpdate.setBtnModification(false);

		updateUIState();
	}

	protected void deleteShape() {
		command = null;
		if (model.getSelectedShapes().isEmpty()) 
			showMessageNoShapeSelected();
		else {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION)
				executeDeleteOperation();
		}
		updateUIState();
	}

	private void executeDeleteOperation() {
		for (int i = model.getSelectedShapes().size() - 1; i >= 0; i--) {
			command = new CmdDeleteOneShape(model, model.getSelectedShapes().get(i));
			setLogMessage(model.getSelectedShapes().get(i), "Deleting");
			executeCommand();
		}
	}

	public void showMessageNoShapeSelected() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/er.png"));
		JOptionPane.showMessageDialog(null, "You did not select any shape!", "Error", JOptionPane.WARNING_MESSAGE,
				icon);
	}
	
	public void updateUIState() {
		checkButtonState();
		updateUndoRedoButtonsState();
		model.getRedo().clear();
		btnUpdate.setBtnRedo(false);
		frame.repaint();
	}

	public void fullBringToBack() {
		command = null;
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		command = new CmdBringToBack(model, shape, index);
		setLogMessage(shape, "BringToBack");
		executeCommandAndUpdateUIState();
	}

	public void bringToBackByOne() {
		command = null;
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		command = new CmdToBackByOne(model, shape, index);
		setLogMessage(shape, "ToBack");
		executeCommandAndUpdateUIState();
	}

	public void fullBringToFront() {
		command = null;
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		command = new CmdBringToFront(model, shape, index);
		setLogMessage(shape, "BringToFront");
		executeCommandAndUpdateUIState();
	}

	public void bringToFrontByOne() {
		command = null;
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		command = new CmdToFrontByOne(model, shape, index);
		setLogMessage(shape, "ToFront");
		executeCommandAndUpdateUIState();
	}

	private void executeCommandAndUpdateUIState() {
		executeCommand();
		checkButtonState();
		model.getRedo().clear();
		btnUpdate.setBtnRedo(false);
		frame.repaint();
	}

	public void undo() {
		if (!model.getUndo().isEmpty()) {
			Command undoCommand = model.getUndo().pop();
			frame.getTextArea().append("Undo: " + undoCommand.toString());
			undoCommand.unexecute();
			model.getRedo().add(undoCommand);
			btnUpdate.setBtnRedo(true);
			if (model.getUndo().isEmpty())
				btnUpdate.setBtnUndo(false);
		} else
			btnUpdate.setBtnUndo(false);
		
		updateUI();
	}

	public void redo() {
		if (!model.getRedo().isEmpty()) {
			Command redoCommand = model.getRedo().pop();
			frame.getTextArea().append("Redo: " + redoCommand.toString());
			redoCommand.execute();
			model.getUndo().add(redoCommand);
			btnUpdate.setBtnUndo(true);
			if (model.getRedo().isEmpty())
				btnUpdate.setBtnRedo(false);
		} else
			btnUpdate.setBtnRedo(false);

		updateUI();
	}

	private void updateUI() {
		checkButtonState();
		frame.repaint();
	}

	public void loadDrawing() {
		fileHandler.loadDrawing();
	}

	public void saveDrawing() {
		fileHandler.saveDrawing();
	}

	public void saveLog() {
		fileHandler.saveLog();
	}

	public void loadLog() {
		fileHandler.loadLog();
	}

}
