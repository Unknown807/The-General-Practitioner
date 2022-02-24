package com.group15A.GUI;

import javax.swing.*;
import java.io.File;

/**
 * To allow for communication to the business layer and to
 * take care of event handling
 * <p>
 * registerPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
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
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public HomePanel(MultiPanelWindow panelController) {
        super("Welcome!", panelController,"homePanel");
        createActionListeners();
    }

    @Override
    public JPanel getPagePanel()
    {
        return this.homePanel;
    }



    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    @Override
    public void createActionListeners() {
        //TODO: Implement this action listener.
        logOutButton.addActionListener(e -> {logOutUser();});
    }

    /**
     * Delete the log-in session file and
     * go to log in page.
     */
    private void logOutUser()
    {
        // TODO: Should be implemented in business logic (e.g., LogInLogic.java).
        File sessionFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/LoggedUser.bin");
        sessionFile.delete();

        panelController.showPage(new LogInPanel(panelController));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
