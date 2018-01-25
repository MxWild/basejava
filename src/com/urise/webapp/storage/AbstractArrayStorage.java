package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return this.size;
    }

    public void clear() {
        // Заполняем нулями весь массив
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        // проверяем, есть ли таке резюме в storage?
        if (getResumeIndex(r.getUuid()) == -1) {
            if (size < STORAGE_LIMIT) storage[size++] = r;
            else System.out.println("Error: Not enough space in Storage");
        } else {
            System.out.println("Resume " + r.getUuid() + "already exist int storage");
        }
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);

        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " not found in Storage");
        }
    }

    public void update(Resume r) {
        int index = getResumeIndex(r.getUuid());

        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not found in Storage");
        }
        else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);

        if (index == -1) {
            System.out.println("Resume " + uuid + " not found in Storage");
        } else {
            return storage[index];
        }
        return null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getResumeIndex(String uuid);
}
