package command;

import geometry.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.DrawingModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CmdDeleteOneShape implements Command {
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		model.deleteShapeFromList(shape);
		model.deleteShapeFromListOfSelected(shape);
	}

	@Override
	public void unexecute() {
		model.addShapeToList(shape);
		model.addShapeToListOfSelected(shape);
	}

	public String toString() {
		return "Deleting: " + shape.toString() + "\n";
	}
}
