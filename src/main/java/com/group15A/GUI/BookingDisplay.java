package com.group15A.GUI;

import javax.swing.*;

/**
 * The BookingDisplay class is a JPanel
 * that displays booking details in a stylised presentation
 */
public class BookingDisplay extends MessagePanel{
    private JPanel bookingDisplayPanel;
    private JPanel contentPanel;
    private JPanel headingContentPanel;
    private JLabel doctorLabel;
    private JButton rescheduleButton;
    private JLabel bookingInfoLabel;

    public BookingDisplay(String time, String doctor)
    {
        super(
                "",
                "With Dr. "+doctor,
                "Booking on "+time,
                "Reschedule"
        );


    }


}
