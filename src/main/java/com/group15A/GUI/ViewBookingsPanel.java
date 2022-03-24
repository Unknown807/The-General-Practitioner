package com.group15A.GUI;

import com.group15A.BusinessLogic.ViewBookingLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.Utils.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip Fois
 */
public class ViewBookingsPanel extends BasePanel {
    private JButton goHomeButton;
    private JPanel viewBookingsPanel;
    private JPanel contentPanel;
    private JPanel bookingsPanel;
    private JLabel messageLabel;
    private JButton newBookingButton;
    private JPanel bookingsDisplayPanel;
    private JLabel titleLabel;
    private JComboBox monthComboBox;
    private JButton searchButton;
    private JComboBox yearComboBox;
    private JLabel dateErrorLabel;

    private ViewBookingLogic viewBookingLogic;
    private List<Booking> bookingsList;
    private List<JPanel> bookingLabelsList;

    private MessageListPanel messageListPanel;

    private Boolean pastBookingFlag = false;

    /**
     * Constructor for ViewBookingsPanel
     */
    public ViewBookingsPanel(MultiPanelWindow panelController)
    {
        super("My bookings", "viewBookingPanel", panelController);
        // contentPanel.setBorder(new EmptyBorder(10,10,10,10));

        dateErrorLabel.setVisible(false);

        messageListPanel = new MessageListPanel(
                "My bookings",
                "No bookings.",
                true
        );
        JWidgetShortcuts.addItemsToCombo(monthComboBox,1,12,1,"Month (All)");
        int year = 2022;
        JWidgetShortcuts.addItemsToCombo(yearComboBox,2022,year+10,1,"Year (All)");
        bookingsPanel.add(messageListPanel.getPanel());

        createActionListeners();

        try {
            bookingLabelsList = new ArrayList<>();
            viewBookingLogic = new ViewBookingLogic();
        } catch (DatabaseException e) {
            JWidgetShortcuts.showDatabaseExceptionPopupAndExit(viewBookingsPanel);
        }
    }

    /**
     * @param pair the received data from another page
     */
    @Override
    public void receiveData(ReceivePair pair)
    {
        if (pair.getFirst().equals(ReceiveType.PATIENT_ID)) {
            Integer patientID = (Integer) pair.getSecond();
            try {
                bookingsList = this.viewBookingLogic.getBookings(patientID, pastBookingFlag);
                messageListPanel.hideNoMessagesLabel();
                this.displayBookings();
            } catch (CustomException e) {
                JWidgetShortcuts.showDatabaseExceptionPopupAndExit(viewBookingsPanel);
            }
        } else if (pair.getFirst().equals(ReceiveType.NEW_BOOKINGS)) {
            pastBookingFlag = false;

        } else if (pair.getFirst().equals(ReceiveType.PAST_BOOKINGS)) {
            pastBookingFlag = true;

        }
    }

    /**
     * TODO: Update documentation to match use of MessageListPanel
     * For each booking in `bookingList`,
     * create a label containing information about the booking,
     * then add it the `bookingLabelList` to be displayed
     *
     * The colour of each booking alternates
     *
     * @throws CustomException when patient's doctor cannot be accessed
     */
    public void displayBookings() throws CustomException {
        messageListPanel.clearMessages();
        messageListPanel.showNoMessagesLabel();

        String message;
        Randomiser randomiser = new Randomiser();

        if(!bookingsList.isEmpty()){
            messageListPanel.hideNoMessagesLabel();
            for (Booking b : bookingsList) {
                Doctor doctor = this.viewBookingLogic.getDoctor(b.getDoctorID());

                if (pastBookingFlag) {
                    if (b.getPrescription() == null) {
                        b.setPrescription(randomiser.getRandPrescription());
                        b.setDetails(randomiser.getRandDetails());
                        // TODO: once values set, update them in db
                    }

                    message = "Booking at "+
                            DataModification.getTime(b.getBookingTime())+
                            " on "+DataModification.fullDate(b.getBookingTime())+
                            ": Doctor assigned prescription: "+b.getPrescription()+
                            ", details include: "+b.getDetails();
                } else {
                    message = "Booking at "+
                            DataModification.getTime(b.getBookingTime())+
                            " on "+DataModification.fullDate(b.getBookingTime());
                }

                MessagePanel bookingMessage = messageListPanel.addMessage(
                        "",
                        "With Dr. "+doctor.getFullName(),
                        message,
                        "Reschedule");

                bookingLabelsList.add(bookingMessage.getMainPanel());

                // Copied from HomePanel.java
                if (!pastBookingFlag) {
                    bookingMessage.getButton().addActionListener(e -> {
                        this.rescheduleBooking(b);
                    });
                } else {
                    bookingMessage.getButton().setVisible(false);
                }
            }
        }
    }

    private void rescheduleBooking(Booking booking)
    {
        this.panelController.showPage(
                PageType.ADD_BOOKING,
                new ReceivePair(ReceiveType.PATIENT_ID, this.panelController.getSession().getLoggedInPatientID()),
                new ReceivePair(ReceiveType.RETURN_PAGE, PageType.VIEW_BOOKINGS),
                new ReceivePair(ReceiveType.BOOKING, booking)
        );
    }

    /**
     * TODO: Implement
     *
     * Display only bookings within the given month-year
     */
    private void filterBookings()
    {
        try {
            bookingsList = this.viewBookingLogic.filterBookings(
                    monthComboBox.getSelectedItem().toString(),
                    yearComboBox.getSelectedItem().toString(),
                    panelController.getSession().getLoggedInPatientID(),
                    pastBookingFlag
            );
            dateErrorLabel.setVisible(false);
            this.displayBookings();
        } catch (DatabaseException e) {
            JWidgetShortcuts.showDatabaseExceptionPopupAndExit(viewBookingsPanel);
        } catch (CustomException e) {
            e.printStackTrace();
            dateErrorLabel.setVisible(true);
        }
    }

    /**
     * @return viewBookingsPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.viewBookingsPanel;
    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        goHomeButton.addActionListener(e -> {panelController.showPage(PageType.HOME);});
        searchButton.addActionListener(e -> {filterBookings();});
    }


}