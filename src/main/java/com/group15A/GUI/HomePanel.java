package com.group15A.GUI;

import com.group15A.BusinessLogic.NotificationLogic;
import com.group15A.BusinessLogic.PatientLogic;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataModel.Notification;
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
    private int patientID;
    private String patientFirstName;

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
        //TODO: Read session file to get patient name.
        createActionListeners();
        if(!panelController.sessionIsEmpty()) {
            try {
                patientFirstName = new PatientLogic().getPatientFirstName(panelController.getSession());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            titleLabel.setText("Welcome, " + patientFirstName + ".");
            displayNotifications(panelController.getSession());
        }
    }

    private void displayNotifications(Session session)
    {
        //messageContentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = JWidgetShortcuts.getStackGBC();
        try {

            List<Notification> notifications = getNotifications(session);
            messageLabel.setText("Messages ("+notifications.size()+")");

            if(!notifications.isEmpty()) {
                noMessagesLabel.setVisible(false);
                for (Notification notification : notifications) {
                    NotificationDisplay notificationDisplay = new NotificationDisplay(
                            notification.getHeader(),
                            "("+ DataModification.shortDate(notification.getTimestamp())+")",
                            notification.getMessage()
                    );
                    //notificationDisplay.getContentPanel().setBorder(new EmptyBorder(8,8,8,8));
                    notificationDisplay.getReadButton().addActionListener(e -> {markAsRead();});
                    messageContentPanel.add(notificationDisplay.getMainPanel(), gbc);
                }
            }
        } catch (DatabaseException e) {
            messageLabel.setText("ERROR: Could not get notifications from the database");
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
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
     * TODO: Implement
     *
     * Returns a list of notifications for the current patient.
     * @return
     * @throws DatabaseException
     */
    private List<Notification> getNotifications(Session session) throws DatabaseException, PatientNotFoundException {
        return new NotificationLogic().getNotifications(session);
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
    public void receiveData(ReceivePair pair) {}


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
