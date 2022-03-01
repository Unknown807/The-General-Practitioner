package com.group15A.GUI;

import javax.swing.*;
import java.util.Map;

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
    protected MultiPanelWindow panelController;

    /**
     * Constructor for BasePanel class
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from subclass panels to call showPage
     */
    public BasePanel(MultiPanelWindow panelController)
    {
        this.panelController = panelController;
    }

    public abstract JPanel getPagePanel();

    public abstract void receiveData(Map<String, Object> data);

    public abstract void createActionListeners();
}
