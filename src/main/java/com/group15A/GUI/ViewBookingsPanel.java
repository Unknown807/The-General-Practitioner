package com.group15A.GUI;

import com.google.protobuf.Message;
import com.group15A.BusinessLogic.ViewBookingLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataModel.Booking;
import com.group15A.DataModel.Doctor;
import com.group15A.Utils.*;

import javax.swing.*;
import java.awt.*;
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

    private ViewBookingLogic viewBookingLogic;
    private List<Booking> bookingsList;
    private List<JPanel> bookingLabelsList;

    private MessageListPanel messageListPanel;

    /**
     * Constructor for ViewBookingsPanel
     */
    public ViewBookingsPanel(MultiPanelWindow panelController)
    {
        super("My bookings", "viewBookingPanel", panelController);
       // contentPanel.setBorder(new EmptyBorder(10,10,10,10));

        messageListPanel = new MessageListPanel(
                "My bookings",
                "No bookings.",
                true
        );

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
                bookingsList = this.viewBookingLogic.getBookings(patientID);
                messageListPanel.hideNoMessagesLabel();
                this.displayBookings();
            } catch (CustomException e) {
                JWidgetShortcuts.showDatabaseExceptionPopupAndExit(viewBookingsPanel);
            }
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

        for (Booking b : bookingsList) {
            Doctor doctor = this.viewBookingLogic.getDoctor(b.getDoctorID());

            MessagePanel bookingMessage = messageListPanel.addMessage(
                    "",
                    "With Dr. "+doctor.getFullName(),
                    "Booking at "+DataModification.getTime(b.getBookingTime())+" on "+DataModification.fullDate(b.getBookingTime()),
                    "Reschedule");


            bookingLabelsList.add(bookingMessage.getMainPanel());

            // Copied from HomePanel.java
            bookingMessage.getButton().addActionListener(e -> {
                this.rescheduleBooking(b);
            });

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
    }

}