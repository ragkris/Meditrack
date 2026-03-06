package com.airtribe.meditrack.service;


import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.menu.MainMenu;
import com.airtribe.meditrack.util.AIHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AIAppointmentBooking extends MainMenu {

    DoctorService doctorService = DoctorService.getInstance();
    PatientService patientService = PatientService.getInstance();
    AppointmentService appointmentService = AppointmentService.getInstance();

    public void help() {

        boolean aiMenu = true;
        boolean updateAppointmentCheck = false;

        while (aiMenu) {
            System.out.println("1. Doctor Recommendation by Symptoms");

            System.out.println("9. Main Menu");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> {

                    String symptoms = readString("Please enter your health concern: ");

                    Specialization specialization = AIHelper.recommendSpecialization(symptoms);

                    System.out.println("Recommended to consult in " + specialization);
                    System.out.println("The consultants in our hospital with expertise in  " + specialization + " : ");

                    List<Doctor> doctors = doctorService.search(specialization);
                    for (Doctor d : doctors) {
                        System.out.println(d);
                    }

                    String confirm = readString("Would you like to book appointment ? (Y/N): ");

                    switch (confirm.toUpperCase()) {
                        case Constants.CONFIRM_YES -> {
                            String d = readString("Enter the doctor ID to book appointment with : ");
                            Doctor doctor = doctorService.search(new EntityID(d));
                            LocalDate dt = LocalDate.parse(readString("Appointment Time (yyyy-MM-dd): "));

                            System.out.println("Available Slots : ");
                            System.out.println(suggestAvailableSlots(doctor, dt));

                            LocalDateTime time = LocalDateTime.parse(readString("Appointment Time (yyyy-MM-dd HH:mm): "));


                            Patient patient = null;
                            String existing = readString("Have you visited our hospital before ? (Y/N): ");
                            if (Constants.CONFIRM_YES.equalsIgnoreCase(existing)) {
                                String name = readString("Name :");
                                List<Patient> patients = patientService.search(name);
                                String pid = readString("Confirm details : ");
                                patient = patientService.search(new EntityID(pid));
                                System.out.println(patient);
                            }
                            if (patient == null) {
                                System.out.println("Adding a new patient:");
                                String name = readString("Name: ");
                                String phone = readString("Phone: ");
                                String email = readString("Email: ");
                                int age = readInt("Age: ");
                                String gender = readString("Gender: ");
                                patient = new Patient(null, name, phone, email, age, gender); //ID will be generated only after validation passes

                                patientService.addPatient(patient);

                                System.out.println("Patient added");
                                patientService.savePatients(Constants.PATIENTS_CSV);
                            }

                            Appointment appointment = new Appointment(null, patient, doctor, time, AppointmentStatus.CONFIRMED);


                            appointment = appointmentService.bookAppointment(appointment);

                            System.out.println("Appointment has been booked successfully. Appointment ID : " + appointment.getAppointmentId());
                            appointmentService.saveAppointments(Constants.APPOINTMENTS_CSV);
                        }
                        default -> {
                            System.out.println("Thank you for visiting our hospital.");
                        }
                    }

                }

                case 9 -> {

                    startMainMenu();
                }
                case 0 -> {

                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }


    }

    public List<LocalDateTime> suggestAvailableSlots(
            Doctor doctor,
            LocalDate date) {

        System.out.println("doc - >" + doctor);
        List<LocalDateTime> slots = AIHelper.suggestSlots(date);

        for (Appointment a : appointmentService.getAllAppointmentsByDocId(doctor.getId().getValue())) {

            slots.remove(a.getAppointmentTime());
        }

        return slots;
    }

}