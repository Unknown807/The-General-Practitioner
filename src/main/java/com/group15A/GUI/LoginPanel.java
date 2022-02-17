package com.group15A.GUI;

import javax.swing.*;

public class LoginPanel extends basePanel {
    private JPanel loginPanel;

    public LoginPanel(String windowTitle, multiPanelWindow panelController) {
        super(windowTitle, panelController);
    }

    public JPanel getPagePanel() {
        return this.loginPanel;
    }

    public void createActionListeners() {}
}
