package com.airtribe.meditrack.observer;


import com.airtribe.meditrack.entity.Appointment;

public interface AppointmentObserver {

    void update(Appointment appointment);
}