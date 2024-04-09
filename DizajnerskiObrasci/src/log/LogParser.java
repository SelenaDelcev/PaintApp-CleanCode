package log;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeleteOneShape;
import command.CmdDeselectShape;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdSelectShape;
import command.CmdToBackByOne;
import command.CmdToFrontByOne;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import lombok.AllArgsConstructor;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.BtnUpdate;

@AllArgsConstructor
public class LogParser implements LogUtil {
	DrawingModel model;
	DrawingController controller;
	DrawingFrame frame;
	BtnUpdate buttonsStateControl;

	@Override
	public Shape makeShapeFromLog(String lineLog, String stringShape, Boolean second) {
		List<Integer> numbersFromString = parseNumbersFromString(lineLog);
		int position = getPosition(stringShape, second);
		Shape shapeFromLog = null;

		switch (stringShape) {
		case "Point":
			shapeFromLog = createPoint(numbersFromString, position);
			break;
		case "Line":
			shapeFromLog = createLine(numbersFromString, position);
			break;
		case "Rectangle":
			shapeFromLog = createRectangle(numbersFromString, position);
			break;
		case "Circle":
			shapeFromLog = createCircle(numbersFromString, position);
			break;
		case "Donut":
			shapeFromLog = createDonut(numbersFromString, position);
			break;
		case "Hexagon":
			shapeFromLog = createHexagon(numbersFromString, position);
			break;
		default:
			break;
		}
		return shapeFromLog;
	}

	private List<Integer> parseNumbersFromString(String lineLog) {
		String[] numbersString = lineLog.replaceAll("[^0-9]", " ").split(" ");
		List<Integer> numbers = new ArrayList<>();
		for (String s : numbersString) {
			if (s != null && !s.trim().isEmpty())
				numbers.add(Integer.parseInt(s));
		}
		return numbers;
	}

	private int getPosition(String stringShape, Boolean second) {
		if (Boolean.TRUE.equals(second)) {
			switch (stringShape) {
			case "Point":
				return 5;
			case "Line":
				return 7;
			case "Circle":
			case "Hexagon":
				return 9;
			case "Rectangle":
			case "Donut":
				return 10;
			default:
				return 0;
			}
		} else {
			return 0;
		}
	}

	private Point createPoint(List<Integer> numbers, int position) {
		int x = numbers.get(0 + position);
		int y = numbers.get(1 + position);
		Point point = new Point(x, y);
		Color edgeColor = getColor(numbers, position + 2);
		point.setEdgeColor(edgeColor);
		return point;
	}

	private Line createLine(List<Integer> numbers, int position) {
		int x1 = numbers.get(0 + position);
		int y1 = numbers.get(1 + position);
		int x2 = numbers.get(2 + position);
		int y2 = numbers.get(3 + position);
		Line line = new Line(new Point(x1, y1), new Point(x2, y2));
		Color edgeColor = getColor(numbers, position + 4);
		line.setEdgeColor(edgeColor);
		return line;
	}

	private Rectangle createRectangle(List<Integer> numbers, int position) {
		int x1 = numbers.get(0 + position);
		int y1 = numbers.get(1 + position);
		int w = numbers.get(2 + position);
		int h = numbers.get(3 + position);
		Rectangle rectangle = new Rectangle(new Point(x1, y1), w, h);
		Color edgeColor = getColor(numbers, position + 4);
		Color innerColor = getColor(numbers, position + 7);
		rectangle.setEdgeColor(edgeColor);
		rectangle.setInnerColor(innerColor);
		return rectangle;
	}

	private Circle createCircle(List<Integer> numbers, int position) {
		int x = numbers.get(0 + position);
		int y = numbers.get(1 + position);
		int r = numbers.get(2 + position);
		Circle circle = new Circle(new Point(x, y), r);
		Color edgeColor = getColor(numbers, position + 3);
		Color innerColor = getColor(numbers, position + 6);
		circle.setEdgeColor(edgeColor);
		circle.setInnerColor(innerColor);
		return circle;
	}

	private Donut createDonut(List<Integer> numbers, int position) {
		int x = numbers.get(0 + position);
		int y = numbers.get(1 + position);
		int a1 = numbers.get(2 + position);
		int a2 = numbers.get(3 + position);
		Donut donut = new Donut(new Point(x, y), a1, a2);
		Color edgeColor = getColor(numbers, position + 4);
		Color innerColor = getColor(numbers, position + 7);
		donut.setEdgeColor(edgeColor);
		donut.setInnerColor(innerColor);
		return donut;
	}

	private HexagonAdapter createHexagon(List<Integer> numbers, int position) {
		int x = numbers.get(0 + position);
		int y = numbers.get(1 + position);
		int a = numbers.get(2 + position);
		HexagonAdapter hexagon = new HexagonAdapter(new Point(x, y), a);
		Color borderColor = getColor(numbers, position + 3);
		Color innerColor = getColor(numbers, position + 6);
		hexagon.setHexagonBorderColor(borderColor);
		hexagon.setHexagonInnerColor(innerColor);
		return hexagon;
	}

