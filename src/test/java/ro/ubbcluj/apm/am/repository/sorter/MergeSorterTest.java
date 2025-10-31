package ro.ubbcluj.apm.am.repository.sorter;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSorterTest {

    @Test
    void sort_withIntegers() {
        AbstractSorter<Integer> sorter = new MergeSorter<>();
        Integer[] array = {1, 9, 4, 5, -1};
        sorter.sort(array);

        assert array[0] == -1;
        assert array[1] == 1;
        assert array[2] == 4;
        assert array[3] == 5;
        assert array[4] == 9;
    }

    @Test
    void sort_withDoctors() {
        AbstractSorter<Doctor> sorter = new MergeSorter<>();
        Doctor[] array = {
                new Doctor(1, "z", null, null, 0d),
                new Doctor(2, "a", null, null, 0d),
                new Doctor(3, "a", null, null, 0d),
                new Doctor(4, "c", null, null, 0d),
        };
        sorter.sort(array);

        assert array[0].getId() == 2;
        assert array[1].getId() == 3;
        assert array[2].getId() == 4;
        assert array[3].getId() == 1;
    }

    @Test
    void sort_withDoctors_withComparator() {
        Comparator<Doctor> comparator = Comparator.comparing(Doctor::getSpecialty);
        MergeSorter<Doctor> sorter = new MergeSorter<>();

        Doctor[] array = {
                new Doctor(1, "z", "Family", null, 0d),
                new Doctor(2, "a", "Cardiologist", null, 0d),
                new Doctor(3, "a", "Neurologist", null, 0d),
                new Doctor(4, "c", "Orthoped", null, 0d),
        };
        sorter.sort(Arrays.asList(array), comparator);

        assert array[0].getId() == 2;
        assert array[1].getId() == 1;
        assert array[2].getId() == 3;
        assert array[3].getId() == 4;
    }
}
