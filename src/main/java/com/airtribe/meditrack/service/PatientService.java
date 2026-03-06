package com.airtribe.meditrack.service;


import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PatientService implements Searchable<Patient> {

    private static PatientService instance;
    private final AppointmentService appointmentService = AppointmentService.getInstance();
    private final DataStore<Patient> patientStore;


    private PatientService(DataStore<Patient> store) {
        this.patientStore = store;
    }


    public static PatientService getInstance() {
        if (instance == null) {
            DataStore<Patient> store = new DataStore<>();
            instance = new PatientService(store);
        }
        return instance;
    }


    // CREATE
    public void addPatient(Patient patient) {
        EntityID id = IdGenerator.generatePatientId();
        patient.setId(id);
        patientStore.add(id.getValue(), patient);
    }

    // READ
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientStore.getAll());
    }

    @Override
    // Search by PatientId
    public Patient search(EntityID id) {
        return patientStore.get(id.getValue());
    }

    @Override

    // Search by Name
    public List<Patient> search(String name) {

        List<Patient> result = new ArrayList<>();

        for (Patient p : patientStore.getAll()) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }

        return result;
    }

    @Override
    // Search by Age
    public List<Patient> search(int age) {

        List<Patient> result = new ArrayList<>();

        for (Patient p : patientStore.getAll()) {
            if (p.getAge() == age) {
                result.add(p);
            }
        }

        return result;
    }

    // UPDATE
    public void updatePatient(String id, String phone, String email) {

        Patient patient = patientStore.get(id);

        if (patient != null) {
            patient.setPhone(phone);
            patient.setEmail(email);
        }
    }

    // DELETE
    public void deletePatient(String id) {
        patientStore.remove(id);
        appointmentService.removeAppointment(id);

    }


    public void savePatients(String filePath) {

        List<String> lines = patientStore.getAll()
                .stream()
                .sorted(Comparator.comparing(p -> p.getId().toString()))
                .map(p -> p.getId() + "," +
                        p.getName() + "," +
                        p.getPhone() + "," +
                        p.getEmail() + "," +
                        p.getAge() + "," +
                        p.getGender()).toList();


        CSVUtil.writeCSV(filePath, lines, Constants.PATIENTS_HEADER);
    }


    public void loadPatients(String filePath) {

        List<String[]> rows = CSVUtil.readCSV(filePath);

        for (String[] data : rows) {

            Patient patient = new Patient(
                    null,
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    data[5]
            );
            patient.setId(new EntityID(data[0]));
            patientStore.add(patient.getId().getValue(), patient);

        }
        System.out.println(patientStore.getAll().size());
    }
}