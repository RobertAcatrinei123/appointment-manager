package ro.ubbcluj.apm.am.domain;

import lombok.Data;

@Data
public class Patient implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
}
