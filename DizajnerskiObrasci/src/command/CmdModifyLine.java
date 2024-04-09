package command;

import geometry.Line;

public class CmdModifyLine implements Command {
	private Line oldLine;
    private Line newLine;
    private Line original;

    public CmdModifyLine(Line original, Line newLine) {
        this.original = original;
        this.newLine = newLine;
    }

    public void execute() {
        this.oldLine = original.clone(this.oldLine);
        this.original = newLine.clone(this.original);
    }
    
    public void unexecute() {
        this.original = oldLine.clone(this.original);
    }

	public String toString() {
		return "Modifying: " + original.toString() + "\n";
	}

}
