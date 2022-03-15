package com.group15A.GUI;

import com.group15A.Utils.ErrorCode;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 */
public class MessagePanel
{
    public JPanel contentPanel;
    public JPanel headingContentPanel;
    public JLabel headingLabel;
    public JLabel subheadingLabel;
    public JTextPane messageTextPane;
    public JButton button;
    public JPanel messagePanel;

    public MessagePanel(String heading, String subheading, String message, String buttonText)
    {
        HashMap<JComponent, String> widgets = new HashMap<>() {{
            put(headingLabel, heading);
            put(subheadingLabel, subheading);
            put(messageTextPane, message);
            put(button, buttonText);
        }};
        
        for(JComponent component : widgets.keySet()){
            if(widgets.get(component).equals("")){
               component.setVisible(false);
            }
            else{
                if(component instanceof JButton){
                    ((JButton)component).setText(widgets.get(component));
                }
                else if(component instanceof JLabel){
                    ((JLabel)component).setText(widgets.get(component));
                }
                else if(component instanceof JTextPane){
                    ((JTextPane)component).setText(widgets.get(component));
                }
            }

        }
    }

    public JPanel getMainPanel()
    {
        return messagePanel;
    }

    public JButton getButton()
    {
        return button;
    }


}
