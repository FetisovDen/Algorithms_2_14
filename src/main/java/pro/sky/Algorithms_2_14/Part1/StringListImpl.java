package pro.sky.Algorithms_2_14.Part1;

import pro.sky.Algorithms_2_14.Exception.IncorrectIndexException;
import pro.sky.Algorithms_2_14.Exception.ItemNullException;
import pro.sky.Algorithms_2_14.Exception.MassivIsFullException;
import pro.sky.Algorithms_2_14.Exception.NotFoundException;

import java.util.Arrays;


public class StringListImpl implements StringList {

    private final String[] massive;
    private int size;

    private StringListImpl(int i) {
        massive = new String[i];
    }

    private void checkItemOnNull(String item) {
        if (item == null) {
            throw new ItemNullException();
        }
    }

    private void checkSizeOnFullness() {
        if (size == massive.length) {
            throw new MassivIsFullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IncorrectIndexException();
        }
    }

    @Override
    public String add(String item) {
        checkSizeOnFullness();
        checkItemOnNull(item);
        massive[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        checkIndex(index);
        checkItemOnNull(item);
        massive[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        checkIndex(index);
        String item = massive[index];
        if (index != size) {
            System.arraycopy(massive, index, massive, index + 1, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i <= size; i++) {
            if (massive[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (massive[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return massive[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(massive, size);
    }
}