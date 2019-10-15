package project.vm0915.drweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser   {
    /**
     * String[] findLastCheckLog(File file)
     * получает на вход файл логов, возвращает конец файла, начиная с последнего вхождения ключевого слова
     * (результат последней проверки)
     * String[] parser(String)
     * получает на вход результат последней проверки, возвращает имя файла, дату и время проверки
     *
     */
    public static String[] findLastCheckLog(File file, int numberOfChecks) throws FileNotFoundException,
                                                                                    IndexOutOfBoundsException {
        String beginningKeyWord = "Dr.Web Scanner SE for Windows";
        String objectsToScanKeyWord = "Object(s) to scan:";
        String lastLog = "";
        String scannedFilePath = "";
        String firstScannedFileLine = "";
        ArrayList<Integer> linesOfCheckBeginnings = new ArrayList<Integer>();
        ArrayList<Integer> linesOfObjectsToScan = new ArrayList<Integer>();
        boolean hasKeyWord = false;
        //чтение файла и поиск строк с ключевыми словами
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            int lineNum = 0;
            //поседующие переменные нужны для игнориования записей с beginningKeyWord не оканчивающихся objectToScanKeyWord
            int lineBegin = -1;
            int lineScan = -1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains(beginningKeyWord)) { //check input
                    lineBegin = lineNum;
                    hasKeyWord = true;
                }
                if (line.contains(objectsToScanKeyWord)) { //check input
                    lineScan = lineNum;
                }
                if ((lineBegin != -1) && (lineScan != -1)){
                    linesOfCheckBeginnings.add(lineBegin);
                    linesOfObjectsToScan.add(lineScan);
                    lineBegin = -1;
                }
                lineScan = -1;
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
        if (linesOfCheckBeginnings.size() < numberOfChecks){
            throw new IndexOutOfBoundsException();
        }
        System.out.println(linesOfCheckBeginnings.size() + " - " + linesOfObjectsToScan.size());
        try {
            Scanner scanner = new Scanner(file,"UTF-8");
            int lineNum = 0;
            int linePointerToRequiredBeginning = linesOfCheckBeginnings.get(linesOfCheckBeginnings.size() - numberOfChecks);
            int linePointerToObjectsToScan = linesOfObjectsToScan.get(linesOfObjectsToScan.size() - numberOfChecks);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lineNum++;
                if (lineNum > linePointerToRequiredBeginning - 1) {
                    if (lineNum == linePointerToRequiredBeginning){
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
            System.err.print("Scanner didn't found file");
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
