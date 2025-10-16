package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Identifiable;
import ro.ubbcluj.apm.am.repository.sorter.BubbleSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryRepository<K, V extends Identifiable<K> & Comparable<V>> implements Repository<K, V> {
    private final Map<K, V> map = new HashMap<>();

    @Override
    public V add(V value) {
        return map.put(value.getId(), value);
    }

    @Override
    public Optional<V> findById(K id) {
        if (map.containsKey(id)) {
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Iterable<V> findAll() {
        return map.values();
    }

    @Override
    public void update(V value) {
        map.put(value.getId(), value);
    }

    @Override
    public void deleteById(K id) {
        map.remove(id);
    }

    public List<V> findAllSorted(Comparator<V> comparator) {
        BubbleSorter<V> sorter = new BubbleSorter<>();
        List<V> copy = new ArrayList<>();
        for (V doctor : findAll()) {
            copy.add(doctor);
        }
        sorter.sort(copy, comparator);
        return copy;
    }
}
