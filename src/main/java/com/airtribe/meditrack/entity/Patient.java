package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.entity.id.EntityID;

public class Patient extends Person implements Cloneable {


    private int age;
    private String gender;

    public Patient(EntityID id, String name, String phone, String email,
                   int age, String gender) {
        super(id, name, phone, email);
        this.age = age;
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patient { " + super.toString() +
                ", Age=" + age +
                ", Gender=" + gender + " }";
    }

    public void displayPatient() {

        System.out.println("Patient ID: " + getId());
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }

    @Override
    public Patient clone() {

        try {
            return (Patient) super.clone();   // shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}