package com.group15A.GUI;

import com.group15A.BusinessLogic.HomeLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;
import com.group15A.Utils.ReceiveType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * To allow for communication to the business layer and to take care of event handling
 *
 * homePanel is the actual panel that gets passed to the multiPanelWindow cardLayout
 * in order to show it in the UI
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class HomePanel extends BasePanel {
    private JPanel homePanel;
    private JButton logOutButton;
    private JPanel contentScrollPane;
    private JLabel titleLabel;
    private JPanel messagePanel;
    private JPanel navigationPanel;
    private JButton viewBookingsButton;
    private JButton newBookingButton;
    private JPanel messageContentPanel;
    private JLabel messageLabel;
    private JLabel noMessagesLabel;
    private JPanel messageExtraPanel;
    private JScrollPane messageScrollPanel;
    private JButton viewProfileButton;

    private HomeLogic homeLogic;
    private List<Notification> notifList;

    /**
     * Constructor for the HomePanel class
     *
     * Creates action listeners for widgets
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public HomePanel(MultiPanelWindow panelController) {
        super("Welcome", "homePanel", panelController);
        createActionListeners();

        try {
            this.homeLogic = new HomeLogic();
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(
                    homePanel,
                    "Please connect to the database and restart the program.",
                    "ERROR: Database not connected",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }

    private void displayNotifications() {
        //messageContentPanel.setLayout(new GridBagLayout());
        JWidgetShortcuts.clearJPanel(messageContentPanel);
        GridBagConstraints gbc = JWidgetShortcuts.getStackGBC();
        messageLabel.setText("Messages ("+notifList.size()+")");

        if(!notifList.isEmpty()) {
            noMessagesLabel.setVisible(false);
            for (Notification notification : notifList) {
                NotificationDisplay notificationDisplay = new NotificationDisplay(
                        notification.getHeader(),
                        "("+JWidgetShortcuts.shortTimestamp(notification.getTimestamp())+")",
                        notification.getMessage()
                );
                //notificationDisplay.getContentPanel().setBorder(new EmptyBorder(8,8,8,8));
                notificationDisplay.getReadButton().addActionListener(e -> {markAsRead();});
                messageContentPanel.add(notificationDisplay.getMainPanel(), gbc);
            }
        }
    }


    /** TODO: Implement
     *
     * Mark a given notification's isNew attribute to false
     */
    private void markAsRead()
    {

    }

    /**
     * @return homePanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.homePanel;
    }

    /**
     * @param pair the received data from another page
     */
    @Override
    public void receiveData(ReceivePair pair) {
        try {
            Patient patient = homeLogic.getPatient(panelController.getSession().getLoggedInPatientID());
            titleLabel.setText("Welcome, " + patient.getFirstName() + ".");
            this.notifList = this.homeLogic.getNotifications(patient);
            this.displayNotifications();
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(
                    homePanel,
                    "Please connect to the database and restart the program.",
                    "ERROR: Database not connected",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners() {
        logOutButton.addActionListener(e -> {logOutUser();});
        viewBookingsButton.addActionListener(e -> {
            panelController.showPage(
                    PageType.VIEW_BOOKINGS,
                    new ReceivePair(ReceiveType.PATIENT_ID, this.panelController.getSession().getLoggedInPatientID())
            );
        });

        newBookingButton.addActionListener(e -> {
            panelController.showPage(
                    PageType.ADD_BOOKING,
                    new ReceivePair(ReceiveType.DOCTOR, this.panelController.getSession().getLoggedInPatientID())
            );
        });

        viewProfileButton.addActionListener(e -> this.panelController.showPage(PageType.VIEW_PROFILE));
    }

    /**
     * Delete the log-in session file and go to log in page.
     */
    private void logOutUser()
    {
        Session.deleteSession();
        panelController.refreshSession();
        panelController.showPage(PageType.LOGIN);
    }

}
