package project.vm0915.drweb;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileCreator {
    /**
     * void createLogFile(String path, String name, String text)
     * получает путь для создания файла, его имя, содержимое файла
     * создает файл
     */
    public static void createLogFile(String path, String name, String text) throws FileNotFoundException {
        try {

        System.out.println(path + "\\" + name);
        PrintWriter writer = new PrintWriter(path + "\\" + name + ".txt", "UTF-8");
        System.out.print(text);
        writer.print(text);
        writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("creator didn't find file");
            throw(e);
        }
        catch (UnsupportedEncodingException e){
            System.out.println("unsupported encoding");
        }
    }
}
