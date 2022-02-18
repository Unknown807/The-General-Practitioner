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

    private JLabel registerTitleLabel;
    private JButton registerButton;
    private JTextField firstNameField;
    private JTextArea passwordDescriptionTextArea;
    private JButton logInButton;
    private JLabel personlSectionLabel;
    private JLabel accountSectionLabel;
    private JLabel firstNameLabel;
    private JLabel middleNameLabel;
    private JLabel lastNameLabel;
    private JLabel sexLabel;
    private JLabel dateOfBirth;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel confirmEmailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel firstNameErrorLabel;
    private JLabel middleNameErrorLabel;
    private JLabel lastNameErrorLabel;
    private JLabel sexErrorLabel;
    private JLabel dateOfBirthErrorLabel;
    private JLabel phoneErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel confirmEmailErrorLabel;
    private JLabel passwordErrorLabel;
    private JLabel confirmPasswordErrorLabel;
    private JSeparator sectionSeparator;
    private JSeparator leftSeparator;
    private JSeparator topSeparator;
    private JSeparator bottomSeparator;
    private JSeparator rightSeparator;
    private JPanel personalPanel;
    private JPanel accountPanel;
    private JPanel registerButtonPanel;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JComboBox sexCombo;
    private JTextField dateOfBirthField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField confirmEmailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    /**
     * @param windowTitle     The title of the register panel, usually the same.
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public RegisterPanel(String windowTitle, multiPanelWindow panelController) {
        super(windowTitle, panelController,"registerPanel");
    }

    @Override
    public JPanel getPagePanel()
    {
        return this.registerPanel;
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
