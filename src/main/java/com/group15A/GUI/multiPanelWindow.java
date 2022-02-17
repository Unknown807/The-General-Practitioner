package com.group15A.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * The window which will be shown, consists of a card layout
 * which can switch between different JPanels (pages).
 *
 * cardLayout is needed to add pages to be switched between
 *
 * cards is the list of JPanels to be stored
 *
 * panelCards is the parent that holds all JPanels, of which
 * its layout is cardLayout
 *
 * @author Milovan Gveric
 * @author Filip Fois
 */
public class multiPanelWindow extends JFrame {
    private CardLayout cardLayout;
    private basePanel[] cards;
    private JPanel panelCards;

    /**
     * Constructor
     */
    public multiPanelWindow() {
        // pages get stored and added to the card layout here
        this.cards = new basePanel[] {
                new LoginPanel("Please Sign In", this)
        };

        for (int i=0; i<1; i++) {
            this.panelCards.add(this.cards[i].getPagePanel());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelCards);
        this.setSize(640, 480);

        this.cardLayout = (CardLayout) (panelCards.getLayout());

        // first page shown is the login page
        this.showPage("Please Sign In", "loginPanel");
    }

    /**
     * Switches to another JPanel in the card layout
     *
     * @param newWindowTitle to set the title of the window
     * @param pageName the page to switch to (another JPanel)
     * @author Milovan Gveric
     */
    public void showPage(String newWindowTitle, String pageName) {
        this.setTitle(newWindowTitle);
        this.cardLayout.show(panelCards, pageName);
    }
}
