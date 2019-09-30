package project.vm0915.drweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser   {
    /**
     * String findLastCheckLog(File file)
     * получает на вход файл логов, возвращает конец файла, начиная с последнего вхождения ключевого слова
     * (результат последней проверки)
     * String[] parser(String)
     * получает на вход результат последней проверки, возвращает имя файла, дату и время проверки
     */
    public static String[] findLastCheckLog(File file) throws FileNotFoundException {
        String beginningKeyWord = "Dr.Web Scanner SE for Windows";
        String scanTimeKeyWord = "Scan time is";
        String lastLog = "";
        String scannedFilePath = "";
        int linePointerToBeginning = 0;
        int linePointerToScanTime = 0;
        boolean hasKeyWord = false;
        try {
            Scanner scanner = new Scanner(file);
            int lineNum = 0;
            linePointerToBeginning = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains(beginningKeyWord)) { //check input
                    linePointerToBeginning = lineNum;
                    hasKeyWord = true;
                }
                if (line.contains(scanTimeKeyWord)) { //check input
                    linePointerToScanTime = lineNum;
                }
                lineNum++;
            }
            if (!hasKeyWord){
                throw new FileNotFoundException();
            }
            System.out.println("Scanner found  - " + lineNum);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("scanner didn't found file");
            throw(e);
        }


        try {
            Scanner scanner = new Scanner(file);
            int lineNum = 0;
            System.out.println("linePointerToScanTime " + linePointerToScanTime);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lineNum++;
                if (lineNum > linePointerToBeginning - 1) {
                    lastLog = lastLog.concat(line + "\r\n");
                    if (lineNum == linePointerToScanTime - 4){
                        scannedFilePath = line;
                        System.out.println("scannedFilePath " + scannedFilePath);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("scanner didn't found file");
        }

        System.out.println("scannedFilePath - " + scannedFilePath);
        File lastScannedFile = new File(scannedFilePath);
        String preName = lastScannedFile.getName();
        System.out.println("preName - " + preName);
        System.out.println(preName.lastIndexOf(" - Ok"));
        int endOfName = preName.lastIndexOf(" - Ok");
        String []newArray = new String[2];
        newArray[0] = lastLog;
        newArray[1] = preName.substring(0, endOfName); //scanned file name
        System.out.print(newArray[1]);
        return newArray;
    }
}
