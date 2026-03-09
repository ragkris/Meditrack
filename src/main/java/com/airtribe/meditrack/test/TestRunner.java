package com.airtribe.meditrack.test;

import com.airtribe.meditrack.billing.DoctorBillingStrategy;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.entity.bill.DoctorBill;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.constants.*;
import com.airtribe.meditrack.menu.Analytics;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Kavitha Krishnan
 * @since 2026
 */


public class TestRunner {

    public static void main(String[] args) throws Exception {

        System.out.println("===== MEDITRACK TEST RUNNER =====");

        PatientService patientService =  PatientService.getInstance();
        DoctorService doctorService =  DoctorService.getInstance();
        AppointmentService appointmentService =  AppointmentService.getInstance();
        Analytics analytics = new Analytics();

        testPatientCRUD(patientService);
        testDoctorCRUD(doctorService);
        testSearch(patientService, doctorService);
        testAppointments(patientService, doctorService, appointmentService);
        testBilling();
        testStreamsAnalytics(analytics);
        testClone();
        testCSV(patientService);

        System.out.println("===== ALL TESTS COMPLETED =====");
    }

    // ----------------------------------------------------

    private static void testPatientCRUD(PatientService patientService) {

        System.out.println("\n--- PATIENT CRUD TEST ---");

        Patient p1 = new Patient(
                new EntityID("PAT-900"),
                "Test Patient",
                "9999999999",
                "test@mail.com",
                35,
                "M"
        );

        patientService.addPatient(p1);

        System.out.println("Patient added.");

        Patient found = patientService.search(p1.getId());

        System.out.println("Retrieved: " + found.getName());

        patientService.deletePatient(p1.getId().toString());

        System.out.println("Patient deleted.");
    }

    // ----------------------------------------------------

    private static void testDoctorCRUD(DoctorService doctorService) {

        System.out.println("\n--- DOCTOR CRUD TEST ---");

        Doctor d1 = new Doctor(
                new EntityID("DOC-900"),
                "Test Doctor",
                "8888888888",
                "doctor@mail.com",
                Specialization.CARDIOLOGY,
                800
        );

        doctorService.addDoctor(d1);

        System.out.println("Doctor added.");

        List<Doctor> doctors = doctorService.getAllDoctors();

        System.out.println("Doctor count: " + doctors.size());

        doctorService.deleteDoctor(d1.getId().toString());

        System.out.println("Doctor deleted.");
    }

    // ----------------------------------------------------

    private static void testSearch(
            PatientService patientService,
            DoctorService doctorService) {

        System.out.println("\n--- SEARCH TEST ---");

        List<Patient> patients = patientService.search("Anita");

        System.out.println("Patients found: " + patients.size());

        List<Doctor> doctors =
                doctorService.search(Specialization.CARDIOLOGY);

        System.out.println("Cardiologists found: " + doctors.size());
    }

    // ----------------------------------------------------

    private static void testAppointments(
            PatientService patientService,
            DoctorService doctorService,
            AppointmentService appointmentService) {

        System.out.println("\n--- APPOINTMENT TEST ---");
        Patient p1 = new Patient(
                new EntityID("PAT-900"),
                "Test Patient",
                "9999999999",
                "test@mail.com",
                35,
                "M"
        );

        patientService.addPatient(p1);
        Doctor d1 = new Doctor(
                new EntityID("DOC-900"),
                "Test Doctor",
                "8888888888",
                "doctor@mail.com",
                Specialization.CARDIOLOGY,
                800
        );

        doctorService.addDoctor(d1);

        System.out.println("Doctor added.");
        System.out.println("Patient added.");
        Patient p = patientService.getAllPatients().get(0);
        Doctor d = doctorService.getAllDoctors().get(0);

        Appointment appt = new Appointment(
                new EntityID("APT-900"),
                p,
                d,
                LocalDateTime.now().plusDays(1), AppointmentStatus.CONFIRMED
        );

        appointmentService.createAppointment(appt);

        System.out.println("Appointment created.");

        List<Appointment> list = appointmentService.getAllAppointments();

        System.out.println("Appointments count: " + list.size());

        appointmentService.cancelAppointment(appt.getAppointmentId().toString());

        System.out.println("Appointment cancelled.");
    }

    // ----------------------------------------------------

    private static void testBilling() {

        System.out.println("\n--- BILLING TEST ---");

        Payable bill = new DoctorBill(
                new EntityID("INV-101"),
                500, new DoctorBillingStrategy()
        );

        BillSummary summary = bill.generateBill();

        System.out.println("Bill total: " + summary.toString());
    }

    // ----------------------------------------------------

    private static void testStreamsAnalytics(
            Analytics analytics) {

        System.out.println("\n--- STREAM ANALYTICS TEST ---");

        double avgFee =   analytics.getAverageConsultationFee();

        System.out.println("Average doctor fee: " + avgFee);

        Map<Doctor, Long> analytics1 =analytics.getAppointmentsPerDoctor();

        analytics1.forEach((doc, count) ->
                System.out.println(doc + " -> " + count));
    }

    // ----------------------------------------------------

    private static void testClone() throws CloneNotSupportedException {

        System.out.println("\n--- CLONE TEST ---");

        Patient p = new Patient(
                new EntityID("PAT-CLONE"),
                "Clone Test",
                "1111111111",
                "clone@mail.com",
                25,
                "F"
        );

        Patient cloned = (Patient) p.clone();

        System.out.println("Original: " + p.getName());
        System.out.println("Cloned: " + cloned.getName());
    }

    // ----------------------------------------------------

    private static void testCSV(PatientService patientService) {

        System.out.println("\n--- CSV TEST ---");

        String file = "patients_test.csv";

        patientService.savePatients(file);

        System.out.println("Patients saved to CSV.");
    }

}