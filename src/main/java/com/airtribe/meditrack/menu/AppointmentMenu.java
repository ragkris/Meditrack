package com.airtribe.meditrack.menu;

import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentMenu extends MainMenu {
    private final static PatientService patientService = PatientService.getInstance();
    private final static DoctorService doctorService = DoctorService.getInstance();
    private final static AppointmentService appointmentService = AppointmentService.getInstance();


    public void show() {

        boolean apptMenu = true;
        boolean updateAppointmentCheck = false;

        while (apptMenu) {
            System.out.println("1. Create Appointment");
            System.out.println("2. View Appointment By ID (APPT/PATIENT/DOC)");
            System.out.println("3. View All Appointments");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Reschedule Appointment");
            System.out.println("6. Confirm Appointment");
            System.out.println("9. Main Menu");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> {

                    String patientId = readString("Patient ID: ");
                    String doctorId = readString("Doctor ID: ");
                    LocalDateTime time = LocalDateTime.parse(readString("Appointment Time (yyyy-MM-dd HH:mm): "));

                    Patient patient = patientService.search(new EntityID(patientId));
                    Doctor doctor = doctorService.search(new EntityID(doctorId));

                    Appointment appointment =
                            new Appointment(null, patient, doctor, time, AppointmentStatus.CONFIRMED);


                    String confirm = readString("Confirm? (Y/N): ");
                    AppointmentStatus status = AppointmentStatus.PENDING;
                    switch (confirm.toUpperCase()) {
                        case Constants.CONFIRM_YES -> {
                            appointmentService.bookAppointment(appointment);
                        }
                        default -> {
                            System.out.println("Slot is blocked but please note that the appointment will be cancelled in 10 mins, if not confirmed");

                            appointmentService.createAppointment(appointment);
                        }
                    }
                    System.out.println("Appointment created.");
                    updateAppointmentCheck = true;
                }
                case 2 -> {
                    String id = readString("Enter ID: ");
                    List<Appointment>  appts = appointmentService.getAppointment(id);

                   displayAppointment(appts);

                }
                case 3 -> {

                    List<Appointment> appointments = appointmentService.getAllAppointments();

                    displayAppointment(appointments);
                }

                case 4 -> {


                    String id = readString("Appointment ID: ");

                    appointmentService.cancelAppointment(id);

                    System.out.println("Appointment cancelled.");
                    updateAppointmentCheck = true;
                }
                case 5 -> {
                    String id = readString("Appointment ID: ");
                    LocalDateTime time = LocalDateTime.parse(readString("Appointment Time (yyyy-MM-dd HH:mm): "));
                    String confirm = readString("Confirm? (Y/N): ");
                    AppointmentStatus status = AppointmentStatus.PENDING;
                    switch (confirm.toUpperCase()) {
                        case Constants.CONFIRM_YES -> {
                            appointmentService.updateAppointment(id, time, AppointmentStatus.CONFIRMED);
                        }
                        default -> {
                            System.out.println("Slot is blocked but please note that the appointment will be cancelled in 10 mins, if not confirmed");
                            List<Appointment> app = appointmentService.getAppointment(id);
                            if(app!=null && app.size()==1) {
                                app.get(0).setAppointmentTime(time);
                                appointmentService.createAppointment(app.get(0));
                            }
                        }
                    }


                    System.out.println("Appointment rescheduled.");
                    updateAppointmentCheck = true;
                }
                case 6 -> {
                    String id = readString("Appointment ID: ");
                    List<Appointment> app = appointmentService.getAppointment(id);
                    if(app!=null && app.size()==1) {
                        app.get(0).setStatus(AppointmentStatus.CONFIRMED);
                        appointmentService.updateAppointment(app.get(0));

                        System.out.println("Appointment Confirmed." + app.get(0).toString());
                    }
                    updateAppointmentCheck = true;
                }
                case 9 -> {
                    apptMenu = false;
                    checkUpdate(updateAppointmentCheck);
                    startMainMenu();
                }
                case 0 -> {
                    System.out.println("Exiting Meditrack System...");
                    checkUpdate(updateAppointmentCheck);
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }


    }


    private void checkUpdate(boolean update) {
        if (update) {
            System.out.println(" Persisting Changes to CSV");
            appointmentService.saveAppointments(Constants.APPOINTMENTS_CSV);
        }

    }

    private void displayAppointment(List<Appointment> appts){

        System.out.println("+---------+--------------+--------------+-------------------+-----------");
        System.out.println("| ID      |  PatientID   |  DoctorID    | Appointment Time  | Status");
        System.out.println("+---------+--------------+--------------+-------------------+-----------");
        for (Appointment d : appts) {
            System.out.printf("| %-5s | %-10s |  %-10s | %-21s | %-18s | %-15s \n",
                    d.getAppointmentId(),
                    d.getPatient().getId(),
                    d.getDoctor().getId(),
                    d.getAppointmentTime(),
                    d.getStatus().getValue());
        }

        System.out.println("+---------+--------------+--------------+-------------------+-----------");

    }


}
