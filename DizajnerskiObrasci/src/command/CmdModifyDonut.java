package command;

import geometry.Donut;

public class CmdModifyDonut implements Command {
	private Donut oldDonut;
	private Donut newDonut;
	private Donut original;

	public CmdModifyDonut(Donut original, Donut newDonut) {
		super();
		this.original = original;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		oldDonut = original.clone(oldDonut);
		oldDonut.setSelected(true);
		original = newDonut.clone(original);
		original.setSelected(true);
	}

	@Override
	public void unexecute() {
		original = oldDonut.clone(original);
		original.setSelected(true);
	}

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}

}
