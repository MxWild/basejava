package com.urise.webapp.model;

import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {


    // Unique identifier
    private final String uuid;
    private String fullName;

    private final Map<ContactSection, String> contacts = new EnumMap<>(ContactSection.class);
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

    public String getContactSection(ContactSection contactSection) {
        return contacts.get(contactSection);
    }

    public Section getDescription(SectionType sectionType) {
        return sections.get(sectionType);
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

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        int compFullName = this.fullName.compareTo(o.fullName);
        return compFullName != 0 ? compFullName : this.uuid.compareTo(o.uuid);
    }
}