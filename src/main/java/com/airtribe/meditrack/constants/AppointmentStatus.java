package com.airtribe.meditrack.constants;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public enum AppointmentStatus {

    PENDING("pending"),
    CONFIRMED("confirmed"),
    CANCELLED("cancelled");

    private final String value;

    AppointmentStatus(String value) {
        this.value = value;
    }

    public static AppointmentStatus getStatus(String value) {

        for (AppointmentStatus spl : AppointmentStatus.values()) {

            if (spl.name().equalsIgnoreCase(value.toLowerCase())) {
                return spl;
            }
        }

        throw new IllegalArgumentException(
                "Invalid AppointmentStatus: " + value
        );
    }

    public String getValue() {
        return value;
    }
}