package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.Before;
import org.junit.Test;

public class LineTest {
	private Line line;
	private Graphics graphics;

	@Before
	public void setUp() {
		Point startPoint = new Point(4,8);
		Point endPoint = new Point(10,15);
		Color edgeColor = Color.RED;
		boolean selected = false;
		line = new Line(startPoint, endPoint, selected, edgeColor);
		graphics = mock(Graphics.class);
	}

	@Test
	public void setStartPointExpectedEqual() {
		Point expectedStartPoint = new Point(10, 10);
		line.setStartPoint(new Point(10, 10));
		assertEquals(expectedStartPoint, line.getStartPoint());
	}
	
	@Test
	public void setEndPointExpectedEqual() {
		Point expectedEndPoint = new Point(20, 20);
		line.setEndPoint(new Point(20, 20));
		assertEquals(expectedEndPoint, line.getEndPoint());
	}
	
	@Test
	public void setStartPointAndEndPointExpectedEqual() {
		Point expectedStartPoint = new Point(1, 1);
		Point expectedEndPoint = new Point(10, 10);
		line.setStartPoint(new Point(1, 1));
		line.setEndPoint(new Point(10, 10));
		assertEquals(expectedStartPoint, line.getStartPoint());
		assertEquals(expectedEndPoint, line.getEndPoint());
	}
	
	@Test
	public void testDrawWhenLineSelected() {
		line.setSelected(true);
		line.draw(graphics);
		verify(graphics).setColor(line.getEdgeColor());
		verify(graphics).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),line.getEndPoint().getY());
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(line.getStartPoint().getX() - 3, line.getStartPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(line.getEndPoint().getX() - 3, line.getEndPoint().getY() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenLineNotSelected() {
		line.draw(graphics);
		verify(graphics).setColor(line.getEdgeColor());
		verify(graphics).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),line.getEndPoint().getY());
	}
	
	@Test
	public void containsWhenPointIsInsideLineExpectedTrue() {
	    Line line2 = new Line(new Point(0, 0), new Point(4, 0));
	    assertTrue(line2.contains(2, 0));
	}

	@Test
	public void containsWhenPointIsOutsideLineExpectedFalse() {
	    Line line2 = new Line(new Point(0, 0), new Point(4, 0));
	    assertFalse(line2.contains(6, 0));
	}
	
	@Test
	public void lengthWhenLineIsHorizontalExpectedEqual() {
	    Line line2 = new Line(new Point(0, 0), new Point(4, 0));
	    assertEquals(4.0, line2.length(), 0.0001);
	}

	@Test
	public void lengthWhenLineIsVerticalExpectedEqual() {
	    Line line2 = new Line(new Point(0, 0), new Point(0, 4));
	    assertEquals(4.0, line2.length(), 0.0001);
	}

	@Test
	public void lengthWhenLineIsDiagonalExpectedEqual() {
	    Line line2 = new Line(new Point(0, 0), new Point(3, 4));
	    assertEquals(5.0, line2.length(), 0.0001);
	}
	
	@Test
	public void compareToExpectedEqual() {
		Line l = new Line(new Point(5,5), new Point(8,8));
		assertEquals(4, line.compareTo(l));
	}
	
	@Test
	public void compareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, line.compareTo(new Point()));
	}
	
	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		line.moveBy(4, 0);
		assertEquals(8, line.getStartPoint().getX());
		assertEquals(14, line.getEndPoint().getX());
	}

	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		line.moveBy(-2, 0);
		assertEquals(2, line.getStartPoint().getX());
		assertEquals(8, line.getEndPoint().getX());
	}
	
	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		line.moveBy(0, 4);
		assertEquals(12, line.getStartPoint().getY());
		assertEquals(19, line.getEndPoint().getY());
	}

	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		line.moveBy(0, -5);
		assertEquals(3, line.getStartPoint().getY());
		assertEquals(10, line.getEndPoint().getY());
	}
	
	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		line.moveOn(5, 15);
		assertEquals(5, line.getStartPoint().getX());
		assertEquals(5, line.getEndPoint().getX());
		assertEquals(15, line.getStartPoint().getY());
		assertEquals(15, line.getEndPoint().getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		line.moveOn(-5, -15);
		assertEquals(-5, line.getStartPoint().getX());
		assertEquals(-5, line.getEndPoint().getX());
		assertEquals(-15, line.getStartPoint().getY());
		assertEquals(-15, line.getEndPoint().getY());
	}
	
	@Test
	public void equalsExpectedTrue() {
		assertTrue(line.equals(new Line(new Point(4, 8), new Point(10, 15))));
	}
	
	@Test
	public void equalsWithWrongStartPointExpectedFalse() {
		assertFalse(line.equals(new Line(new Point(4, 4), new Point(10, 15))));
	}
	
	@Test
	public void equalsWithWrongEndPointExpectedFalse() {
		assertFalse(line.equals(new Line(new Point(4, 8), new Point(4, 4))));
	}
	
	@Test
	public void equalsWithDifferentObjectExpectedFalse() {
		assertFalse(line.equals(new Line(new Point(3, 3), new Point(1, 1))));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(line.equals(obj));
	}
	
	@Test
	public void hashCodeForStartPointExpectedNotEqual() {
	    Line line1 = new Line(null, new Point(1, 1));
	    Line line2 = new Line(new Point(0, 0), new Point(1, 1));
	    assertNotEquals(line1.hashCode(), line2.hashCode());
	}
	
	@Test
	public void hashCodeForEndPointExpectedNotEqual() {
	    Line line1 = new Line(new Point(1, 1), null);
	    Line line2 = new Line(new Point(1, 1), new Point(0, 0));
	    assertNotEquals(line1.hashCode(), line2.hashCode());
	}
	
	@Test
	public void cloneExpectedEqual() {
		Line startLine = new Line(new Point(0, 0), new Point(0, 0));
		line.clone(startLine);
		assertEquals(4, startLine.getStartPoint().getX());
		assertEquals(8, startLine.getStartPoint().getY());
		assertEquals(10, startLine.getEndPoint().getX());
		assertEquals(15, startLine.getEndPoint().getY());
		assertEquals(Color.RED, startLine.getEdgeColor());
	}
	
	@Test
	public void cloneWithWrongShapeSubclassExpectedEqual() {
		Point point = new Point();
		Line line2;
		line2 = line.clone(point);
		assertEquals(4, line2.getStartPoint().getX());
		assertEquals(8, line2.getStartPoint().getY());
		assertEquals(10, line2.getEndPoint().getX());
		assertEquals(15, line2.getEndPoint().getY());
		assertEquals(Color.RED, line2.getEdgeColor());
	}
	
	@Test
	public void cloneReferencesExpectedEqual() {
		Line line1 = new Line(new Point(), new Point());
		Line line2 = new Line(new Point(), new Point());
		assertEquals(line1.hashCode(), line2.clone(line1).hashCode());
	}

	@Test
	public void toStringExpectedEquals() {
		String expected = "Line [StartPoint=(4, 8), EndPoint=(10, 15), Color= "
				+ line.getColorRGB() + "]";
		assertEquals(expected, line.toString());
	}

}
