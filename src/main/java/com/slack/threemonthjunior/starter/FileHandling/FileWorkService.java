package com.slack.threemonthjunior.starter.FileHandling;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by a_pan on 15.05.2016.
 */
public class FileWorkService {
    static RandomAccessFile raf = null;

    public static int counterStringsInFile(File file) {
        int n = 0;
        try {
            raf = new RandomAccessFile(file, "r");
            String s;
            while ((s = raf.readLine()) != null) {
                n++;
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return n;

    }


    public static String getStringOnIndex(int counter, File file) {
        String current = null;
        try {
            raf = new RandomAccessFile(file, "r");
            String s;
            int n = 0;
            while ((s = raf.readLine()) != null) {
                if (n == counter && n < counterStringsInFile(file)) {
                    current = s;
                }
                n++;
            }
        } catch (IOException io) {
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return current;
    }

    public static boolean isThereStringInTheFile(String line, File file) {
        try {
            raf = new RandomAccessFile(file, "r");
            String s;
            while ((s = raf.readLine()) != null) {
                if (line.equals(s)) {
                    return true;
                }
            }
        } catch (IOException io) {
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return false;
    }

    public static int getNumberLineOfString(String line, File file) {
        int position = -1;
        try {
            raf = new RandomAccessFile(file, "r");
            String s;
            int n = 0;
            while ((s = raf.readLine()) != null) {
                if (line != null && line.equals(s)) {
                    return n;
                }
                n++;
            }
        } catch (IOException io) {
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return -1;
    }
}
