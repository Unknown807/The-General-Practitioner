package com.group15A.GUI;

import com.group15A.Utils.Page;

import javax.swing.*;

/**
 * To allow for communication to the business layer and to take care of event handling
 *
 * chooseDoctorPanel is the actual panel that gets passed to the multiPanelWindow cardLayout
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
    private JPanel doctorListPanel;
    private JScrollPane doctorListScrollPane;

    /**
     * Constructor for the ChooseDoctorPanel class
     *
     * Creates action listeners for widgets
     *
     * @param panelController the instance of multiPanelWindow in order for
     *                        events from this panel to call showPage
     */
    public ChooseDoctorPanel(MultiPanelWindow panelController)
    {
        super(panelController);
        createActionListeners();
    }

    /**
     * @return chooseDoctorPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.chooseDoctorPanel;
    }

    /**
     * To create all event handlers, which will point to other methods in the class
     */
    @Override
    public void createActionListeners()
    {
        logInButton.addActionListener( e -> panelController.showPage(Page.LOGIN));
        registerButton.addActionListener( e -> panelController.showPage(Page.REGISTER));
    }

}
