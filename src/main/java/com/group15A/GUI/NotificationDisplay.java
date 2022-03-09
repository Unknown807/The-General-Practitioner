package com.group15A.GUI;

import javax.swing.*;

public class NotificationDisplay {
    private JPanel notificationDisplayPanel;
    private JTextPane messageTextPane;
    private JLabel dateLabel;
    private JLabel headerLabel;
    private JPanel headingContentPanel;
    private JPanel contentPanel;
    private JButton readButton;

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
