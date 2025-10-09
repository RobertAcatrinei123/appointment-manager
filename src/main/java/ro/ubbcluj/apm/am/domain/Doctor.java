package ro.ubbcluj.apm.am.domain;

public class Doctor {
    private String name;
    private String specialty;
    private String location;
    private double grade;

    public Doctor(String name, String specialty, String location,
                  double grade) {
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
