package ro.ubbcluj.apm.am.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Doctor implements Identifiable<Integer>, Comparable<Doctor> {
    private Integer id;
    private String name;
    private String specialty;
    private String location;
    private double grade;

    @Override
    public int compareTo(Doctor doctor) {
        return this.name.compareToIgnoreCase(doctor.name);
    }
}
