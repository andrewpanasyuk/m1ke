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
    private File tempFile;

    public void accsesToFiles(String path) {

        try {
            newFileVersion = getAccessFile(path + System.getProperty("file.separator") + "test_new.txt");
            oldFileVersion = getAccessFile(path + System.getProperty("file.separator") + "test_old.txt");
            deltaFile = getAccesDeltaFile(path + System.getProperty("file.separator") + "delta.txt");
            tempFile = getAccesDeltaFile(path + System.getProperty("file.separator") + "temp.txt");
            Writer.setNameDeltaFile(path + System.getProperty("file.separator") + "delta.txt");
            Writer.setNameNewFile(path + System.getProperty("file.separator") + "test_new.txt");
            Writer.setNameOldFile(path + System.getProperty("file.separator") + "test_old.txt");
            Writer.setNameTempFile(path + System.getProperty("file.separator") + "temp.txt");

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private File getAccessFile(String way) {
        File unit = new File(way);
        return unit;
    }

    private File getAccesDeltaFile(String way) {
        File unit = new File(way);
        if (unit.exists()) {
            try {
                unit.delete();
                unit.createNewFile();
            } catch (IOException ex) {
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

    public File getTempFile() {
        return tempFile;
    }
}
