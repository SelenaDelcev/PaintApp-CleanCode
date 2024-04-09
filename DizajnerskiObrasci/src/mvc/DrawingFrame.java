package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;

@Getter
@Setter
public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String FONT_NAME = "Bahnschrift";
	private DrawingView view = new DrawingView();
	private transient DrawingController controller;
	private JToggleButton btnPoint = new JToggleButton("Point          ");
	private JToggleButton btnLine = new JToggleButton("Line            ");
	private JToggleButton btnRectangle = new JToggleButton("Rectangle ");
	private JToggleButton btnCircle = new JToggleButton("Circle         ");
	private JToggleButton btnDonut = new JToggleButton("Donut         ");
	private JToggleButton btnSelect = new JToggleButton("Selection");
	private JToggleButton btnHexagon = new JToggleButton("Hexagon    ");
	private JButton btnModification = new JButton("Modification");
	private JButton btnUndo = new JButton("Undo ");
	private JButton btnRedo = new JButton("Redo");
	private JButton btnLoadCommands = new JButton("Load commands");
	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");
	private JButton btnNext = new JButton("Next");
	private JButton btnSaveCommands = new JButton("Save commands");
	private JButton btnSaveDrawing = new JButton("Save drawing");
	private JButton btnLoadDrawing = new JButton("Load drawing");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnOuterColor = new JButton("Outer color");
	private JButton btnInnerColor = new JButton("Inner color");
	ButtonGroup btnGroup = new ButtonGroup();
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JPanel panel = new JPanel();
	private Color outerColorFrame;
	private Color innerColorFrame;

	public DrawingFrame() {
		initializeUI();
		addActionListeners();
	}

	private void initializeUI() {
		// Setting up frame properties
		setTitle("IT 21-2018 Komosar Aleksa");
		setFont(new Font(FONT_NAME, Font.PLAIN, 12));
		setBackground(UIManager.getColor("Tree.selectionForeground"));
		setBounds(150, 150, 1000, 700);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// Setting up layouts
		setCenterLayout();
		setNorthLayout();
		setSouthLayout();
		setWestLayout();
		setEastLayout();
	}

	private void setCenterLayout() {
		getContentPane().add(view);
		getContentPane().setBackground(Color.WHITE);
		view.setBackground(Color.WHITE);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		GroupLayout glView = new GroupLayout(view);
		glView.setHorizontalGroup(glView.createParallelGroup(Alignment.LEADING).addGap(0, 994, Short.MAX_VALUE));
		glView.setVerticalGroup(glView.createParallelGroup(Alignment.LEADING).addGap(0, 595, Short.MAX_VALUE));
		view.setLayout(glView);
	}

	private void setNorthLayout() {
		JPanel pnlNorth = createPanelWithLayout(new GridBagLayout());
		setPropertiesForNorthPanel(pnlNorth);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);

		addNorthButtonToPanel(pnlNorth, btnSelect, gbc, 2, 0, false);
		addNorthButtonToPanel(pnlNorth, btnModification, gbc, 3, 0, false);
		addNorthButtonToPanel(pnlNorth, btnDelete, gbc, 4, 0, false);

		JLabel lblColors = new JLabel("COLORS:");
		addColorButtonsToPanel(pnlNorth, lblColors, gbc, 8, 0);
		addColorButtonsToPanel(pnlNorth, btnOuterColor, gbc, 9, 1);
		addColorButtonsToPanel(pnlNorth, btnInnerColor, gbc, 7, 1);
		setIconsForNorthButtons();
	}

	private JPanel createPanelWithLayout(LayoutManager layout) {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(layout);
		return newPanel;
	}

	private void setPropertiesForNorthPanel(JPanel pnlNorth) {
		pnlNorth.setBackground(UIManager.getColor("activeCaption"));
		pnlNorth.setForeground(UIManager.getColor("activeCaption"));
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gblPnlNorth = new GridBagLayout();
		gblPnlNorth.columnWidths = new int[] { 292, 0, 61, 61, 0, 108, 0, 89, 60, 0, 0, 0, 0 };
		gblPnlNorth.rowHeights = new int[] { 55, 0, 0, 0 };
		gblPnlNorth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gblPnlNorth.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlNorth.setLayout(gblPnlNorth);
	}

	private void addNorthButtonToPanel(JPanel panel, AbstractButton button, GridBagConstraints gbc, int gridX,
			int gridY, boolean enabled) {
		btnGroup.add(button);
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		button.setBackground(Color.WHITE);
		button.setEnabled(enabled);
		panel.add(button, gbc);
	}

	private void addColorButtonsToPanel(JPanel panel, JComponent component, GridBagConstraints gbc, int gridX,
			int gridY) {
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		component.setForeground(Color.WHITE);
		panel.add(component, gbc);
	}

	private void setIconsForNorthButtons() {
		Image deleteImg = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		btnDelete.setIcon(new ImageIcon(deleteImg));
		Image modificationImg = new ImageIcon(this.getClass().getResource("/modify.png")).getImage();
		btnModification.setIcon(new ImageIcon(modificationImg));
		Image selectImg = new ImageIcon(this.getClass().getResource("/select.png")).getImage();
		btnSelect.setIcon(new ImageIcon(selectImg));
	}

	private void setSouthLayout() {
		JPanel pnlSouth = createPanelWithLayout(new GridBagLayout());
		setPropertiesForSouthPanel(pnlSouth);

		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 0);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 0;
		pnlSouth.add(scrollPane, gbcScrollPane);

		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlSouth.add(panel, gbcPanel);

		addSouthButtonsToPanel(btnSaveCommands, false);
		addSouthButtonsToPanel(btnLoadCommands, true);
		addSouthButtonsToPanel(btnNext, false);
		addSouthButtonsToPanel(btnSaveDrawing, false);
		addSouthButtonsToPanel(btnLoadDrawing, true);
		setIconsForSouthButtons();
		addTextAreaForLog();
	}

	private void setPropertiesForSouthPanel(JPanel pnlSouth) {
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gblPnlSouth = new GridBagLayout();
		gblPnlSouth.columnWidths = new int[] { 814, 0 };
		gblPnlSouth.rowHeights = new int[] { 127, 0, 0 };
		gblPnlSouth.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gblPnlSouth.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		pnlSouth.setLayout(gblPnlSouth);
	}

	private void addSouthButtonsToPanel(JButton button, boolean enabled) {
		button.setBackground(Color.WHITE);
		button.setEnabled(enabled);
		panel.add(button);
	}

	private void setIconsForSouthButtons() {
		Image saveCommandsImg = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnSaveCommands.setIcon(new ImageIcon(saveCommandsImg));
		Image saveDrawingImg = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		btnSaveDrawing.setIcon(new ImageIcon(saveDrawingImg));
		Image loadCommandsImg = new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		btnLoadCommands.setIcon(new ImageIcon(loadCommandsImg));
		Image loadDrawingImg = new ImageIcon(this.getClass().getResource("/load.png")).getImage();
		btnLoadDrawing.setIcon(new ImageIcon(loadDrawingImg));
		Image next = new ImageIcon(this.getClass().getResource("/next.png")).getImage();
		btnNext.setIcon(new ImageIcon(next));
	}

	private void addTextAreaForLog() {
		GridBagConstraints gbcTextArea = new GridBagConstraints();
		gbcTextArea.insets = new Insets(0, 0, 0, 5);
		gbcTextArea.anchor = GridBagConstraints.NORTHWEST;
		gbcTextArea.gridx = 0;
		gbcTextArea.gridy = 0;
		gbcTextArea.gridy = 0;
		textArea.setEditable(false);
		textArea.setColumns(20);
		scrollPane.setViewportView(textArea);
	}

	private void setWestLayout() {
		JPanel pnlWest = createPanelWithLayout(new GridBagLayout());
		setPropertiesForWestPanel(pnlWest);

		AbstractButton[] shapeButtons = { btnPoint, btnLine, btnRectangle, btnCircle, btnDonut, btnHexagon };
		GridBagConstraints gbc = new GridBagConstraints();
		addShapeButtonsToPanel(pnlWest, shapeButtons, gbc);
		setIconsForWestButtons();
	}

	private void setPropertiesForWestPanel(JPanel pnlWest) {
		pnlWest.setBackground(UIManager.getColor("activeCaption"));
		getContentPane().add(pnlWest, BorderLayout.WEST);
		GridBagLayout gblPnlWest = new GridBagLayout();
		gblPnlWest.columnWidths = new int[] { 33, 55, -1, 0 };
		gblPnlWest.rowHeights = new int[] { 25, 0, 0, 0, 0, 0, 0, 0 };
		gblPnlWest.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gblPnlWest.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlWest.setLayout(gblPnlWest);
	}

	private void addShapeButtonsToPanel(JPanel panel, AbstractButton[] buttons, GridBagConstraints gbc) {
		for (int i = 0; i < buttons.length; i++) {
			AbstractButton button = buttons[i];
			btnGroup.add(button);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.gridx = 1;
			gbc.gridy = i;
			button.setBackground(Color.WHITE);
			button.setFont(new Font(FONT_NAME, Font.PLAIN, 13));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(button, gbc);
		}
	}

	private void setIconsForWestButtons() {
		Image pointImg = new ImageIcon(this.getClass().getResource("/point.png")).getImage();
		btnPoint.setIcon(new ImageIcon(pointImg));
		Image lineImg = new ImageIcon(this.getClass().getResource("/line.png")).getImage();
		btnLine.setIcon(new ImageIcon(lineImg));
		Image rectangleImg = new ImageIcon(this.getClass().getResource("/rectangle.png")).getImage();
		btnRectangle.setIcon(new ImageIcon(rectangleImg));
		Image circleImg = new ImageIcon(this.getClass().getResource("/circle.png")).getImage();
		btnCircle.setIcon(new ImageIcon(circleImg));
		Image donutImg = new ImageIcon(this.getClass().getResource("/donut.png")).getImage();
		btnDonut.setIcon(new ImageIcon(donutImg));
		Image hexagonImg = new ImageIcon(this.getClass().getResource("/hexagon.png")).getImage();
		btnHexagon.setIcon(new ImageIcon(hexagonImg));
	}

	private void setEastLayout() {
		JPanel pnlEast = createPanelWithLayout(new GridBagLayout());
		setPropertiesForEastPanel(pnlEast);

		AbstractButton[] operationButtons = { btnUndo, btnRedo, btnToFront, btnToBack, btnBringToFront,
				btnBringToBack };
		GridBagConstraints gbc = new GridBagConstraints();
		addOperationButtonsToPanel(pnlEast, operationButtons, gbc);
		setIconsForEastButtons();
	}

	private void setPropertiesForEastPanel(JPanel pnlEast) {
		pnlEast.setBackground(UIManager.getColor("activeCaption"));
		getContentPane().add(pnlEast, BorderLayout.EAST);
		GridBagLayout gblPnlEast = new GridBagLayout();
		gblPnlEast.columnWidths = new int[] { 135, 61, 0 };
		gblPnlEast.rowHeights = new int[] { 0, 25, 0, 0, 0, 0, 0 };
		gblPnlEast.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gblPnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlEast.setLayout(gblPnlEast);
	}

	private void addOperationButtonsToPanel(JPanel panel, AbstractButton[] buttons, GridBagConstraints gbc) {
		for (int i = 0; i < buttons.length; i++) {
			AbstractButton button = buttons[i];
			gbc.insets = new Insets(0, 0, i == buttons.length - 1 ? 0 : 5, i % 2 == 0 ? 5 : 0);
			gbc.anchor = i % 2 == 0 ? GridBagConstraints.NORTHEAST : GridBagConstraints.NORTHWEST;
			gbc.gridx = i % 2;
			gbc.gridy = i / 2 * 2 + 1;
			button.setBackground(i < 2 ? Color.LIGHT_GRAY : Color.WHITE);
			button.setEnabled(false);
			panel.add(button, gbc);
		}
	}

	private void setIconsForEastButtons() {
		Image undoImg = new ImageIcon(this.getClass().getResource("/undo.png")).getImage();
		btnUndo.setIcon(new ImageIcon(undoImg));
		Image redoImg = new ImageIcon(this.getClass().getResource("/redo.png")).getImage();
		btnRedo.setIcon(new ImageIcon(redoImg));
		Image bringToFrontImg = new ImageIcon(this.getClass().getResource("/tofront.png")).getImage();
		btnBringToFront.setIcon(new ImageIcon(bringToFrontImg));
		Image bringToBackImg = new ImageIcon(this.getClass().getResource("/toback.png")).getImage();
		btnBringToBack.setIcon(new ImageIcon(bringToBackImg));
		Image toBackImg = new ImageIcon(this.getClass().getResource("/toback.png")).getImage();
		btnToBack.setIcon(new ImageIcon(toBackImg));
		Image toFrontImg = new ImageIcon(this.getClass().getResource("/tofront.png")).getImage();
		btnToFront.setIcon(new ImageIcon(toFrontImg));
	}

	private void addActionListeners() {
		btnUndo.addActionListener(e -> controller.undo());
		btnRedo.addActionListener(e -> controller.redo());
		btnToFront.addActionListener(e -> controller.bringToFrontByOne());
		btnToBack.addActionListener(e -> controller.bringToBackByOne());
		btnBringToFront.addActionListener(e -> controller.fullBringToFront());
		btnBringToBack.addActionListener(e -> controller.fullBringToBack());
		btnSaveCommands.addActionListener(e -> controller.saveLog());
		btnLoadCommands.addActionListener(e -> controller.loadLog());
		btnNext.addActionListener(e -> {
		});
		btnSaveDrawing.addActionListener(e -> controller.saveDrawing());
		btnLoadDrawing.addActionListener(e -> controller.loadDrawing());
		btnModification.addActionListener(e -> controller.editShape());
		btnDelete.addActionListener(e -> controller.deleteShape());
		btnOuterColor.addActionListener(e -> borderColor());
		btnInnerColor.addActionListener(e -> innerColor());
	}

	public void borderColor() {
		outerColorFrame = JColorChooser.showDialog(null, "Choose color", getBtnOuterColor().getBackground());
		if (outerColorFrame == null) {
			getBtnOuterColor().setBackground(Color.BLACK);
			getBtnOuterColor().setForeground(Color.WHITE);
		} else {
			getBtnOuterColor().setBackground(outerColorFrame);
			if (outerColorFrame.equals(Color.BLACK))
				getBtnOuterColor().setForeground(Color.WHITE);
			else
				getBtnOuterColor().setForeground(Color.BLACK);
		}
	}

	public void innerColor() {
		innerColorFrame = JColorChooser.showDialog(null, "Choose inner color", getBtnInnerColor().getBackground());
		if (innerColorFrame == null) {
			getBtnInnerColor().setBackground(Color.BLACK);
			getBtnInnerColor().setForeground(Color.WHITE);
		} else {
			getBtnInnerColor().setBackground(innerColorFrame);
			if (innerColorFrame.equals(Color.BLACK))
				getBtnInnerColor().setForeground(Color.WHITE);
			else
				getBtnInnerColor().setForeground(Color.BLACK);
		}
	}
}