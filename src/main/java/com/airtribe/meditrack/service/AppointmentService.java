package com.airtribe.meditrack.service;


import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.observer.AppointmentExpiryObserver;
import com.airtribe.meditrack.observer.AppointmentObserver;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppointmentService {


    private static AppointmentService instance;
    private final DataStore<Appointment> appointmentStore;

    private AppointmentService(DataStore<Appointment> store) {
        this.appointmentStore = store;
    }


    public static AppointmentService getInstance() {
        if (instance == null) {
            DataStore<Appointment> store = new DataStore<>();
            instance = new AppointmentService(store);
        }
        return instance;
    }


    //BOOK appointment
    public Appointment bookAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() == null) appointment.setAppointmentId(IdGenerator.generateAppointmentId());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentStore.add(appointment.getAppointmentId().getValue(), appointment);
        return appointment;

    }

    //BLOCK Appointment
    public void createAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() == null) appointment.setAppointmentId(IdGenerator.generateAppointmentId());
        appointment.setStatus(AppointmentStatus.PENDING);
        AppointmentObserver expiryObserver =
                new AppointmentExpiryObserver();

        appointment.registerObserver(expiryObserver);

        appointment.notifyObservers();

        appointmentStore.add(
                appointment.getAppointmentId().getValue(),
                appointment
        );
    }

    //VIEW appointment by id
    public List<Appointment>  getAppointment(String id) {
        String entity = id.startsWith("DOC-") ? "DOCTOR" : id.startsWith("PAT-") ? "PATIENT" : "APPOINTMENT";
        List<Appointment> list = null;
        switch (entity) {
            case "DOCTOR" -> {
                list = getAllAppointmentsByDocId(id);

            }
            case "PATIENT" -> {
                list = getAllAppointmentsByPatientId(id);
            }
            case "APPOINTMENT" -> {
                list = new ArrayList<>();
                list.add(appointmentStore.get(id));
            }
        }

        if (list == null || list.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment not found");
        }

        return list;
    }

    //VIEW all appointments
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointmentStore.getAll());
    }

    //VIEW all appointments
    public List<Appointment> getAllAppointmentsByDocId(String id) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentStore.getAll()) {
            try {
//                System.out.println(a);
                if (a.getDoctor().getId().getValue().equalsIgnoreCase(id)) {
                    result.add(a);
                }
            } catch (Exception e) {
                System.out.println("Doctor  assigned to " + a.getAppointmentId() + " has been deleted");

            }
        }
        return result;

    }

    public List<Appointment> getAllAppointmentsByPatientId(String id) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentStore.getAll()) {
            try {
//                System.out.println(a);
                if (a.getPatient().getId().getValue().equalsIgnoreCase(id)) {
                    result.add(a);
                }
            } catch (Exception e) {
                System.out.println("Patient  assigned to " + a.getAppointmentId() + " has been deleted");
            }
        }
        return result;

    }


    //CANCEL appointment
    public void cancelAppointment(String id) {

        Appointment appt = getAppointment(id);
        appt.setStatus(AppointmentStatus.CANCELLED);

    }

    public boolean removeAppointment(String id) {
        boolean updated = false;
        String person = id.startsWith("DOC-") ? "DOCTOR" : id.startsWith("PAT-") ? "PATIENT" : "";
        List<Appointment> rm = null;
        switch (person) {
            case "DOCTOR" -> {
                rm = getAllAppointmentsByDocId(id);

            }
            case "PATIENT" -> {
                rm = getAllAppointmentsByPatientId(id);
            }
        }
        if (rm != null)
            for (Appointment r : rm) {
                appointmentStore.remove(r.getAppointmentId().getValue());
                updated = true;
            }
        return updated;


    }


    public void updateAppointment(String appointmentId, LocalDateTime newTime, AppointmentStatus newStatus) {

        Appointment appointment = appointmentStore.get(appointmentId);

        if (appointment == null) {
            throw new AppointmentNotFoundException(
                    "Appointment not found: " + appointmentId
            );
        }

        if (newTime != null) {
            appointment.setAppointmentTime(newTime);
        }

        if (newStatus != null) {
            appointment.setStatus(newStatus);
        }
    }

    public void updateAppointment(Appointment app) {

        appointmentStore.add(app.getAppointmentId().getValue(), app);

    }

    public void loadAppointments(String filePath) throws ParseException {

        List<String[]> rows = CSVUtil.readCSV(filePath);

        for (String[] data : rows) {

            Appointment a = new Appointment(
                    new EntityID(data[0]),
                    new EntityID(data[1]),
                    new EntityID(data[2]),
                    DateUtil.getLocalDate(data[3]),
                    AppointmentStatus.getStatus(data[4])
            );

            appointmentStore.add(a.getAppointmentId().getValue(), a);
        }
    }

    public void saveAppointments(String filePath) {


        List<String> lines = appointmentStore.getAll()
                .stream()
                .sorted(Comparator.comparing(d -> d.getAppointmentId().toString()))
                .map(a -> a.getAppointmentId() + "," +
                        a.getPatient().getId() + "," +
                        a.getDoctor().getId() + "," +
                        DateUtil.getLocalDateStr(a.getAppointmentTime()) + "," +
                        a.getStatus()).toList();


        CSVUtil.writeCSV(filePath, lines, Constants.APPOINTMENTS_HEADER);
    }

}