	private Color getColor(List<Integer> numbers, int position) {
		int r = numbers.get(position);
		int g = numbers.get(position + 1);
		int b = numbers.get(position + 2);
		return new Color(r, g, b);
	}

	@Override
	public void executeLineLog(String lineLog) {
		if (lineLog != null) {
			Command cmd = null;
			String[] splitLine = lineLog.split(" ");

			String command = splitLine[0];
			try {
				switch (command) {
				case "Select:":
					this.executeSelect(lineLog, splitLine, cmd);
					break;
				case "Deselect:":
					this.executeDeselect(lineLog, splitLine, cmd);
					break;
				case "Adding:":
					this.executeAdding(lineLog, splitLine, cmd);
					break;
				case "Deleting:":
					this.executeDeleting(lineLog, splitLine, cmd);
					break;
				case "Modifying:":
					this.executeModifying(lineLog, splitLine, cmd);
					break;
				case "BringToFront:":
					this.executeBringToFront(lineLog);
					break;
				case "ToFront:":
					this.executeToFront(lineLog);
					break;
				case "BringToBack:":
					this.executeBringToBack(lineLog);
					break;
				case "ToBack:":
					this.executeToBack(lineLog);
					break;
				case "Undo:":
					this.executeUndo(lineLog);
					break;
				case "Redo:":
					this.executeRedo(lineLog);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "File is corrupted!", "Error", JOptionPane.ERROR_MESSAGE);
				model.getRedo().clear();
				model.getRedo().clear();
				buttonsStateControl.setBtnRedo(false);

				frame.repaint();
			}
		}
	}

