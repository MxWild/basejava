package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;
    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.addSections(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.addSections(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.addSections(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.addSections(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.addSections(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.addSections(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }

    // Unique identifier
    private String uuid;
    private String fullName;

    public Resume() {
    }

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContacts(ContactType contactType) {
        return contacts.get(contactType);
    }

    public void addContacts(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public Section getSections(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public void addSections(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return uuid + " : " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        int compFullName = this.fullName.compareTo(o.fullName);
        return compFullName != 0 ? compFullName : this.uuid.compareTo(o.uuid);
    }
}