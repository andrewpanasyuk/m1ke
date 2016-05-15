package com.slack.threemonthjunior.starter.FileHandling;

import java.io.*;

/**
 * Created by a_pan on 14.05.2016.
 */
public class Writer {
    private static String nameDeltaFile;
    private static String nameOldFile;
    private static String nameNewFile;
    private static File currentFile;
    private static String nameTempFile;

    public static void deltaWriter(String line) {
        currentFile = new File(nameDeltaFile);
        write(line);
    }
    public static void recoveryFileWriter(String line){
        currentFile = new File(nameTempFile);
        write(line);
    }

    public static void write(String line) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(currentFile, "rw");
            while (randomAccessFile.readLine() != null) {
                randomAccessFile.seek(randomAccessFile.getFilePointer());
            }
            randomAccessFile.writeBytes(line + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

    public static void renameFile(File temp, File unit){
        temp.renameTo(unit);
        temp.delete();
    }

    public static void setNameOldFile(String nameOldFile) {
        Writer.nameOldFile = nameOldFile;
    }

    public static void setNameNewFile(String nameNewFile) {
        Writer.nameNewFile = nameNewFile;
    }

    public static void setNameDeltaFile(String nameDeltaFile) {
        Writer.nameDeltaFile = nameDeltaFile;
    }

    public static void setNameTempFile(String nameTempFile) {
        Writer.nameTempFile = nameTempFile;
    }
}
