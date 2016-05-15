package com.slack.threemonthjunior.starter.FileHandling;

import java.io.File;
import java.io.IOException;

/**
 * Created by a_pan on 15.05.2016.
 */
public class FileMaker {
    private File newFileVersion;
    private File oldFileVersion;
    private File deltaFile;

    public void accessToFiles(String path) {

        String fullPath = path + System.getProperty("file.separator");

        try {
            newFileVersion = getAccessFile(fullPath + "test_new.txt");
            oldFileVersion = getAccessFile(fullPath + "test_old.txt");
            deltaFile = getAccessDeltaFile(fullPath + "tmp.txt");
            Writer.setNameDeltaFile(fullPath + "tmp.txt");
            Writer.setNameNewFile(fullPath + "test_new.txt");
            Writer.setNameOldFile(fullPath + "test_old.txt");

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private File getAccessFile(String way) {
        File unit = new File(way);
        return unit;
    }
    private File getAccessDeltaFile(String way) {
        File unit = new File(way);
        if (unit.exists()) {
            try {
                unit.delete();
                unit.createNewFile();
            } catch (IOException ex){
                ex.printStackTrace();
            }

        }
        return unit;
    }

    public File getNewFileVersion() {
        return newFileVersion;
    }

    public File getOldFileVersion() {
        return oldFileVersion;
    }

    public File getDeltaFile() {
        return deltaFile;
    }
}
