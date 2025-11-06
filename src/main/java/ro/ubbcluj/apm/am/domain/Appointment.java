package ro.ubbcluj.apm.am.domain;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Appointment implements Identifiable<Integer> {
    private Integer id;
    private Doctor doctor;
    private Patient patient;
    private ZonedDateTime dateTime;
}
