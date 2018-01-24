package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        // Заполняем нулями весь массив
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
        // проверяем, есть ли таке резюме в storage?
        if (getResumeIndex(r.getUuid()) == -1) {
            if (size < STORAGE_LIMIT) storage[size++] = r;
            else System.out.println("Error: Not enough space in Storage");
        } else {
            System.out.println("Resume " + r.getUuid() + "already exist int storage");
        }
    }


    @Override
    public void update(Resume r) {
        int index = getResumeIndex(r.getUuid());

        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not found in Storage");
        }
        else {
            storage[index] = r;
        }
    }

    @Override
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


    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        // -1 если такого резюме нет в storage
        return -1;
    }
}
