package com.group15A.GUI;

import javax.swing.*;

/**
 * The NotificationDisplay class is a JPanel
 * that displays notification details in a stylised presentation
 */
public class NotificationDisplay {
    private JPanel notificationDisplayPanel;
    private JTextPane messageTextPane;
    private JLabel dateLabel;
    private JLabel headerLabel;
    private JPanel headingContentPanel;
    private JPanel contentPanel;
    private JButton readButton;

    /**
     * The constructor for the NotificationDisplay class
     *
     * Sets the value of the header, date and message content components
     * @param header The header of the notification
     * @param date The date the notification was created
     * @param message The content of the message
     */
    public NotificationDisplay(String header, String date, String message)
    {
        headerLabel.setText(header);
        dateLabel.setText(date);
        messageTextPane.setText(message);
    }

    public JPanel getMainPanel()
    {
        return notificationDisplayPanel;
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public JButton getReadButton()
    {
        return readButton;
    }

    public JPanel getHeading()
    {
        return headingContentPanel;
    }
}
