package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import mvc.DrawingModel;

@AllArgsConstructor
public class CmdBringToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		try {
			model.deleteShapeAtIndex(index);
			model.getShapes().add(shape);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		try {
			model.deleteShapeAtIndex(model.getShapes().size() - 1);
			model.addShapeAtIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "BringToFront: " + shape.toString() + "\n";
	}

}
