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
public abstract class BasePanel {
    private String windowTitle;
    protected multiPanelWindow panelController;
    private final String panelFieldName;

    /**
     * Constructor.
     *
     * @param windowTitle The title of the login panel; usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from subclass panels to call showPage
     */
    public BasePanel(String windowTitle, multiPanelWindow panelController, String panelFieldName)
    {
        this.windowTitle = windowTitle;
        this.panelController = panelController;
        this.panelFieldName = panelFieldName;
    }

    /**
     * Returns the name of the field storing a page's JPanel object.
     * @return panelFieldName The name of the field storing a page's JPanel object.
     * @author Filip Fois
     */
    public String getPanelFieldName()
    {
        return this.panelFieldName;
    }

    public String getWindowTitle()
    {
        return this.windowTitle;
    }

    public abstract JPanel getPagePanel();

    public abstract void createActionListeners();
}
