package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.Objects;

public class LocationFilter implements Filter {
    private final String location;

    public LocationFilter(String location) {
        this.location = location;
    }

    @Override
    public boolean accept(Doctor doctor) {
        return Objects.equals(doctor.getLocation(), location);
    }
}
