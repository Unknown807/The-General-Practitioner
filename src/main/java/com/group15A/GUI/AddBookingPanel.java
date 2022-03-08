package com.group15A.GUI;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;

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
    private Doctor doctor;
    DataAccess dataAccess;

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
            dataAccess = new DataAccess();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        if(!panelController.sessionIsEmpty()) {
            try {
                doctor = getPatientDoctor();
                promptLabel.setText("Book your appointment with Dr " + doctor.getFullName());
            } catch (DatabaseException e) {
                e.printStackTrace();
            } catch (DoctorNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    private Doctor getPatientDoctor() throws DatabaseException, DoctorNotFoundException
    {
        return dataAccess.getDoctor(panelController.getSession().getLoggedInPatient());
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

    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        goHomeButton.addActionListener(e -> {panelController.showPage(PageType.HOME);});
        createBookingButton.addActionListener(e -> {makeBooking();});
    }

    /**
     * TODO: (Fully) implement makeBooking()
     * Pass the entered data to BusinessLogic.BookingLogic.makeBooking()
     *
     */
    private void makeBooking()
    {
        // DUMMY CODE
        bookingErrorLabel.setVisible(false);
        boolean bookingSuccess = false;
        // set bookingSuccess to true
        bookingErrorLabel.setVisible(!bookingSuccess);
    }

}