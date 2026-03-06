package com.airtribe.meditrack.menu;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.service.PatientService;

import java.util.List;

public class PatientMenu extends MainMenu {
    private final PatientService patientService =  PatientService.getInstance();

    public  void show() {

        boolean patientMenu = true;
        boolean updatePatientCsv = false;

        while (patientMenu) {

            System.out.println("\n===== Meditrack Clinic System - Patients =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Search By ID");
            System.out.println("3. Search By Name" );
            System.out.println("4. View All Patients");
            System.out.println("5. Update Patient Contact Details");
            System.out.println("6. Delete Patient");

            System.out.println("9. Main Menu");
            System.out.println("0. Exit");


            int choice =   readInt("Enter choice: ");
            switch (choice) {
                case 1 -> {

                    String name = readString("Name: ");
                    String phone = readString("Phone: ");
                    String email = readString("Email: ");
                    int age = readInt("Age: ");
                    String gender = readString("Gender (F/M/O): ");
                    Patient patient = new Patient(null, name, phone, email, age, gender); //ID will be generated only after validation passes

                    patientService.addPatient(patient);
                    updatePatientCsv = true;
                    System.out.println("Patient added successfully");
                }
                case 2 -> {
                    String pid = readString("Enter Patient ID : ");
                    Patient patient = patientService.search(new EntityID(pid));

                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");
                    System.out.println("| ID      |  Name                              |  Phone        | Email                  | Age | Gender  ");
                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");


                        System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-3s | %-5s \n",
                                patient.getId(),
                                patient.getName(),
                                patient.getPhone(),
                                patient.getEmail(),
                                patient.getAge(),
                                patient.getGender());


                    System.out.println("+----------+------------------------------------+---------------+--------------------+-----+-------");

                }
                case 3 -> {
                    String name = readString("Enter Patient Name : ");
                    List<Patient> patients = patientService.search(name);

                    System.out.println("Patients List : ");
                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");
                    System.out.println("| ID      |  Name                              |  Phone        | Email                  | Age | Gender  ");
                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");

                    for (Patient s : patients) {
                        System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-3s | %-5s \n",
                                s.getId(),
                                s.getName(),
                                s.getPhone(),
                                s.getEmail(),
                                s.getAge(),
                                s.getGender());
                    }

                    System.out.println("+----------+------------------------------------+---------------+--------------------+-----+-------");

                }
                case 4 -> {
                    List<Patient> patients = patientService.getAllPatients();

                    System.out.println("Patients List : ");
                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");
                    System.out.println("| ID      |  Name                              |  Phone        | Email                  | Age | Gender  ");
                    System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");

                    for (Patient s : patients) {
                        System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-3s | %-5s \n",
                                s.getId(),
                                s.getName(),
                                s.getPhone(),
                                s.getEmail(),
                                s.getAge(),
                                s.getGender());
                    }

                    System.out.println("+----------+------------------------------------+---------------+--------------------+-----+-------");

                }
                case 5 -> {
                    String pid = readString("Enter Patient ID : ");
                    String phnum = readString("Enter Phone Number:");
                    String email = readString("Enter Email IDr:");

                    patientService.updatePatient(pid, phnum, email);
                    System.out.println("Patients details have been updated successfully!");
                    updatePatientCsv = true;
                }
                case 6 -> {
                    String pid = readString("Enter Patient ID : ");
                    patientService.deletePatient(pid);
                    updatePatientCsv = true;
                    System.out.println("Patients details have been deleted successfully!");

                }
                case 9 -> {
                    patientMenu = false;
                    checkUpdate(updatePatientCsv);
                    startMainMenu();
                }
                case 0 -> {
                    System.out.println("Exiting Meditrack System...");
                    checkUpdate(updatePatientCsv);
                    return;
                }

                default -> {
                    System.out.println("Invalid option");
                }
            }

            readString("Press Enter to continue");

        }



    }



    private  void checkUpdate(boolean updatePatientCsv) {
        if(updatePatientCsv){
            System.out.println(" Persisting Changes to CSV");
            patientService.savePatients(Constants.PATIENTS_CSV);
        }

    }



}
