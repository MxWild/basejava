package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int lengthDelta = size - index - 1;
        // сдвигаем только в том случае, если  длина массива для копирования не отрицательная
        if (lengthDelta > 0) {
            // копирование массива из массива со следующего индекса, на текущий
            System.arraycopy(storage, index + 1, storage, index, lengthDelta);
        }
    }

    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
