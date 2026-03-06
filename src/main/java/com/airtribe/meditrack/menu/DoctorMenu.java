package com.airtribe.meditrack.menu;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.DoctorService;

import java.util.List;

public class DoctorMenu extends MainMenu {
    private final static DoctorService doctorService = DoctorService.getInstance();


    public void show() {

        boolean doctorMenu = true;
        boolean updateDoctorCheck = false;
        while (doctorMenu) {
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Update Doctor Particulars");
            System.out.println("5. Delete Doctor");
            System.out.println("9. Main Menu");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> {

                    String name = readString("Name: ");
                    String phone = readString("Phone: ");
                    String email = readString("Email: ");

                    for (Specialization s : Specialization.values()) {
                        System.out.println(s);
                    }
                    String specInput = readString("Select Specialization:");
                    Specialization specialization = Specialization.valueOf(specInput.toUpperCase());

                    double fee = readDouble("Consultation Fee: ");
                    Doctor doctor = new Doctor(null, name, phone, email, specialization, fee);

                    doctorService.addDoctor(doctor);

                    System.out.println("Doctor added successfully");
                    updateDoctorCheck = true;
                }
                case 2 -> {
                    List<Doctor> doctors = doctorService.getAllDoctors();
                    displayDoctor(doctors);
                }

                case 3 -> {
                    for (Specialization s : Specialization.values()) {
                        System.out.println(s);
                    }
                    String specInput = readString("Select Specialization:");
                    Specialization specialization = Specialization.valueOf(specInput.toUpperCase());

                    List<Doctor> doctors = doctorService.search(specInput);

                    displayDoctor(doctors);

                }
                case 4 -> {
                    String pid = readString("Enter Doctor ID : ");
                    String phnum = readString("Enter Phone Number:");
                    String email = readString("Enter Email IDr:");

                    doctorService.updateDoctor(pid, phnum, email);
                    updateDoctorCheck = true;
                    System.out.println("Doctors details have been updated.");
                }
                case 5 -> {
                    String doc = readString("Enter Doctor ID : ");
                    doctorService.deleteDoctor(doc);
                    updateDoctorCheck = true;
                    System.out.println("Doctors details have been deleted.");
                }
                case 9 -> {
                    doctorMenu = false;
                    checkUpdate(updateDoctorCheck);
                    startMainMenu();
                }
                case 0 -> {
                    System.out.println("Exiting Meditrack System...");
                    checkUpdate(updateDoctorCheck);
                    return;
                }

                default -> System.out.println("Invalid option");
            }
            readString("Press ENTER to continue");
        }


    }


    private void checkUpdate(boolean update) {
        if (update) {
            System.out.println(" Persisting Changes to CSV");
            doctorService.saveDoctors(Constants.DOCTORS_CSV);
        }

    }


    private void displayDoctor(List<Doctor> doctors){

        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");
        System.out.println("| ID      |  Name                               |  Phone        | Email                  | Specialization     | Consultation Fee  ");
        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");

        for (Doctor d : doctors) {
            System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-18s | %-15s \n",
                    d.getId(),
                    d.getName(),
                    d.getPhone(),
                    d.getEmail(),
                    d.getSpecialization(),
                    d.getConsultationFee());
        }
        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");


    }

}
