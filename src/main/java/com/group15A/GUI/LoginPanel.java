package com.group15A.GUI;

import javax.swing.*;

public class LoginPanel extends basePanel {
    private JPanel pagePanel;

    public LoginPanel(String windowTitle, JFrame windowController) {
        super(windowTitle, windowController);
    }

    public JPanel getPagePanel() {
        return pagePanel;
    }

}
