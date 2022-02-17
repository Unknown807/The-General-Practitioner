package com.group15A.GUI;

import javax.swing.*;

public class HomePanel extends basePanel {
    private JPanel pagePanel;

    public HomePanel(String windowTitle, JFrame windowController) {
        super(windowTitle, windowController);
    }

    public JPanel getPagePanel() {
        return pagePanel;
    }

}