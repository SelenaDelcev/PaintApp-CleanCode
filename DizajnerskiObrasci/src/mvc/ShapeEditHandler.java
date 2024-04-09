package mvc;

import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import gui.DlgCircle;
import gui.DlgDonut;
import gui.DlgHexagon;
import gui.DlgLine;
import gui.DlgPoint;
import gui.DlgRectangle;

public class ShapeEditHandler {
	private DrawingController controller;

	public ShapeEditHandler(DrawingController controller) {
		this.controller = controller;
	}

	public Command executeEditShape(Shape shape, Command command, Shape selectedShape) {
		if (shape instanceof Point) {
			command = editPoint((Point) shape, command);
		} else if (shape instanceof Line) {
			command = editLine((Line) shape, command, selectedShape);
		} else if (shape instanceof Rectangle) {
			command = editRectangle((Rectangle) shape, command, selectedShape);
		} else if (shape instanceof Donut) {
			command = editDonut((Donut) shape, command, selectedShape);
		} else if (shape instanceof Circle) {
			command = editCircle((Circle) shape, command, selectedShape);
		} else if (shape instanceof HexagonAdapter) {
			command = editHexagon((HexagonAdapter) shape, command, selectedShape);
		}
		return command;
	}

	private Command editPoint(Point point, Command command) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.getTxtX().setText("" + point.getX());
		dlgPoint.getTxtY().setText("" + point.getY());
		dlgPoint.setPicked(point.getEdgeColor());
		dlgPoint.setModal(true);
		dlgPoint.setTitle("Edit point");
		dlgPoint.setVisible(true);

