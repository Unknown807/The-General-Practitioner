package com.group15A.GUI;

import com.group15A.BusinessLogic.DoctorLogic;
import com.group15A.BusinessLogic.RegisterLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataModel.Doctor;
import com.group15A.Utils.ErrorCode;
import com.group15A.Utils.Page;

import javax.swing.*;
import java.awt.*;

import java.util.HashMap;
import java.util.List;

/**
 * To allow for communication to the business layer and to take care of event handling

 * registerPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
 * in order to show it in the UI
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class RegisterPanel extends BasePanel {
    private JPanel registerPanel;

    private JLabel registerTitleLabel;
    private JButton continueButton;
    private JTextField firstNameField;
    private JTextArea passwordTextArea;
    private JButton logInButton;
    private JLabel personalSectionLabel;
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
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField confirmEmailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JPanel contentPanel;
    private JScrollPane contentScrollPane;
    private JPanel formPanel;
    private JComboBox dayCombo;
    private JComboBox monthCombo;
    private JComboBox yearCombo;
    private JPanel datePanel;
    private JLabel doctorLabel;
    private JComboBox doctorCombo;

    private RegisterLogic registerLogic;
    private DoctorLogic doctorLogic;
    private List<Doctor> doctorsList;

    private HashMap<ErrorCode,JLabel> errorLabelCodes;

    /**
     * Constructor for RegisterPanel class
     *
     * Adds items to date combo boxes,
     * adds doctors to combo box,
     * displays error pop up if database isn't connected then closes the program
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public RegisterPanel(MultiPanelWindow panelController) {
        super(panelController);

        // TODO: Implement setMargin on these buttons using LogInPanel.form instead of in this file.
        logInButton.setMargin(new Insets(0,0,0,0));

        addNumbersToCombo(dayCombo,1,31,"Day");
        addNumbersToCombo(monthCombo,1,12,"Month");
        addNumbersToCombo(yearCombo,2022,1900,"Year");

        createErrorMap();
        createActionListeners();

        try {
            registerLogic = new RegisterLogic();
            doctorLogic = new DoctorLogic();

            doctorsList = doctorLogic.getDoctors();
            for (Doctor d : doctorsList) {
                doctorCombo.addItem(d.getFirstName()+" "+d.getLastName());
            }
        }
        catch (DatabaseException e) {
            JOptionPane.showMessageDialog(
                      registerPanel,
                        "Please connect to the database and restart the program.",
                   "ERROR: Database not connected",
                        JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }

    /**
     * Adds error codes : error label key-value pairs to hash map
     *
     * Error labels can be accessed by giving their respective error code
     */
    private void createErrorMap() {
        errorLabelCodes = new HashMap<>(){{
            put(ErrorCode.WRONG_FIRST_NAME, firstNameErrorLabel);
            put(ErrorCode.WRONG_MIDDLE_NAME, middleNameErrorLabel);
            put(ErrorCode.WRONG_LAST_NAME, lastNameErrorLabel);
            put(ErrorCode.WRONG_GENDER, sexErrorLabel);
            put(ErrorCode.WRONG_DATE, dateOfBirthErrorLabel);
            put(ErrorCode.WRONG_PHONE_NO, phoneErrorLabel);
            put(ErrorCode.WRONG_EMAIL, emailErrorLabel);
            put(ErrorCode.EMAIL_IN_USE, emailErrorLabel);
            put(ErrorCode.WRONG_CONFIRMED_EMAIL, confirmEmailErrorLabel);
            put(ErrorCode.WRONG_PASSWORD, passwordErrorLabel);
            put(ErrorCode.WRONG_CONFIRMED_PASSWORD, confirmPasswordErrorLabel);
        }};
    }

    /**
     * @return return registerPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.registerPanel;
    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        logInButton.addActionListener( e -> panelController.showPage(Page.LOGIN));
        continueButton.addActionListener(e -> this.registerNewPatient());
    }

    /**
     * Try to register patient by passing given inputs to
     * registerLogic.register method
     *
     * If registration is successful, go to the home panel,
     * otherwise, show relevant error labels for invalid inputs
     */
    private void registerNewPatient() {
        try {
            registerLogic.register(
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                yearCombo.getSelectedItem().toString()+"-"+
                    monthCombo.getSelectedItem().toString()+"-"+
                    dayCombo.getSelectedItem().toString(),
                sexCombo.getSelectedItem().toString(),
                phoneField.getText(),
                emailField.getText(),
                confirmEmailField.getText(),
                new String(passwordField.getPassword()),
                new String(confirmPasswordField.getPassword()),
                doctorsList.get(doctorCombo.getSelectedIndex())
            );

            panelController.showPage(Page.HOME);
        } catch (CustomException e) {
            setErrorLabels(e);
        }

    }

    /**
     * Adds a range of numbers as items in a given combobox,
     * in order of first to last.
     *
     * @param comboBox The combobox which will have values added to it
     * @param first The first value to added (after unchosenValue)
     * @param last The last value to be added
     * @param unchosenValue The first value to be shown
     *                      (unchosenValue usually indicates a valid item has not been chosen)
     */
    public void addNumbersToCombo(JComboBox comboBox, int first, int last, String unchosenValue)
    {
        comboBox.addItem(unchosenValue);
        if(first < last) {
            for(int i = first; i <= last; i++){
                comboBox.addItem(i);
            }
        }
        else {
            for(int i = first; i >= last; i--){
                comboBox.addItem(i);
            }
        }
    }

    /**
     * Sets the visibility of each error label
     *
     * Will do so depending on if its respective error code is
     * in the list that is returned by the CustomException
     *
     * @param e The customException, contains a list of error codes.
     */
    public void setErrorLabels(CustomException e)
    {
        List<ErrorCode> errorCodes = e.getErrorList();

        Boolean visibleValue;
        for (ErrorCode errorCode : errorLabelCodes.keySet()) {
            visibleValue = errorCodes.contains(errorCode);
            errorLabelCodes.get(errorCode).setVisible(visibleValue);
        }
    }

}
