package com.urise.webapp.storage;


import com.urise.webapp.storage.serialize.XmlStreamSerializer;

public class XmlStreamPathStorageTest extends AbstractStorageTest {

    public XmlStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }

}