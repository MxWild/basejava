package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error: ", e);
        }

        File dir = new File("./src/com/urise/webapp");

        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PrintDirectoryResursive(dir);

    }

    private static void PrintDirectoryResursive(File dir) {
        File[] files = dir.listFiles();

        Objects.requireNonNull(files, "Empty dir");

        for (File file : files) {
            if (file.isFile()) {
                System.out.println("This is file: " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("This is directory: " + file.getName());
                PrintDirectoryResursive(file);
            }
        }

    }

}
