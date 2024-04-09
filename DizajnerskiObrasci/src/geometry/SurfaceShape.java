package geometry;

import java.awt.Color;
import java.awt.Graphics;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = 1L;
	private Color innerColor = Color.BLACK;
	public abstract void fill(Graphics g);
	public abstract double calculateArea();

	public String getInnerColorRGB() {
		int red = this.innerColor.getRed();
		int green = this.innerColor.getGreen();
		int blue = this.innerColor.getBlue();
		return "RGB(" + red + ", " + green + ", " + blue + ")";
	}
}
