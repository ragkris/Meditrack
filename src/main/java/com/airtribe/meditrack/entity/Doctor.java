package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.id.EntityID;

public class Doctor extends Person implements Cloneable {

    private Specialization specialization;
    private double consultationFee;

    public Doctor(EntityID id, String name, String phone, String email,
                  Specialization specialization, double consultationFee) {
        super(id, name, phone, email);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return "Doctor { " + super.toString() +
                ", Specialization=" + specialization +
                ", Fee=" + consultationFee + " }";
    }

    public void displayDoctor() {

        System.out.println("Doctor ID: " + getId());
        System.out.println("Name: " + name);  // protected variable
        System.out.println("Specialization: " + specialization);
        System.out.println("Fee: " + consultationFee);
    }

    @Override
    public Doctor clone() {

        try {
            return (Doctor) super.clone();   // shallow copy is fine here
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}