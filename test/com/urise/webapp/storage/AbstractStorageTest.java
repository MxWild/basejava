package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "Name1";
    private static final Resume resume1 = new Resume(UUID_1, NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String NAME_2 = "Name2";
    private static final Resume resume2 = new Resume(UUID_2, NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String NAME_3 = "Name3";
    private static final Resume resume3 = new Resume(UUID_3, NAME_3);

    private static final String UUID_TEST = "test_uuid";
    private static final String NAME_TEST = "test_Name";
    public static final Resume resume4 = new Resume(UUID_TEST, NAME_TEST);

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertSize(4);
        Assert.assertEquals(resume4, storage.get(UUID_TEST));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, NAME_TEST);
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get(UUID_1));
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(UUID_1));
        Assert.assertEquals(resume2, storage.get(UUID_2));
        Assert.assertEquals(resume3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(resume1);
    }

    @Test
    public void getAll() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(3, resumes.size());
//        Assert.assertEquals(resume1, resumes[0]);
//        Assert.assertEquals(resume2, resumes[1]);
//        Assert.assertEquals(resume3, resumes[2]);
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}