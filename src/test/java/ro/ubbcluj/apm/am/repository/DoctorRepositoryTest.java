package ro.ubbcluj.apm.am.repository;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.doctor.DoctorRepository;
import ro.ubbcluj.apm.am.repository.filter.DoctorFilter;
import ro.ubbcluj.apm.am.repository.filter.FilterFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DoctorRepositoryTest {

    @Test
    void getDoctorById_success() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.add(new Doctor(1, "a", "orl", "Cluj", 0d));
        Optional<Doctor> optionalDoctor = doctorRepository.findById(1);

        assert optionalDoctor.isPresent();
    }

    @Test
    void findAll_withSpecialityFiltering() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.add(new Doctor(1, "d1", "orl", "Cluj", 0d));
        doctorRepository.add(new Doctor(2, "d2", "orthopedia", "Cluj", 0d));
        DoctorFilter doctorFilter = FilterFactory.buildFilter("speciality", "orthopedia");
        List<Doctor> filterDoctors = doctorRepository.findAll(doctorFilter);

        assert filterDoctors != null;
        assert filterDoctors.size() == 1;
        assert filterDoctors.getFirst().getId() == 2;
    }

    @Test
    void findAll_withLocationFiltering() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.add(new Doctor(1, "d1", "orl", "Cluj", 0d));
        doctorRepository.add(new Doctor(2, "d2", "orthopedia", null, 0d));
        DoctorFilter doctorFilter = FilterFactory.buildFilter("location", null);
        List<Doctor> filterDoctors = doctorRepository.findAll(doctorFilter);

        assert filterDoctors != null;
        assert filterDoctors.size() == 1;
        assert filterDoctors.getFirst().getId() == 2;
    }

    @Test
    void findAllSorted_basedOnSpeciality() {
        DoctorRepository doctorRepository = new DoctorRepository();
        doctorRepository.add(new Doctor(1, "a", "d", "Cluj", 1d));
        doctorRepository.add(new Doctor(2, "a", "a", "Cluj", 2d));
        doctorRepository.add(new Doctor(3, "a", "c", "Cluj", 3d));
        Comparator<Doctor> comparator = Comparator.comparing(Doctor::getSpecialty);

        List<Doctor> filterDoctors = doctorRepository.findAllSorted(comparator);

        assert filterDoctors.get(0).getId() == 2;
        assert filterDoctors.get(1).getId() == 3;
        assert filterDoctors.get(2).getId() == 1;
    }
}
