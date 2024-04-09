package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point extends Shape implements Moveable {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean selected;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected, Color edgeColor) {
		this(x, y, selected);
		setEdgeColor(edgeColor);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawLine(this.x - 3, this.y, this.x + 2, this.y);
		graphics.drawLine(this.x, this.y - 3, this.x, this.y + 3);
		drawSelectedPoint(graphics);
	}

	public void drawSelectedPoint(Graphics graphics) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			markPoint(graphics, this.x, this.y);
		}
	}

	public boolean contains(int x, int y) {
		return this.calculateDistance(x, y) <= 3;
	}

	public double calculateDistance(int x2, int y2) {
		double differenceInX = (double) this.x - (double) x2;
	    double differenceInY = (double) this.y - (double) y2;
		return Math.sqrt(differenceInX * differenceInX + differenceInY * differenceInY);
	}

	public int compareTo(Object object) {
		if (object instanceof Point)
			return (int) (this.calculateDistance(0, 0) - ((Point) object).calculateDistance(0, 0));
		return 0;
	}

	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}

	public void moveOn(int onX, int onY) {
		this.x = onX;
		this.y = onY;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point) obj;
			return this.x == point.getX() && this.y == point.getY();
		} else
			return false;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(x, y);
	}

	public Point clone(Shape shape) {
	    Point point = new Point();
	    if (shape instanceof Point)
	    	point = (Point) shape;
	    point.setX(this.getX());
	    point.setY(this.getY());
	    point.setEdgeColor(getEdgeColor());
        return point;
    }

	public String toStringPoint() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public String toString() {
		return "Point [x= " + x + ", y= " + y + ", Color= " + this.getColorRGB() + "]";
	}
}
