package mvc;

import static org.mockito.Mockito.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.junit.Before;
import org.junit.Test;
import geometry.*;

public class DrawingViewTest {
	private DrawingView view;
	private Graphics graphics;
	private MouseListener mouseListener;

	@Before
	public void setUp() {
		DrawingModel model = new DrawingModel();
		view = new DrawingView();
		view.setModel(model);
		graphics = mock(Graphics.class);
		mouseListener = mock(MouseListener.class);
	}
	
	@Test
	public void testPaintWithoutAnyShapesExpectedTrue() {
		view.paint(graphics);
	}
	
	@Test
	public void testPaintWithOneShapeExpectedTrue() {
		Point point = mock(Point.class);
		view.getModel().addShapeToList(point);
		view.paint(graphics);
		verify(point).draw(graphics);
	}
	
	@Test
	public void testPaintWithMoreThanOneShapeExpectedTrue() {
		Point point = mock(Point.class);
		Circle circle = mock(Circle.class);
		view.getModel().addShapeToList(point);
		view.getModel().addShapeToList(circle);
		view.paint(graphics);
		verify(point).draw(graphics);
		verify(circle).draw(graphics);
	}
	
	@Test
    public void testMouseClicked() {
        view.addMouseListener(mouseListener);
        view.dispatchEvent(new MouseEvent(view, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
        verify(mouseListener).mouseClicked(any(MouseEvent.class));
    }

}
