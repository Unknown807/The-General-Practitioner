package com.group15A.GUI;

import com.group15A.Utils.JWidgetShortcuts;
import com.group15A.Utils.PageType;
import com.group15A.Utils.ReceivePair;

import javax.swing.*;

/**
 *
 * @author Filip Fois
 */
public class AddBookingPanel extends BasePanel {
    private JPanel addBookingPanel;
    private JButton goHomeButton;
    private JButton createBookingButton;
    private JPanel contentPanel;
    private JScrollPane contentScrollPane;
    private JPanel bookingSelectionPanel;
    private JComboBox dayCombo;
    private JPanel dateSelectionPanel;
    private JComboBox monthCombo;
    private JComboBox yearCombo;
    private JComboBox hourCombo;
    private JComboBox minuteCombo;
    private JPanel timeSelectionPanel;
    private JLabel bookingErrorLabel;
    private JLabel promptLabel;

    /**
     *
     */
    public AddBookingPanel(MultiPanelWindow panelController)
    {
        super("New Booking", "addBookingPanel", panelController);
        JWidgetShortcuts.addNumbersToCombo(dayCombo,1,31,1,"Day");
        JWidgetShortcuts.addNumbersToCombo(monthCombo,1,12,1,"Month");
        int year = 2022;
        JWidgetShortcuts.addNumbersToCombo(yearCombo,year,year+10,1,null);

        JWidgetShortcuts.addNumbersToCombo(hourCombo,9,17,1,"Hour");
        JWidgetShortcuts.addNumbersToCombo(minuteCombo,0,55,5,"Minute");
        createActionListeners();
    }

    /**
     * @return addBookingPanel
     */
    @Override
    public JPanel getPagePanel()
    {
        return this.addBookingPanel;
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
        goHomeButton.addActionListener(e -> {panelController.showPage(PageType.HOME);});
    }

}