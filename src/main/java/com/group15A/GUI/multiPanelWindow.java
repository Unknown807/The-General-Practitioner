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
                new LoginPanel(this),
                new RegisterPanel(this),
                new HomePanel(this)
                // pages get stored and added to the card layout in here
        };

        this.cardLayout = (CardLayout) (panelCards.getLayout());
        for (BasePanel card : cards) {
            this.panelCards.add(card.getPagePanel(),card.getPanelFieldName());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelCards);
        this.setSize(640, 480);

        // first page shown is the login page (`loginPanel`)
        this.showPage(this.cards[0]);
    }


    /**
     * Switches to a given JPanel that is in the card layout
     *
     * @param page       the page to switch to, contains window title and the required JPanel
     * @author Milovan Gveric
     */
    public void showPage(BasePanel page) {
        this.setTitle(page.getWindowTitle());
        this.cardLayout.show(panelCards, page.getPanelFieldName());
    }

}
