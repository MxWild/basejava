package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for com.urise.webapp.storage.com.urise.webapp.storage.ArrayStorage
 */
public class MainTestArraySortedStorage {
    private static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");

        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);

        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        System.out.println("Get dummy: " + SORTED_ARRAY_STORAGE.get("dummy"));
        System.out.println("Get r3: " + SORTED_ARRAY_STORAGE.get(r3.getUuid()));


        SORTED_ARRAY_STORAGE.delete("uuid1");
        printAll();

//        Resume r1 = new Resume();
//        r1.setUuid("uuid1");
//        Resume r2 = new Resume();
//        r2.setUuid("uuid2");
//        Resume r3 = new Resume();
//        r3.setUuid("uuid3");
//
//        ARRAY_STORAGE.save(r1);
//        ARRAY_STORAGE.save(r2);
//        ARRAY_STORAGE.save(r3);
//
//        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//
//        printAll();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
//        ARRAY_STORAGE.clear();
//        printAll();
//
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
