package ro.ubbcluj.apm.am.repository.sorter;

import java.util.Comparator;
import java.util.List;

public interface AbstractSorter<T extends Comparable<T>> {
    void sort(T[] array);

    void sort(List<T> list, Comparator<T> comparator);
}
