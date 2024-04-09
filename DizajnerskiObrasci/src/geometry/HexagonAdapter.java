package geometry;

import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon = new Hexagon(0, 0, 0);

	public HexagonAdapter() {}

	public HexagonAdapter(Point center) {
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}

	public HexagonAdapter(Point center, int radius) {
		this(center);
		hexagon.setR(radius);
	}

	public HexagonAdapter(int x, int y, int radius) {
		hexagon = new Hexagon(x, y, radius);
	}

	public HexagonAdapter(Point center, int radius, boolean selected) {
		this(center, radius);
		hexagon.setSelected(selected);
	}

	public HexagonAdapter(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		hexagon.setBorderColor(color);
	}

	public HexagonAdapter(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		hexagon.setAreaColor(innerColor);
	}
	
	public HexagonAdapter(Hexagon hexagon,Color edgeColor, Color innerColor) {
    	this.hexagon = hexagon;
		this.setEdgeColor(edgeColor);
		this.setHexagonInnerColor(innerColor);
	}

	@Override
	public void draw(Graphics graphics) {
		hexagon.paint(graphics);
		drawSelectedHexagonPoints(graphics);
	}

	public void drawSelectedHexagonPoints(Graphics graphics) {
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			markPoint(graphics, this.hexagon.getX(), this.hexagon.getY());
			markPoint(graphics, this.hexagon.getX() - this.hexagon.getR(), this.hexagon.getY());
			markPoint(graphics, this.hexagon.getX() + this.hexagon.getR(), this.hexagon.getY());
			markPoint(graphics, this.hexagon.getX() - this.hexagon.getR() / 2, this.hexagon.getY() - (int) (this.hexagon.getR() * Math.sqrt(3) / 2));
			markPoint(graphics, this.hexagon.getX() + this.hexagon.getR() / 2, this.hexagon.getY() - (int) (this.hexagon.getR() * Math.sqrt(3) / 2));
			markPoint(graphics, this.hexagon.getX() - this.hexagon.getR() / 2, this.hexagon.getY() + (int) (this.hexagon.getR() * Math.sqrt(3) / 2));
			markPoint(graphics, this.hexagon.getX() + this.hexagon.getR() / 2, this.hexagon.getY() + (int) (this.hexagon.getR() * Math.sqrt(3) / 2));
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof HexagonAdapter)
			return this.hexagon.getR() - ((HexagonAdapter) obj).hexagon.getR();
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
	}

	@Override
	public void moveOn(int onX, int onY) {
		hexagon.setX(onX);
		hexagon.setY(onY);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) obj;
			return hexagonAdapter.hexagon.getX() == this.hexagon.getX()
					&& hexagonAdapter.hexagon.getY() == this.hexagon.getY()
					&& hexagonAdapter.hexagon.getR() == this.hexagon.getR();
		}
		return false;
	}

	public HexagonAdapter clone(HexagonAdapter hexagon) {
		hexagon.setHexagonCenter(this.getHexagonCenter());
		hexagon.setHexagonRadius(this.getHexagonRadius());
		hexagon.setHexagonBorderColor(this.getHexagonBorderColor());
		hexagon.setHexagonInnerColor(this.getHexagonInnerColor());
		hexagon.setSelected(this.hexagon.isSelected());
		return hexagon;
	}

	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "Hexagon [Center=" + center.toStringPoint() + ", Radius=" + hexagon.getR() + ", OuterColor= "
				+ this.getColorRGB() + ", InnerColor= " + this.getInnerColorRGB() + "]";
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public Point getHexagonCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setHexagonCenter(Point center) {
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}

	public int getHexagonRadius() {
		return this.hexagon.getR();
	}

	public void setHexagonRadius(int radius) {
		this.hexagon.setR(radius);
	}

	public Color getHexagonBorderColor() {
		return this.hexagon.getBorderColor();
	}

	public void setHexagonBorderColor(Color edgeColor) {
		this.hexagon.setBorderColor(edgeColor);
	}

	public Color getHexagonInnerColor() {
		return this.hexagon.getAreaColor();
	}

	public void setHexagonInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}

	public String getInnerColorRGB() {
		int red = hexagon.getAreaColor().getRed();
		int green = hexagon.getAreaColor().getGreen();
		int blue = hexagon.getAreaColor().getBlue();
		return "(" + red + ", " + green + ", " + blue + ")";
	}

	@Override
	public String getColorRGB() {
		int red = hexagon.getBorderColor().getRed();
		int green = hexagon.getBorderColor().getGreen();
		int blue = hexagon.getBorderColor().getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
}
