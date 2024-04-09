package mvc;

import java.awt.Color;
import javax.swing.JFrame;

public class Application {
	
	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		frame.getBtnInnerColor().setBackground(Color.BLACK);
		frame.getBtnOuterColor().setBackground(Color.BLACK);
		frame.setTitle("Aleksa Komosar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
