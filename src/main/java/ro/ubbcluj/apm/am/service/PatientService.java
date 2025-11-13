package ro.ubbcluj.apm.am.service;

import lombok.RequiredArgsConstructor;
import ro.ubbcluj.apm.am.domain.Patient;
import ro.ubbcluj.apm.am.repository.patient.PatientRepository;
import ro.ubbcluj.apm.am.service.action.DeleteAction;
import ro.ubbcluj.apm.am.service.action.HistoryService;
import ro.ubbcluj.apm.am.service.action.UndoableService;

import java.util.Optional;

@RequiredArgsConstructor
public class PatientService implements UndoableService {
    private final PatientRepository patientRepository;
    private final AppointmentService appointmentService;
    private final HistoryService historyService;

    public void deleteById(Integer patientId) {
        appointmentService.deleteAppointmentsByPatientId(patientId);

        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            Patient deletedPatient = patient.get();
            patientRepository.deleteById(patientId);
            historyService.addAction(new DeleteAction<>(patientRepository, deletedPatient));
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