	@Override
	public void loadFileByLoadingType(int type, Scanner logFile) {
		try {
			if (logFile.hasNextLine()) {
				buttonsStateControl.setBtnUndo(true);
				buttonsStateControl.setBtnRedo(true);
				if (type == 0) {
					createWholeDrawing(logFile);
				} else 
					createDrawingStepByStep(logFile);
			} else {
				ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
				JOptionPane.showMessageDialog(frame, "Try again! Something is not good.", "Error",
						JOptionPane.ERROR_MESSAGE, img);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ImageIcon img = new ImageIcon(this.getClass().getResource("/fail.png"));
			JOptionPane.showMessageDialog(frame, "Try again! Something is not good.", "Error",
					JOptionPane.ERROR_MESSAGE, img);
		}
	}

	public void createWholeDrawing(Scanner logFile) {
		while (logFile.hasNextLine()) {
			String lineLog = logFile.nextLine();
			executeLineLog(lineLog);
		}
		ImageIcon img = new ImageIcon(this.getClass().getResource("/ok.png"));
		JOptionPane.showMessageDialog(frame, "Log is loaded!", "OK", JOptionPane.OK_OPTION, img);
		logFile.close();
		frame.getBtnNext().setEnabled(false);
	}

	public void createDrawingStepByStep(Scanner logFile) {
		frame.getTextArea().append("Click next for the first step of drawing!\n");
		frame.getBtnNext().setEnabled(true);
		frame.getBtnNext().addActionListener(e -> handleNextButtonClick(logFile));

		frame.getBtnRedo().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnUndo().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnBringToBack().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnBringToFront().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnToBack().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnToFront().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnPoint().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnRectangle().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnCircle().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnHexagon().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnDonut().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
		frame.getBtnLine().addActionListener(e -> disableNextButtonAndCloseLogFile(logFile));
	}

	public void disableNextButtonAndCloseLogFile(Scanner logFile) {
		frame.getBtnNext().setEnabled(false);
		logFile.close();
	}

	public void handleNextButtonClick(Scanner logFile) {
		String lineLog = logFile.nextLine();
		if (lineLog != null) {
			executeLineLog(lineLog);
			checkNextLineInLogFile(logFile);
		}
	}

	public void checkNextLineInLogFile(Scanner logFile) {
		if (!logFile.hasNextLine()) {
			ImageIcon img = new ImageIcon(this.getClass().getResource("/ok.png"));
			JOptionPane.showMessageDialog(frame, "Log is loaded!", "OK", JOptionPane.OK_OPTION, img);
			disableNextButtonAndCloseLogFile(logFile);
		}
	}

	private void executeBringToFront(String command) {
		command = command.replace("BringToFront: ", "");
		frame.getTextArea().append("BringToFront: ");
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		Command bringToFrontCommand = new CmdBringToFront(model, shape, index);
		bringToFrontCommand.execute();
		model.getUndo().add(bringToFrontCommand);
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeToFront(String command) {
		command = command.replace("ToFront: ", "");
		frame.getTextArea().append("ToFront: ");
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		Command toFrontCommand = new CmdToFrontByOne(model, shape, index);
		toFrontCommand.execute();
		model.getUndo().add(toFrontCommand);
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeBringToBack(String command) {
		command = command.replace("BringToBack: ", "");
		frame.getTextArea().append("BringToBack: ");
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		Command bringToBackCommand = new CmdBringToBack(model, shape, index);
		bringToBackCommand.execute();
		model.getUndo().add(bringToBackCommand);
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeToBack(String command) {
		command = command.replace("ToBack: ", "");
		frame.getTextArea().append("ToBack: ");
		Shape shape = model.getSelectedShapes().get(0);
		int index = model.getShapes().indexOf(shape);
		Command toBackCommand = new CmdToBackByOne(model, shape, index);
		toBackCommand.execute();
		model.getUndo().add(toBackCommand);
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeUndo(String command) {
		command = command.replace("Undo: ", "");
		frame.getTextArea().append("Undo: ");
		if (model.getUndo().isEmpty())
			buttonsStateControl.setBtnUndo(false);
		else {
			Command undoCommand = model.getUndo().pop();
			frame.getTextArea().append(command + "\n");
			undoCommand.unexecute();
			model.getRedo().add(undoCommand);
		}
		controller.checkButtonState();
		frame.repaint();
	}

	private void executeRedo(String command) {
		command = command.replace("Redo: ", "");
		frame.getTextArea().append("Redo: ");
		if (model.getRedo().isEmpty())
			buttonsStateControl.setBtnRedo(false);
		else {
			Command redoCommand = model.getRedo().pop();
			redoCommand.execute();
			model.getUndo().add(redoCommand);
		}
		controller.checkButtonState();
		frame.getTextArea().append(command + "\n");
		frame.repaint();
	}

	private void executeAdding(String lineLog, String[] splitLineLog, Command cmd) {
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		if (shape != null) {
			cmd = new CmdAddShape(model, shape);
			cmd.execute();
			model.getUndo().add(cmd);
			frame.getTextArea().append("Adding: " + shape.toString() + "\n");
		}
		controller.checkButtonState();
		buttonsStateControl.setBtnRedo(false);
		frame.repaint();
	}

	private void executeDeleting(String lineLog, String[] splitLineLog, Command cmd) {
		List<Shape> selectedshapesToDelete = getShapesToDelete();

		for (Shape s : selectedshapesToDelete) {
			frame.getTextArea().append("Deleting: " + s.toString() + "\n");
			cmd = new CmdDeleteOneShape(model, s);
			cmd.execute();
			model.getUndo().add(cmd);
		}
		controller.checkButtonState();
		buttonsStateControl.setBtnRedo(false);
		frame.repaint();
	}

	private List<Shape> getShapesToDelete() {
		List<Shape> shapesToDelete = new ArrayList<>();
		for (Shape s : model.getSelectedShapes())
			shapesToDelete.add(s);
		return shapesToDelete;
	}

	private void executeModifying(String lineLog, String[] splitLineLog, Command cmd) {
		Shape newShape = makeShapeFromLog(lineLog, splitLineLog[1], true);
		Shape oldShape = model.getSelectedShapes().get(0);
		cmd = createModifyCommand(splitLineLog, newShape, oldShape);
		frame.getTextArea().append("Modifying: " + oldShape.toString() + " To: " + newShape.toString() + "\n");
		cmd.execute();
		model.getUndo().add(cmd);
		controller.checkButtonState();
		buttonsStateControl.setBtnRedo(false);
		frame.repaint();
	}

	private Command createModifyCommand(String[] splitLineLog, Shape newShape, Shape oldShape) {
		if (splitLineLog[1].equals("Point"))
			return new CmdModifyPoint((Point) oldShape, (Point) newShape);
		else if (splitLineLog[1].equals("Line"))
			return new CmdModifyLine((Line) oldShape, (Line) newShape);
		else if (splitLineLog[1].equals("Rectangle"))
			return new CmdModifyRectangle((Rectangle) oldShape, (Rectangle) newShape);
		else if (splitLineLog[1].equals("Circle"))
			return new CmdModifyCircle((Circle) oldShape, (Circle) newShape);
		else if (splitLineLog[1].equals("Donut"))
			return new CmdModifyDonut((Donut) oldShape, (Donut) newShape);
		return new CmdModifyHexagon((HexagonAdapter) oldShape, (HexagonAdapter) newShape);
	}

	private void executeSelect(String lineLog, String[] splitLineLog, Command cmd) {
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		for (Shape s : model.getShapes())
			if (shape.equals(s))
				shape = s;
		if (shape != null) {
			cmd = new CmdSelectShape(model, shape);
			cmd.execute();
			frame.getTextArea().append("Select: " + shape.toString() + "\n");
			model.getUndo().add(cmd);
			controller.checkButtonState();
			buttonsStateControl.setBtnRedo(false);
			frame.repaint();
		}
	}

	private void executeDeselect(String lineLog, String[] splitLineLog, Command cmd) {
		Shape shape = makeShapeFromLog(lineLog, splitLineLog[1], false);
		shape.setSelected(true);
		for (Shape s : model.getShapes())
			if (shape.equals(s))
				shape = s;
		cmd = new CmdDeselectShape(model, shape);
		cmd.execute();
		frame.getTextArea().append("Deselect: " + shape.toString() + "\n");
		model.getUndo().add(cmd);
		controller.checkButtonState();
		buttonsStateControl.setBtnRedo(false);
		frame.repaint();
	}

}
