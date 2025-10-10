package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    List<Doctor> doctorList;

    public DoctorRepository() {
        doctorList = new ArrayList<>();
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctorList) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        throw new RuntimeException("Doctor id not found");
    }

    public void addDoctor(Doctor doctor) {
        if (doctorList.stream().anyMatch(doctor1 -> doctor1.getId() == doctor.getId())) {
            throw new IllegalArgumentException("Doctor already exists");
        }
        doctorList.add(doctor);
    }

    public void updateDoctor(Doctor doctor) {
        Doctor oldDoctor = getDoctorById(doctor.getId());
        oldDoctor.setName(doctor.getName());
        oldDoctor.setLocation(doctor.getLocation());
        oldDoctor.setSpecialty(doctor.getSpecialty());
        oldDoctor.setGrade(doctor.getGrade());
    }

    public void deleteDoctorById(int id) {
        getDoctorById(id);
        doctorList.removeIf(doctor -> doctor.getId() == id);
    }

    public List<Doctor> filterDoctors(Filter filter) {
        List<Doctor> newLst = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            if (filter.accept(doctor)) {
                newLst.add(doctor);
            }
        }
        return newLst;
    }


}
