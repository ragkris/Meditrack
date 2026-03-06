package com.airtribe.meditrack.constants;

public class Constants {

    public static final String PATIENTS_CSV = "patients.csv";
    public static final String DOCTORS_CSV = "doctors.csv";
    public static final String APPOINTMENTS_CSV = "appointments.csv";

    public static final String PATIENTS_HEADER = "ID,NAME,PHONE,EMAIL,AGE,GENDER";
    public static final String DOCTORS_HEADER = "ID,NAME,PHONE,EMAIL,SPECIALIZATION,CONSULTATION_FEE";
    public static final String APPOINTMENTS_HEADER = "ID,PATIENT_ID,DOCTOR_ID,APPOINTMENT_TIME,STATUS";


    public static final double TAX_RATE = 0.18;
    public static final double LAB_FEE = 150;

    public static final String DOCTOR_PREFIX = "DOC";
    public static final String PATIENT_PREFIX = "PAT";
    public static final String APPOINTMENT_PREFIX = "APT";

    public static final String CONFIRM_YES = "Y";
    public static final String CONFIRM_NO = "N";
}