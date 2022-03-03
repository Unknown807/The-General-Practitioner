package com.group15A.Utils;

import javax.swing.*;

/**
 * A collection of methods that can be used on Java Swing widgets
 *
 * @author Filip Fois
 */
public class JWidgetShortcuts {

    /**
     * Adds a range of numbers as items in a given combobox,
     * in order of first to last
     *
     * @param comboBox The combobox which will have values added to it
     * @param first The first value to added (after unchosenValue)
     * @param last The last value to be added
     * @param unchosenValue The first value to be shown
     *                      (unchosenValue usually indicates a valid item has not been chosen)
     */
    public static void addNumbersToCombo(JComboBox comboBox, int first, int last, int steps, String unchosenValue)
    {
        if(!(unchosenValue == null)){
            comboBox.addItem(unchosenValue);
        }
        if(first < last) {
            for(int i = first; i <= last; i += steps){
                addItemToCombo(comboBox,i);
            }
        }
        else {
            for(int i = first; i >= last; i--){
                addItemToCombo(comboBox,i);
            }
        }
    }

    /**
     * Adds an array of words as items in a given combobox,
     * in the given or reversed order (as chosen)
     *
     * @param comboBox The combobox which will have values added to it
     * @param words The collection of strings to add as items
     * @param reverse If true, add items in array in reverse, otherwise, add in given order
     * @param unchosenValue The first value to be shown
     *                      (unchosenValue usually indicates a valid item has not been chosen)
     */
    public static void addStringsToCombo(JComboBox comboBox, String[] words, boolean reverse, String unchosenValue)
    {
        if(!(unchosenValue == null)){
            comboBox.addItem(unchosenValue);
        }
        if(!reverse) {
            for (String word : words) {
                comboBox.addItem(word);
            }
        }
        else {
            for(int i = words.length-1; i >= 0; i--){
                comboBox.addItem(words[i]);
            }
        }
    }

    private static void addItemToCombo(JComboBox comboBox, int item)
    {
        String newItem = String.valueOf(item);
        if(item < 10){
            newItem = twoCharacterNumber(item);
        }

        comboBox.addItem(newItem);
    }

    private static String twoCharacterNumber(int number)
    {
        return "0"+number;
    }

}
