package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {

//        for (Map.Entry<String, Resume> pair : resumeMap.entrySet()) {
//            if (uuid.equals(pair.getValue().getUuid())) return uuid;
//        }

        return uuid;
    }

    @Override
    protected boolean isExistKey(Object key) {

        for (Map.Entry<String, Resume> pair : resumeMap.entrySet()) {
            if (key.equals(pair.getValue().getUuid())) return true;
        }

        return false;
    }

    @Override
    protected void doSave(Resume r, Object key) {
            resumeMap.put(r.getUuid(), r);
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
