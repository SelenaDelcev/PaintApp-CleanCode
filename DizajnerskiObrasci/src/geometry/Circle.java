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
public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center = new Point();
	private int radius;
	private boolean selected;

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected, Color edgeColor) {
		this(center, radius, selected);
		setEdgeColor(edgeColor);
	}

	public Circle(Point center, int radius, boolean selected, Color edgeColor, Color innerColor) {
		this(center, radius, selected, edgeColor);
		setInnerColor(innerColor);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2,
				this.radius * 2);
		fill(graphics);
		drawSelectedCirclePoints(graphics, this.radius);
	}

	@Override
	public void fill(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, (radius * 2) - 2,
				(radius * 2) - 2);
	}

	public void drawSelectedCirclePoints(Graphics graphics, int radius) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			markPoint(graphics, this.center.getX(), this.center.getY());
			markPoint(graphics, this.center.getX() - radius, this.center.getY());
			markPoint(graphics, this.center.getX() + radius, this.center.getY());
			markPoint(graphics, this.center.getX(), this.center.getY() - radius);
			markPoint(graphics, this.center.getX(), this.center.getY() + radius);
		}
	}

	public boolean contains(Point point) {
		return this.getCenter().calculateDistance(point.getX(), point.getY()) <= radius;
	}

	@Override
	public boolean contains(int x, int y) {
		return this.getCenter().calculateDistance(x, y) <= radius;
	}

	public double calculateArea() {
		return radius * radius * Math.PI;
	}

	public int compareTo(Object obj) {
		if (obj instanceof Circle)
			return this.radius - ((Circle) obj).radius;
		return 0;
	}

	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}

	public void moveOn(int onX, int onY) {
		center.moveOn(onX, onY);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle circle = (Circle) obj;
			return this.center.equals(circle.getCenter()) && this.radius == circle.getRadius();
		}
		return false;
	}

	public Circle clone(Shape shape) {
		Circle circle = new Circle(new Point(0, 0), 0);
		if (shape instanceof Circle)
			circle = (Circle) shape;
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());
		circle.setRadius(this.getRadius());
		circle.setInnerColor(getInnerColor());
		circle.setEdgeColor(getEdgeColor());
		return circle;
	}

	@Override
	public String toString() {
		return "Circle [Center=" + center.toStringPoint() + ", Radius=" + radius + ", OuterColor= " + this.getColorRGB()
				+ ", InnerColor= " + this.getInnerColorRGB() + "]";
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
