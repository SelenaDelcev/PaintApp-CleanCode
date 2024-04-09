package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import geometry.Shape;
import lombok.AllArgsConstructor;
import mvc.DrawingFrame;
import mvc.DrawingModel;

@AllArgsConstructor
public class PaintingFile implements StrategyFile {
	DrawingModel model;
	DrawingFrame frame;

	@Override
	public void save(String path) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
			saveShapesToFile(fileOutputStream, model.getShapes());
			showSuccessSaveMessageDialog();
		} catch (Exception e) {
			e.printStackTrace();
			ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
			JOptionPane.showMessageDialog(frame, "This drawing cannot be saved!", "Error", JOptionPane.ERROR_MESSAGE, img);
		}
	}

	private void saveShapesToFile(FileOutputStream fileOutputStream, ArrayList<Shape> shapes) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(shapes);
		objectOutputStream.close();
	}

	private void showSuccessSaveMessageDialog() {
		ImageIcon img = new ImageIcon(this.getClass().getResource("/ok.png"));
		JOptionPane.showMessageDialog(frame, "Drawing is saved!", "OK", JOptionPane.OK_OPTION, img);
	}

	@Override
	public void load(String path) {
		try (FileInputStream fileInputStream = new FileInputStream(path)) {
			loadShapesFromFile(fileInputStream);
			showSuccessLoadMessageDialog();
			manageButtonsState();
		} catch (Exception e) {
			e.printStackTrace();
			ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
			JOptionPane.showMessageDialog(frame, "Cannot load this drawing! Corrupted file.", "Error",
					JOptionPane.ERROR_MESSAGE, img);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadShapesFromFile(FileInputStream fileInputStream) throws ClassNotFoundException, IOException {
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		ArrayList<Shape> shapes = (ArrayList<Shape>) objectInputStream.readObject();
		model.getShapes().addAll(shapes);
		objectInputStream.close();
	}

	private void showSuccessLoadMessageDialog() {
		ImageIcon img = new ImageIcon(this.getClass().getResource("/ok.png"));
		JOptionPane.showMessageDialog(frame, "Drawing is loaded!", "OK", JOptionPane.OK_OPTION, img);
	}

	private void manageButtonsState() {
		frame.getBtnSelect().setEnabled(true);
		model.getUndo().clear();
		model.getRedo().clear();
		frame.getBtnRedo().setEnabled(false);
		frame.getBtnUndo().setEnabled(false);
	}

}
