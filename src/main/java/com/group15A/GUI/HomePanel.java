package com.group15A.GUI;

import javax.swing.*;

/**
 * To allow for communication to the business layer and to
 * take care of event handling
 * <p>
 * registerPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
 * in order to show it in the UI
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class HomePanel extends BasePanel {
    private JPanel homePanel;

    private JLabel greetingLabel;
    private JPanel messagesPanel;

    /**
     * @param windowTitle     The title of the register panel, usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public HomePanel(String windowTitle, multiPanelWindow panelController) {
        super(windowTitle, panelController,"homePanel");
    }

    @Override
    public JPanel getPagePanel()
    {
        System.err.println(this.homePanel.getClass());
        return this.homePanel;
    }



    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    @Override
    public void createActionListeners() {
    }

}
