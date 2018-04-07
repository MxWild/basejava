package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String key) {
        return resumeMap.containsKey(key);
    }

    @Override
    protected void doSave(Resume r, String key) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String key) {
        resumeMap.remove(key);
    }

    @Override
    protected void doUpdate(Resume r, String key) {
        resumeMap.put(key, r);
    }

    @Override
    protected Resume doGet(String key) {
        return resumeMap.get(key);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
