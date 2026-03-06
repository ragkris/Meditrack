package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DoctorService implements Searchable<Doctor> {

    private static DoctorService instance;
    private final DataStore<Doctor> doctorStore;
    private final AppointmentService appointmentService = AppointmentService.getInstance();

    private DoctorService(DataStore<Doctor> store) {
        this.doctorStore = store;
    }


    public static DoctorService getInstance() {
        if (instance == null) {
            DataStore<Doctor> store = new DataStore<>();
            instance = new DoctorService(store);
        }
        return instance;
    }


    // CREATE
    public void addDoctor(Doctor doctor) {
        doctor.setId(IdGenerator.generateDoctorId());
        doctorStore.add(doctor.getId().getValue(), doctor);
    }

    // READ

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorStore.getAll());
    }

    @Override
    // Search by DoctorID
    public Doctor search(EntityID id) {
        System.out.println(id.getValue());
        System.out.println(doctorStore.getAll());
        return doctorStore.get(id.getValue());
    }

    @Override
    // Search by Specialization
    public List<Doctor> search(String specInput) {


        List<Doctor> result = new ArrayList<>();

        for (Doctor d : doctorStore.getAll()) {
            if (d.getSpecialization().getValue().equalsIgnoreCase(specInput)) {
                result.add(d);
            }
        }

        return result;
    }

    // Search by Specialization
    public List<Doctor> search(Specialization specialization) {


        List<Doctor> result = new ArrayList<>();

        for (Doctor d : doctorStore.getAll()) {
            if (d.getSpecialization() == (specialization)) {
                result.add(d);
            }
        }

        return result;
    }


    @Override
    public List<Doctor> search(int keyword) {
        return null;
    }

    @Override
    public void printSearchHeader() {
        Searchable.super.printSearchHeader();
    }


    // UPDATE
    public void updateDoctor(String id, String phone, String email) {

        Doctor doctor = doctorStore.get(id);

        if (doctor != null) {
            doctor.setPhone(phone);
            doctor.setEmail(email);
        }
    }

    // DELETE
    public void deleteDoctor(String id) {
        doctorStore.remove(id);

        appointmentService.removeAppointment(id);
    }


    public void saveDoctors(String filePath) {

        List<String> lines = doctorStore.getAll()
                .stream()
                .sorted(Comparator.comparing(d -> d.getId().toString()))
                .map(d -> d.getId() + "," +
                        d.getName() + "," +
                        d.getPhone() + "," +
                        d.getEmail() + "," +
                        d.getSpecialization() + "," +
                        d.getConsultationFee()).toList();


        CSVUtil.writeCSV(filePath, lines, Constants.DOCTORS_HEADER);
    }

    public void loadDoctors(String filePath) {

        List<String[]> rows = CSVUtil.readCSV(filePath);

        for (String[] data : rows) {

            Doctor d = new Doctor(
                    null,
                    data[1],
                    data[2],
                    data[3],
                    Specialization.getSpecialization(data[4]),
                    Double.parseDouble(data[5])
            );
            d.setId(new EntityID(data[0]));
            doctorStore.add(d.getId().getValue(), d);
        }
    }
}