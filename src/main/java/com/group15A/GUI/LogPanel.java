package com.group15A.GUI;

import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.InvalidDataException;
import com.group15A.CustomExceptions.NullDataException;
import com.group15A.CustomExceptions.PatientNotFoundException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Log;
import com.group15A.DataModel.Patient;
import com.group15A.Utils.DataModification;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;

import javax.swing.*;
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
public class LogPanel extends BasePanel {
    private JPanel loggingPanel;
    private JButton homeButton;
    private JPanel contentPanel;
    private JPanel logsPanel;
    private MessageListPanel messageListPanel;

    /**
     * Constructor for the LogPanel class
     *
     * Creates action listeners for widgets
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public LogPanel(MultiPanelWindow panelController)
    {
        super("Activity logs", "loggingPanel", panelController);

        messageListPanel = new MessageListPanel("My activity","No logs.", false);
        logsPanel.add(messageListPanel.getPanel());

        try{
            displayLogs();
        }
        catch(Exception e){

        }
        createActionListeners();
    }

    /**
     * For each message provided by the LogLogic object,
     * create a LogDisplay object and add it to the log display panel.
     */
    private void displayLogs() throws DatabaseException, InvalidDataException, PatientNotFoundException, NullDataException {
        DataAccess dataAccess = new DataAccess();
        Patient patient = dataAccess.getPatient(panelController.getSession().getLoggedInPatientID());
        List<Log> logs = dataAccess.getLogs(patient);

        messageListPanel.clearMessages();
        for(Log log : logs){
            messageListPanel.addMessage(
                    "",
                    DataModification.shortDateTime(log.getTimestamp()),
                    log.getMessage(),
                    ""
            );
        }
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
        homeButton.addActionListener(e -> this.panelController.showPage(PageType.VIEW_PROFILE));
    }

}
