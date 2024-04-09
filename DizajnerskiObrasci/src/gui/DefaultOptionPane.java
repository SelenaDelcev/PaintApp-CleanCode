package gui;

import java.awt.Component;
import javax.swing.JOptionPane;

public class DefaultOptionPane implements OptionPane {
	
    public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
    
}
