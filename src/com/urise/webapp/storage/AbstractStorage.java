package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    // метод для поиска ключа резюме
    protected abstract Object getKey(String uuid);

    // ключ существует?
    protected abstract boolean isExistKey(Object key);

    // сохраняем резюме
    protected abstract void doSave(Resume r, Object key);

    // удаляем резюме по ключу
    protected abstract void doDelete(Object key);

    // метод для обновления резюме для каждой реализации
    protected abstract void doUpdate(Resume r, Object key);

    // получить резюме по ключу
    protected abstract Resume doGet(Object key);

    // выделяю NotExistStorageException
    private Object checkNotExistStorageException(String uuid) {
        Object key = getKey(uuid);
        if (!isExistKey(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    // выделяю ExistStorageException
    private Object checkExistStorageException(String uuid) {
        Object key = getKey(uuid);
        if (isExistKey(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public void save(Resume r) {
        Object key = checkExistStorageException(r.getUuid());
        doSave(r, key);
    }

    public void delete(String uuid) {
        Object key = checkNotExistStorageException(uuid);
        doDelete(key);
    }


    public void update(Resume r) {
        Object key = checkNotExistStorageException(r.getUuid());
        doUpdate(r, key);
    }

    public Resume get(String uuid) {
        Object key = checkNotExistStorageException(uuid);
        return doGet(key);
    }

}
