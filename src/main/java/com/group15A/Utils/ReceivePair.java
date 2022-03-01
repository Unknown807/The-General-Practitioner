package com.group15A.Utils;

/**
 * The form of the data being passed between pages
 *
 * @author Milovan Gveric
 * @param <B> the type of the data to be passed
 */
public class ReceivePair<B> {
    private ReceiveType first;
    private B second;

    public ReceivePair(ReceiveType first, B second) {
        this.first = first;
        this.second = second;
    }

    public ReceiveType getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
