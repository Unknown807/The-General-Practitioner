package com.group15A.Utils;

/**
 * The form of the data being passed between pages
 *
 * @author Milovan Gveric
 */
public class ReceivePair {
    private ReceiveType first;
    private Object second;

    public ReceivePair(ReceiveType first, Object second) {
        this.first = first;
        this.second = second;
    }

    public ReceiveType getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }
}
