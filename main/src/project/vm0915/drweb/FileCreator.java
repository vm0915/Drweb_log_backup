package project.vm0915.drweb;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileCreator {
    /**
     * void createLogFile(String path, String name, String text)
     * получает путь для создания файла, его имя, содержимое файла
     * создает файл
     *
     * TODO:
     * проверить что файл с таким именем уже существует
     */
    public static void createLogFile(String path, String name, String text) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(path + "\\" + name + ".txt", "UTF-8");
            writer.print(text);
            writer.close();
            System.err.println("Создан файл " + path + "\\" + name);
            System.out.print(text);
        }
        catch (FileNotFoundException e){
            System.err.println("Creator didn't find file");
            throw(e);
        }
        catch (UnsupportedEncodingException e){
            System.err.println("Unsupported encoding");
        }
    }
}
