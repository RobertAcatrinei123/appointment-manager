package ro.ubbcluj.apm.am.repository.filter;

public class FilterFactory {
    public static DoctorFilter buildFilter(String filterType, Object filterValue) {
        return switch (filterType) {
            case "location" -> new LocationDoctorFilter(filterValue == null ? null : filterValue.toString());
            case "speciality" -> new SpecialityDoctorFilter(filterValue == null ? null : filterValue.toString());
            default -> throw new IllegalArgumentException("Filter type not supported: " + filterType);
        };
    }
}
