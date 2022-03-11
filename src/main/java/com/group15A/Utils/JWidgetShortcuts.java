package com.group15A.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

/**
 * A collection of methods that can be used on Java Swing widgets
 *
 * @author Filip Fois
 * @author Milovan Gveric
 */
public class JWidgetShortcuts {

    /**
     * Adds a range of numbers as items in a given combobox,
     * in order of first to last
     *
     * @param comboBox The combobox which will have values added to it
     * @param first The first value to added (after unchosen)
     * @param last The last value to be added
     * @param inc the number of increments going from first to last
     * @param unchosen The first value to be shown
     *                      (unchosen usually indicates a valid item has not been chosen)
     */
    public static void addItemsToCombo(JComboBox comboBox, int first, int last, int inc, String unchosen)
    {
        if (!(unchosen == null)){
            comboBox.addItem(unchosen);
        }

        int steps = (first < last) ? inc : (-1*inc);
        // ternary condition for increment order
        for(int i = first; ((first < last) ? (i<=last) : (i>=last)); i+=steps){
            addItemToCombo(comboBox, i);
        }
    }

    /**
     * Adds an array of words as items in a given combobox,
     * in the given or reversed order (as chosen)
     *
     * @param comboBox The combobox which will have values added to it
     * @param words The collection of strings to add as items
     * @param reverse If true, add items in array in reverse, otherwise, add in given order
     * @param unchosen The first value to be shown
     *                      (unchosen usually indicates a valid item has not been chosen)
     */
    public static void addItemsToCombo(JComboBox comboBox, String[] words, boolean reverse, String unchosen)
    {
        if (!(unchosen == null)){
            comboBox.addItem(unchosen);
        }

        int start = reverse ? words.length-1 : 0;
        int end   = reverse ? 0 : words.length-1;
        int steps = reverse ? -1 : 1;

        for(int i = start; i != end; i+=steps){
            comboBox.addItem(words[i]);
        }
    }

    /**
     * Adds an integer to a combobox,
     * before prepending the number with a zero (0) if it's a single digit
     * @param comboBox the combobox to add the item to
     * @param item the integer to add to the combo box
     */
    private static void addItemToCombo(JComboBox comboBox, int item)
    {
        comboBox.addItem(
            (item < 10) ? "0"+item : ""+item
        );
    }

    /**
     * Return a preset GridBagContraints styling that
     * aligns Components vertically and makes them
     * fill space on the horizontal axis.
     *
     * @return The GridBagConstraints with the stack style.
     */
    public static GridBagConstraints getStackGBC()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;

        return gbc;
    }


    public static void clearJPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public static void showDatabaseExceptionPopupAndExit(JPanel panel) {
        JOptionPane.showMessageDialog(
                panel,
                "Please connect to the database and restart the program.",
                "ERROR: Database not connected",
                JOptionPane.ERROR_MESSAGE
        );
        System.exit(0);
    }

}
