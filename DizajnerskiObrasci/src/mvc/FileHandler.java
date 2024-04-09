package mvc;

import javax.swing.JFileChooser;

import lombok.Setter;
import strategy.LogFile;
import strategy.PaintingFile;
import strategy.StrategyFile;

@Setter
public class FileHandler {
	private DrawingController controller;
	JFileChooser loadLog;
	JFileChooser loadDrawing;
	JFileChooser saveLog;
	JFileChooser saveDrawing;

	public FileHandler(DrawingController controller) {
		this.controller = controller;
		this.loadDrawing = new JFileChooser();
		this.loadLog = new JFileChooser();
		this.saveDrawing = new JFileChooser();
		this.saveLog = new JFileChooser();
	}

	public void loadDrawing() {
		handleChooseFile(loadDrawing);
	}

	public void saveDrawing() {
		handleChooseFile(saveDrawing);
	}

	public void saveLog() {
		handleChooseFile(saveLog);
	}

	public void loadLog() {
		handleChooseFile(loadLog);
	}

	public void handleChooseFile(JFileChooser chooser) {
		int selection = chooser.showOpenDialog(controller.getFrame());

		if (selection == JFileChooser.APPROVE_OPTION) {
			clearModelDataIfLoadChoosen(chooser);
			String path = chooser.getSelectedFile().getPath();
			StrategyFile strategy = null;

			if (chooser == loadDrawing || chooser == saveDrawing)
				strategy = new PaintingFile(controller.getModel(), controller.getFrame());
			else if (chooser == loadLog || chooser == saveLog)
				strategy = new LogFile(controller.getModel(), controller.getFrame(), controller,
						controller.getBtnUpdate());
			if (strategy != null) {
				handleStrategy(chooser, strategy, path);
			}
		}
	}

	private void handleStrategy(JFileChooser chooser, StrategyFile strategy, String path) {
		if (chooser == loadDrawing || chooser == loadLog) {
			strategy.load(path);
			controller.getFrame().repaint();
		} else
			strategy.save(path);
	}

	public void clearModelDataIfLoadChoosen(JFileChooser chooser) {
		if (chooser == loadDrawing || chooser == loadLog) {
			controller.getFrame().getTextArea().setText("");
			controller.getModel().getShapes().clear();
			controller.getModel().getRedo().clear();
			controller.getModel().getUndo().clear();
			controller.getModel().getSelectedShapes().clear();
		}
	}
}
