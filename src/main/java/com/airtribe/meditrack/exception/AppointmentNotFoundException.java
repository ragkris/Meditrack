package com.airtribe.meditrack.exception;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}