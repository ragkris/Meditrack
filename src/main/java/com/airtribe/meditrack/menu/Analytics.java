package com.airtribe.meditrack.menu;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public class Analytics extends MainMenu {

    private final DoctorService doctorService = DoctorService.getInstance();

    private final AppointmentService appointmentService = AppointmentService.getInstance();


    public void view() {
        System.out.println("-----------------------------  Meditrack Clinics Analytics  ------------------------------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Average Doctor's Consultation Fee : " + getAverageConsultationFee());
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Number of Appointments By Doctor: ");
        Map<Doctor, Long> appts = getAppointmentsPerDoctor();
        for (Map.Entry<Doctor, Long> e : appts.entrySet()) {
            System.out.printf(" %-15s        %-5s", e.getKey().getId().value(), e.getValue());
            IntStream.range(0, 10).forEach(i -> System.out.print(" "));
            LongStream.range(0, e.getValue()).forEach(i -> System.out.print("*"));

            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");

        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------------------------------");
        List<Map.Entry<Doctor, Long>> doc = getMostBusyDoctors();
        System.out.println("Most Busiest Doctor(s)  with " + doc.get(0).getValue() + " appointments :");
        doc.forEach(e -> {
            Doctor d = e.getKey();
            System.out.printf(" %-15s  %-20s   %-15s ", d.getId(), d.getName(), d.getSpecialization());
            System.out.println();
        });
        System.out.println("-----------------------------------------------------------------");
        startMainMenu();
    }

    public double getAverageConsultationFee() {

        return doctorService.getAllDoctors().stream().mapToDouble(Doctor::getConsultationFee).average().orElse(0.0);
    }

    public Map<Doctor, Long> getAppointmentsPerDoctor() {

        return appointmentService.getAllAppointments().stream().collect(Collectors.groupingBy(Appointment::getDoctor, Collectors.counting()));
    }

    public List<Map.Entry<Doctor, Long>> getMostBusyDoctors() {

        Map<Doctor, Long> doctorAppointments = appointmentService.getAllAppointments().stream().collect(Collectors.groupingBy(Appointment::getDoctor, Collectors.counting()));

        long maxAppointments = doctorAppointments.values().stream().mapToLong(Long::longValue).max().orElse(0);

        return doctorAppointments.entrySet().stream().filter(e -> e.getValue() == maxAppointments).toList();
    }

}
