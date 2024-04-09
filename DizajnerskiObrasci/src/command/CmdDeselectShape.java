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
public class CmdDeselectShape implements Command {
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		shape.setSelected(false);
		model.deleteShapeFromListOfSelected(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		model.addShapeToListOfSelected(shape);
	}

	public String toString() {
		return "Deselect: " + shape.toString() + "\n";
	}

}
