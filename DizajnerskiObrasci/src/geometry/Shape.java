package geometry;

import java.awt.Graphics;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.Color;

@NoArgsConstructor
@Getter
@Setter
public abstract class Shape implements Moveable, Comparable<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color edgeColor = Color.BLACK;
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);

	public void markPoint(Graphics g, int x, int y) {
		g.drawRect(x - 3, y - 3, 6, 6);
	}

	public String getColorRGB() {
		int red = this.getEdgeColor().getRed();
		int green = this.edgeColor.getGreen();
		int blue = this.edgeColor.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}

}
