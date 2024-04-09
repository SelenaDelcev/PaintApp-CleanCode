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
public class CmdSelectShape implements Command {
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		shape.setSelected(true);
		model.addShapeToListOfSelected(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.deleteShapeFromListOfSelected(shape);
	}

	public String toString() {
		return "Select: " + shape.toString() + "\n";
	}

}
