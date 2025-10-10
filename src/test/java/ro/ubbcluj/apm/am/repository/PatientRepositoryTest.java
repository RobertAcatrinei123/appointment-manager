package ro.ubbcluj.apm.am.repository;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Patient;

import java.util.Iterator;

class PatientRepositoryTest {

    @Test
    void getPatients_withSuccess() {
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.addPatient(new Patient(1, "b", "a", "0771234556"));
        Iterator<Patient> iterator = patientRepository.iterator();

        assert iterator.hasNext();
        iterator.next();
        assert !iterator.hasNext();
    }
}