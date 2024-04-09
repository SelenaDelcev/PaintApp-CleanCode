package gui;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
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
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DlgDonut extends JDialog implements CommonDialog{
	private static final long serialVersionUID = 1L;
	private static final String FONT_NAME = "Tahoma";
	private final JPanel contentPanel = new JPanel();
	private final JButton btnOuterColor = new JButton("Outer Color");
	private final JButton btnInnerColor = new JButton("Inner Color");
	private final JButton okButton = new JButton("OK");
	private final JButton cancelButton = new JButton("Cancel");
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private Color outerColor = Color.BLACK;
	private Color innerColor = Color.BLACK;
	private boolean isOK;
	private boolean innerColorChosen = false;
	private boolean outerColorChosen = false;
	private transient OptionPane optionPane = new DefaultOptionPane();

	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDonut() {
		txtX = new JTextField();
		txtY = new JTextField();
		txtRadius = new JTextField();
		txtInnerRadius = new JTextField();
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Donut dialog");
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		createCoordinateInputs();
		createColorButtons();
		createButtonPane();
	}

	private void createCoordinateInputs() {
		addCoordinateInput("X coordinate:", txtX);
		addCoordinateInput("Y coordinate:", txtY);
		addCoordinateInput("Radius:", txtRadius);
		addCoordinateInput("Inner Radius:", txtInnerRadius);
		addKeyListeners();
	}

	private void createColorButtons() {
		addColorButton(btnOuterColor, "Outer Color", outerColor, () -> outerColorChosen = true);
		addColorButton(btnInnerColor, "Inner Color", innerColor, () -> innerColorChosen = true);
	}

	private void createButtonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		createOKButton(buttonPane);
		createCancelButton(buttonPane);
	}

	private void addCoordinateInput(String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		contentPanel.add(label);
		contentPanel.add(textField);
	}

	private void addColorButton(JButton button, String buttonText, Color initialColor, Runnable onClickAction) {
		button.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		button.setText(buttonText);
		contentPanel.add(button);
		button.addActionListener(e -> {
			Color selectedColor = JColorChooser.showDialog(null, "Choose color", initialColor);
			if (selectedColor != null) {
				onClickAction.run();
				setColorForButton(button, selectedColor);
			}
		});
	}

	public void setColorForButton(JButton button, Color color) {
		if (button == btnOuterColor) {
			outerColor = color;
		} else if (button == btnInnerColor) {
			innerColor = color;
		}

		button.setBackground(color);
		if (color.equals(Color.BLACK)) {
			button.setForeground(Color.WHITE);
		}
	}

	private void addKeyListeners() {
		txtX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleInputValidation(e);
			}
		});

		txtY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleInputValidation(e);
			}
		});

		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleInputValidation(e);
			}
		});

		txtInnerRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				handleInputValidation(e);
			}
		});
	}

	public void handleInputValidation(KeyEvent e) {
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

	private void createOKButton(JPanel buttonPane) {
		okButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		buttonPane.add(okButton);
		okButton.addActionListener(e -> handleOkButtonClick());
	}

	private void handleOkButtonClick() {
		if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty() || txtInnerRadius.getText().isEmpty()) {
			isOK = false;
			setVisible(true);
			optionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
		} else if (Integer.parseInt(txtRadius.getText()) <= 0 || Integer.parseInt(txtInnerRadius.getText()) <= 0) {
			optionPane.showMessageDialog(null, "Radius and inner radius must be greater than 0!", "Error", JOptionPane.WARNING_MESSAGE);
		} else if (Integer.parseInt(txtInnerRadius.getText()) > Integer.parseInt(txtRadius.getText())) {
			optionPane.showMessageDialog(null, "Inner radius must be smaller than radius!", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			isOK = true;
			dispose();
		}
	}

	private void createCancelButton(JPanel buttonPane) {
		cancelButton.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
		cancelButton.addActionListener(e -> dispose());
		buttonPane.add(cancelButton);
	}
}
