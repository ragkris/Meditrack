package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String GENDER_REGEX = "^[oOfFmM]{1,}$";

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidDataException("Invalid phone number");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.length() > 254 || !email.matches(EMAIL_REGEX)) {
            throw new InvalidDataException("Invalid email");
        }
    }

    public static void validateName(String name) {

        if (name == null || name.trim().isEmpty() || name.length() > 50) {
            throw new InvalidDataException("Name cannot be empty");
        }
    }

    public static void validateGender(String gender) {

        if (gender == null || gender.trim().isEmpty() || gender.length() > 1 || !gender.matches(GENDER_REGEX)) {
            throw new InvalidDataException("Invalid Gender");
        }
    }


}