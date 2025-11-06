package ro.ubbcluj.apm.am.service;

import ro.ubbcluj.apm.am.domain.Appointment;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppointmentService {
    private List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getDoctorAppointments(int doctorId, ZonedDateTime dateTime) {
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(doctorId) &&
                        Objects.equals(appointment.getDateTime().getYear(), dateTime.getYear()) &&
                        Objects.equals(appointment.getDateTime().getMonth(), dateTime.getMonth()) &&
                        Objects.equals(appointment.getDateTime().getDayOfMonth(), dateTime.getDayOfMonth()))
                .sorted(Comparator.comparing(Appointment::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public List<String> getPatientsNames(int doctorId, ZonedDateTime dateTime) {
        return getDoctorAppointments(doctorId, dateTime).stream()
                .map(appointment -> appointment.getPatient().getName())
                .distinct()
                .toList();
    }

    public List<Appointment> getPatientAppointments(int patientId) {
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .toList();
    }

    public List<Double> getPatientDoctorsGrades(int patientId) {
        return appointments.stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .map(Appointment::getDoctor)
                .distinct()
                .map(Doctor::getGrade)
                .toList();
    }


}
