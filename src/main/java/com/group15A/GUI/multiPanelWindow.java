package com.group15A.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * The window which will be shown, consists of a card layout
 * which can switch between different JPanels (pages).
 * <p>
 * cardLayout is needed to add pages to be switched between
 * <p>
 * cards is the list of JPanels to be stored
 * <p>
 * panelCards is the parent that holds all JPanels, of which
 * its layout is cardLayout
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class multiPanelWindow extends JFrame {
    private CardLayout cardLayout;
    private BasePanel[] cards;
    private JPanel panelCards;

    /**
     * Constructor
     */
    public multiPanelWindow() {
        this.cards = new BasePanel[]{
                //TODO: Can't add more than two panels
                new LoginPanel("Log in", this),
                new RegisterPanel("Register", this),
                new HomePanel("Home", this)
                // pages get stored and added to the card layout in here
        };

        this.cardLayout = (CardLayout) (panelCards.getLayout());
        for (BasePanel card : cards) {
            this.panelCards.add(card.getPagePanel(),card.getPanelFieldName());
            // TODO: `this.cardLayout.show(panelCards, pageName)` doesn't show the specified page.
            // TODO: To fix the above issue, replace "registerPanel" below with the JPanel field name of the current card.
            //       Must create getPanelFieldName in basePanel or in each sub-panel.
           // this.cardLayout.addLayoutComponent(card.getPagePanel(),card.getPanelFieldName());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelCards);
        this.setSize(640, 480);


        // first page shown is the login page (`loginPanel`)
        this.showPage("Please Sign In", "loginPanel");
        //TODO: WindowTitle is repeated.
    }


    /**
     * Switches to a given JPanel that is in the card layout
     *
     * @param newWindowTitle to set the title of the window
     * @param pageName       the page to switch to (i.e., another JPanel)
     * @author Milovan Gveric
     */
    public void showPage(String newWindowTitle, String pageName) {
        this.setTitle(newWindowTitle);
        this.cardLayout.show(panelCards, pageName);
    }

}
