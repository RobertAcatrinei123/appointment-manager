package ro.ubbcluj.apm.am.repository.doctor;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileDoctorRepositoryTest {
    @Test
    void findAll_withSuccess() throws IOException {
        JsonFileDoctorRepository repository = new JsonFileDoctorRepository();

        var doctors = repository.findAll();

        assertEquals(5, doctors.size());
        List<Doctor> list = new ArrayList<>(doctors);
        assertEquals("Dr. David Brown", list.get(3).getName());
    }
}