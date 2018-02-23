package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    // метод для поиска ключа резюме
    abstract Object getKey(String uuid);

    // ключ существует?
    abstract boolean isExistKey(Object key);

    // сохраняем резюме
    abstract void doSave(Resume r, Object key);

    // удаляем резюме по ключу
    abstract void doDelete(Object key);

    // метод для обновления резюме для каждой реализации
    abstract void doUpdate(Resume r, Object key);

    // получить резюме по ключу
    abstract Resume doGet(Object key);

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

//        if (index < 0) {
//            if (size < STORAGE_LIMIT) {
//                // добавляем метод для добавления резюме в Storage
//                insertResume(r, index);
//                size++;
//            } else {
//                //System.out.println("Error: Not enough space in Storage");
//                throw new StorageException("Error: Not enough space in Storage", r.getUuid());
//            }
//        } else {
//            //System.out.println("Resume " + r.getUuid() + "already exist int storage");
//            throw new ExistStorageException(r.getUuid());
//        }
    }

    public void delete(String uuid) {
        //int index = getResumeIndex(uuid);
        Object key = checkNotExistStorageException(uuid);
        doDelete(key);

//        if (index >= 0) {
//            //storage[index] = storage[size - 1];
//            // добавляем метод для удаления резюме из Storage
//            deleteResume(index);
//            // хвост зануляем
//            storage[size - 1] = null;
//            size--;
//        } else {
//            //System.out.println("Resume " + uuid + " not found in Storage");
//            throw new NotExistStorageException(uuid);
//        }
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
