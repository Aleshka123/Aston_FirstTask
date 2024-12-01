package org.example;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация ArrayList с методами добавление, удаление, сортировку и очистку элементов.
 * @param <T> тип элементов в этой коллекции
 */
public class MyArrayList<T> {
    private Object[] elements;
    private int size;

    /**
     * Создаёт пустой список с начальной ёмкостью 10.
     */
    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в указанную позицию, сдвигая последующие элементы вправо.
     *
     * @param index   индекс, по которому будет вставлен элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по указанному индексу.
     *
     * @param index индекс элемента для получения
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу, сдвигая последующие элементы влево.
     *
     * @param index индекс элемента для удаления
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        elements[size] = null;
        return removedElement;
    }

    /**
     * Заменяет элемент по указанному индексу на новый элемент.
     *
     * @param index   индекс элемента для замены
     * @param element новый элемент
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        elements[index] = element;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Сортирует список с использованием указанного компаратора.
     * Если компаратор не указан, элементы сортируются в естественном порядке.
     *
     * @param comparator компаратор для определения порядка сортировки
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> comparator) {
        quickSort((T[]) elements, 0, size - 1, comparator);
    }

    /**
     * Возвращает размер списка.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Обеспечивает достаточную ёмкость для хранения новых элементов.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    /**
     * Выполняет алгоритм быстрой сортировки для массива.
     *
     * @param array      массив для сортировки
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для определения порядка сортировки
     */
    private void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Выполняет разделение массива вокруг опорного элемента.
     *
     * @param array      массив для разделения
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для определения порядка
     * @return индекс опорного элемента
     */
    private int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Обменивает два элемента в массиве.
     *
     * @param array массив
     * @param i     индекс первого элемента
     * @param j     индекс второго элемента
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}

