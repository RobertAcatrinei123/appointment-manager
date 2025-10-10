package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Doctor;

public interface Filter {
    boolean accept(Doctor doctor);
}
