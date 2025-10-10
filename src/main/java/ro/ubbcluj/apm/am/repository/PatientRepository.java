package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Patient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatientRepository implements Iterable<Patient> {
    private final List<Patient> patients;

    public PatientRepository() {
        patients = new ArrayList<>();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient getPatient(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(int id) {
        patients.removeIf(patient -> patient.getId() == id);
    }

    public void updatePatient(Patient updatedPatient) {
        for (Patient patient : patients) {
            if (patient.getId() == updatedPatient.getId()) {
                patient.setName(updatedPatient.getName());
                patient.setAddress(updatedPatient.getAddress());
                patient.setPhoneNumber(updatedPatient.getPhoneNumber());
                return;
            }
        }
    }

    @Override
    public Iterator<Patient> iterator() {
        return patients.iterator();
    }
}
