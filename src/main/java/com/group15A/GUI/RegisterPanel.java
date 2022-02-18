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
public class RegisterPanel extends BasePanel {
    private JPanel registerPanel;
    private JButton registerButton;
    private JTextField textField3;
    private JTextArea passwordMustContain8TextArea;
    private JButton alreadyHaveAnAccountButton;

    /**
     * @param windowTitle     The title of the register panel, usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public RegisterPanel(String windowTitle, multiPanelWindow panelController) {
        super(windowTitle, panelController);
    }

    public JPanel getPagePanel()
    {
        System.err.println(this.registerPanel.getClass());
        return this.registerPanel;
    }

    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    public void createActionListeners() {
    }

}
