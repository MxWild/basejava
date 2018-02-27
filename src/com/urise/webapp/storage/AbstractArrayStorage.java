package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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

    @Override
    protected boolean isExistKey(Object key) {
        return (Integer) key >= 0;
    }

//    @Override
//    protected Object getKey(String uuid) {
//        return getResumeIndex(uuid);
//    }

    @Override
    protected void doSave(Resume r, Object key) {
        if (size < STORAGE_LIMIT) {
            // добавляем метод для добавления резюме в Storage
            insertResume(r, (Integer) key);
            size++;
        } else {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    @Override
    protected void doDelete(Object key) {
        deleteResume((Integer) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        storage[(Integer) key] = r;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    //protected abstract int getResumeIndex(String uuid);
    protected abstract Integer getKey(String uuid);

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
