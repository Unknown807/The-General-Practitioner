package com.group15A.Utils;

import java.util.Random;

public class Randomiser {
    private final String[] prescriptions = new String[] {
            "Headache medicine",
            "Skin Cream",
            "Pain-killer",
            "Oxprenolol",
            "Cardiovascular drugs",
            "Insulin",
            "Cold medication",
            "Antacids",
            "Stomach medicine",
            "Allergy medication",
            "Asthma medicine",
            "Antivirus medication",
            "Heart disease medication",
            "Hypertension medicine",
            "Gout medication",
            "Diabetes drugs",
            "Contraception pills",
            "Chronic disease medication",
            "Sore throat medicine"
    };

    private final String[] details = new String[] {
            "My weekly checkup was performed and the doctor reported that I was in good health",
            ""
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