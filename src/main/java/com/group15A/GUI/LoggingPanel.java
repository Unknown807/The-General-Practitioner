package com.group15A.GUI;

import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;

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
public class LoggingPanel extends BasePanel {
    private JPanel loggingPanel;
    private JButton homeButton;
    private JPanel contentPanel;
    private JPanel messagePanel;
    private JLabel messageLabel;
    private JScrollPane logScrollPanel;
    private JPanel logExtraPanel;
    private JPanel logContentPanel;
    private JLabel noLogsLabel;

    /**
     * Constructor for the LoggingPanel class
     *
     * Creates action listeners for widgets
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public LoggingPanel(MultiPanelWindow panelController)
    {
        super("Activity logs", "loggingPanel", panelController);
        createActionListeners();
        try{
            displayLogs();
        }
        catch(Exception e){

        }
    }

    /**
     * For each message provided by the LogLogic object,
     * create a LogDisplay object and add it to the log display panel.
     */
    private void displayLogs()
    {
        //TODO: Implement
    }


    /**
     * @return homePanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.loggingPanel;
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
        homeButton.addActionListener(e -> this.panelController.showPage(PageType.HOME));
    }

}
