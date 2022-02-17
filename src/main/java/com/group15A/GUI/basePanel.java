package com.group15A.GUI;

import javax.swing.*;

public abstract class basePanel {
    private String windowTitle;
    protected JFrame windowController;

    public basePanel(String windowTitle, JFrame windowController) {
        this.windowTitle = windowTitle;
        this.windowController = windowController;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public abstract JPanel getPagePanel();
}
