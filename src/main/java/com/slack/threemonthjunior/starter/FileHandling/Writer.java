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

    public static void deltaWriter(String line) {
    currentFile = new File(nameDeltaFile);
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

    public static void ChangeString(int number, File file) {
//        File file = new File("Delta.info");
        RandomAccessFile randomAccessFile = null;

//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException ex){
//                ex.printStackTrace();
//            }
//        }

        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            int i = 0;
            String tempString;
            while ((tempString = randomAccessFile.readLine()) != null) {
                if (i == number - 1) {
                    randomAccessFile.writeBytes("#");
                }
                i++;
                //randomAccessFile.seek(randomAccessFile.getFilePointer());
            }

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


    public static void remove(File file) {
        File list_temp = new File("list_temp.txt");
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(list_temp)), true);
            String str;
            while ((str = br.readLine()) != null) {
                if (!str.substring(0, 1).equals("#")) {

                    //System.out.println("*********************" + str + "*************************");
                    pw.println(str);
                }
            }
        } catch (IOException io) {
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException io) {
            }
        }
        //file.delete();
        //list_temp.renameTo(file);
        //list_temp.delete();
    }


    public static void FWrite(String line, File file) {
//        File file = new File("Delta.info");
        RandomAccessFile randomAccessFile = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            String tempString;
            while ((tempString = randomAccessFile.readLine()) != null) {
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

    public static void addString(int number, String string) {
        File file = new File("list_temp.txt");
        File file_tmp = new File("tmp.txt");
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file_tmp)), true);
            String str;
            int i = 0;
            while ((str = br.readLine()) != null) {
                pw.println(str);

                if (i == number - 1) {
                    pw.println(string);
                }
                i++;
            }
        } catch (IOException io) {
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException io) {
            }
        }
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
}