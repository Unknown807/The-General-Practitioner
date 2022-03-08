package com.group15A.GUI;

import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;
import com.group15A.Utils.ReceiveType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

    /**
     *
     */
    public ViewBookingsPanel(MultiPanelWindow panelController)
    {
        super("View Bookings", "viewBookingPanel", panelController);
       // contentPanel.setBorder(new EmptyBorder(10,10,10,10));
        createActionListeners();
    }

    /**
     * @param pair the received data from another page
     */
    @Override
    public void receiveData(ReceivePair pair)
    {

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
        newBookingButton.addActionListener(e -> {
            panelController.showPage(
                    PageType.ADD_BOOKING,
                    new ReceivePair(ReceiveType.DOCTOR, this.panelController.getSession().getLoggedInPatientID())
            );
        });

    }

}