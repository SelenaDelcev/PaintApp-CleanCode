package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.*;
import java.awt.geom.*;

import org.junit.*;

public class DonutTest {
	private Donut donut;
	private Graphics2D graphics2D;
	Area area;

	@Before
	public void setUp() {
		donut = new Donut(new Point(4, 4), 20, 10, false, Color.RED, Color.PINK);
		graphics2D = mock(Graphics2D.class);
		Ellipse2D outer = new Ellipse2D.Double(donut.getCenter().getX() - donut.getRadius(),
				donut.getCenter().getY() - donut.getRadius(), donut.getRadius() * 2, donut.getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(donut.getCenter().getX() - donut.getInnerRadius(),
				donut.getCenter().getY() - donut.getInnerRadius(), donut.getInnerRadius() * 2,
				donut.getInnerRadius() * 2);
		area = new Area(outer);
		area.subtract(new Area(inner));
	}

	@Test
	public void testDrawWhenSelected() {
		donut.setSelected(true);
	    donut.draw(graphics2D);
	    verify(graphics2D, times(2)).setColor(Color.RED);
	    verify(graphics2D, times(2)).drawOval(anyInt(), anyInt(), anyInt(), anyInt());
	    verify(graphics2D, times(4)).setColor(Color.BLUE);
	    verify(graphics2D, times(3)).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D, times(2)).drawRect(donut.getCenter().getX() - 3 - donut.getRadius(), donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D, times(2)).drawRect(donut.getCenter().getX() - 3 + donut.getRadius(), donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D, times(2)).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3 + donut.getRadius(), 6, 6);
		verify(graphics2D, times(2)).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3 - donut.getRadius(), 6, 6);
		verify(graphics2D, times(1)).drawRect(donut.getCenter().getX() - 3 - donut.getInnerRadius(), donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D, times(1)).drawRect(donut.getCenter().getX() - 3 + donut.getInnerRadius(), donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D, times(1)).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3 + donut.getInnerRadius(), 6, 6);
		verify(graphics2D, times(1)).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3 - donut.getInnerRadius(), 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		donut.setSelected(false);
		donut.draw(graphics2D);
		verify(graphics2D, times(2)).setColor(Color.RED);
		verify(graphics2D, times(2)).drawOval(anyInt(), anyInt(), anyInt(), anyInt());
	}
	
	@Test
	public void containsPointExpectedTrue() {
		assertTrue(donut.contains(new Point(15, 15)));
	}

	@Test
	public void containsPointExpectedFalse() {
		assertFalse(donut.contains(new Point(100, 100)));
	}
	
	@Test
	public void containsPointWithWrongXCoordinateExpectedFalse() {
		assertFalse(donut.contains(new Point(100,15)));
	}
	
	@Test
	public void containsPointWithWrongYCoordinateExpectedFalse() {
		assertFalse(donut.contains(new Point(15,100)));
	}
	
	@Test
	public void containsPointWithCoordinatesInInnerCircleExpectedFalse() {
		assertFalse(donut.contains(new Point(6, 6)));
	}
	
	@Test
	public void containsCoordinatesExpectedTrue() {
		assertTrue(donut.contains(15,15));
	}

	@Test
	public void containsCoordinatesExpectedFalse() {
		assertFalse(donut.contains(0, 100));
	}
	
	@Test
	public void containsCoordinatesWithWrongXCoordinateExpectedFalse() {
		assertFalse(donut.contains(100,15));
	}
	
	@Test
	public void containsCoordinatesWithWrongYCoordinateExpectedFalse() {
		assertFalse(donut.contains(15,100));
	}
	
	@Test
	public void containsWithCoordinatesInInnerCircleExpectedFalse() {
		assertFalse(donut.contains(6, 6));
	}
	
	@Test
	public void calculateAreaExpectedEqual() {
		double expectedArea = (Math.PI * donut.getRadius() * donut.getRadius()) - donut.getInnerRadius() * donut.getInnerRadius() * Math.PI;
        double actualArea = donut.calculateArea();
        assertEquals(expectedArea, actualArea, 0.0001);
	}
	
	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		donut.moveBy(4, 0);
		assertEquals(8, donut.getCenter().getX());
	}
	
	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		donut.moveBy(-1, 0);
		assertEquals(3, donut.getCenter().getX());
	}
	
	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		donut.moveBy(0, 2);
		assertEquals(6, donut.getCenter().getY());
	}
	
	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		donut.moveBy(0, -2);
		assertEquals(2, donut.getCenter().getY());
	}
	
	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		donut.moveOn(5, 10);
		assertEquals(5, donut.getCenter().getX());
		assertEquals(10, donut.getCenter().getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		donut.moveOn(-5, -15);
		assertEquals(-5, donut.getCenter().getX());
		assertEquals(-15, donut.getCenter().getY());
	}
	
	@Test
	public void equalsExpectedTrue() {
		assertTrue(donut.equals(new Donut(new Point(4, 4), 20, 10)));
	}
	
	@Test
	public void equalsExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(1, 1), 10, 20)));
	}
	
	@Test
	public void equalsWithWrongCenterExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(1, 1), 20, 10)));
	}
	
	@Test
	public void equalsWithWrongRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(4, 4), 99, 10)));
	}
	
	@Test
	public void equalsWithWrongInnerRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(4, 4), 20, 99)));
	}
	
	@Test
	public void equalsWithWrongCentersAndRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(3, 3), 30, 10)));
	}
	
	@Test
	public void equalsWithWrongCentersAndInnerRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(3, 3), 20, 30)));
	}
	
	@Test
	public void equalsWithWrongRadiusAndInnerRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(4, 4), 10, 20)));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(donut.equals(obj));
	}
	
	@Test
	public void cloneExpectedEqual() throws CloneNotSupportedException {
		Donut newDonut = new Donut(new Point(0, 0), 0, 0);
		newDonut = donut.clone(newDonut);
		assertEquals(4, newDonut.getCenter().getX());
		assertEquals(4, newDonut.getCenter().getY());
		assertEquals(20, newDonut.getRadius());
		assertEquals(10, newDonut.getInnerRadius());
		assertEquals(Color.RED, newDonut.getEdgeColor());
		assertEquals(Color.PINK, newDonut.getInnerColor());
	}
	
	@Test
	public void cloneWithWrongShapeSubclassExpectedEqual() throws CloneNotSupportedException {
		Point point = new Point();
		Donut newDonut;
		newDonut = donut.clone(point);
		assertEquals(4, newDonut.getCenter().getX());
		assertEquals(4, newDonut.getCenter().getY());
		assertEquals(20, newDonut.getRadius());
		assertEquals(10, newDonut.getInnerRadius());
		assertEquals(Color.RED, newDonut.getEdgeColor());
		assertEquals(Color.PINK, newDonut.getInnerColor());
	}
	
	@Test
	public void cloneReferencesExpectedEqual() throws CloneNotSupportedException {
		Donut donut1 = new Donut(new Point(0, 0), 0, 0);
		Donut donut2 = new Donut(new Point(0, 0), 0, 0);
		assertEquals(donut1.hashCode(), donut2.clone(donut1).hashCode());
	}
	
	@Test
	public void cloneReferencesWithWrongShapeSubclassExpectedNotEqual() throws CloneNotSupportedException {
		Donut donut1 = new Donut();
		donut1.setCenter(new Point());
		donut1.setRadius(0);
		donut1.setInnerRadius(0);
		Donut donut2 = new Donut(new Point(0, 0), 0 , 0);
		Point point = new Point();
		assertNotEquals(donut1.hashCode(), donut2.clone(point).hashCode());
	}
	
	@Test
	public void toStringExpectedEquals() {
		assertEquals("Donut [Center=(4, 4), OuterRadius=20, InnerRadius=10, OuterColor= " + donut.getColorRGB() + ", InnerColor= " + donut.getInnerColorRGB() + "]", donut.toString());
	}

}
