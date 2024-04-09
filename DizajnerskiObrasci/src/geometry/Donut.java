package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color edgeColor) {
		this(center, radius, innerRadius, selected);
		setEdgeColor(edgeColor);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color edgeColor, Color innerColor) {
		this(center, radius, innerRadius, selected, edgeColor);
		setInnerColor(innerColor);
	}

	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
		graphics.setColor(getEdgeColor());
		graphics.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius,
				getInnerRadius() * 2, getInnerRadius() * 2);
		drawSelectedDonutPoints(graphics);
	}

	@Override
	public void fill(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setColor(getInnerColor());
		Ellipse2D whole = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), 2 * this.getRadius(), 2 * this.getRadius());
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.innerRadius,
				this.getCenter().getY() - this.innerRadius, 2 * this.innerRadius, 2 * this.innerRadius);
		Area outerArea = new Area(whole);
		Area innerArea = new Area(inner);
		outerArea.subtract(innerArea);
		graphics2D.fill(outerArea);
	}

	public void drawSelectedDonutPoints(Graphics graphics) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			super.drawSelectedCirclePoints(graphics, this.getRadius());
			super.drawSelectedCirclePoints(graphics, innerRadius);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		double distanceFromCenter = this.getCenter().calculateDistance(x, y);
		return super.contains(x, y) && distanceFromCenter > innerRadius;
	}

	@Override
	public boolean contains(Point p) {
		double distanceFromCenter = this.getCenter().calculateDistance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && distanceFromCenter > innerRadius;
	}

	@Override
	public double calculateArea() {
		return super.calculateArea() - innerRadius * innerRadius * Math.PI;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut donut = (Donut) obj;
			return this.getCenter().equals(donut.getCenter()) && this.getRadius() == donut.getRadius()
					&& innerRadius == donut.getInnerRadius();
		}
		return false;
	}

	@Override
	public Donut clone(Shape shape) {
		Donut donut = new Donut(new Point(0, 0), 0, 0);
		if (shape instanceof Donut)
			donut = (Donut) shape;
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setEdgeColor(this.getEdgeColor());
		donut.setInnerColor(this.getInnerColor());
		return donut;
	}

	@Override
	public String toString() {
		return "Donut [Center=" + this.getCenter().toStringPoint() + ", OuterRadius=" + this.getRadius()
				+ ", InnerRadius=" + innerRadius + ", OuterColor= " + this.getColorRGB() + ", InnerColor= "
				+ this.getInnerColorRGB() + "]";
	}
}
