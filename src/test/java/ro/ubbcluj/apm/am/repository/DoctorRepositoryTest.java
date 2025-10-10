package ro.ubbcluj.apm.am.repository;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.List;

public class DoctorRepositoryTest {

    @Test
    void getDoctorById_success() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.addDoctor(new Doctor(1, "a", "orl", "Cluj", 0d));
        Doctor doctor = doctorRepository.getDoctorById(1);

        assert doctor != null;
    }

    @Test
    void filterDoctors_withSpecialityFiltering() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.addDoctor(new Doctor(1, "d1", "orl", "Cluj", 0d));
        doctorRepository.addDoctor(new Doctor(2, "d2", "orthopedia", "Cluj", 0d));
        List<Doctor> filterDoctors = doctorRepository.filterDoctors(new SpecialityFilter("orthopedia"));

        assert filterDoctors != null;
        assert filterDoctors.size() == 1;
        assert filterDoctors.getFirst().getId() == 2;
    }

    @Test
    void filterDoctors_withLocationFiltering() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.addDoctor(new Doctor(1, "d1", "orl", "Cluj", 0d));
        doctorRepository.addDoctor(new Doctor(2, "d2", "orthopedia", null, 0d));
        List<Doctor> filterDoctors = doctorRepository.filterDoctors(new LocationFilter(null));

        assert filterDoctors != null;
        assert filterDoctors.size() == 1;
        assert filterDoctors.getFirst().getId() == 2;
    }
}
