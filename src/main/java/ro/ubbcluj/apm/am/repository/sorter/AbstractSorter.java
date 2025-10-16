package ro.ubbcluj.apm.am.repository.sorter;

public interface AbstractSorter<T extends Comparable<T>> {
    void sort(T[] array);
}
