package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
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
                        try {
                            dos.writeUTF(((TextSection) v).getDescription());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
//                        try {
//                            writeCollection(dos, organizationList);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        organizationList.forEach(value -> {
                            System.out.println("Name home page -> " + value.getHomePage().getName());
                            System.out.println("Name url page -> " + value.getHomePage().getUrl());
                            value.getPositions().forEach(position -> {
                                System.out.println("DateStart -> " + position.getDateStart());
                                System.out.println("DateEnd -> " + position.getDateEnd());
                                System.out.println("Title -> " + position.getTitle());
                                System.out.println("Descr -> " + position.getDescription());
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

            // TODO implements Sections
            return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            dos.writeUTF(item.toString());
            System.out.println("item -> " + item);
        }
    }

}
