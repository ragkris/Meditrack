package com.airtribe.meditrack;


import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.menu.MainMenu;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.text.ParseException;

public class Main {


    public static void main(String[] args) throws ParseException {

        MainMenu menu = new MainMenu();
        if (args.length > 0 && args[0].equals("--loadData")) {

            System.out.println("Loading persisted data...");

            PatientService.getInstance().loadPatients(Constants.PATIENTS_CSV);
            DoctorService.getInstance().loadDoctors(Constants.DOCTORS_CSV);
            AppointmentService.getInstance().loadAppointments(Constants.APPOINTMENTS_CSV);
        }

        System.out.println("Meditrack system started.");

        menu.startMainMenu();
    }

}