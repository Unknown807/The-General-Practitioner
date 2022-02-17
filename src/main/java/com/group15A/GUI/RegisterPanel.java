package com.group15A.GUI;

import javax.swing.*;

public class RegisterPanel extends basePanel {
    private JPanel pagePanel;

    public RegisterPanel(String windowTitle, JFrame windowController) {
        super(windowTitle, windowController);
    }

    public JPanel getPagePanel() {
        return pagePanel;
    }

}