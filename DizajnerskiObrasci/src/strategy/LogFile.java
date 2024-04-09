package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import log.LogParser;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;

public class LogFile implements StrategyFile {
	DrawingModel model;
	DrawingFrame frame;
	DrawingController controller;
	LogParser logParser;
	BtnUpdate btnUpdate;

	public LogFile(DrawingModel model, DrawingFrame frame, DrawingController controller, BtnUpdate btnUpdate) {
		this.model = model;
		this.frame = frame;
		this.controller = controller;
		this.btnUpdate = btnUpdate;
		this.logParser = new LogParser(model, controller, frame, btnUpdate);
	}

	@Override
	public void save(String path) {
		try {
			PrintWriter printWriter = new PrintWriter(path);
			printWriter.write(frame.getTextArea().getText());
			ImageIcon img = new ImageIcon(this.getClass().getResource("/ok.png"));
			JOptionPane.showMessageDialog(frame, "Log is saved!", "OK", JOptionPane.OK_OPTION, img);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
			JOptionPane.showMessageDialog(frame, "Cannot save this file!", "Error", JOptionPane.ERROR_MESSAGE, img);
		}
	}

	@Override
	public void load(String path) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path));
			Object[] options = { "Whole", "Step by step" };
			int answer = JOptionPane.showOptionDialog(frame, "Do you want whole or step by step log preview loaded?",
					"Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			this.logParser.loadFileByLoadingType(answer, scanner);
			model.getRedo().clear();
			model.getUndo().clear();
			btnUpdate.setBtnUndo(false);
			btnUpdate.setBtnRedo(false);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
			JOptionPane.showMessageDialog(frame, "Cannot load this file!", "Error", JOptionPane.ERROR_MESSAGE, img);
		}
	}

}
