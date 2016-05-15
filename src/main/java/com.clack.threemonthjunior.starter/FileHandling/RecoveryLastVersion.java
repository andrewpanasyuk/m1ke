package Compare.FileHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a_pan on 15.05.2016.
 */
public class RecoveryLastVersion {
    private static List<String> recoveryData = new ArrayList<String>();
    private static String mark = "<<<del>>>";


    public static void recoveryFile(FileMaker fileMaker) {
        createTempDataList(fileMaker);
        String currentString;
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getDeltaFile()); i++) {
            currentString = FileWorkService.getStringOnIndex(i, fileMaker.getDeltaFile());
            parsingCurrentStringForDelete(currentString);
        }
        deleteMarkElement();
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getDeltaFile()); i++) {
            currentString = FileWorkService.getStringOnIndex(i, fileMaker.getDeltaFile());
            parsingCurrentStringForRecovery(currentString);
        }


        printArray();
    }

    private static void parsingCurrentStringForDelete(String currentString) {
        if (currentString.substring(0, 1).equals(">")) {
            int numberString = Integer.valueOf(currentString.substring(currentString.lastIndexOf("$") + 1));
            markStringForDelete(numberString);
        }
    }

    private static void parsingCurrentStringForRecovery(String currentString){
        if (currentString.substring(0, 1).equals("<")) {
            int numberString = Integer.valueOf(currentString.substring(currentString.lastIndexOf("$") + 1));
            String recoverString = currentString.substring(2, currentString.lastIndexOf("$"));
            addRecoveryString(numberString, recoverString);
        }
    }

    private static void addRecoveryString(int numberString, String recoverString){
        List<String> tmpList = new ArrayList<String>();
        for (int i = 0; i < recoveryData.size(); i++ ){
            if (i == numberString){
                tmpList.add(recoverString);
            }
            tmpList.add(recoveryData.get(i));
        }
        recoveryData = tmpList;
    }

    private static void markStringForDelete(int numberString){
        recoveryData.set(numberString, mark);
    }

    private static void deleteMarkElement(){
        for (int i = 0; i < recoveryData.size(); i++){
            if (recoveryData.get(i).contains(mark)){
                recoveryData.remove(i);
                deleteMarkElement();
            }
        }
    }

    private static void printArray(){
        for (int i = 0; i < recoveryData.size(); i++){
            System.out.println(recoveryData.get(i));
        }
    }

    private static void createTempDataList(FileMaker fileMaker){
        for (int i = 0; i < FileWorkService.counterStringsInFile(fileMaker.getNewFileVersion()); i++){
            recoveryData.add(FileWorkService.getStringOnIndex(i, fileMaker.getNewFileVersion()));
        }
    }
}
