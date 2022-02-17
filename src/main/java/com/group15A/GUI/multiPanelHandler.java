package com.group15A.GUI;

import javax.swing.*;
import java.util.HashMap;

public class multiPanelHandler extends JFrame {
    private HashMap<String, basePanel> pages = new HashMap<>();

    public multiPanelHandler() {
        pages.put("loginPage", new LoginPanel("Please Sign In", this));
        pages.put("registerPage", new RegisterPanel("Please Enter Your Details", this));
        pages.put("homePage", new HomePanel("Welcome!", this));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.showPage("loginPage");
    }

    public void showPage(String pageName) {
        basePanel page = pages.get(pageName);
        this.setTitle(page.getWindowTitle());
        this.setContentPane(page.getPagePanel());
    }

}
