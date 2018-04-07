package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

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
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume r, Integer key) {
            resumeList.add(r);
    }

    @Override
    protected void doDelete(Integer key) {
        resumeList.remove(key.intValue());
    }

    @Override
    protected void doUpdate(Resume r, Integer key) {
        resumeList.set(key, r);
    }

    @Override
    protected Resume doGet(Integer key) {
        return resumeList.get(key);
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(resumeList);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
