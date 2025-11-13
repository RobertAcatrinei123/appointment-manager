package ro.ubbcluj.apm.am.service;

import lombok.RequiredArgsConstructor;
import ro.ubbcluj.apm.am.domain.Appointment;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.appointment.AppointmentRepository;
import ro.ubbcluj.apm.am.service.action.DeleteAction;
import ro.ubbcluj.apm.am.service.action.HistoryService;
import ro.ubbcluj.apm.am.service.action.MultipleDeleteAction;
import ro.ubbcluj.apm.am.service.action.UndoableService;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AppointmentService implements UndoableService {
    private final AppointmentRepository appointmentRepository;
    private final HistoryService historyService;

    public List<Appointment> getDoctorAppointments(int doctorId, ZonedDateTime dateTime) {
        return appointmentRepository.findAll().stream()
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
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .toList();
    }

    public List<Double> getPatientDoctorsGrades(int patientId) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .map(Appointment::getDoctor)
                .distinct()
                .map(Doctor::getGrade)
                .toList();
    }

    public void deleteById(Integer appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment deletedAppointment = optionalAppointment.get();
            appointmentRepository.deleteById(appointmentId);
            historyService.addAction(new DeleteAction<>(appointmentRepository, deletedAppointment));
        }
    }

    public void deleteAppointmentsByDoctorId(Integer doctorId) {
        List<Appointment> appointmentsToDelete = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(doctorId))
                .toList();
        delete(appointmentsToDelete);
    }

    public void deleteAppointmentsByPatientId(Integer patientId) {
        List<Appointment> appointmentsToDelete = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .toList();
        delete(appointmentsToDelete);
    }

    public void delete(List<Appointment> appointments) {
        appointments.forEach(appointment -> appointmentRepository.deleteById(appointment.getId()));
        historyService.addAction(new MultipleDeleteAction<>(appointmentRepository, appointments));
    }

    @Override
    public void undo() {
        historyService.undo();
    }

    @Override
    public void redo() {
        historyService.redo();
    }
}
