package com.group15A.GUI;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.DoctorNotFoundException;
import com.group15A.CustomExceptions.SessionEmptyException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Notification;
import com.group15A.DataModel.Patient;
import com.group15A.Session;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;

import javax.swing.*;
import javax.xml.crypto.Data;
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
    private JLabel messageLabel;
    private JLabel titleLabel;
    private JPanel messagePanel;
    private JPanel navigationPanel;
    private JButton viewBookingsButton;
    private JButton newBookingButton;
    private JPanel messageContentPanel;

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
            String patientFirstName = panelController.getSession().getLoggedInPatient().getFirstName();
            titleLabel.setText("Welcome, " + patientFirstName + ".");
            displayNotifications();
        }
    }

    private void displayNotifications()
    {
        getNotifications();
    }

    //TODO: Should be in NotificationLogic
    private void getNotifications()
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

    private void createUIComponents() {
        //TODO (for Filip): Finish adding notifications to panel
        messageContentPanel = new JPanel();
        for(int i = 0; i < 10; i++){
            JTextPane messageTextPane = new JTextPane();
            //messageTextPane.setText();
        }
    }
}
