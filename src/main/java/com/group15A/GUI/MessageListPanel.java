package com.group15A.GUI;

import com.group15A.Utils.JWidgetShortcuts;

import javax.swing.*;
import java.awt.*;

public class MessageListPanel {
    private JLabel headerLabel;
    private JPanel messageContentPanel;
    private JLabel noMessagesLabel;
    private JPanel messageListPanel;
    private JScrollPane messageScrollPanel;
    private JPanel messageExtraPanel;
    private GridBagConstraints gbc;
    private String headerText;
    private String noMessagesText;
    private boolean showCount;

    public MessageListPanel(String headerText, String noMessagesText, boolean showCount)
    {
        this.headerText = headerText;
        this.showCount = showCount;
        this.noMessagesText = noMessagesText;
        this.headerLabel.setText(this.headerText);
        this.noMessagesLabel.setText(this.noMessagesText);

        gbc = JWidgetShortcuts.getStackGBC();
    }

    public MessagePanel addMessage(String heading, String subheading, String message, String buttonText)
    {
        MessagePanel messagePanel = new MessagePanel(heading,subheading,message,buttonText);
        messageContentPanel.add(messagePanel.getMainPanel(),gbc);
        updateCount();
        return messagePanel;
    }

    private void updateCount()
    {
        if(showCount) {
            setHeaderText(headerText + " (" + (messageContentPanel.getComponentCount()-1) + ")");
        }
    }

    public JPanel getPanel()
    {
        return messageListPanel;
    }

    public JLabel getHeaderLabel()
    {
        return headerLabel;
    }

    public JLabel getNoMessagesLabel()
    {
        return noMessagesLabel;
    }

    public JPanel getContentPanel()
    {
        return messageContentPanel;
    }

    public void clearMessages()
    {
        JWidgetShortcuts.clearJPanel(getContentPanel());
        messageContentPanel.add(noMessagesLabel);
        updateCount();
        showNoMessagesLabel();

    }

    public void hideNoMessagesLabel()
    {
        getNoMessagesLabel().setVisible(false);
    }
    public void showNoMessagesLabel()
    {
        getNoMessagesLabel().setVisible(true);
    }

    public void setHeaderText(String string)
    {
        getHeaderLabel().setText(string);
    }

    private void createUIComponents() {
        noMessagesLabel = new JLabel(noMessagesText);
    }
}
