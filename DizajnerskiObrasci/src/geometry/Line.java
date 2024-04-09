package geometry;

import java.awt.Color;
import java.awt.Graphics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private boolean selected;

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color edgeColor) {
		this(startPoint, endPoint, selected);
		setEdgeColor(edgeColor);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		drawSelectedLinePoints(graphics);
	}

	public void drawSelectedLinePoints(Graphics graphics) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			graphics.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
		}
	}

	public boolean contains(int x, int y) {
		return (startPoint.calculateDistance(x, y) + endPoint.calculateDistance(x, y)) - length() <= 0.05;
	}

	public double length() {
		return startPoint.calculateDistance(endPoint.getX(), endPoint.getY());
	}

	public int compareTo(Object object) {
		if (object instanceof Line)
			return (int) (this.length() - ((Line) object).length());
		return 0;
	}

	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}

	public void moveOn(int onX, int onY) {
		startPoint.moveOn(onX, onY);
		endPoint.moveOn(onX, onY);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return this.startPoint.equals(line.getStartPoint()) && this.endPoint.equals(line.getEndPoint());
		} else
			return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startPoint == null) ? 0 : startPoint.hashCode());
		result = prime * result + ((endPoint == null) ? 0 : endPoint.hashCode());
		return result;
	}

	public Line clone(Shape shape) {
		Line line = new Line(new Point(0, 0), new Point(0, 0));
		if (shape instanceof Line)
			line = (Line) shape;
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		line.setEdgeColor(getEdgeColor());
		return line;
	}

	@Override
	public String toString() {
		return "Line [StartPoint=" + startPoint.toStringPoint() + ", EndPoint=" + endPoint.toStringPoint() + ", Color= "
				+ this.getColorRGB() + "]";
	}
}
