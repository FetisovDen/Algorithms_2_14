package pro.sky.Algorithms_2_14.Part2;

import pro.sky.Algorithms_2_14.Exception.IncorrectIndexException;
import pro.sky.Algorithms_2_14.Exception.ItemNullException;
import pro.sky.Algorithms_2_14.Exception.MassivIsFullException;
import pro.sky.Algorithms_2_14.Exception.NotFoundException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] massive;
    private int size;

    public IntegerListImpl() {
        massive = new Integer[3];
    }

    public IntegerListImpl(int i) {
        massive = new Integer[i];
    }

    public void checkItemOnNull(Integer item) {
        if (item == null) {
            throw new ItemNullException();
        }
    }

    public void checkIfNeedGrow() {
        if (size == massive.length) {
            grow();
        }
    }

    public void checkIndex(Integer index) {
        if (index < 0 || index > size) {
            throw new IncorrectIndexException();
        }
    }

    @Override
    public Integer add(Integer item) {
        checkIfNeedGrow();
        checkItemOnNull(item);
        massive[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIfNeedGrow();
        checkItemOnNull(item);
        checkIndex(index);
        if (index == size) {
            massive[size++] = item;
        } else {
            System.arraycopy(massive, index, massive, index + 1, size - index);
            massive[index] = item;
            size++;
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndex(index);
        checkItemOnNull(item);
        massive[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItemOnNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException();
        }
        if (index != size) {
            System.arraycopy(massive, index, massive, index + 1, size - index);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = massive[index];
        if (index != size) {
            System.arraycopy(massive, index, massive, index + 1, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer[] arr, Integer item) {
        checkItemOnNull(item);
        sort(arr);
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkItemOnNull(item);
        for (int i = 0; i <= size; i++) {
            if (massive[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (massive[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return massive[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(massive, size);
    }

    @Override
    public void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void grow() {
        massive = Arrays.copyOf(massive, size + size / 2);
    }
}