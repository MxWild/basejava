package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_TEST = "test_uuid";
    public static final Resume resume4 = new Resume(UUID_TEST);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(resume1);
        collection.add(resume2);
        collection.add(resume3);

        // Удаление в итератора, ниже через лямбду
//      Iterator<Resume> iterator = collection.iterator();
//        while (iterator.hasNext()) {
//            Resume r = iterator.next();
//
//            if(Objects.equals(r.getUuid(), UUID_2)) {
//                iterator.remove();
//            }
//        }

        // Тоже самое только через лябду
        collection.removeIf(r -> Objects.equals(r.getUuid(), UUID_2));

//        for (Resume r : collection) {
//            System.out.println(r);
//            if (Objects.equals(r.getUuid(), UUID_1)) {
//                //collection.remove(r);
//            }
//        }

        System.out.println(collection.toString() + "\n");

        // Инициализация через анонимный класс
        Map<String, Resume> map = new HashMap<String, Resume>() {
            {
                put(UUID_1,resume1);
                put(UUID_2,resume2);
                put(UUID_3,resume3);
            }
        };

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        System.out.println();

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
