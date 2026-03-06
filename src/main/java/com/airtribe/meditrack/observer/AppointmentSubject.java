package com.airtribe.meditrack.observer;


public interface AppointmentSubject {

    void registerObserver(AppointmentObserver observer);

    void removeObserver(AppointmentObserver observer);

    void notifyObservers();
}