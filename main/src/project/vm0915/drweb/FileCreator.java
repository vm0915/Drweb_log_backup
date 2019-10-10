package project.vm0915.drweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileCreator {
    /**
     * void createLogFile(String path, String name, String text)
     * получает путь для создания файла, его имя, содержимое файла
     * создает файл
     */
    public static void createLogFile(String path, String name, String text) throws FileNotFoundException,
                                                                                IllegalArgumentException {
        checkForDuplicates(path, name);
        try {
            PrintWriter writer = new PrintWriter(path + "\\" + name + ".txt", "UTF-8");
            writer.print(text);
            writer.close();
            System.err.println("Создан файл " + path + "\\" + name + ".txt");
            //System.out.print(text);
        }
        catch (FileNotFoundException e){
            System.err.println("Creator didn't find file");
            throw(e);
        }
        catch (UnsupportedEncodingException e){
            System.err.println("Unsupported encoding");
        }
    }

    private static void checkForDuplicates(String dirPath, String name) throws IllegalArgumentException{
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file: files){
            if (file.getName().equals(name + ".txt")){
                throw new IllegalArgumentException();
            }
        }
    }
}
