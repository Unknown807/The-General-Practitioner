package com.group15A.Utils;

import java.util.Random;

public class Randomiser {
    private final String[] prescriptions = new String[] {
            "Headache medicine",
            "Skin cream",
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
            "Sore throat medicine",
            "Oral Health medication"
    };

    private final String[] details = new String[] {
            "Weekly checkup was performed. Patient was in good health.",
            "Blood sample was taken.",
            "Old prescription is no longer being recommended. New prescription assigned.",
            "Patient found to have an allergy. Regular check-ups recommended to assess patient health.",
            "Patient was advised to drink warm water.",
            "Patient was advised to eat low-sugar food for daily meals, to prevent diabetes.",
            "Patient was advised to eat low-salt food for daily meals, to prevent CVD.",
            "Patient was advised to eat low-fat food for daily meals, to prevent cardiovascular disease.",
            "Examination shows patient has gluten allergy. A gluten free diet was recommended.",
            "Patient's bio-sample was taken.",
            "Patient took first vaccine. An appointment for the second vaccination must be made.",
            "Scheduled future full Orthopantomogram X-Ray appointment.",
            "Scheduled future appointment for patient to have orthopedic surgery.",

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