package com.airtribe.meditrack.observer;


import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.entity.Appointment;

import java.util.Timer;
import java.util.TimerTask;

public class AppointmentExpiryObserver implements AppointmentObserver {

    @Override
    public void update(Appointment appointment) {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                if (appointment.getStatus() == AppointmentStatus.PENDING) {

                    appointment.cancel();

                    System.out.println(
                            "Appointment " +
                                    appointment.getAppointmentId() +
                                    " expired and cancelled."
                    );
                }

            }

        }, 5 * 60 * 1000); // 30 minutes
    }
}