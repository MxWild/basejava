package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume r, Object key) {
            resumeList.add(r);
    }

    @Override
    protected void doDelete(Object key) {
        resumeList.remove(((Integer) key).intValue());
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        resumeList.set((Integer) key, r);
    }

    @Override
    protected Resume doGet(Object key) {
        return resumeList.get((Integer) key);
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

//    @Override
//    public Resume[] getAll() {
//        return resumeList.toArray(new Resume[resumeList.size()]);
//    }


    @Override
    protected List<Resume> doGetAll() {
        return resumeList;
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
