package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();

            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> pair : contacts.entrySet()) {
                dos.writeUTF(pair.getKey().name());
                dos.writeUTF(pair.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());

//            sections.forEach((k, v) -> {
            for (Map.Entry<SectionType, Section> section : sections.entrySet()) {

                dos.writeUTF(section.getKey().name());

                switch (section.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section.getValue()).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> lists = ((ListSection) section.getValue()).getList();
                        dos.writeInt(lists.size());
                        for (String list : lists) {
                            dos.writeUTF(list);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = ((OrganizationSection) section.getValue()).getOrganizations();
                        dos.writeInt(organizationList.size());
                        for (Organization organization : organizationList) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());

                            List<Organization.Position> positions = organization.getPositions();
                            for (Organization.Position position : positions) {
                                writeLocalDate(dos, position.getDateStart());
                                writeLocalDate(dos, position.getDateEnd());
                                try {
                                    dos.writeUTF(position.getTitle());
                                    dos.writeUTF(position.getDescription());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        break;
                }
            }
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
            int numSections = dis.readInt();

            for (int i = 0; i < numSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSections(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSections(sectionType, new ListSection(readList(dis, dis.readInt())));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int numOrganization = dis.readInt();
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < numOrganization; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            organizations.add(new Organization(name, url, new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF())));
                        }
                        resume.addSections(sectionType, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }

    private List<String> readList(DataInputStream dis, int i) throws IOException {
        List<String> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            list.add(dis.readUTF());
        }
        return list;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate dateStart) {
        try {
            dos.writeInt(dateStart.getYear());
            dos.writeInt(dateStart.getMonthValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
