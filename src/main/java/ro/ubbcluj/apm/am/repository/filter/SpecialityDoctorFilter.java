package ro.ubbcluj.apm.am.repository.filter;

import ro.ubbcluj.apm.am.domain.Doctor;

public record SpecialityDoctorFilter(String specialty) implements DoctorFilter {

    @Override
    public boolean accept(Doctor doctor) {
        return doctor.getSpecialty().equals(specialty);
    }
}
