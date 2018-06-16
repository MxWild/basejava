package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error: ", e);
        }

        File dir = new File("./src/com/");

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

        printDirectoryRecursive(dir, "");

    }

    private static void printDirectoryRecursive(File dir, String tab) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(tab + " -> " + file.getName());
                    printDirectoryRecursive(file, tab + "   ");
                }
            }
        }

    }

//    private static void printDirectoryRecursive(File dir, String tab) {
//        File[] files = dir.listFiles();
//
////        Objects.requireNonNull(files, "Empty dir");
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
////                    System.out.println(tab + "File -> " + file.getName());
//                } else if (file.isDirectory()) {
//                    System.out.println(tab + "Directory -> " + file.getName());
//                    printDirectoryRecursive(file, tab += " ");
//                }
//            }
//        }
//
//    }

}
