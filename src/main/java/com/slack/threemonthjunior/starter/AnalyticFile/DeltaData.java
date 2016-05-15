package com.slack.threemonthjunior.starter.AnalyticFile;



import com.slack.threemonthjunior.starter.FileHandling.FileMaker;
import com.slack.threemonthjunior.starter.FileHandling.FileWorkService;
import com.slack.threemonthjunior.starter.FileHandling.Writer;

import java.util.ArrayList;

/**
 * Created by a_pan on 15.05.2016.
 */
public class DeltaData {
    static ArrayList<String> oldString = new ArrayList();
    static ArrayList<String> newString = new ArrayList();

    public static void createDeltaChanges(FileMaker fileMaker) {
        selectAddString(fileMaker);
        selectDelString(fileMaker);
        selectChangedPosString();
    }

    private static void selectAddString(FileMaker fileMaker) {
        String newStringForTest;

        for (int stringIndex = 0; stringIndex < FileWorkService.counterStringsInFile(fileMaker.getNewFileVersion()); stringIndex++) {
            newStringForTest = FileWorkService.getStringOnIndex(stringIndex, fileMaker.getNewFileVersion());

            if (!FileWorkService.isThereStringInTheFile(newStringForTest, fileMaker.getOldFileVersion())) {
                Writer.deltaWriter("> " + FileWorkService.getStringOnIndex(stringIndex, fileMaker.getNewFileVersion()) + " $" + stringIndex);
            } else {
                oldString.add(newStringForTest);
            }
        }
    }

    private static void selectDelString(FileMaker fileMaker){
        String oldStringForTest;
        for (int stringIndex = 0; stringIndex < FileWorkService.counterStringsInFile(fileMaker.getOldFileVersion()); stringIndex++) {
            oldStringForTest = FileWorkService.getStringOnIndex(stringIndex, fileMaker.getOldFileVersion());
            if (!FileWorkService.isThereStringInTheFile(oldStringForTest, fileMaker.getNewFileVersion())) {
                int oldIndex = FileWorkService.getNumberLineOfString(oldStringForTest, fileMaker.getOldFileVersion());
                Writer.deltaWriter("< " + oldStringForTest + " $" + oldIndex);

            }
            if (FileWorkService.isThereStringInTheFile(oldStringForTest, fileMaker.getNewFileVersion())) {
                newString.add(oldStringForTest);
            }

        }
    }

    private static void selectChangedPosString(){
        for (int i = 0; i < newString.size(); i++) {

            if (i != oldString.indexOf(newString.get(i))) {
                Writer.deltaWriter("*" + newString.get(i) + "$" + oldString.indexOf(oldString.get(i)));
            }
        }
    }
}