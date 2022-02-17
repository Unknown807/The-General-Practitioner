package com.group15A.GUI;

import javax.swing.*;

public abstract class basePanel {
    private String windowTitle;
    protected multiPanelWindow panelController;

    public basePanel(String windowTitle, multiPanelWindow panelController) {
        this.windowTitle = windowTitle;
        this.panelController = panelController;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public abstract JPanel getPagePanel();

    public abstract void createActionListeners();
}

