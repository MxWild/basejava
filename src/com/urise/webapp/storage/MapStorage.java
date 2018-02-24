package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapStorage extends AbstractStorage {
    @Override
    Integer getKey(String uuid) {
        return null;
    }

    @Override
    boolean isExistKey(Object key) {
        return false;
    }

    @Override
    void doSave(Resume r, Object key) {

    }

    @Override
    void doDelete(Object key) {

    }

    @Override
    void doUpdate(Resume r, Object key) {

    }

    @Override
    Resume doGet(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
