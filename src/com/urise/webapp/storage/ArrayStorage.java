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
        // проверяем, есть ли таке резюме в storage?
        if (getResumeIndex(r.getUuid()) == -1) {
            if (size < storage.length) storage[size++] = r;
            else System.out.println("Error: Not enough space in Storage");
        } else {
            System.out.println("Already storage had this resume");
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);

        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume not found in Storage");
        }
        return null;
    }

    public void update(Resume r) {
        int index = getResumeIndex(r.getUuid());

        if (index != -1) storage[index] = r;
        else {
            System.out.println("Resume not found in Storage");
        }
    }


    public void delete(String uuid) {
        int index = getResumeIndex(uuid);

        if (index != -1) {
                storage[index] = storage[size - 1];
                storage[size - 1] = null;
                size--;
        } else {
            System.out.println("Resume not found in Storage");
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

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        // -1 если такого резюме нет в storage
        return -1;
    }
}
