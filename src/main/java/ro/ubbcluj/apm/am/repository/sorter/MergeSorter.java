package ro.ubbcluj.apm.am.repository.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSorter<T extends Comparable<T>> implements AbstractSorter<T> {

    @Override
    public void sort(T[] array) {
        List<T> result = mergeSort(Arrays.asList(array), Comparator.naturalOrder());
        for (int i = 0; i < array.length; i++) {
            array[i] = result.get(i);
        }
    }

    public void sort(List<T> list, Comparator<T> comparator) {
        List<T> result = mergeSort(list, comparator);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, result.get(i));
        }
    }

    private List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        if (n <= 1) return list;
        int mid = n / 2;
        List<T> list1 = new ArrayList<>(list.subList(0, mid));
        List<T> list2 = new ArrayList<>(list.subList(mid, n));
        return merge(mergeSort(list1, comparator), mergeSort(list2, comparator), comparator);
    }

    private List<T> merge(List<T> list1, List<T> list2, Comparator<T> comparator) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (comparator.compare(list1.get(i), list2.get(j)) <= 0) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
        }
        while (i < list1.size()) result.add(list1.get(i++));
        while (j < list2.size()) result.add(list2.get(j++));
        return result;
    }
}
