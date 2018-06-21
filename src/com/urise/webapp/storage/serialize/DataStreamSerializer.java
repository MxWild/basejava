package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();

            writeCollection(dos, contacts.entrySet());

            Map<SectionType, Section> sections = resume.getSections();

            sections.forEach((k, v) -> {
                switch (k) {
                    case PERSONAL:
                    case OBJECTIVE:
                        writeText(dos, ((TextSection) v).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) v).getList();
                        try {
                            writeCollection(dos, list);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = ((OrganizationSection) v).getOrganizations();

                        organizationList.forEach(value -> {
                            writeText(dos, value.getHomePage().getName());

                            if (value.getHomePage().getUrl() != null) {
                                writeText(dos, value.getHomePage().getUrl());
                            } else writeText(dos, "null");

                            value.getPositions().forEach(position -> {
                                writeLocalDate(dos, position.getDateStart());
                                writeLocalDate(dos, position.getDateEnd());
                                writeText(dos, position.getTitle());
                                writeText(dos, position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();
            System.out.println(size);

            SectionType sectionType = SectionType.valueOf(dis.readUTF());

            System.out.println("Debug - >" + sectionType.getTitle());

            // TODO implements Sections
            return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            dos.writeUTF(item.toString());
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate dateStart) {
        try {
            dos.writeInt(dateStart.getYear());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dos.writeInt(dateStart.getMonthValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeText(DataOutputStream dos, String value) {
        try {
            dos.writeUTF(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
