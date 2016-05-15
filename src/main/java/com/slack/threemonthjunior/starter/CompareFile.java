package com.slack.threemonthjunior.starter;

import com.slack.threemonthjunior.starter.FileHandling.FileMaker;
import com.slack.threemonthjunior.starter.FileHandling.RecoveryLastVersion;


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
        fileMaker.accessToFiles("D:" + System.getProperty("file.separator") + "test");
        DeltaData.createDeltaChanges(fileMaker);
        RecoveryLastVersion.recoveryFile(fileMaker);
    }
}

