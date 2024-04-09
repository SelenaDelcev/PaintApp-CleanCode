package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

public class CircleTest {
	private Circle circle;
	private Graphics graphics;

	@Before
	public void setUp() {
		circle = new Circle(new Point(5,5), 25, false, Color.RED, Color.PINK);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawWhenSelected() {
		circle.setSelected(true);
		circle.draw(graphics);
		verify(graphics).setColor(Color.PINK);
		verify(graphics).fillOval(circle.getCenter().getX() - circle.getRadius() + 1, circle.getCenter().getY() - circle.getRadius() + 1, circle.getRadius() * 2 - 2, circle.getRadius() * 2 - 2);
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3, 6, 6);
		verify(graphics).drawRect(circle.getCenter().getX() - 3 - circle.getRadius(), circle.getCenter().getY() - 3, 6, 6);
		verify(graphics).drawRect(circle.getCenter().getX() - 3 + circle.getRadius(), circle.getCenter().getY() - 3, 6, 6);
		verify(graphics).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3 + circle.getRadius(), 6, 6);
		verify(graphics).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3 - circle.getRadius(), 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		circle.setSelected(false);
		circle.draw(graphics);
		verify(graphics).setColor(Color.PINK);
		verify(graphics).fillOval(circle.getCenter().getX() - circle.getRadius() + 1, circle.getCenter().getY() - circle.getRadius() + 1, circle.getRadius() * 2 - 2, circle.getRadius() * 2 - 2);
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
	}
	
	@Test
	public void containsPointExpectedTrue() {
		Point insidePoint = new Point();
		insidePoint.setX(3);
		insidePoint.setY(3);
		assertTrue(circle.contains(insidePoint));
	}

	@Test
	public void containsPointExpectedFalse() {
		Point outsidePoint = new Point(0, 30);
		assertFalse(circle.contains(outsidePoint));
	}
	
	@Test
	public void containsCoordinatesExpectedTrue() {
		assertTrue(circle.contains(3, 3));
	}

	@Test
	public void containsCoordinatesExpectedFalse() {
		assertFalse(circle.contains(0, 30));
	}
	
	@Test
	public void calculateAreaExpectedEqual() {
		double expectedArea = Math.PI * circle.getRadius() * circle.getRadius();
        double actualArea = circle.calculateArea();
        assertEquals(expectedArea, actualArea, 0.0001);
	}
	
	@Test
	public void compareToExpectedEqual() {
		Circle circle2 = new Circle();
		circle2.setCenter(new Point(2, 2));
		circle2.setRadius(10);
		assertEquals(15, circle.compareTo(circle2));
	}
	
	@Test
	public void compareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, circle.compareTo(new Point()));
	}
	
	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		circle.moveBy(5, 0);
		assertEquals(10, circle.getCenter().getX());
	}
	
	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		circle.moveBy(-1, 0);
		assertEquals(4, circle.getCenter().getX());
	}
	
	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		circle.moveBy(0, 3);
		assertEquals(8, circle.getCenter().getY());
	}
	
	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		circle.moveBy(0, -2);
		assertEquals(3, circle.getCenter().getY());
	}
	
	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		circle.moveOn(5, 10);
		assertEquals(5, circle.getCenter().getX());
		assertEquals(10, circle.getCenter().getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		circle.moveOn(-5, -15);
		assertEquals(-5, circle.getCenter().getX());
		assertEquals(-15, circle.getCenter().getY());
	}
	
	@Test
	public void equalsExpectedTrue() {
		assertTrue(circle.equals(new Circle(new Point(5, 5), 25)));
	}
	
	@Test
	public void equalsExpectedFalse() {
		assertFalse(circle.equals(new Circle(new Point(1, 1), 25)));
	}
	
	@Test
	public void equalsWithWrongRadiusExpectedFalse() {
		assertFalse(circle.equals(new Circle(new Point(5, 5), 99)));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(circle.equals(obj));
	}
	
	@Test
	public void cloneExpectedEqual() throws CloneNotSupportedException {
		Circle circle2 = new Circle(new Point(0, 0), 0);
		circle2 = circle.clone(circle2);
		assertEquals(5, circle2.getCenter().getX());
		assertEquals(5, circle2.getCenter().getY());
		assertEquals(25, circle2.getRadius());
		assertEquals(Color.RED, circle2.getEdgeColor());
		assertEquals(Color.PINK, circle2.getInnerColor());
	}
	
	@Test
	public void cloneWithWrongShapeSubclassExpectedEqual() throws CloneNotSupportedException {
		Point point = new Point();
		Circle circle2;
		circle2 = circle.clone(point);
		assertEquals(5, circle2.getCenter().getX());
		assertEquals(5, circle2.getCenter().getY());
		assertEquals(25, circle2.getRadius());
		assertEquals(Color.RED, circle2.getEdgeColor());
		assertEquals(Color.PINK, circle2.getInnerColor());
	}
	
	@Test
	public void cloneReferencesExpectedEqual() throws CloneNotSupportedException {
		Circle circle1 = new Circle(new Point(0, 0), 0);
		Circle circle2 = new Circle(new Point(0, 0), 0);
		assertEquals(circle1.hashCode(), circle2.clone(circle1).hashCode());
	}
	
	@Test
	public void toStringExpectedEquals() {
		assertEquals("Circle [Center=(5, 5), Radius=25, OuterColor= " + circle.getColorRGB() + ", InnerColor= " + circle.getInnerColorRGB() + "]", circle.toString());
	}
}
