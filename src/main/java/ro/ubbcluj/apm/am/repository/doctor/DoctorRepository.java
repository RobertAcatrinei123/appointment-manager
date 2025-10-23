package ro.ubbcluj.apm.am.repository.doctor;

import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.MemoryRepository;
import ro.ubbcluj.apm.am.repository.filter.DoctorFilter;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository extends MemoryRepository<Integer, Doctor> {

    public List<Doctor> findAll(DoctorFilter doctorFilter) {
        List<Doctor> newLst = new ArrayList<>();
        for (Doctor doctor : findAll()) {
            if (doctorFilter.accept(doctor)) {
                newLst.add(doctor);
            }
        }
        return newLst;
    }
}
