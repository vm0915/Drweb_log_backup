package project.vm0915.drweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.security.util.Debug;

import java.io.File;

public class FileFinder {
    //private static final Logger log = LogManager.getLogger();

/***
 * File fileFinder(path)
 * находит и возвращает последний измененный файл в path
 */
    public static File fileFinder(String dirPath){
        System.err.println("Поиск последнего измененного файла в " + dirPath);
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0){
            throw new NullPointerException();
        }

        File lastModifiedFile = files[0]; //можно добавить проверку на расширение файла
        for (int i = 1; i < files.length; i++){
            if ((lastModifiedFile.lastModified() < files[i].lastModified())){
                lastModifiedFile = files[i];
            }
        }
        System.err.println("Найден файл: " + lastModifiedFile.getName());
        return lastModifiedFile;
    }
}
