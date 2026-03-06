package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.observer.AppointmentObserver;
import com.airtribe.meditrack.observer.AppointmentSubject;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Appointment implements AppointmentSubject, Cloneable {

    PatientService patientService = PatientService.getInstance();
    DoctorService doctorService = DoctorService.getInstance();

    private EntityID appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;

    private List<AppointmentObserver> observers = new ArrayList<>();


    public Appointment(EntityID appointmentId,
                       Patient patient,
                       Doctor doctor,
                       LocalDateTime appointmentTime, AppointmentStatus status) {

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Appointment(EntityID appointmentId,
                       EntityID patientId,
                       EntityID doctorId,
                       LocalDateTime appointmentTime, AppointmentStatus status) {

        this.appointmentId = appointmentId;
        this.patient = patientService.search(patientId);
        this.doctor = doctorService.search(doctorId);
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patient=" + patient.getId() +
                ", doctor=" + doctor.getId() +
                ", appointmentTime=" + appointmentTime +
                ", status=" + status +
                '}';
    }

    public EntityID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(EntityID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }


    public void confirm() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    @Override
    public Appointment clone() {

        try {

            Appointment cloned = (Appointment) super.clone();

            // Deep copy nested objects
            cloned.patient = patient.clone();
            cloned.doctor = doctor.clone();


            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AppointmentObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (AppointmentObserver o : observers) {
            o.update(this);
        }
    }
}