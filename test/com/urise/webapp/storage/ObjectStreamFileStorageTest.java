package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectStreamSerialize;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialize()));
    }
}