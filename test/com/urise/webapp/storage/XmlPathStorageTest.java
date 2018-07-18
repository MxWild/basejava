package com.urise.webapp.storage;


import com.urise.webapp.storage.serialize.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
// Add -ea --add-modules java.xml.bind
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }

}