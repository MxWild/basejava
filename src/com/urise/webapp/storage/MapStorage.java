package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

// TODO create new MapStorage with Search Key not uuid
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return resumeMap.containsKey(key);
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

//    @Override
//    public Resume[] getAll() {
//        // взял тут http://www.baeldung.com/convert-map-values-to-array-list-set
//        Collection<Resume> values = resumeMap.values();
//        return values.toArray(new Resume[values.size()]);
//    }


    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>(resumeMap.values());
        return resumeList;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
