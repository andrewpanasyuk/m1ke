package com.slack.threemonthjunior.starter.AnalyticFile;


import com.slack.threemonthjunior.starter.FileHandling.FileMaker;
import com.slack.threemonthjunior.starter.FileHandling.RecoveryLastVersion;


/**
 * Created by a_pan on 12.05.2016.
 */
public class CompareFile {
    static FileMaker fileMaker;

    public void compareTwoFile() {
        fileMaker = new FileMaker();
        fileMaker.accessToFiles("D:" + System.getProperty("file.separator") + "test");
        DeltaData.createDeltaChanges(fileMaker);
        RecoveryLastVersion.recoveryFile(fileMaker);

    }

}

