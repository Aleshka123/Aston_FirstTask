import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.MyArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        list.add(10);
        list.add(20);
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testAddAtIndex() {
        list.add(10);
        list.add(30);
        list.add(1, 20);
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testGet() {
        list.add(10);
        list.add(20);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);
        int removed = list.remove(1);
        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void testSet() {
        list.add(10);
        list.add(20);
        list.set(1, 30);
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.sort(Integer::compareTo);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testSortWithCustomComparator() {
        list.add(10);
        list.add(30);
        list.add(20);
        list.sort(Comparator.reverseOrder());
        assertEquals(30, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(10, list.get(2));
    }

    @Test
    void testEnsureCapacity() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(100, list.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void testIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void testRemoveLastElement() {
        list.add(10);
        list.add(20);
        list.add(30);
        int removed = list.remove(2);
        assertEquals(30, removed);
        assertEquals(2, list.size());
    }

    @Test
    void testAddAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }

    @Test
    void testSortEmptyList() {
        list.sort(Integer::compareTo);
        assertEquals(0, list.size());
    }

    @Test
    void testSortSingleElement() {
        list.add(10);
        list.sort(Integer::compareTo);
        assertEquals(10, list.get(0));
    }

    @Test
    void testSetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));
    }

    @Test
    void testAddNull() {
        list.add(null);
        assertNull(list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testSetNull() {
        list.add(10);
        list.set(0, null);
        assertNull(list.get(0));
    }
    @Test
    void testAddElements() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        assertEquals(1000, list.size());
        assertEquals(999, list.get(999));
    }

    @Test
    void testRemoveElements() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 100; i++) {
            list.remove(0);
        }
        assertEquals(900, list.size());
        assertEquals(100, list.get(0));
    }
    @Test
    void testSortElements() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 1000; i > 0; i--) {
            list.add(i);
        }
        list.sort(Integer::compareTo);
        assertEquals(1, list.get(0));
        assertEquals(1000, list.get(999));
    }

    @Test
    void testClearElements() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }
}

