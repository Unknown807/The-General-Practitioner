package com.group15A.GUI;

import javax.swing.*;

/**
 * To allow for communication to the business layer and to
 * take care of event handling
 * <p>
 * loginPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
 * in order to show it in the UI
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class ChooseDoctorPanel extends BasePanel {
    private JPanel chooseDoctorPanel;
    private JPanel contentPanel;
    private JButton logInButton;
    private JButton registerButton;
    private JButton completeRegisterButton;

    /**
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public ChooseDoctorPanel(MultiPanelWindow panelController)
    {
        super("Choose your new doctor", panelController,"chooseDoctorPanel");
        createActionListeners();
    }

    @Override
    public JPanel getPagePanel()
    {
        return this.chooseDoctorPanel;
    }

    /**
     * TODO: Add action listeners
     * To create all event handlers, which will point
     * to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        logInButton.addActionListener( e -> {panelController.showPage(new LogInPanel(panelController));});
        registerButton.addActionListener( e -> {panelController.showPage(new RegisterPanel(panelController));});
    }

}
