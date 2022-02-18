package com.group15A.GUI;

import javax.swing.*;

/**
 * To allow for communication to the business layer and to
 * take care of event handling
 * <p>
 * loginPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
 * in order to show it in the UI
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class LoginPanel extends BasePanel {
    private JPanel logInPanel;
    private JLabel titleLabel;

    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton registerButton;
    private JButton logInButton;
    private JLabel invalidEmailAddressAndLabel;

    /**
     * @param windowTitle     The title of the log in panel, usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public LoginPanel(String windowTitle, multiPanelWindow panelController) {
        super(windowTitle, panelController);
    }

    public JPanel getPagePanel()
    {
        System.err.println(this.logInPanel.getClass());
        return this.logInPanel;
    }

    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    public void createActionListeners() {
    }

}
