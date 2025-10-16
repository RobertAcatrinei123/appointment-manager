package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Doctor;
import ro.ubbcluj.apm.am.repository.filter.DoctorFilter;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository extends MemoryRepository<Integer, Doctor> {

    public List<Doctor> findAll(DoctorFilter doctorFilter) {
        Iterable<Doctor> doctors = findAll();
        List<Doctor> newLst = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctorFilter.accept(doctor)) {
                newLst.add(doctor);
            }
        }
        return newLst;
    }
}