		if (dlgPoint.isOK()) {
			Point newPoint = new Point(Integer.parseInt(dlgPoint.getTxtX().getText()),
					Integer.parseInt(dlgPoint.getTxtY().getText()));
			newPoint.setEdgeColor(dlgPoint.getColor());
			command = new CmdModifyPoint(point, newPoint);
			controller.getFrame().getTextArea()
					.append("Modifying: " + point.toString() + " To: " + newPoint.toString() + "\n");
		}
		return command;
	}

	private Command editLine(Line line, Command command, Shape selectedShape) {
		DlgLine dlgLine = new DlgLine();
		dlgLine.getTxtXStartPoint().setText("" + line.getStartPoint().getX());
		dlgLine.getTxtYStartPoint().setText("" + line.getStartPoint().getY());
		dlgLine.getTxtXEndPoint().setText("" + line.getEndPoint().getX());
		dlgLine.getTxtYEndPoint().setText("" + line.getEndPoint().getY());
		dlgLine.setPicked(line.getEdgeColor());
		dlgLine.setModal(true);
		dlgLine.setTitle("Edit line");
		dlgLine.setVisible(true);

		if (dlgLine.isOK()) {
			Point startPoint = new Point(Integer.parseInt(dlgLine.getTxtXStartPoint().getText()),
					Integer.parseInt(dlgLine.getTxtYStartPoint().getText()));
			Point endPoint = new Point(Integer.parseInt(dlgLine.getTxtXEndPoint().getText()),
					Integer.parseInt(dlgLine.getTxtYEndPoint().getText()));
			line = new Line(startPoint, endPoint);
			line.setEdgeColor(dlgLine.getColor());
			command = new CmdModifyLine((Line) controller.getModel().getSelectedShapes().get(0), line);
			controller.getFrame().getTextArea()
					.append("Modifying: " + ((Line) selectedShape).toString() + " To: " + line.toString() + "\n");
		}
		return command;
	}

	public Command editRectangle(Rectangle rectangle, Command command, Shape selectedShape) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.getTxtX().setText("" + rectangle.getUpperLeftPoint().getX());
		dlgRectangle.getTxtY().setText("" + rectangle.getUpperLeftPoint().getY());
		dlgRectangle.getTxtHeight().setText("" + rectangle.getHeight());
		dlgRectangle.getTxtWidth().setText("" + rectangle.getWidth());
		dlgRectangle.setColorForButton(dlgRectangle.getBtnOuterColor(), rectangle.getEdgeColor());
		dlgRectangle.setColorForButton(dlgRectangle.getBtnInnerColor(), rectangle.getInnerColor());
		dlgRectangle.setModal(true);
		dlgRectangle.setTitle("Edit rectangle");
		dlgRectangle.setVisible(true);

		if (dlgRectangle.isOK()) {
			Point point = new Point(Integer.parseInt(dlgRectangle.getTxtX().getText()),
					Integer.parseInt(dlgRectangle.getTxtY().getText()));
			rectangle = new Rectangle(point, Integer.parseInt(dlgRectangle.getTxtHeight().getText()),
					Integer.parseInt(dlgRectangle.getTxtWidth().getText()));

			if (dlgRectangle.isOuterColorChosen()) {
				rectangle.setEdgeColor(dlgRectangle.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgRectangle.getOuterColor());
			} else
				rectangle.setEdgeColor(dlgRectangle.getOuterColor());

			if (dlgRectangle.isInnerColorChosen()) {
				rectangle.setInnerColor(dlgRectangle.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgRectangle.getInnerColor());
			} else
				rectangle.setInnerColor(dlgRectangle.getInnerColor());

			selectedShape.setSelected(true);
			controller.getFrame().repaint();
			command = new CmdModifyRectangle((Rectangle) selectedShape, rectangle);
			controller.getFrame().getTextArea().append(
					"Modifying: " + ((Rectangle) selectedShape).toString() + " To: " + rectangle.toString() + "\n");
		}
		return command;
	}

	private Command editDonut(Donut donut, Command command, Shape selectedShape) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.getTxtX().setText("" + donut.getCenter().getX());
		dlgDonut.getTxtY().setText("" + donut.getCenter().getY());
		dlgDonut.getTxtRadius().setText("" + donut.getRadius());
		dlgDonut.getTxtInnerRadius().setText("" + donut.getInnerRadius());
		dlgDonut.setColorForButton(dlgDonut.getBtnOuterColor(), donut.getEdgeColor());
		dlgDonut.setColorForButton(dlgDonut.getBtnInnerColor(), donut.getInnerColor());
		dlgDonut.setModal(true);
		dlgDonut.setTitle("Edit donut");
		dlgDonut.setVisible(true);

		if (dlgDonut.isOK()) {
			Point point = new Point(Integer.parseInt(dlgDonut.getTxtX().getText()),
					Integer.parseInt(dlgDonut.getTxtY().getText()));
			donut = new Donut(point, Integer.parseInt(dlgDonut.getTxtRadius().getText()),
					Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));

			if (dlgDonut.isOuterColorChosen()) {
				donut.setEdgeColor(dlgDonut.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgDonut.getOuterColor());
			} else
				donut.setEdgeColor(dlgDonut.getOuterColor());

			if (dlgDonut.isInnerColorChosen()) {
				donut.setInnerColor(dlgDonut.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgDonut.getInnerColor());
			} else
				donut.setInnerColor(dlgDonut.getInnerColor());

			command = new CmdModifyDonut((Donut) controller.getModel().getSelectedShapes().get(0), donut);
			controller.getFrame().getTextArea()
					.append("Modifying: " + ((Donut) selectedShape).toString() + " To: " + donut.toString() + "\n");
		}
		return command;
	}

	private Command editCircle(Circle circle, Command command, Shape selectedShape) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.getTxtX().setText("" + circle.getCenter().getX());
		dlgCircle.getTxtY().setText("" + circle.getCenter().getY());
		dlgCircle.getTxtRadius().setText("" + circle.getRadius());
		dlgCircle.setColorForButton(dlgCircle.getBtnOuterColor(), circle.getEdgeColor());
		dlgCircle.setColorForButton(dlgCircle.getBtnInnerColor(), circle.getInnerColor());
		dlgCircle.setModal(true);
		dlgCircle.setTitle("Edit circle");
		dlgCircle.setVisible(true);

		if (dlgCircle.isOK()) {
			Point point = new Point(Integer.parseInt(dlgCircle.getTxtX().getText()),
					Integer.parseInt(dlgCircle.getTxtY().getText()));
			circle = new Circle(point, Integer.parseInt(dlgCircle.getTxtRadius().getText()));

			if (dlgCircle.isOuterColorChosen()) {
				circle.setEdgeColor(dlgCircle.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgCircle.getOuterColor());
			} else
				circle.setEdgeColor(dlgCircle.getOuterColor());

			if (dlgCircle.isInnerColorChosen()) {
				circle.setInnerColor(dlgCircle.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgCircle.getInnerColor());
			} else
				circle.setInnerColor(dlgCircle.getInnerColor());

			command = new CmdModifyCircle((Circle) controller.getModel().getSelectedShapes().get(0), circle);
			controller.getFrame().getTextArea()
					.append("Modifying: " + ((Circle) selectedShape).toString() + " To: " + circle.toString() + "\n");
		}
		return command;
	}

	private Command editHexagon(HexagonAdapter hexagon, Command command, Shape selectedShape) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.getTxtX().setText("" + hexagon.getHexagon().getX());
		dlgHexagon.getTxtY().setText("" + hexagon.getHexagon().getY());
		dlgHexagon.getTxtRadius().setText("" + hexagon.getHexagon().getR());
		dlgHexagon.setColorForButton(dlgHexagon.getBtnOuterColor(), hexagon.getHexagonBorderColor());
		dlgHexagon.setColorForButton(dlgHexagon.getBtnInnerColor(), hexagon.getHexagonInnerColor());
		dlgHexagon.setModal(true);
		dlgHexagon.setTitle("Edit hexagon");
		dlgHexagon.setVisible(true);

		if (dlgHexagon.isOK()) {
			Point point = new Point(Integer.parseInt(dlgHexagon.getTxtX().getText()),
					Integer.parseInt(dlgHexagon.getTxtY().getText()));
			hexagon = new HexagonAdapter(point, Integer.parseInt(dlgHexagon.getTxtRadius().getText()));

			if (dlgHexagon.isOuterColorChosen()) {
				hexagon.setHexagonBorderColor(dlgHexagon.getOuterColor());
				controller.getFrame().getBtnOuterColor().setBackground(dlgHexagon.getOuterColor());
			} else
				hexagon.setHexagonBorderColor(dlgHexagon.getPicked());

			if (dlgHexagon.isInnerColorChosen()) {
				hexagon.setHexagonInnerColor(dlgHexagon.getInnerColor());
				controller.getFrame().getBtnInnerColor().setBackground(dlgHexagon.getInnerColor());
			} else
				hexagon.setHexagonInnerColor(dlgHexagon.getInnerPickedColor());

			command = new CmdModifyHexagon((HexagonAdapter) controller.getModel().getSelectedShapes().get(0), hexagon);
			controller.getFrame().getTextArea().append(
					"Modifying: " + ((HexagonAdapter) selectedShape).toString() + " To: " + hexagon.toString() + "\n");
		}
		return command;

	}

}
