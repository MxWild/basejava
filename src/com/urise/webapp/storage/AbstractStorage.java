package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    // метод для поиска ключа резюме
    protected abstract SK getKey(String uuid);

    // ключ существует?
    protected abstract boolean isExist(SK key);

    // сохраняем резюме
    protected abstract void doSave(Resume r, SK key);

    // удаляем резюме по ключу
    protected abstract void doDelete(SK key);

    // метод для обновления резюме для каждой реализации
    protected abstract void doUpdate(Resume r, SK key);

    // получить резюме по ключу
    protected abstract Resume doGet(SK key);

    // получить все резюме
    protected abstract List<Resume> doCopyAll();

    // выделяю NotExistStorageException
    private SK checkNotExistStorageException(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not found in Storage");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    // выделяю ExistStorageException
    private SK checkExistStorageException(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist in storage.");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK key = checkExistStorageException(r.getUuid());
        doSave(r, key);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = checkNotExistStorageException(uuid);
        doDelete(key);
    }


    public void update(Resume r) {
        LOG.info("Update " + r);
        SK key = checkNotExistStorageException(r.getUuid());
        doUpdate(r, key);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = checkNotExistStorageException(uuid);
        return doGet(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

}
