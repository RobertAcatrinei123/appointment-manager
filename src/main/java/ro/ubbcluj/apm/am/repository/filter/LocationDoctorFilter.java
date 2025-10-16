package ro.ubbcluj.apm.am.repository.filter;

import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.Objects;

public class LocationDoctorFilter implements DoctorFilter {
    private final String location;

    public LocationDoctorFilter(String location) {
        this.location = location;
    }

    @Override
    public boolean accept(Doctor doctor) {
        return Objects.equals(doctor.getLocation(), location);
    }
}
