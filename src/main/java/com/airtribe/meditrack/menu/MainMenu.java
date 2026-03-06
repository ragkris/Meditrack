package com.airtribe.meditrack.menu;

import com.airtribe.meditrack.service.AIAppointmentBooking;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public void startMainMenu() {
        int choice;

        PatientMenu patientMenu = new PatientMenu();
        DoctorMenu doctorMenu = new DoctorMenu();
        AppointmentMenu appointmentMenu = new AppointmentMenu();
        AIAppointmentBooking ai = new AIAppointmentBooking();


        printMainMenu();

        choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> patientMenu.show();
            case 2 -> doctorMenu.show();
            case 3 -> appointmentMenu.show();
            case 4 -> ai.help();
            case 0 -> System.out.println("Exiting...");

            default -> System.out.println("Invalid choice. Please try again.");
        }

    }


    private void printMainMenu() {

        System.out.println("\n===== Meditrack Clinic System =====");

        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment Management");
        System.out.println("4. AI - Recommendations by Symptoms");
        System.out.println("0. Exit");


    }


    protected int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    protected double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    protected String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

}
