package ro.ubbcluj.apm.am.service;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Appointment;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.domain.Patient;
import ro.ubbcluj.apm.am.repository.appointment.AppointmentRepository;
import ro.ubbcluj.apm.am.repository.doctor.DoctorRepository;
import ro.ubbcluj.apm.am.repository.patient.PatientRepository;
import ro.ubbcluj.apm.am.service.action.HistoryService;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class DoctorServiceTest {

    @Test
    void deleteById_withUndoAndRedo() {
        Doctor d1 = new Doctor(1, "John", "Doe", "Cardiology", 4.1);
        Doctor d2 = new Doctor(2, "Emily", "Johnson", "Neurology", 4.3);
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.add(d1);
        doctorRepository.add(d2);

        Patient p1 = new Patient(1, "Alice", "Smith", "132");
        Patient p2 = new Patient(2, "Bob", "Brown", "133");
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.add(p1);
        patientRepository.add(p2);

        AppointmentRepository appointmentRepository = new AppointmentRepository();
        appointmentRepository.add(new Appointment(1, d1, p1, null));
        appointmentRepository.add(new Appointment(2, d1, p2, null));
        appointmentRepository.add(new Appointment(3, d2, p1, null));

        DoctorService doctorService = new DoctorService(doctorRepository,
                new AppointmentService(appointmentRepository, new HistoryService()),
                new HistoryService());

        assertThat(doctorRepository.findAll()).hasSize(2);

        doctorService.deleteById(d1.getId());

        assertThat(doctorRepository.findAll()).hasSize(1);
        Collection<Appointment> remainingAppointments = appointmentRepository.findAll();
        assertThat(remainingAppointments).hasSize(1);
        assertThat(remainingAppointments.stream().findAny().map(Appointment::getDoctor)).get().isEqualTo(d2);

        doctorService.undo();

        assertThat(doctorRepository.findAll()).hasSize(2);
        assertThat(appointmentRepository.findAll()).hasSize(3);

        doctorService.redo();

        assertThat(doctorRepository.findAll()).hasSize(1);
        assertThat(appointmentRepository.findAll()).hasSize(1);
    }
}