package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import mvc.DrawingModel;

@AllArgsConstructor
public class CmdBringToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		try {
			model.deleteShapeAtIndex(index);
			model.addShapeAtIndex(shape, 0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unexecute() {
		try {
			model.deleteShapeAtIndex(0);
			model.addShapeAtIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "BringToBack: " + shape.toString() + "\n";
	}

}
