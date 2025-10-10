package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Doctor;

public class SpecialityFilter implements Filter {
    public final String specialty;

    public SpecialityFilter(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean accept(Doctor doctor) {
        return doctor.getSpecialty().equals(specialty);
    }
}
