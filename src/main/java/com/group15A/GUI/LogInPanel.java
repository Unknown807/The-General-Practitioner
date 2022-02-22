package com.group15A.GUI;

import com.group15A.BusinessLogic.LogInLogic;

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

    private LogInLogic logInLogic;

    /**
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public LogInPanel(MultiPanelWindow panelController)
    {
        super("Please Sign In", panelController,"logInPanel");
        // TODO: Implement setMargin on these buttons using LogInPanel.form instead of in this file.
        resetPasswordButton.setMargin(new Insets(0,0,0,0));
        registerButton.setMargin(new Insets(0,0,0,0));
        createActionListeners();

        try {
            logInLogic = new LogInLogic();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "\nDatabase connection could not be made." +
                            "\nPlease connect to the database and restart the program.",
                    "ERROR: Database not connected",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
        logInErrorLabel.setVisible(false);
        try {
            logInLogic.login(emailField.getText(),
                             new String(passwordField.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Error Encountered: Log in unsuccessful.");
            logInErrorLabel.setVisible(true);
        }
    }

}
