package com.group15A.GUI;

import javax.swing.*;
import java.awt.*;

public class multiPanelWindow extends JFrame {
    private CardLayout cardLayout;
    private basePanel[] cards;
    private JPanel panelCards;

    public multiPanelWindow() {
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

        this.showPage("Please Sign In", "loginPanel");
    }

    public void showPage(String newWindowTitle, String pageName) {
        this.setTitle(newWindowTitle);
        this.cardLayout.show(panelCards, pageName);
    }
}
