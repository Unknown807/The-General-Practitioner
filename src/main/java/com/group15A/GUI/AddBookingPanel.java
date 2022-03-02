package com.group15A.GUI;

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
    private JScrollPane dateSelectionPanel;
    private JTextArea descriptionTextArea;

    /**
     *
     */
    public AddBookingPanel(MultiPanelWindow panelController)
    {
        super("New Booking", "addBookingPanel", panelController);
        createActionListeners();
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

    }

}