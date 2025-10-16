package ro.ubbcluj.apm.am.domain;

public interface Identifiable<T> {
    T getId();

    void setId(T id);
}
