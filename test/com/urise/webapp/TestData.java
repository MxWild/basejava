package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {

    public static final String UUID_1 = UUID.randomUUID().toString();
    //    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "Name1";
    public static final Resume RESUME_1 = new Resume(UUID_1, NAME_1);

    public static final String UUID_2 = UUID.randomUUID().toString();
    private static final String NAME_2 = "Name2";
    public static final Resume RESUME_2 = new Resume(UUID_2, NAME_2);

    public static final String UUID_3 = UUID.randomUUID().toString();
    private static final String NAME_3 = "Name3";
    public static final Resume RESUME_3 = new Resume(UUID_3, NAME_3);

    public static final String UUID_TEST = UUID.randomUUID().toString();
    public static final String NAME_TEST = "test_Name";
    public static final Resume RESUME_4 = new Resume(UUID_TEST, NAME_TEST);

    static {
        RESUME_1.addContacts(ContactType.TEL, "+7 922 222 22 22");
        RESUME_1.addContacts(ContactType.EMAIL, "maximus@maximus.ru");
        RESUME_1.addContacts(ContactType.GITHUB, "https://github.com/maximus");
        RESUME_1.addContacts(ContactType.SKYPE, "maximus@skype.com");

        RESUME_2.addContacts(ContactType.TEL, "+7 911 111 11 11");
        RESUME_2.addContacts(ContactType.EMAIL, "resume2@gmail.com");
        RESUME_2.addContacts(ContactType.GITHUB, "https://github.com/resume2");

        RESUME_1.addSections(SectionType.PERSONAL, new TextSection("My super description about me!"));
        RESUME_1.addSections(SectionType.OBJECTIVE, new TextSection("My objective"));
        RESUME_1.addSections(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievent2"));
        RESUME_1.addSections(SectionType.QUALIFICATIONS, new ListSection("Java", "Spring", "Hibernate", "JavaScript"));

        RESUME_1.addSections(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", "http://university.ru",
                        new Organization.Position(1996, Month.SEPTEMBER, 2001, Month.MAY, "Engeener", "Facultet")),
                new Organization("Institute",  "http://institute.com",
                        new Organization.Position(2001, Month.SEPTEMBER, 2005, Month.MAY, "Bakalavr", "Magistratura"))
        ));

        RESUME_1.addSections(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Work3", "http://work3.com",
                        new Organization.Position(2015, Month.JANUARY, "Developer", "Some description")),
                new Organization("Work2", "http://work2.com",
                    new Organization.Position(2013, Month.APRIL, 2014, Month.DECEMBER, "Admin", "Some Admin roles")),
                new Organization("Work1", "http://work1.com",
                    new Organization.Position(2011, Month.JULY, 2014, Month.DECEMBER, "Manager", "Some managementwoles"))
        ));

        RESUME_2.addContacts(ContactType.TEL, "+7 911 111 11 11");
        RESUME_2.addContacts(ContactType.SKYPE, "RESUME_2@skype.com");

        RESUME_3.addContacts(ContactType.TEL, "+7 923 233 03 03");
        RESUME_3.addContacts(ContactType.EMAIL, "RESUME_3@yandex.ru");

        RESUME_4.addContacts(ContactType.TEL, "+7 933 333 33 33");
    }
}
