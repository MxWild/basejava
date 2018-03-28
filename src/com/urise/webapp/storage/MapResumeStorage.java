package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO create new MapStorage with Search Key not uuid
public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Resume r, Object key) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object key) {
        map.remove(((Resume)key).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    public void clear() {
        map.clear();
    }

//    @Override
//    public Resume[] getAll() {
//        // взял тут http://www.baeldung.com/convert-map-values-to-array-list-set
//        Collection<Resume> values = map.values();
//        return values.toArray(new Resume[values.size()]);
//    }


    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>(map.values());
        return resumeList;
    }

    @Override
    public int size() {
        return map.size();
    }
}
