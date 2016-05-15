package Compare;

import Compare.FileHandling.FileMaker;
import Compare.FileHandling.RecoveryLastVersion;
import Compare.FileHandling.Writer;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by a_pan on 12.05.2016.
 */
public class CompareFile {
    static FileMaker fileMaker;
    private RandomAccessFile raf = null;
    ArrayList<Integer> oldString = new ArrayList();
    ArrayList<Integer> newString = new ArrayList();

    public void compareTwoFile() {
        fileMaker = new FileMaker();
        fileMaker.accsesToFiles("D:" + System.getProperty("file.separator") + "test");
        DeltaData.createDeltaChanges(fileMaker);
        RecoveryLastVersion.recoveryFile(fileMaker);



        //vostanovlenie();


    }
//
////    private void accsesToFiles(String path) {
////        try {
////            getAccessFile(path + "test_new.txt");
////            getAccessFile(path + "test_old.txt");
////            getAccessFile(path + "tmp.txt");
////
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////
////    }
//
////    private File getAccessFile(String way) {
////        File unit = new File(way);
////        if (!unit.exists()) {
////            unit.createNewFile();
////        }
////        return unit;
////    }
//
//    private void getOldFile() throws Exception {
//        oldVersion = new File("list.txt");
//    }
//
//    private void getDeltaFile() throws Exception {
//        deltaFile = new File("Delta.info");
//        if (deltaFile.exists()) {
//            deltaFile.delete();
//            deltaFile.createNewFile();
//        }
//    }
//
//    private void vostanovlFile() throws Exception {
//        vostVersion = new File("Vost.txt");
//        if (vostVersion.exists()) {
//            vostVersion.delete();
//            vostVersion.createNewFile();
//        }
//    }
//
//
//    private void createDeltaChanges() {
//
//        String newStringForTest;
//        String oldStringForTest;
//        for (int stringIndex = 0; stringIndex < counterStringsInFile(newVersion); stringIndex++) {
//            newStringForTest = getStringOnIndex(stringIndex, newVersion);
//            oldStringForTest = getStringOnIndex(stringIndex, oldVersion);
//
//            if (!isThereStringInTheFile(newStringForTest, oldVersion)) {
//                Writer.FileWrite("> " + getStringOnIndex(stringIndex, newVersion) + " $" + stringIndex);
//                System.out.println("> " + getStringOnIndex(stringIndex, newVersion) + " $" + stringIndex);
//            } else {
//                oldString.add(newStringForTest.hashCode());
//            }
//
//        }
//
//        for (int si = 0; si < counterStringsInFile(oldVersion); si++) {
//            oldStringForTest = getStringOnIndex(si, oldVersion);
//            if (!isThereStringInTheFile(oldStringForTest, newVersion)) {
//                int oldIndex = getNumberLineOfString(oldStringForTest, oldVersion);
//                Writer.FileWrite("< " + oldStringForTest + " $" + oldIndex);
//
//            }
//            if (isThereStringInTheFile(oldStringForTest, newVersion)) {
//                newString.add(oldStringForTest.hashCode());
//            }
//
//        }
//        sdvig();
//
//    }
//
//    private void sdvig() {
//        for (int i = 0; i < newString.size(); i++) {
//
//            if (i == oldString.indexOf(newString.get(i))) {
//                Writer.FileWrite("*" + newString.get(i) + "$" + oldString.indexOf(newString.get(i)));
//            }
//        }
//
//
//    }
//
//    private void vostanovlenie() {
//        String obrabotka;
//        for (int i = 0; i < counterStringsInFile(deltaFile); i++) {
//            obrabotka = getStringOnIndex(i, deltaFile);
//            parsing1(obrabotka);
//        }
//        for (int i = 0; i < counterStringsInFile(deltaFile); i++) {
//            obrabotka = getStringOnIndex(i, deltaFile);
//            parsing2(obrabotka);
//        }
//
//        deleteString();
//
//    }
//
//    private void parsing1(String obrabotka) {
//        int number;
//        if (obrabotka.substring(0, 1).equals(">")) {
//            number = Integer.valueOf(obrabotka.substring(obrabotka.lastIndexOf("$") + 1));
//            markForDelete(number);
//
//        }
//
//    }
//
//    private void parsing2(String obrabotka) {
//        int number;
//        if (obrabotka.substring(0, 1).equals("<")) {
//            number = Integer.valueOf(obrabotka.substring(obrabotka.lastIndexOf("$") + 1));
//            String s = obrabotka.substring(2, obrabotka.lastIndexOf("$"));
//            addString(number, s);
//        }
//
//    }
//
//    private void markForDelete(int number) {
//        Writer.ChangeString(number, newVersion);
//    }
//
//    private void deleteString() {
//        Writer.remove(newVersion);
//
//    }
//
//    private void addString(int number, String s) {
//        Writer.addString(number, s);
//    }
//
//    private void sravnenie() {
//
//
//        for (int i = 0; i < counterStringsInFile(newVersion); i++) {
//            System.out.println(getStringOnIndex(i, newVersion).hashCode());
//        }
//    }
//
//
//    private String getLastExampleFile(String nameNewFile) {
//        return "TestN.txt";
//    }
//
//
//    private int counterStringsInFile(File file) {
//        int n = 0;
//        try {
//            raf = new RandomAccessFile(file, "r");
//            String s;
//            while ((s = raf.readLine()) != null) {
//                n++;
//            }
//        } catch (IOException io) {
//            io.printStackTrace();
//        } finally {
//            try {
//                raf.close();
//            } catch (IOException i) {
//            }
//        }
//        return n;
//
//    }
//
//    private String getStringOnIndex(int counter, File file) {
//
//        String current = null;
//        try {
//            raf = new RandomAccessFile(file, "r");
//            String s;
//            int n = 0;
//            while ((s = raf.readLine()) != null) {
//                if (n == counter && n < counterStringsInFile(file)) {
//                    current = s;
//                }
//                n++;
//            }
//        } catch (IOException io) {
//        } finally {
//            try {
//                raf.close();
//            } catch (IOException i) {
//            }
//        }
//        return current;
//    }
//
//    private int getNumberLineOfString(String line, File file) {
//        int position = -1;
//        try {
//            raf = new RandomAccessFile(file, "r");
//            String s;
//            int n = 0;
//            while ((s = raf.readLine()) != null) {
//                if (line != null && line.equals(s)) {
//                    return n;
//                }
//                n++;
//            }
//        } catch (IOException io) {
//        } finally {
//            try {
//                raf.close();
//            } catch (IOException i) {
//            }
//        }
//        return -1;
//    }
//
//    private boolean isThereStringInTheFile(String line, File file) {
//        try {
//            raf = new RandomAccessFile(file, "r");
//            String s;
//            while ((s = raf.readLine()) != null) {
//                if (line.equals(s)) {
//                    return true;
//                }
//            }
//        } catch (IOException io) {
//        } finally {
//            try {
//                raf.close();
//            } catch (IOException i) {
//            }
//        }
//        return false;
//    }
//
//    public void setNewVersion(File newVersion) {
//        this.newVersion = newVersion;
//    }
//
//    public void setOldVersion(File oldVersion) {
//        this.oldVersion = oldVersion;
//    }
    }

