package project.vm0915.drweb;

import java.io.File;

public class FileFinder {
/***
 * File fileFinder(path)
 * находит и возвращает последний измененный файл в path
 */
    public static File fileFinder(String dirPath){
        System.out.println("fileFinder получил - " + dirPath);
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
        System.out.println("fileFinder возвращает имя" + lastModifiedFile.getName());
        return lastModifiedFile;
    }
}
