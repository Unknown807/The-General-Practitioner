package com.group15A.GUI;

import javax.swing.*;

/**
 * All panels in the card layout inherit from this, allows using JPanels
 * in card layout that are written in different files
 *
 * windowTitle is the title of the login panel, usually the same.
 *
 * panelController is the instance of multiPanelWindow in order for
 * events from this panel to call showPage
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public abstract class basePanel {
    private String windowTitle;
    protected multiPanelWindow panelController;

    /**
     * @param windowTitle The title of the login panel, usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from subclass panels to call showPage
     */
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

