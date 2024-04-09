package mvc;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import gui.CommonDialog;
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgHexagon;
import gui.DlgRectangle;

public class ShapeAddHandler {
	private DrawingController controller;
	Point startPoint;

	public ShapeAddHandler(DrawingController controller) {
		this.controller = controller;
	}

	public Shape createPointShape(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		point.setEdgeColor(controller.getFrame().getBtnInnerColor().getBackground());
		return point;
	}

	public Shape createLineShape(MouseEvent e, Shape newShape) {
		if (startPoint == null)
			startPoint = new Point(e.getX(), e.getY());
		else {
			Line line = new Line(startPoint, new Point(e.getX(), e.getY()));
			line.setEdgeColor(controller.getFrame().getBtnInnerColor().getBackground());
			newShape = line;
			startPoint = null;
		}
		return newShape;

	}

	public Shape createRectangleShape(MouseEvent e, Shape newShape) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		Rectangle rectangle;
		setDialogPreferences(dlgRectangle, e, "Rectangle");

		if (dlgRectangle.isOK()) {
			rectangle = new Rectangle(new Point(e.getX(), e.getY()),
					Integer.parseInt(dlgRectangle.getTxtHeight().getText()),
					Integer.parseInt(dlgRectangle.getTxtWidth().getText()));
			
			if (dlgRectangle.isOuterColorChosen()) {
				rectangle.setEdgeColor(dlgRectangle.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgRectangle.getOuterColor());
			} else
				rectangle.setEdgeColor(controller.getFrame().getBtnOuterColor().getBackground());
			
			if (dlgRectangle.isInnerColorChosen()) {
				rectangle.setInnerColor(dlgRectangle.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgRectangle.getInnerColor());
			} else
				rectangle.setInnerColor(controller.getFrame().getBtnInnerColor().getBackground());
			
			try {
				newShape = rectangle;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(controller.getFrame(), "Wrong data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return newShape;
	}

	public Shape createCircleShape(MouseEvent e, Shape newShape) {
		DlgCircle dlgCircle = new DlgCircle();
		Circle circle;
		setDialogPreferences(dlgCircle, e, "Circle");

		if (dlgCircle.isOK()) {
			circle = new Circle(new Point(e.getX(), e.getY()), Integer.parseInt(dlgCircle.getTxtRadius().getText()));
			
			if (dlgCircle.isOuterColorChosen()) {
				circle.setEdgeColor(dlgCircle.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgCircle.getOuterColor());
			} else 
				circle.setEdgeColor(controller.getFrame().getBtnOuterColor().getBackground());
			
			if (dlgCircle.isInnerColorChosen()) {
				circle.setInnerColor(dlgCircle.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgCircle.getInnerColor());
			} else 
				circle.setInnerColor(controller.getFrame().getBtnInnerColor().getBackground());
			
			try {
				newShape = circle;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(controller.getFrame(), "Wrong data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return newShape;
	}

	public Shape createDonutShape(MouseEvent e, Shape newShape) {
		DlgDonut dlgDonut = new DlgDonut();
		Donut donut;
		setDialogPreferences(dlgDonut, e, "Donut");

		if (dlgDonut.isOK()) {
			if (Integer.parseInt(dlgDonut.getTxtRadius().getText()) == Integer
					.parseInt(dlgDonut.getTxtInnerRadius().getText())) {
				JOptionPane.showMessageDialog(controller.getFrame(), "Radius and inner radius are the same!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				donut = new Donut(new Point(e.getX(), e.getY()), Integer.parseInt(dlgDonut.getTxtRadius().getText()),
						Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
				
				if (dlgDonut.isOuterColorChosen()) {
					donut.setEdgeColor(dlgDonut.getOuterColor());
					controller.getFrame().getBtnOuterColor().setBackground(dlgDonut.getOuterColor());
				} else
					donut.setEdgeColor(controller.getFrame().getBtnOuterColor().getBackground());
				
				if (dlgDonut.isInnerColorChosen()) {
					donut.setInnerColor(dlgDonut.getInnerColor());
					controller.getFrame().getBtnInnerColor().setBackground(dlgDonut.getInnerColor());
				} else
					donut.setInnerColor(controller.getFrame().getBtnInnerColor().getBackground());
				
				try {
					newShape = donut;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(controller.getFrame(), "Wrong data", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return newShape;
	}

	public Shape createHexagonShape(MouseEvent e, Shape newShape) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		HexagonAdapter hexagon;
		setDialogPreferences(dlgHexagon, e, "Hexagon");

		if (dlgHexagon.isOK()) {
			hexagon = new HexagonAdapter(new Point(e.getX(), e.getY()),
					Integer.parseInt(dlgHexagon.getTxtRadius().getText()));

			if (dlgHexagon.isOuterColorChosen()) {
				hexagon.setHexagonBorderColor(dlgHexagon.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgHexagon.getOuterColor());
			} else
				hexagon.setHexagonBorderColor(controller.getFrame().getBtnOuterColor().getBackground());
			
			if (dlgHexagon.isInnerColorChosen()) {
				hexagon.setHexagonInnerColor(dlgHexagon.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgHexagon.getInnerColor());
			} else
				hexagon.setHexagonInnerColor(controller.getFrame().getBtnInnerColor().getBackground());
			
			newShape = hexagon;
		}
		return newShape;
	}

	private void setDialogPreferences(CommonDialog dialog, MouseEvent e, String title) {
		dialog.setModal(true);
		dialog.getTxtX().setText("" + e.getX());
		dialog.getTxtY().setText("" + e.getY());
		dialog.setTitle(title);
		dialog.setVisible(true);
	}

}
