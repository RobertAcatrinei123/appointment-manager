package ro.ubbcluj.apm.am.processor;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctorTest {
    static Doctor d1 = new Doctor(1, "Mihai", "d", "Cluj", 5.6);
    static Doctor d2 = new Doctor(2, "Andrei", "a", "Cluj", 3.4);
    static Doctor d3 = new Doctor(3, "George", "c", "Cluj", 8.1);
    static List<Doctor> doctors = List.of(
            d1,
            d2,
            d3
    );

    @Test
    void passedGrade() {
        List<Doctor> filteredDoctors = doctors.stream()
                .filter(doctor -> doctor.getGrade() >= 5)
                .toList();

        assertThat(filteredDoctors).hasSize(2);
        assertThat(filteredDoctors).containsAll(List.of(d1, d3));
    }

    @Test
    void nameContainsSubstring() {
        List<Doctor> filteredDoctors = doctors.stream()
                .filter(doctor -> doctor.getName().contains("ha"))
                .toList();

        assertThat(filteredDoctors).hasSize(1);
        assertThat(filteredDoctors).containsAll(List.of(d1));
    }
}
