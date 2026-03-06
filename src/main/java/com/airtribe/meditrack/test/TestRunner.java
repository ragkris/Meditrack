package com.airtribe.meditrack.test;

import com.airtribe.meditrack.billing.DoctorBillingStrategy;
import com.airtribe.meditrack.billing.LabBillingStrategy;
import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.bill.DoctorBill;
import com.airtribe.meditrack.entity.bill.LabBill;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {

        DoctorService doctorService = DoctorService.getInstance();
        PatientService patientService = PatientService.getInstance();
        AppointmentService appointmentService = AppointmentService.getInstance();

        Doctor doctor = new Doctor(
                IdGenerator.generateDoctorId(),
                "Dr.Ravi",
                "9876543210",
                "ravi@clinic.com",
                Specialization.CARDIOLOGY,
                500
        );

        doctorService.addDoctor(doctor);

        Patient patient = new Patient(
                IdGenerator.generatePatientId(),
                "Anita",
                "9871234567",
                "anita@mail.com",
                30,
                "Female"
        );

        patientService.addPatient(patient);

        Appointment appointment = new Appointment(
                IdGenerator.generateAppointmentId(),
                patient, doctor,
                LocalDateTime.now(), AppointmentStatus.PENDING
        );

        appointmentService.bookAppointment(appointment);

        System.out.println("Doctor:");
        System.out.println(doctor);

        System.out.println("\nPatient:");
        System.out.println(patient);

        System.out.println("\nAppointment:");
        System.out.println(appointment);


        //Deep Copy

        Patient p1 = new Patient(
                new EntityID("PAT001"),
                "Anita",
                "9876543210",
                "anita@mail.com",
                30,
                "Female"
        );

        Doctor d1 = new Doctor(
                new EntityID("DOC101"),
                "Dr Kumar",
                "9876543210",
                "kumar@clinic.com",
                Specialization.CARDIOLOGY,
                500
        );

        Appointment appt1 = new Appointment(
                IdGenerator.generateAppointmentId(),
                p1,
                d1,
                LocalDateTime.now(), AppointmentStatus.CONFIRMED
        );

        Appointment appt2 = appt1.clone();
        appt2.getPatient().setAge(78);


        //Shallow Copy
        Patient p2 = p1.clone();
        p2.setAge(55);


        //immutable


        //Dynamic Dispatch
        Payable dr = new DoctorBill(new EntityID("B101"), 500, new DoctorBillingStrategy());

        BillSummary summary = dr.generateBill();

        System.out.println(summary.toString());


        Payable lab = new LabBill(new EntityID("B101"), 200, new LabBillingStrategy(100));

        BillSummary summary1 = dr.generateBill();

        System.out.println(summary.toString());


        //AI Helper

        String symptom = "chest pain";

        Specialization specialization =
                AIHelper.recommendSpecialization(symptom);

        System.out.println("Recommended doctor type: " + specialization);

        List<LocalDateTime> slots =
                AIHelper.suggestSlots(LocalDate.now());

        for (LocalDateTime slot : slots) {
            System.out.println(slot);
        }

    }
}