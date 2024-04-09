package command;

import geometry.Rectangle;

public class CmdModifyRectangle implements Command {
	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original;

	public CmdModifyRectangle(Rectangle original, Rectangle newRectangle) {
		super();
		this.original = original;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		oldRectangle = original.clone(this.oldRectangle);
		oldRectangle.setSelected(true);
		original = newRectangle.clone(original);
		original.setSelected(true);
	}

	@Override
	public void unexecute() {
		original = oldRectangle.clone(original);
		original.setSelected(true);
	}

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}

}
