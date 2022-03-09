package com.group15A.GUI;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.CustomExceptions.SessionEmptyException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;
import org.mockito.internal.matchers.Not;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.security.Timestamp;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
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

    private Patient patientObject;

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
            patientObject = panelController.getSession().getLoggedInPatient();
            String patientFirstName = patientObject.getFirstName();
            titleLabel.setText("Welcome, " + patientFirstName + ".");
            displayNotifications();
        }
    }

    private void displayNotifications()
    {
        //messageContentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = JWidgetShortcuts.getStackGBC();
        try {

            List<Notification> notifications = getNotifications();
            messageLabel.setText("Messages ("+notifications.size()+")");

            if(!notifications.isEmpty()) {
                noMessagesLabel.setVisible(false);
                for (Notification notification : notifications) {
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
        } catch (DatabaseException e) {
            messageLabel.setText("ERROR: Could not get notifications from the database");
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
    private List<Notification> getNotifications() throws DatabaseException {
        //TODO: Should be in NotificationLogic
        DataAccess dataAccess = new DataAccess();
        return dataAccess.getNotifications(patientObject);
//        return Collections.emptyList();
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
        viewBookingsButton.addActionListener(e -> {panelController.showPage(PageType.VIEW_BOOKINGS);});
        newBookingButton.addActionListener(e -> {panelController.showPage(PageType.ADD_BOOKING);});
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
