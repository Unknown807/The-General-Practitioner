package com.group15A.GUI;

import com.group15A.BusinessLogic.AddBookingLogic;
import com.group15A.BusinessLogic.RegisterLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;
import com.group15A.Utils.ReceiveType;

import javax.swing.*;

/**
 *
 * @author Filip Fois
 */
public class AddBookingPanel extends BasePanel {
    private JPanel addBookingPanel;
    private JButton goHomeButton;
    private JButton createBookingButton;
    private JPanel contentPanel;
    private JPanel bookingSelectionPanel;
    private JComboBox dayCombo;
    private JPanel dateSelectionPanel;
    private JComboBox monthCombo;
    private JComboBox yearCombo;
    private JComboBox hourCombo;
    private JComboBox minuteCombo;
    private JPanel timeSelectionPanel;
    private JLabel bookingErrorLabel;
    private JLabel promptLabel;

    private AddBookingLogic addBookingLogic;

    /**
     *
     */
    public AddBookingPanel(MultiPanelWindow panelController)
    {
        super("New Booking", "addBookingPanel", panelController);
        JWidgetShortcuts.addItemsToCombo(dayCombo,1,31,1,"Day");
        JWidgetShortcuts.addItemsToCombo(monthCombo,1,12,1,"Month");
        int year = 2022;
        JWidgetShortcuts.addItemsToCombo(yearCombo,year,year+10,1,null);

        JWidgetShortcuts.addItemsToCombo(hourCombo,9,17,1,"Hour");
        JWidgetShortcuts.addItemsToCombo(minuteCombo,0,55,5,"Minute");
        createActionListeners();

        try {
            addBookingLogic = new AddBookingLogic();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(
                    addBookingPanel,
                    "Please connect to the database and restart the program.",
                    "ERROR: Database not connected",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }

    /**
     * @return addBookingPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.addBookingPanel;
    }

    /**
     * @param pair the received data from another page
     */
    @Override
    public void receiveData(ReceivePair pair)
    {
        if (pair.getFirst().equals(ReceiveType.DOCTOR)) {
            this.updateDoctorLabels((Patient) pair.getSecond());
        }
    }

    private void updateDoctorLabels(Patient patient) {
        try {
            Doctor patientDoctor = this.addBookingLogic.getPatientDoctor(patient);
            this.promptLabel.setText("Book your appointment with Dr "+patientDoctor.getFullName());
            this.bookingErrorLabel.setVisible(false);

        } catch(DatabaseException e) {
            JOptionPane.showMessageDialog(
                    addBookingPanel,
                    "Please connect to the database and restart the program.",
                    "ERROR: Database not connected",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);

        } catch (DoctorNotFoundException e) {
            this.bookingErrorLabel.setText("The requested doctor is unavailable");
            this.bookingErrorLabel.setVisible(true);
        }
    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        goHomeButton.addActionListener(e -> {panelController.showPage(PageType.HOME);});
        createBookingButton.addActionListener(e -> this.createNewBooking());
    }

    private void createNewBooking() {
        try {
            this.addBookingLogic.createNewBooking(
                    yearCombo.getSelectedItem().toString(),
                    monthCombo.getSelectedItem().toString(),
                    dayCombo.getSelectedItem().toString(),
                    hourCombo.getSelectedItem().toString(),
                    minuteCombo.getSelectedItem().toString(),
                    this.panelController.getSession().getLoggedInPatient()
            );
        } catch (CustomException e) {

        }
    }

}