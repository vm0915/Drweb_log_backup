package project.vm0915.drweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser   {
    /**
     * String[] findLastCheckLog(File file)
     * получает на вход файл логов, возвращает конец файла, начиная с последнего вхождения ключевого слова
     * (результат последней проверки)
     * String[] parser(String)
     * получает на вход результат последней проверки, возвращает имя файла, дату и время проверки
     */
    public static String[] findLastCheckLog(File file) throws FileNotFoundException {
        String beginningKeyWord = "Dr.Web Scanner SE for Windows";
        String objectsToScanKeyWord = "Object(s) to scan:";
        String lastLog = "";
        String scannedFilePath = "";
        String firstScannedFileLine = "";
        int linePointerToBeginning = 0;
        int linePointerToObjectsToScan = 0;
        boolean hasKeyWord = false;
        //чтение файла и поиск строк с ключевыми словами
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            int lineNum = 0;
            linePointerToBeginning = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains(beginningKeyWord)) { //check input
                    linePointerToBeginning = lineNum;
                    hasKeyWord = true;
                }
                if (line.contains(objectsToScanKeyWord)) { //check input
                    linePointerToObjectsToScan = lineNum;
                }
                lineNum++;
            }
            if (!hasKeyWord){
                throw new FileNotFoundException();
            }
            System.err.println("Scanner read " + lineNum + " lines in file");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.print("Scanner didn't found file");
            throw(e);
        }
        //
        try {
            Scanner scanner = new Scanner(file,"UTF-8");
            int lineNum = 0;
            System.err.println("Последнее вхождение \" " + objectsToScanKeyWord + "\" на строке " + linePointerToObjectsToScan);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lineNum++;
                if (lineNum > linePointerToBeginning - 1) {
                    if (lineNum == linePointerToBeginning){
                        if (line.contains("п»ї"))
                        {
                            line = line.substring("п»ї".length(), line.length());
                        }
                    }
                    lastLog = lastLog.concat(line + "\r\n");
                    if (lineNum == linePointerToObjectsToScan + 2){
                        scannedFilePath = line.substring(" - ".length(), line.length());
                        System.err.println("Scanned file path: " + scannedFilePath);
                    }
                    if (lineNum == linePointerToObjectsToScan + 5){
                        firstScannedFileLine = line;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("Scanner didn't found file");
        }

        File lastScannedFile = new File(scannedFilePath);
        String preName = lastScannedFile.getName();
        if (preName.equals("")){
            String beginning =
                    firstScannedFileLine.substring(firstScannedFileLine.indexOf(scannedFilePath) + scannedFilePath.length());
            int indexOfSlash;
            indexOfSlash = beginning.indexOf("\\");
            if (indexOfSlash == -1)
                preName = beginning;
            else
                preName = beginning.substring(0,indexOfSlash);
        }
        System.err.println("preName = " + preName);
        String []newArray = new String[2];
        newArray[0] = lastLog;
        newArray[1] = preName; //scanned file name
        return newArray;
    }
}
