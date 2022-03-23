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
            "Sore throat medicine",
            "Oral Health medication"
    };

    private final String[] details = new String[] {
            "My weekly checkup was performed and the doctor reported that I was in good health",
            "The blood sample was taken by the doctor",
            "The prescriptions has updated by doctor, you need using new medication",
            "For Allergy the doctor suggest to make regular check, to made sure the progression of seriously ",
            "doctor suggest drink boiling water",
            "doctor suggest eating low-sugar food in the daily meals, for prevent diabetes",
            "doctor suggest eating low-salt food in the daily meals, for prevent CVD",
            "doctor suggest eating low-fat food in the daily meals, for prevent cardiovascular disease",
            "report form doctor examine shown I have gluten allergy, I need start eat gluten-free diet ",
            "The bio-sample was taken by the doctor",
            "The first vaccine has taken by the doctor and second vaccine need make a appointment",
            "The doctor has booked XR Orthopantomogram full for the body check, I need keep an appointment",
            "The doctor has booked surgery for my bones, I need keep an appointment",

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