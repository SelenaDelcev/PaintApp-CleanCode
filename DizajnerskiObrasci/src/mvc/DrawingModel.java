package mvc;

import java.util.ArrayList;
import java.util.Stack;
import command.Command;
import geometry.Shape;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	private Stack<Command> undo = new Stack<>();
	private Stack<Command> redo = new Stack<>();

	public void addShapeToListOfSelected(Shape shape) {
		selectedShapes.add(shape);
	}

	public void deleteShapeFromListOfSelected(Shape shape) {
		selectedShapes.remove(shape);
	}

	public void addShapeToList(Shape shape) {
		shapes.add(shape);
	}

	public void deleteShapeFromList(Shape shape) {
		shapes.remove(shape);
	}

	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}

	public void deleteShapeAtIndex(int index) {
		shapes.remove(index);
	}

	public void addShapeAtIndex(Shape shape, int index) {
		shapes.add(index, shape);
	}

	public Shape getShapeFromIndex(int index) {
		return shapes.get(index);
	}

}
