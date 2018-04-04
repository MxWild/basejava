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

        Resume r3 = new Resume("uuid3", "name3");
        //r3.setUuid("uuid3"); -- add on HW4
        Resume r1 = new Resume("uuid1", "name1");
        //r1.setUuid("uuid1"); -- add on HW4
        Resume r2 = new Resume("uuid2", "name2");
        //r2.setUuid("uuid2"); -- add on HW4

        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);

        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        System.out.println("Get r3: " + SORTED_ARRAY_STORAGE.get(r3.getUuid()));


        SORTED_ARRAY_STORAGE.delete("uuid1");
        printAll();

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
