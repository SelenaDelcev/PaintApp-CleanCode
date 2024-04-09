package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.Before;
import org.junit.Test;

public class PointTest {
	private Point point;
	private Graphics graphics;

	@Before
	public void setUp() {
		point = new Point(4, 8, false, Color.RED);
		graphics = mock(Graphics.class);
	}
	
	@Test
	public void setXExpectedEqual() {
		point.setX(10);
		assertEquals(10, point.getX());
	}
	
	@Test
	public void setYExpectedEqual() {
		point.setY(5);
		assertEquals(5, point.getY());
	}
	
	@Test
	public void setXAndYExpectedEqual() {
		point.setX(8);
		point.setY(4);
		assertEquals(8, point.getX());
		assertEquals(4, point.getY());
	}

	@Test
	public void testDrawWhenPointSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(point.getEdgeColor());
		verify(graphics).drawLine(point.getX() - 3, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 3, point.getX(), point.getY() + 3);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(point.getX() - 3, point.getY() - 3, 6, 6);
	}

	@Test
	public void testDrawWhenPointNotSelected() {
		point.setSelected(false);
		point.draw(graphics);
		verify(graphics).setColor(point.getEdgeColor());
		verify(graphics).drawLine(point.getX() - 3, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 3, point.getX(), point.getY() + 3);
	}

	@Test
	public void containsExpectedTrue() {
		assertTrue(point.contains(4, 8));
	}

	@Test
	public void containsExpectedFalse() {
		assertFalse(point.contains(3, 3));
	}

	@Test
	public void containsWithWrongCoordinateForXExpectedFalse() {
		assertFalse(point.contains(20, 8));
	}

	@Test
	public void containsWrongCoordinateForYExpectedFalse() {
		assertFalse(point.contains(4, 25));
	}

	@Test
	public void calculateDistanceExpectedEqual() {
		Point newPoint = new Point(1, 4);
		double distance = newPoint.calculateDistance(point.getX(), point.getY());
		assertEquals(5.0, distance, 0.0001);
	}

	@Test
	public void compareToExpectedEqual() {
		Point point2 = new Point(5, 10);
		assertEquals(-2, point.compareTo(point2));
	}

	@Test
	public void compareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, point.compareTo(new Line()));
	}

	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		point.moveBy(6, 0);
		assertEquals(10, point.getX());
	}

	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		point.moveBy(-2, 0);
		assertEquals(2, point.getX());
	}

	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		point.moveBy(0, 4);
		assertEquals(12, point.getY());
	}

	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		point.moveBy(0, -2);
		assertEquals(6, point.getY());
	}

	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		point.moveOn(5, 15);
		assertEquals(5, point.getX());
		assertEquals(15, point.getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		point.moveOn(-5, -15);
		assertEquals(-5, point.getX());
		assertEquals(-15, point.getY());
	}

	@Test
	public void equalsExpectedTrue() {
		assertTrue(point.equals(new Point(4, 8)));
	}

	@Test
	public void equalsWithWrongCoordinateForXExpectedFalse() {
		assertFalse(point.equals(new Point(1, 8)));
	}

	@Test
	public void equalsWithWrongCoordinateForYExpectedFalse() {
		assertFalse(point.equals(new Point(4, 1)));
	}

	@Test
	public void equalsWithDifferentObjectExpectedFalse() {
		assertFalse(point.equals(new Point(1, 1)));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(point.equals(obj));
	}

	@Test
	public void cloneExpectedEqual() {
		Point startPoint = new Point(0, 0);
		point.clone(startPoint);
		assertEquals(4, point.getX());
		assertEquals(8, point.getY());
		assertEquals(Color.RED, point.getEdgeColor());
	}

	@Test
	public void cloneReferencesExpectedEqual() {
		Point point1 = new Point();
		Point point2 = new Point();
		assertEquals(point1.hashCode(), point2.clone(point1).hashCode());
	}

	@Test
	public void toStringPointExpectedEquals() {
		String expected = "(4, 8)";
		assertEquals(expected, point.toStringPoint());
	}
	
	@Test
	public void toStringExpectedEquals() {
		String expected = "Point [x= 4, y= 8, Color= " + point.getColorRGB() + "]";
		assertEquals(expected, point.toString());
	}
}
