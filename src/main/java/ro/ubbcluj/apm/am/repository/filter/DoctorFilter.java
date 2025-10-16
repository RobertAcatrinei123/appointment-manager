package ro.ubbcluj.apm.am.repository.filter;

import ro.ubbcluj.apm.am.domain.Doctor;

public interface DoctorFilter {
    boolean accept(Doctor doctor);
}
