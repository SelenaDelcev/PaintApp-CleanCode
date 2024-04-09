package mvc;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JPanel;
import geometry.Shape;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrawingView extends JPanel {
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model = new DrawingModel();

	public DrawingView() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// This method is intentionally blank because
				// we do not want the base class functionality
			}
		});
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		drawShapes(graphics);
	}

	private void drawShapes(Graphics graphics) {
		Iterator<Shape> shapeIterator = model.getShapes().iterator();
		while (shapeIterator.hasNext()) {
			shapeIterator.next().draw(graphics);
		}
	}
}
