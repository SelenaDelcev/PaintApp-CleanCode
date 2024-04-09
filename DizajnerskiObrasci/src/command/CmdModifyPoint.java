package command;

import geometry.Point;

public class CmdModifyPoint implements Command {
	private Point oldPoint;
	private Point newPoint;
	private Point original;

	public CmdModifyPoint(Point original, Point newPoint) {
		this.original = original;
		this.newPoint = newPoint;
	}

	public void execute() {
		this.oldPoint = original.clone(this.oldPoint);
		this.original = newPoint.clone(this.original);
	}

	public void unexecute() {
		this.original = oldPoint.clone(this.original);
	}

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}

}
