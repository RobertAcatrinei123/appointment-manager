package ro.ubbcluj.apm.am.repository.doctor;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqliteDoctorRepositoryTest {

    @Test
    void findAll_withSuccess() {
        SqliteDoctorRepository repository = new SqliteDoctorRepository();

        var doctors = repository.findAll();

        assertEquals(0, doctors.size());
    }

    @Test
    void addDoctor_withSuccess() {
        SqliteDoctorRepository repository = new SqliteDoctorRepository();

        var doctors = repository.findAll();

        assertEquals(0, doctors.size());

        Doctor newDoctor = new Doctor(10, "Dr. Test Doctor", "test specialty", "test location", 100.0);
        repository.add(newDoctor);
        doctors = repository.findAll();

        assertEquals(1, doctors.size());
        List<Doctor> list = new ArrayList<>(doctors);
        assertEquals("Dr. Test Doctor", list.getFirst().getName());
    }
}