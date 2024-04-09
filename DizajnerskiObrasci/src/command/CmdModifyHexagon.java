package command;

import geometry.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	private HexagonAdapter oldHexagon = new HexagonAdapter(0, 0, 0);
	private HexagonAdapter newHexagon;
	private HexagonAdapter original;

	public CmdModifyHexagon(HexagonAdapter original, HexagonAdapter newHexagon) {
		super();
		this.original = original;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		oldHexagon = original.clone(oldHexagon);
		oldHexagon.setSelected(true);
		original = newHexagon.clone(original);
		original.setSelected(true);
	}

	@Override
	public void unexecute() {
		original = oldHexagon.clone(original);
		original.setSelected(true);
	}

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}

}
