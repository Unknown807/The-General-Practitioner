package com.group15A.GUI;

import com.group15A.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

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
public class MultiPanelWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelCards;

    /**
     * Constructor
     */
    public MultiPanelWindow() {
        BasePanel[] cards = new BasePanel[]{
                new LogInPanel(this),
                new RegisterPanel(this),
                new HomePanel(this),
                new ChooseDoctorPanel(this)
        };

        this.cardLayout = (CardLayout) (panelCards.getLayout());
        for (BasePanel card : cards) {
            this.panelCards.add(card.getPagePanel(),card.getPanelFieldName());
        }

        // Run closeProgram() when window close button is clicked
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeProgram();
            }
        });

        this.setContentPane(panelCards);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(
                (int)(dimension.getWidth()*0.6), // Make window width 60% that of the screen
                (int)(dimension.getHeight()*0.8) // Make window height 80% that of the screen
        );

        // Choose the page to be displayed when starting the program
        File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"/LoggedUser.bin");
        int pageToShow = 0; // log in page
        if(file.exists()) {
            pageToShow = 2; // home page
        }
        showPage(new LogInPanel(this));
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

    /**
     * When the close window button is clicked,
     * the user will be logged out (if they choose to be),
     * and the program will terminate.
     * @author Filip Fois
     */
    public void closeProgram()
    {
        // Delete session file if user doesn't want to stay logged in (i.e. log out user)
        // TODO: Delete session if user doesn't want to stay logged in.
        //---
        // TODO: Access `session.keepLoggedIn`.
//        Session session = new Session();
//        if(!session.keepLoggedIn) {
//            try {
//                File sessionFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/LoggedUser.bin");
//                sessionFile.delete();
//            }
//            catch (Exception e){
//                JOptionPane.showMessageDialog(
//                        panelCards,
//                        "Could not delete session file.",
//                        "ERROR: Log out failed",
//                        JOptionPane.ERROR_MESSAGE
//                );
//                System.exit(0);
//            }
//        }
        //---

        // Exit program
        System.exit(0);
    }

}
