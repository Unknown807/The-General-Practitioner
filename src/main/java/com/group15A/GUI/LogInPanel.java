package com.group15A.GUI;

import com.group15A.BusinessLogic.LoginLogic;

import javax.swing.*;
import java.awt.*;

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
public class LogInPanel extends BasePanel {
    private JPanel logInPanel;

    private JLabel logInTitleLabel;

    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton registerButton;
    private JButton logInButton;
    private JLabel logInErrorLabel;
    private JPanel textFieldsPanel;
    private JButton resetPasswordButton;
    private JPanel contentPanel;
    private JScrollPane contentScrollPane;

    private LoginLogic logInLogic;

    /**
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public LogInPanel(MultiPanelWindow panelController)
    {
        super("Please Sign In", panelController,"logInPanel");
        // TODO: Implement setMargin on these buttons using LogInPanel.form instead of in this file.
        logInLogic = new LoginLogic(this);
        resetPasswordButton.setMargin(new Insets(0,0,0,0));
        registerButton.setMargin(new Insets(0,0,0,0));
        createActionListeners();
    }

    @Override
    public JPanel getPagePanel()
    {
        return this.logInPanel;
    }

    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    @Override
    public void createActionListeners() {
        registerButton.addActionListener( e -> panelController.showPage(new RegisterPanel(panelController)));
        logInButton.addActionListener(e -> this.logInPatient());
    }

    private void logInPatient() {
        try {
            logInLogic.login(
                    emailField.getText(),
                    passwordField.getPassword().toString()
            );
        } catch (Exception e) {
            
        }
    }

}
