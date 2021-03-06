package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "name1";
    private static final Resume resume1 = new Resume(UUID_1, NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String NAME_2 = "name2";
    private static final Resume resume2 = new Resume(UUID_2, NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String NAME_3 = "name3";
    private static final Resume resume3 = new Resume(UUID_3, NAME_3);

    private static final String UUID_TEST = "test_uuid";
    private static final String NAME_TEST = "test_name";
    public static final Resume resume4 = new Resume(UUID_TEST, NAME_TEST);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(resume1);
        collection.add(resume2);
        collection.add(resume3);

        collection.removeIf(r -> Objects.equals(r.getUuid(), UUID_2));

        System.out.println(collection.toString() + "\n");

        // Инициализация через анонимный класс
        Map<String, Resume> map = new HashMap<>();

        map.put(UUID_1, resume1);
        map.put(UUID_2, resume2);
        map.put(UUID_3, resume3);


        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        List<Resume> resumes = Arrays.asList(resume1, resume2, resume3);
        resumes.remove(1);
        System.out.println(resumes.toString());
    }

}
