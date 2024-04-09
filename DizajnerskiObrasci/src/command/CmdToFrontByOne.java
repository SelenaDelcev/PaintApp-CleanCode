package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import mvc.DrawingModel;

@AllArgsConstructor
public class CmdToFrontByOne implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	@Override
	public void execute() {
		try {
			model.deleteShapeAtIndex(index);
			model.addShapeAtIndex(shape, index + 1);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void unexecute() {
		try {
			model.deleteShapeAtIndex(index + 1);
			model.addShapeAtIndex(shape, index);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "ToFront: " + shape.toString() + "\n";
	}

}
