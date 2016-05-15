package com.slack.threemonthjunior.starter.FileHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a_pan on 15.05.2016.
 */
public class RecoveryLastVersion {
    private static List<String> recoveryData = new ArrayList<String>();
    private static List<String> tempData = new ArrayList<String>();
    private static String mark = "<<<del>>>";
    private static FileMaker fileMaker;
    private static String currentString;

    public static void recoveryFile(FileMaker fileMaker) {
        setFileMaker(fileMaker);
        createTempDataList();
        delNewString();
        recoveryOldString();
        changeStringPosition();
        writeRecoveryFile();

//        printArray();
    }

    private static void createTempDataList() {
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getNewFileVersion()); i++) {
            recoveryData.add(FileWorkService.getStringOnIndex(i, fileMaker.getNewFileVersion()));
        }
    }

    private static void delNewString() {
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getDeltaFile()); i++) {
            currentString = FileWorkService.getStringOnIndex(i, fileMaker.getDeltaFile());
            parsingCurrentStringForDelete();
        }
        deleteMarkElement();
    }

    private static void recoveryOldString() {
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getDeltaFile()); i++) {
            currentString = FileWorkService.getStringOnIndex(i, fileMaker.getDeltaFile());
            parsingCurrentStringForRecovery();
        }
    }

    private static void changeStringPosition() {
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getDeltaFile()); i++) {
            currentString = FileWorkService.getStringOnIndex(i, fileMaker.getDeltaFile());
            parsingCurrentStringForChange();
        }
    }

    private static void writeRecoveryFile(){
        for (int i = 0; i < recoveryData.size(); i++){
            fileMaker.getNewFileVersion().delete();
            Writer.recoveryFileWriter(recoveryData.get(i));
        }
//        Writer.renameFile(fileMaker.getTempFile(), fileMaker.getNewFileVersion());
//        fileMaker.getTempFile().delete();


    }

    private static void parsingCurrentStringForChange() {
        if (currentString.substring(0, 1).equals("*")) {
            int numberString = Integer.valueOf(currentString.substring(currentString.lastIndexOf("$") + 1));
            String stringForUpdate = searchStringForChangePosition(currentString.substring(1, currentString.lastIndexOf("$")));
            updateTempData(stringForUpdate);
            addRecoveryString(numberString, stringForUpdate);
        }
    }

    private static void updateTempData(String actualString) {
        for (int i = 0; i < recoveryData.size(); i++) {
            if (recoveryData.get(i).equals(actualString)) {
                recoveryData.remove(i);
            }
        }
    }

    private static String searchStringForChangePosition(String stringForUpdate) {
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getNewFileVersion()); i++) {
            if (FileWorkService.getStringOnIndex(i, fileMaker.getNewFileVersion()).equals(stringForUpdate)) {
                return FileWorkService.getStringOnIndex(i, fileMaker.getNewFileVersion());
            }
        }
        return null;
    }


    private static void parsingCurrentStringForRecovery() {
        if (currentString.substring(0, 1).equals("<")) {
            int numberString = Integer.valueOf(currentString.substring(currentString.lastIndexOf("$") + 1));
            String recoverString = currentString.substring(2, currentString.lastIndexOf("$"));
            addRecoveryString(numberString, recoverString);
        }
    }

    private static void parsingCurrentStringForDelete() {
        if (currentString.substring(0, 1).equals(">")) {
            int numberString = Integer.valueOf(currentString.substring(currentString.lastIndexOf("$") + 1));
            markStringForDelete(numberString);
        }
    }

    private static void addRecoveryString(int numberString, String recoverString) {
        for (int i = 0; i < recoveryData.size(); i++) {
            if (i == numberString) {
                tempData.add(recoverString);
            }
            tempData.add(recoveryData.get(i));
        }
        setRecoveryData(new ArrayList<String>(tempData));
        tempData.clear();
    }

    private static void markStringForDelete(int numberString) {
        recoveryData.set(numberString, mark);
    }

    private static void deleteMarkElement() {
        for (int i = 0; i < recoveryData.size(); i++) {
            if (recoveryData.get(i).contains(mark)) {
                recoveryData.remove(i);
                deleteMarkElement();
            }
        }
    }

    private static void printArray() {
        for (int i = 0; i < recoveryData.size(); i++) {
            System.out.println(recoveryData.get(i));
        }
    }

    private static void setFileMaker(FileMaker fileMaker) {
        RecoveryLastVersion.fileMaker = fileMaker;
    }

    private static void setRecoveryData(List<String> recoveryData) {
        RecoveryLastVersion.recoveryData = recoveryData;
    }
}