package ro.ubbcluj.apm.am.repository.filter;

import ro.ubbcluj.apm.am.domain.Doctor;

public class SpecialityDoctorFilter implements DoctorFilter {
    public final String specialty;

    public SpecialityDoctorFilter(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean accept(Doctor doctor) {
        return doctor.getSpecialty().equals(specialty);
    }
}
