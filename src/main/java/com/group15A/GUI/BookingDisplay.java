package com.group15A.GUI;

import javax.swing.*;

/**
 * The BookingDisplay class is a JPanel
 * that displays booking details in a stylised presentation
 */
public class BookingDisplay {
    private JPanel bookingDisplayPanel;
    private JPanel contentPanel;
    private JPanel headingContentPanel;
    private JLabel doctorLabel;
    private JButton rescheduleButton;
    private JLabel bookingInfoLabel;

    public BookingDisplay(String time, String doctor)
    {
        this.doctorLabel.setText("With Dr. "+doctor);
        this.bookingInfoLabel.setText("Booking on "+time);
    }

    public JPanel getMainPanel()
    {
        return bookingDisplayPanel;
    }

    public JButton getRescheduleButton()
    {
        return rescheduleButton;
    }

}
