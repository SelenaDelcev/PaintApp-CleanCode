package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DlgLine extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final String FONT_NAME = "Tahoma";
	private final JPanel contentPanel = new JPanel();
	private final JPanel contentPanel2 = new JPanel();
	private final JTextField txtXStartPoint = new JTextField(10);
	private final JTextField txtYStartPoint = new JTextField(10);
	private final JTextField txtXEndPoint = new JTextField(10);
	private final JTextField txtYEndPoint = new JTextField(10);
	private final JButton okButton = new JButton("OK");
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton colorButton = new JButton("Color");
	private boolean isOK;
	private Color color = Color.BLACK;
	private Color picked = Color.BLACK;
	private boolean colorChosen;
	private transient OptionPane optionPane = new DefaultOptionPane();

	public DlgLine() {
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Line dialog");
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.setPreferredSize(new Dimension(200, 175));
		createCoordinateInputs();
		createColorButton();
		createButtonPane();
	}

	private void createCoordinateInputs() {
		addCoordinateLabelAndField("X start coordinate:", txtXStartPoint);
		addCoordinateLabelAndField("Y start coordinate:", txtYStartPoint);
		addCoordinateLabelAndField("X end coordinate:", txtXEndPoint);
		addCoordinateLabelAndField("Y end coordinate:", txtYEndPoint);
		addKeyListeners();
	}

	private void addCoordinateLabelAndField(String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		contentPanel.add(label);
		contentPanel.add(textField);
	}

	private void createColorButton() {
		contentPanel2.setBorder(new EmptyBorder(0, 5, 5, 5));
		getContentPane().add(contentPanel2, BorderLayout.CENTER);
		contentPanel2.setLayout(new BorderLayout(0, 0));
		contentPanel2.setPreferredSize(new Dimension(200, 50));
		colorButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		colorButton.addActionListener(e -> {
			color = JColorChooser.showDialog(null, "Choose color", picked);
			colorChosen = true;
			colorButton.setBackground(color);
			if (color.equals(Color.BLACK)) {
				colorButton.setForeground(Color.WHITE);
			}
		});
		contentPanel2.add(colorButton);
	}

	private void createButtonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		createOKButton(buttonPane);
		createCancelButton(buttonPane);
	}

	private void createOKButton(JPanel buttonPane) {
		okButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		okButton.addActionListener(e -> handleOKButtonClick());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	private void createCancelButton(JPanel buttonPane) {
		cancelButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		cancelButton.addActionListener(e -> dispose());
		buttonPane.add(cancelButton);
	}

	private void addKeyListeners() {
		txtXStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleCoordinateInput(e);
			}
		});

		txtYStartPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleCoordinateInput(e);
			}
		});
		
		txtXEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleCoordinateInput(e);
			}
		});

		txtYEndPoint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleCoordinateInput(e);
			}
		});
	}

	public void handleCoordinateInput(KeyEvent e) {
		char coordinate = e.getKeyChar();
		if (!(isNumeric(coordinate) || isBackspaceOrDelete(coordinate))) {
			e.consume();
			getToolkit().beep();
		}

	}

	private boolean isNumeric(char character) {
		return (character >= '0' && character <= '9');
	}

	private boolean isBackspaceOrDelete(char character) {
		return (character == KeyEvent.VK_BACK_SPACE || character == KeyEvent.VK_DELETE);
	}

	private void handleOKButtonClick() {
		if (areFieldsEmpty()) {
			isOK = false;
			setVisible(true);
			optionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
		} else {
			isOK = true;
			dispose();
		}
	}

	private boolean areFieldsEmpty() {
		return txtXStartPoint.getText().isEmpty() || txtYStartPoint.getText().isEmpty()
				|| txtXEndPoint.getText().isEmpty() || txtYEndPoint.getText().isEmpty();
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getColorButton() {
		return colorButton;
	}

	public void setOptionPane(OptionPane optionPane) {
		this.optionPane = optionPane;
	}
}
