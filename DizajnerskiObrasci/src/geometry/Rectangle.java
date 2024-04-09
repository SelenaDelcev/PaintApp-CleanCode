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
public class Rectangle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint = new Point();
	private int height;
	private int width;
	private boolean selected;

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color edgeColor) {
		this(upperLeftPoint, height, width, selected);
		setEdgeColor(edgeColor);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color edgeColor, Color innerColor) {
		this(upperLeftPoint, height, width, selected, edgeColor);
		setInnerColor(innerColor);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		this.fill(graphics);
		drawSelectedRectanglePoints(graphics);
	}

	@Override
	public void fill(Graphics graphics) {
		graphics.setColor(getInnerColor());
		graphics.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);
	}

	public void drawSelectedRectanglePoints(Graphics graphics) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			markPoint(graphics, this.upperLeftPoint.getX(), this.upperLeftPoint.getY());
			markPoint(graphics, this.upperLeftPoint.getX() + this.width, this.upperLeftPoint.getY());
			markPoint(graphics, this.upperLeftPoint.getX(), this.upperLeftPoint.getY() + this.height);
			markPoint(graphics, this.upperLeftPoint.getX() + this.width, this.upperLeftPoint.getY() + this.height);
		}
	}

	public boolean contains(Point point) {
		return (upperLeftPoint.getX() <= point.getX() && this.getUpperLeftPoint().getY() <= point.getY()
				&& point.getX() <= upperLeftPoint.getX() + width && point.getY() <= upperLeftPoint.getY() + height);
	}

	@Override
	public boolean contains(int x, int y) {
		return (upperLeftPoint.getX() <= x && this.getUpperLeftPoint().getY() <= y && x <= upperLeftPoint.getX() + width
				&& y <= upperLeftPoint.getY() + height);
	}

	public double calculateArea() {
		return (double) width * height;
	}

	public int compareTo(Object o) {
		if (o instanceof Rectangle)
			return (int) (this.calculateArea() - ((Rectangle) o).calculateArea());
		return 0;
	}

	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
	}

	public void moveOn(int onX, int onY) {
		upperLeftPoint.moveOn(onX, onY);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) obj;
			return this.upperLeftPoint.equals(rectangle.getUpperLeftPoint()) && this.height == rectangle.getHeight()
					&& this.width == rectangle.getWidth();
		} else
			return false;
	}
	
	@Override
	public int hashCode() {
	    int result = upperLeftPoint.hashCode();
	    result = 31 * result + height;
	    result = 31 * result + width;
	    return result;
	}

	public Rectangle clone(Shape shape) {
		 Rectangle rectangle = new Rectangle(new Point(0,0), 0, 0);
	        if(shape instanceof Rectangle)
	        	rectangle = (Rectangle) shape;
	        rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
	        rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
	        rectangle.setHeight(this.getHeight());
	        rectangle.setWidth(this.getWidth());
	        rectangle.setInnerColor(getInnerColor());
	        rectangle.setEdgeColor(getEdgeColor());
	        return rectangle;
	}

	@Override
	public String toString() {
		return "Rectangle [UpperLeftPoint=" + upperLeftPoint.toStringPoint() + ", Height=" + height + ", Width=" + width
				+ ", OuterColor= " + this.getColorRGB() + ", InnerColor= " + this.getInnerColorRGB() + "]";
	}
}
