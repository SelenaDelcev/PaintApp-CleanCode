package gui;

import java.awt.Component;

public interface OptionPane {
    void showMessageDialog(Component parentComponent, Object message, String title, int messageType);
}
