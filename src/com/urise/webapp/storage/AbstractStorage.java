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
    protected abstract void doSave(Resume r);

    // удаляем резюме по ключу
    protected abstract void doDelete(String uuid);

    // метод для обновления резюме для каждой реализации
    protected abstract void doUpdate(Resume r);

    // получить резюме по ключу
    protected abstract Resume doGet(String uuid);

    // выделяю NotExistStorageException
    private void checkNotExistStorageException(String uuid) {
        Object key = getKey(uuid);
        if (!isExistKey(key)) {
            throw new NotExistStorageException(uuid);
        }
    }

    // выделяю ExistStorageException
    private void checkExistStorageException(String uuid) {
        Object key = getKey(uuid);
        if (isExistKey(key)) {
            throw new ExistStorageException(uuid);
        }
    }

    public void save(Resume r) {

        checkExistStorageException(r.getUuid());
        doSave(r);

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
        checkNotExistStorageException(uuid);
        doDelete(uuid);

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
        checkNotExistStorageException(r.getUuid());
        doUpdate(r);
    }

    public Resume get(String uuid) {
        checkNotExistStorageException(uuid);
        return doGet(uuid);
    }

}
