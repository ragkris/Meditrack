package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.id.EntityID;


public class IdGenerator {

    private static int doctorCounter;
    private static int patientCounter;
    private static int appointmentCounter;
    private static int invoiceCounter;

    static {

        System.out.println("Initializing ID counters...");

        doctorCounter = 100;
        patientCounter = 500;
        appointmentCounter = 1000;
    }

    public static EntityID generateDoctorId() {
        return new EntityID("DOC-" + (++doctorCounter));
    }

    public static EntityID generatePatientId() {
        return new EntityID("PAT-" + (++patientCounter));
    }

    public static EntityID generateAppointmentId() {
        return new EntityID("APT-" + (++appointmentCounter));
    }

    public static EntityID generateInvoiceId() {
        return new EntityID("INV-" + (++invoiceCounter));
    }
}