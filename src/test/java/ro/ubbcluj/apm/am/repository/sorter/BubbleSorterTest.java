package ro.ubbcluj.apm.am.repository.sorter;

import org.junit.jupiter.api.Test;
import ro.ubbcluj.apm.am.domain.Doctor;

class BubbleSorterTest {

    @Test
    void sort_withIntegers() {
        BubbleSorter<Integer> sorter = new BubbleSorter<>();
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
        BubbleSorter<Doctor> sorter = new BubbleSorter<>();
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
}