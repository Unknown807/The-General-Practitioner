package com.group15A.GUI;

import com.group15A.BusinessLogic.HomeLogic;
import com.group15A.CustomExceptions.CustomException;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.*;

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
    private JButton myActivityButton;

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
        super("Home", "homePanel", panelController);
        createActionListeners();

        try {
            this.homeLogic = new HomeLogic();
        } catch (CustomException e) {
            JWidgetShortcuts.showDatabaseExceptionPopupAndExit(homePanel);
        }
    }

    /**
     * For each notification in `notifList`,
     * if it's marked as new,
     * then create a NotificationDisplay and add it
     * to the list of notifications on the page
     *
     * Then display the number of new bookings
     */
    private void displayNotifications() {
        //messageContentPanel.setLayout(new GridBagLayout());
        JWidgetShortcuts.clearJPanel(messageContentPanel);
        GridBagConstraints gbc = JWidgetShortcuts.getStackGBC();

        int unseen = 0;
        if(!notifList.isEmpty()) {
            noMessagesLabel.setVisible(false);
            for (int i=notifList.size()-1; i>=0; i--) {
                Notification notification = notifList.get(i);
                if (!notification.isNew()) {
                    continue;
                }

                NotificationDisplay notificationDisplay = new NotificationDisplay(
                        notification.getHeader(),
                        "("+DataModification.shortDate(notification.getTimestamp())+")",
                        notification.getMessage()
                );

                //notificationDisplay.getContentPanel().setBorder(new EmptyBorder(8,8,8,8));
                notificationDisplay.getReadButton().addActionListener(e -> {
                    notificationDisplay.getReadButton().setVisible(false);
                    this.markAsRead(notification);
                });
                messageContentPanel.add(notificationDisplay.getMainPanel(), gbc);

                unseen++;
            }

            messageLabel.setText("New Messages ("+unseen+")");
        }
    }


    /**
     * Mark a given notification's isNew attribute to false
     */
    private void markAsRead(Notification notification) {
        try {
            this.homeLogic.readNotification(notification);
        } catch (CustomException e) {
            JWidgetShortcuts.showDatabaseExceptionPopupAndExit(homePanel);
        }
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
            JWidgetShortcuts.showDatabaseExceptionPopupAndExit(homePanel);
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

        myActivityButton.addActionListener(e -> this.panelController.showPage(PageType.LOGGING));
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
