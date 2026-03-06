package com.airtribe.meditrack.constants;


public enum Specialization {

    CARDIOLOGY("CARDIOLOGY"),
    NEUROLOGY("NEUROLOGY"),
    ORTHOPEDICS("ORTHOPEDICS"),
    PEDIATRICS("PEDIATRICS"),
    DERMATOLOGY("DERMATOLOGY"),
    GENERAL_MEDICINE("GENERAL_MEDICINE");


    private String value;

    Specialization(String value) {
        this.value = value;
    }

    public static Specialization getSpecialization(String value) {

        for (Specialization spl : Specialization.values()) {

            if (spl.name().equalsIgnoreCase(value)) {
                return spl;
            }
        }

        throw new IllegalArgumentException(
                "Invalid Specialization: " + value
        );
    }

    public String getValue() {
        return value;
    }


}