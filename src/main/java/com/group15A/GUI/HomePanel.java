package com.group15A.GUI;

import com.group15A.Session;
import com.group15A.Utils.Pages;

import javax.swing.*;

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
    private JScrollPane messageScrollPane;
    private JLabel messageLabel;
    private JLabel titleLabel;

    /**
     * Constructor for the HomePanel class
     *
     * Creates action listeners for widgets
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public HomePanel(MultiPanelWindow panelController) {
        super(panelController);
        //TODO: Read session file to get patient name.
        createActionListeners();
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
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners() {
        logOutButton.addActionListener(e -> {logOutUser();});
    }

    /**
     * Delete the log-in session file and go to log in page.
     */
    private void logOutUser()
    {
        Session.deleteSession();
        panelController.showPage(Pages.LOGIN);
    }

}
