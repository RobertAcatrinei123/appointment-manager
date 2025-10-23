package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    V add(V value);

    Optional<V> findById(K id);

    Collection<V> findAll();

    void update(V value);

    void deleteById(K id);
}
