package pro.sky.Algorithms_2_14.Part2;

import pro.sky.Algorithms_2_14.Exception.IncorrectIndexException;
import pro.sky.Algorithms_2_14.Exception.ItemNullException;
import pro.sky.Algorithms_2_14.Exception.MassivIsFullException;
import pro.sky.Algorithms_2_14.Exception.NotFoundException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] massive;
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

    public void checkSizeOnFullness() {
        if (size == massive.length) {
            throw new MassivIsFullException();
        }
    }

    public void checkIndex(Integer index) {
        if (index < 0 || index > size) {
            throw new IncorrectIndexException();
        }
    }

    @Override
    public Integer add(Integer item) {
        checkSizeOnFullness();
        checkItemOnNull(item);
        massive[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkSizeOnFullness();
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
    public boolean  contains(Integer[]arr,Integer item) {
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
    public void sort(Integer[]arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            Integer tmp = arr[i];
            arr[i] = arr[minElementIndex];
            arr[minElementIndex] = tmp;
        }
    }
}