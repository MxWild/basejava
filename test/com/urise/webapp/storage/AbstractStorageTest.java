package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.List;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("./storage");

    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "Name1";
    private static final Resume RESUME_1 = new Resume(UUID_1, NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String NAME_2 = "Name2";
    private static final Resume RESUME_2 = new Resume(UUID_2, NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String NAME_3 = "Name3";
    private static final Resume RESUME_3 = new Resume(UUID_3, NAME_3);

    private static final String UUID_TEST = "test_uuid";
    private static final String NAME_TEST = "test_Name";
    public static final Resume RESUME_4 = new Resume(UUID_TEST, NAME_TEST);

    static {
        RESUME_1.addContacts(ContactType.TEL, "+7 922 222 22 22");
        RESUME_1.addContacts(ContactType.EMAIL, "maximus@maximus.ru");
        RESUME_1.addContacts(ContactType.GITHUB, "https://github.com/maximus");
        RESUME_1.addContacts(ContactType.SKYPE, "maximus@skype.ru");

        RESUME_1.addSections(SectionType.PERSONAL, new TextSection("My super description about me!"));
        RESUME_1.addSections(SectionType.OBJECTIVE, new TextSection("My objective"));
        RESUME_1.addSections(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievent2"));
        RESUME_1.addSections(SectionType.QUALIFICATIONS, new ListSection("Java", "Spring", "Hibernate", "JavaScript"));

        RESUME_1.addSections(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", "http://university.ru",
                        new Organization.Position(1996, Month.SEPTEMBER, 2001, Month.MAY, "Engeener", "Facultet")),
                new Organization("Institute",  "http://institute.com",
                        new Organization.Position(2001, Month.SEPTEMBER, "Bakalavr", "Magistratura"))
        ));

        RESUME_1.addSections(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Work3", "http://work3.com",
                        new Organization.Position(2015, Month.JANUARY, "Developer", "Some description")),
                new Organization("Work2", "http://work2.com",
                    new Organization.Position(2013, Month.APRIL, 2014, Month.DECEMBER, "Admin", "Some Admin roles")),
                new Organization("Work1", "http://work1.com",
                    new Organization.Position(2011, Month.JULY, 2014, Month.DECEMBER, "Manager", "Some managementwoles"))
        ));

        RESUME_3.addContacts(ContactType.TEL, "+7 923 233 03 03");
        RESUME_3.addContacts(ContactType.EMAIL, "RESUME_3@yandex.ru");
    }

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
        storage.update(newResume);
        Assert.assertTrue(newResume.equals(storage.get(UUID_1)));
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