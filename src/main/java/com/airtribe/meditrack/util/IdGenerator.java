package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.id.EntityID;

import java.util.Collection;


public class IdGenerator {

    private static int doctorCounter;
    private static int patientCounter;
    private static int appointmentCounter;
    private static int invoiceCounter;
    static {

        System.out.println("Initializing ID counters...");

        doctorCounter = 100;
        patientCounter = 500;
        appointmentCounter =1000;
        invoiceCounter =0000;
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

    public static int findMaxDoctorId(Collection<Doctor> doctors) {

        int max = doctorCounter;

        for (Doctor doctor : doctors) {

            String id = doctor.getId().value();   // DOC-105
            int number = Integer.parseInt(id.split("-")[1]);

            if (number > max) {
                max = number;
            }
        }
        doctorCounter=max;
        return max;
    }
    public static int findMaxPatientId(Collection<Patient> patients) {

        int max = patientCounter;

        for (Patient p : patients) {

            String id = p.getId().value();   // DOC-105
            int number = Integer.parseInt(id.split("-")[1]);

            if (number > max) {
                max = number;
            }
        }
        patientCounter=max;
        return max;
    }
    public static int findMaxApptId(Collection<Appointment> appts) {

        int max = appointmentCounter;

        for (Appointment doctor : appts) {

            String id = doctor.getAppointmentId().value();   // DOC-105
            int number = Integer.parseInt(id.split("-")[1]);

            if (number > max) {
                max = number;
            }
        }
        appointmentCounter=max;
        return max;
    }



}