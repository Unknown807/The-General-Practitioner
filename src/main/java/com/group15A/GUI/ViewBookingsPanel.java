package com.group15A.GUI;

import com.group15A.Utils.ReceivePair;

import javax.swing.*;

/**
 *
 * @author Filip Fois
 */
public class ViewBookingsPanel extends BasePanel {
    private JPanel viewBookingPanel;
    private JButton goHomeButton;
    private JPanel viewBookingsPanel;
    private JScrollPane bookingsScrollPane;

    /**
     *
     */
    public ViewBookingsPanel(MultiPanelWindow panelController)
    {
        super("View Bookings", "viewBookingPanel", panelController);
        createActionListeners();
    }

    /**
     * @return viewBookingPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.viewBookingPanel;
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