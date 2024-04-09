package geometry;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.Before;
import org.junit.Test;

import hexagon.Hexagon;

public class HexagonAdapterTest {
	private HexagonAdapter hexagonAdapter;
	private Graphics graphics;
	private Hexagon hexagon;

	@Before
	public void setUp() {
		hexagon = mock(Hexagon.class);
		hexagonAdapter = new HexagonAdapter(new Point(5, 5), 25, false, Color.BLACK, Color.PINK);
		graphics = mock(Graphics.class);
	}

	@Test
	public void testDrawWhenSelected() {
		HexagonAdapter hex = new HexagonAdapter(hexagon, Color.BLACK, Color.PINK);
		hex.setSelected(true);
		hex.draw(graphics);
		verify(hexagon).paint(graphics);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		HexagonAdapter hex = new HexagonAdapter(hexagon, Color.BLACK, Color.PINK);
		hex.setSelected(false);
		hex.draw(graphics);
		verify(hexagon).paint(graphics);
	}
	
	@Test
	public void containsExpectedTrue() {
		assertTrue(hexagonAdapter.contains(6,6));
	}

	@Test
	public void containsExpectedFalse() {
		assertFalse(hexagonAdapter.contains(100, 100));
	}
	
	@Test
	public void compareToExpectedEqual() {
		HexagonAdapter hexagon2 = new HexagonAdapter(5, 5, 15);
		assertEquals(10, hexagonAdapter.compareTo(hexagon2));
	}
	
	@Test
	public void compareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, hexagonAdapter.compareTo(new Point()));
	}
	
	@Test
	public void moveByXWithPositiveNumberExpectedEqual() {
		hexagonAdapter.moveBy(5, 0);
		assertEquals(10, hexagonAdapter.getHexagonCenter().getX());
	}
	
	@Test
	public void moveByXWithNegativeNumberExpectedEqual() {
		hexagonAdapter.moveBy(-1, 0);
		assertEquals(4, hexagonAdapter.getHexagonCenter().getX());
	}
	
	@Test
	public void moveByYWithPositiveNumberExpectedEqual() {
		hexagonAdapter.moveBy(0, 5);
		assertEquals(10, hexagonAdapter.getHexagonCenter().getY());
	}
	
	@Test
	public void moveByYWithNegativeNumberExpectedEqual() {
		hexagonAdapter.moveBy(0, -2);
		assertEquals(3, hexagonAdapter.getHexagonCenter().getY());
	}
	
	@Test
	public void moveOnWithPositiveNumbersExpectedEqual() {
		hexagonAdapter.moveOn(5, 10);
		assertEquals(5, hexagonAdapter.getHexagonCenter().getX());
		assertEquals(10, hexagonAdapter.getHexagonCenter().getY());
	}

	@Test
	public void moveOnWithNegativeNumbersExpectedEqual() {
		hexagonAdapter.moveOn(-5, -15);
		assertEquals(-5, hexagonAdapter.getHexagonCenter().getX());
		assertEquals(-15, hexagonAdapter.getHexagonCenter().getY());
	}
	
	@Test
	public void equalsExpectedTrue() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(5, 5, 25)));
	}
	
	@Test
	public void equalsWithWrongCenterExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(1, 1, 25)));
	}
	
	@Test
	public void equalsWithWrongEdgeExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(5, 5, 100)));
	}
	
	@Test
	public void equalsWithWrongXCoordinateExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(1, 5, 25)));
	}
	
	@Test
	public void equalsWithWrongYCoordinateExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(5, 1, 25)));
	}
	
	@Test
	public void equalsWithDifferentObjectType() {
	    Object obj = new Object();
	    assertFalse(hexagonAdapter.equals(obj));
	}
	
	@Test
	public void cloneExpectedEqual() {
		HexagonAdapter newHexagon = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		newHexagon = hexagonAdapter.clone(newHexagon);
		assertEquals(5, newHexagon.getHexagonCenter().getX());
		assertEquals(5, newHexagon.getHexagonCenter().getY());
		assertEquals(25, newHexagon.getHexagonRadius());
		assertEquals(Color.BLACK, newHexagon.getEdgeColor());
		assertEquals(Color.PINK, newHexagon.getHexagonInnerColor());
	}
	
	@Test
	public void cloneReferencesExpectedEqual() {
		HexagonAdapter hexagon1 = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		HexagonAdapter hexagon2 = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		assertEquals(hexagon1.hashCode(), hexagon2.clone(hexagon1).hashCode());
	}
	
	@Test
	public void toStringExpectedEquals() {
		assertEquals("Hexagon [Center=(5, 5), Radius=25, OuterColor= " + hexagonAdapter.getColorRGB() + ", InnerColor= " + hexagonAdapter.getInnerColorRGB() + "]", hexagonAdapter.toString());
	}
	
	@Test
    public void getHexagonExpectedEqual() {
		Hexagon hexagon2 = new Hexagon(10, 20, 30);
        HexagonAdapter hexAdapter = new HexagonAdapter(hexagon2, Color.RED, Color.BLUE);
        assertEquals(hexagon2, hexAdapter.getHexagon());
    }

    @Test
    public void setHexagon() {
    	HexagonAdapter hexAdapter = new HexagonAdapter();
        Hexagon hexagon2 = new Hexagon(0, 0, 0);
        hexAdapter.setHexagon(hexagon2);
        assertEquals(hexagon2, hexAdapter.getHexagon());
    }

}
