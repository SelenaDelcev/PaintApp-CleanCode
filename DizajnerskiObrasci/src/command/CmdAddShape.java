package command;

import geometry.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import mvc.DrawingModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CmdAddShape implements Command {
	private DrawingModel model;
	private Shape shape;

	@Override
	public void execute() {
		model.addShapeToList(shape);
	}

	@Override
	public void unexecute() {
		model.deleteShapeFromList(shape);
	}

	public String toString() {
		return "Adding: " + shape.toString() + "\n";
	}

}
