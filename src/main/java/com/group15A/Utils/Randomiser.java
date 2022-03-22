package com.group15A.Utils;

import java.util.Random;

public class Randomiser {
    private final String[] prescriptions = new String[] {

    };

    private final String[] details = new String[] {

    };

    private Random randomGen;

    public Randomiser() {
        this.randomGen = new Random();
    }

    public String getRandPrescription() {
        return prescriptions[randomGen.nextInt(prescriptions.length)];
    }

    public String getRandDetails() {
        return details[randomGen.nextInt(details.length)];
    }
}