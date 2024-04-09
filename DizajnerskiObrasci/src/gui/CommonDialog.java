package gui;

import javax.swing.JTextField;

public interface CommonDialog {
    void setModal(boolean modal);
    JTextField getTxtX();
    JTextField getTxtY();
    void setTitle(String title);
    void setVisible(boolean visible);
}
