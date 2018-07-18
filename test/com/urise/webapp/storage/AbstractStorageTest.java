package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.urise.webapp.TestData.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
        storage.save(RESUME_4);
        assertSize(4);
        Assert.assertEquals(RESUME_4, storage.get(UUID_TEST));
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
        newResume.addContacts(ContactType.TEL, "New telephone");
        newResume.addContacts(ContactType.EMAIL, "New email");
        newResume.addContacts(ContactType.GITHUB, "New github");
        newResume.addContacts(ContactType.SKYPE, "New skype");
        storage.update(newResume);
//        Assert.assertTrue(newResume.equals(storage.get(UUID_1)));
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
        Assert.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void getAll() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(3, resumes.size());
//        Assert.assertEquals(RESUME_1, resumes[0]);
//        Assert.assertEquals(RESUME_2, resumes[1]);
//        Assert.assertEquals(RESUME_3, resumes[2]);
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}