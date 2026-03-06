package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.util.Validator;

public abstract class Person extends MedicalEntity {

    protected EntityID id;
    protected String name;
    protected String phone;
    protected String email;

    public Person(EntityID id, String name, String phone, String email) {
        super(id);
        Validator.validateName(name);
        Validator.validatePhone(phone);
        Validator.validateEmail(email);
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public EntityID getId() {
        return id;
    }

    public void setId(EntityID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Phone=" + phone + ", Email=" + email;
    }

}
