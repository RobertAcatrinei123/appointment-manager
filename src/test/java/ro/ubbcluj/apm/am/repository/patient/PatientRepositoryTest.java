package ro.ubbcluj.apm.am.repository.patient;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Patient;

import java.util.Iterator;

class PatientRepositoryTest {

    @SuppressWarnings("AssertWithSideEffects")
    @Test
    void getPatients_withSuccess() {
        Patient p1 = new Patient(1, "John", "Cluj", null);
        Patient p2 = new Patient(2, "Anna", "Brasov", null);
        Patient p3 = new Patient(1, "John", "Cluj", null);

        PatientRepository patientRepository = new PatientRepository();
        patientRepository.add(p1);
        patientRepository.add(p2);
        patientRepository.add(p3);
        Iterator<Patient> iterator = patientRepository.findAll().iterator();

        assert iterator.hasNext();
        assert iterator.next().getId() == 1;
        assert iterator.hasNext();
        assert iterator.next().getId() == 2;
        assert !iterator.hasNext();
    }
}