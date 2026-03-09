package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;

import java.util.List;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public class DisplayUtil {


    public static void displayDoctor(List<Doctor> doctors){

        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");
        System.out.println("| ID      |  Name                               |  Phone        | Email                  | Specialization     | Consultation Fee  ");
        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");

        for (Doctor d : doctors) {
            System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-18s | %-15s \n",
                    d.getId(),
                    d.getName(),
                    d.getPhone(),
                    d.getEmail(),
                    d.getSpecialization(),
                    d.getConsultationFee());
        }
        System.out.println("+---------+-------------------------------------+---------------+------------------------+--------------------+-------------------");


    }


    public static void displayPatient(List<Patient> patients){
        System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");
        System.out.println("| ID      |  Name                              |  Phone        | Email                  | Age | Gender  ");
        System.out.println("+---------+------------------------------------+---------------+------------------------+-----+-------");

        for (Patient s : patients) {
            System.out.printf("| %-5s | %-35s |  %-12s | %-22s | %-3s | %-5s \n",
                    s.getId(),
                    s.getName(),
                    s.getPhone(),
                    s.getEmail(),
                    s.getAge(),
                    s.getGender());
        }

        System.out.println("+----------+------------------------------------+---------------+--------------------+-----+-------");


    }

    public static void displayAppointment(List<Appointment> appts){

        System.out.println("+---------+--------------+--------------+-------------------+-----------");
        System.out.println("| ID      |  PatientID   |  DoctorID    | Appointment Time  | Status");
        System.out.println("+---------+--------------+--------------+-------------------+-----------");
        for (Appointment d : appts) {
            System.out.printf("| %-5s | %-10s |  %-10s | %-21s | %-15s \n",
                    d.getAppointmentId(),
                    d.getPatient().getId(),
                    d.getDoctor().getId(),
                    d.getAppointmentTime(),
                    d.getStatus().getValue());
        }

        System.out.println("+---------+--------------+--------------+-------------------+-----------");

    }

}
