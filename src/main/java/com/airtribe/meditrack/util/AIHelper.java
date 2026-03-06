package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIHelper {

    private static final Map<String, Specialization> symptomRules = new HashMap<>();

    static {

        symptomRules.put("chest pain", Specialization.CARDIOLOGY);
        symptomRules.put("heart", Specialization.CARDIOLOGY);
        symptomRules.put("headache", Specialization.NEUROLOGY);
        symptomRules.put("backpain", Specialization.NEUROLOGY);
        symptomRules.put("brain", Specialization.NEUROLOGY);
        symptomRules.put("skin", Specialization.DERMATOLOGY);
        symptomRules.put("rash", Specialization.DERMATOLOGY);
        symptomRules.put("child", Specialization.PEDIATRICS);
        symptomRules.put("bone", Specialization.ORTHOPEDICS);
        symptomRules.put("flu", Specialization.GENERAL_MEDICINE);
    }

    AppointmentService appointmentService = AppointmentService.getInstance();

    public static Specialization recommendSpecialization(String symptom) {

        symptom = symptom.toLowerCase();

        for (Map.Entry<String, Specialization> entry : symptomRules.entrySet()) {

            if (symptom.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return Specialization.GENERAL_MEDICINE;
    }


    public static List<LocalDateTime> suggestSlots(LocalDate date) {

        List<LocalDateTime> slots = new ArrayList<>();

        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(20, 0);

        while (!start.isAfter(end)) {

            slots.add(LocalDateTime.of(date, start));

            start = start.plusMinutes(30);
        }

        return slots;
    }


}