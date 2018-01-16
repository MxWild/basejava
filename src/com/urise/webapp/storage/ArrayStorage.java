package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        //TODO check resume not in storage
        // поиск свободного элемента
        if (get(r.getUuid()) == null) {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        //TODO check resume in storage
            for (int i = 0; i < size; i++) {
                if(storage[i].getUuid().equals(uuid)) return storage[i];
            }
        return null;
    }

    public void update(Resume r) {
        //TODO check resume in storage
        int index = getResumeIndex(r.getUuid());
        if (index != -1) storage[index] = r;
        else System.out.println("Resume not found in Storage");
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    public void delete(String uuid) {
        //TODO check resume in storage
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return this.size;
    }
}
