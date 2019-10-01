package project.vm0915.drweb;
/**TODO:
 * выбор пути сериализации
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class MainForm extends JFrame {
    private JButton button3;
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private String pathFrom = "";
    private String pathTo = "";
    private String inputName = "";
    private String logText;

    public MainForm(){
        this.setContentPane(panel1);
        this.pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pathFrom = textField1.getText();
                pathTo = textField2.getText();
                label3.setText("В обработке");
                try {
                    File drWebCommonLogFile = FileFinder.fileFinder(pathFrom); //здесь все ок только с одной чертой
                    String[] newFile = FileParser.findLastCheckLog(drWebCommonLogFile);
                    inputName = newFile[1];
                    logText = newFile[0];
                    changeName(inputName);
                }
                catch (NullPointerException e1){
                    label3.setText("Нет файлов в исходном каталоге");
                }
                catch (FileNotFoundException e1){
                    label3.setText("Исходный файл не найден");
                }
            }
        });
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //Serialization
                serialization();
                System.exit(0);
            }
        });
        //Deserialization
        try {
            FileInputStream file = new FileInputStream("C:\\save\\save.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            PathSaver pathSaver = (PathSaver) in.readObject();
            pathFrom = pathSaver.getPathFrom();
            pathTo = pathSaver.getPathTo();
            textField1.setText(pathFrom);
            textField2.setText(pathTo);
            in.close();
            file.close();
            System.out.println("Object has been deserialized\n");
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
        this.setMinimumSize(new Dimension(400,190));
        this.setSize(400,190);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }


    public void changeName(String presetName){
        SmallForm form = new SmallForm(presetName, this);

    }

    public void setInputName(String name){
        inputName = name;
    }

    public void createLogFile(){
        try{
        FileCreator.createLogFile(pathTo, inputName, logText); //здесь имя должен возращать метод
        label3.setText("Готово. " + "Создан файл: " + inputName + ".txt");
        }
        catch(FileNotFoundException e){
            label3.setText("Не существует путь назначения");
        }
    }

    public void serialization(){
        try {
            PathSaver pathSaver = new PathSaver(pathFrom,pathTo);
            FileOutputStream file = new FileOutputStream("C:\\save\\save.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(pathSaver);
            out.close();
            file.close();
            System.out.println("Object has been serialized\n");
        }

        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
    }
}
