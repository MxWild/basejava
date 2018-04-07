package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Resume r, Resume key) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume key) {
        map.remove((key).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume key) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
