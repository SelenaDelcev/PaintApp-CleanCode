package command;

import geometry.Circle;

public class CmdModifyCircle implements Command {
	private Circle oldCircle;
	private Circle newCircle;
	private Circle original;

	public CmdModifyCircle(Circle original, Circle newCircle) {
		this.original = original;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		oldCircle = original.clone(this.oldCircle);
		oldCircle.setSelected(true);
		original = newCircle.clone(original);
		original.setSelected(true);
	}

	@Override
	public void unexecute() {
		original = oldCircle.clone(original);
		original.setSelected(true);
	}

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}
}
