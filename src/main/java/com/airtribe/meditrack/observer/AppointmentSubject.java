package com.airtribe.meditrack.observer;

/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public interface AppointmentSubject {

    void registerObserver(AppointmentObserver observer);

    void removeObserver(AppointmentObserver observer);

    void notifyObservers();
}