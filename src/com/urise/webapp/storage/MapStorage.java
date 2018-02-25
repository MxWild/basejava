package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new TreeMap<>();

    @Override
    protected Object getKey(String uuid) {

        for (Map.Entry<String, Resume> pair : resumeMap.entrySet()) {
            if (uuid.equals(pair.getValue().getUuid())) return uuid;
        }

        return null;
    }

    @Override
    protected boolean isExistKey(Object key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume r, Object key) {
        if (resumeMap.size() < AbstractArrayStorage.STORAGE_LIMIT) {
            resumeMap.put(r.getUuid(), r);
        } else throw new StorageException("Storage overflow", r.getUuid());
    }

    @Override
    protected void doDelete(Object key) {
        resumeMap.remove(key);
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        resumeMap.put((String) key, r);
    }

    @Override
    protected Resume doGet(Object key) {
        return resumeMap.get(key);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        // взял тут http://www.baeldung.com/convert-map-values-to-array-list-set
        Collection<Resume> values = resumeMap.values();
        return values.toArray(new Resume[values.size()]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
