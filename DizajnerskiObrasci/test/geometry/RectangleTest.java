package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {
	private Rectangle rectangle;
	private Graphics graphics;

	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(4,4), 10, 20, false, Color.RED, Color.PINK);
		graphics = mock(Graphics.class);
	}
	
	@Test
	public void testDrawWhenSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);
		verify(graphics).setColor(Color.PINK);
		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getX() + 1, rectangle.getUpperLeftPoint().getY() + 1, rectangle.getWidth() - 1, rectangle.getHeight() - 1);
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3, rectangle.getUpperLeftPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3 + rectangle.getWidth(), rectangle.getUpperLeftPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3, rectangle.getUpperLeftPoint().getY() - 3 + rectangle.getHeight(), 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() + rectangle.getWidth() - 3, rectangle.getUpperLeftPoint().getY() + rectangle.getHeight() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		rectangle.setSelected(false);
		rectangle.draw(graphics);
		verify(graphics).setColor(Color.PINK);
		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getX() + 1, rectangle.getUpperLeftPoint().getY() + 1, rectangle.getWidth() - 1, rectangle.getHeight() - 1);
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());	
	}

	@Test
	public void containsPointExpectedTrue() {
		Point insidePoint = new Point();
		insidePoint.setX(4);
		insidePoint.setY(4);
		assertTrue(rectangle.contains(insidePoint));
	}

	@Test
	public void containsPointExpectedFalse() {
		Point outsidePoint = new Point(1, 1);
		assertFalse(rectangle.contains(outsidePoint));
	}
	
	@Test
	public void containsPointToLeftOfUpperLeftBoundaryExpectedFalse() {
	    Point abovePoint = new Point(2, 4); 
	    assertFalse(rectangle.contains(abovePoint));
	}
	
	@Test
	public void containsPointAboveUpperLeftBoundaryExpectedFalse() {
	    Point abovePoint = new Point(4, 2); 
	    assertFalse(rectangle.contains(abovePoint));
	}
	
	@Test
	public void containsPointOnUpperLeftBoundaryExpectedTrue() {
	    Point onBoundaryPoint = new Point(4, 4);
	    assertTrue(rectangle.contains(onBoundaryPoint));
	}
	
	@Test
	public void containsPointOnLowerLeftBoundaryExpectedTrue() {
	    Point onBoundaryPoint = new Point(4, 14);
	    assertTrue(rectangle.contains(onBoundaryPoint));
	}
	
	@Test
	public void containsPointOnUpperRightBoundaryExpectedTrue() {
	    Point onBoundaryPoint = new Point(24, 4);
	    assertTrue(rectangle.contains(onBoundaryPoint));
	}
	
	@Test
	public void containsPointOnLowerRightBoundaryExpectedTrue() {
	    Point onBoundaryPoint = new Point(24, 14);
	    assertTrue(rectangle.contains(onBoundaryPoint));
	}
	
	@Test
	public void containsPointBelowToLowerBoundaryExpectedFalse() {
	    Point outsidePoint = new Point(4, 15);
	    assertFalse(rectangle.contains(outsidePoint));
	}
	
	@Test
	public void containsPointLeftToLowerLeftBoundaryExpectedFalse() {
	    Point outsidePoint = new Point(2, 14);
	    assertFalse(rectangle.contains(outsidePoint));
	}
	
	@Test
	public void containsPointBelowToLowerRightBoundaryExpectedFalse() {
	    Point outsidePoint = new Point(24, 15);
	    assertFalse(rectangle.contains(outsidePoint));
	}
	
	@Test
	public void containsPointRightToLowerBoundaryExpectedFalse() {
	    Point outsidePoint = new Point(25, 14);
	    assertFalse(rectangle.contains(outsidePoint));
	}
	
	@Test
	public void containsCoordinatesExpectedTrue() {
		assertTrue(rectangle.contains(6, 6));
	}

	@Test
	public void containsCoordinatesExpectedFalse() {
		assertFalse(rectangle.contains(1, 1));
	}
	
	@Test
	public void containsCoordinatesLeftOfUpperLeftBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(2, 4));
	}
	
	@Test
	public void containsCoordinatesAboveUpperLeftBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(4, 2));
	}
	
	@Test
	public void containsCoordinatesUpperLeftBoundaryExpectedTrue() {
	    assertTrue(rectangle.contains(4, 4));
	}
	
	@Test
	public void containsCoordinatesOnLowerLeftBoundaryExpectedTrue() {
	    assertTrue(rectangle.contains(4, 14));
	}
	
	@Test
	public void containsCoordinatesOnUpperRightBoundaryExpectedTrue() {
	    assertTrue(rectangle.contains(24, 4));
	}
	
	@Test
	public void containsCoordinatesOnLowerRightBoundaryExpectedTrue() {
	    assertTrue(rectangle.contains(24, 14));
	}
	
	@Test
	public void containsCoordinatesBelowToLowerBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(4, 15));
	}
	
	@Test
	public void containsCoordinatesLeftToLowerLeftBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(2, 14));
	}
	
	@Test
	public void containsCoordinatesBelowToLowerRightBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(24, 15));
	}
	
	@Test
	public void containsCoordinatesRightToLowerBoundaryExpectedFalse() {
	    assertFalse(rectangle.contains(25, 14));
	}
	
	@Test
	public void calculateAreaExpectedEqual() {
		assertEquals(200, rectangle.calculateArea(), 1e-15);
	}
	
	@Test
	public void compareToExpectedEqual() {
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setUpperLeftPoint(new Point(4, 4));
		rectangle2.setHeight(10);
		rectangle2.setWidth(10);
		assertEquals(100, rectangle.compareTo(rectangle2));
	}
	
	@Test
	public void compareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, rectangle.compareTo(new Point()));
	}
	
	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		rectangle.moveBy(4, 0);
		assertEquals(8, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		rectangle.moveBy(-1, 0);
		assertEquals(3, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		rectangle.moveBy(0, 6);
		assertEquals(10, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		rectangle.moveBy(0, -2);
		assertEquals(2, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		rectangle.moveOn(5, 10);
		assertEquals(5, rectangle.getUpperLeftPoint().getX());
		assertEquals(10, rectangle.getUpperLeftPoint().getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		rectangle.moveOn(-5, -15);
		assertEquals(-5, rectangle.getUpperLeftPoint().getX());
		assertEquals(-15, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void equalsExpectedTrue() {
		assertTrue(rectangle.equals(new Rectangle(new Point(4, 4), 10, 20)));
	}
	
	@Test
	public void equalsWithWrongUpperLeftPointExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 1), 10, 20)));
	}
	
	@Test
	public void equalsWithWrongWidthExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(4, 4), 99, 20)));
	}
	
	@Test
	public void equalsWithWrongHeightExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(4, 4), 10, 99)));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(rectangle.equals(obj));
	}
	
	@Test
	public void cloneExpectedEqual() throws CloneNotSupportedException {
		Rectangle newRectangle = new Rectangle(new Point(0, 0), 0, 0);
		newRectangle = rectangle.clone(newRectangle);
		assertEquals(4, newRectangle.getUpperLeftPoint().getX());
		assertEquals(4, newRectangle.getUpperLeftPoint().getY());
		assertEquals(10, newRectangle.getHeight());
		assertEquals(20, newRectangle.getWidth());
		assertEquals(Color.RED, newRectangle.getEdgeColor());
		assertEquals(Color.PINK, newRectangle.getInnerColor());
	}
	
	@Test
	public void cloneWithWrongShapeSubclassExpectedEqual() throws CloneNotSupportedException {
		Point point = new Point();
		Rectangle newRectangle;
		newRectangle = rectangle.clone(point);
		assertEquals(4, newRectangle.getUpperLeftPoint().getX());
		assertEquals(4, newRectangle.getUpperLeftPoint().getY());
		assertEquals(10, newRectangle.getHeight());
		assertEquals(20, newRectangle.getWidth());
		assertEquals(Color.RED, newRectangle.getEdgeColor());
		assertEquals(Color.PINK, newRectangle.getInnerColor());
	}
	
	@Test
	public void cloneReferencesExpectedEqual() throws CloneNotSupportedException {
		Rectangle rectangle1 = new Rectangle(new Point(), 0, 0);
		Rectangle rectangle2 = new Rectangle(new Point(), 0, 0);
		assertEquals(rectangle1.hashCode(), rectangle2.clone(rectangle1).hashCode());
	}
	
	@Test
	public void cloneReferencesWithWrongShapeSubclassExpectedNotEqual() throws CloneNotSupportedException {
		Point point = new Point();
		Rectangle rectangle2;
		rectangle2 = rectangle.clone(point);
		assertEquals(4, rectangle2.getUpperLeftPoint().getX());
		assertEquals(4, rectangle2.getUpperLeftPoint().getY());
		assertEquals(10, rectangle2.getHeight());
		assertEquals(20, rectangle2.getWidth());
		assertEquals(Color.RED, rectangle2.getEdgeColor());
		assertEquals(Color.PINK, rectangle2.getInnerColor());
	}
	
	@Test
	public void toStringExpectedEquals() {
		assertEquals("Rectangle [UpperLeftPoint=(4, 4), Height=10, Width=20, OuterColor= " + rectangle.getColorRGB() + ", InnerColor= " + rectangle.getInnerColorRGB() + "]", rectangle.toString());
	}

}
