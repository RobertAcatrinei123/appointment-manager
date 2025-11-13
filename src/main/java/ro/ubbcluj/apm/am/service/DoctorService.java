package ro.ubbcluj.apm.am.service;

import lombok.RequiredArgsConstructor;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.doctor.DoctorRepository;
import ro.ubbcluj.apm.am.service.action.DeleteAction;
import ro.ubbcluj.apm.am.service.action.HistoryService;
import ro.ubbcluj.apm.am.service.action.UndoableService;

import java.util.Optional;

@RequiredArgsConstructor
public class DoctorService implements UndoableService {
    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;
    private final HistoryService historyService;

    public void deleteById(Integer doctorId) {
        appointmentService.deleteAppointmentsByDoctorId(doctorId);

        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isPresent()) {
            Doctor deletedDoctor = doctor.get();
            doctorRepository.deleteById(doctorId);
            historyService.addAction(new DeleteAction<>(doctorRepository, deletedDoctor));
        }
    }

    @Override
    public void undo() {
        historyService.undo();
        appointmentService.undo();
    }

    @Override
    public void redo() {
        historyService.redo();
        appointmentService.redo();
    }
}
