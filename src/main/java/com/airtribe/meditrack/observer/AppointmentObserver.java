package com.airtribe.meditrack.observer;


import com.airtribe.meditrack.entity.Appointment;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public interface AppointmentObserver {

    void update(Appointment appointment);
}