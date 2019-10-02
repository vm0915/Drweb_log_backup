package project.vm0915.drweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

public class MainForm extends JFrame {
    private JButton configButton;
    private JComboBox comboBox1;
    private JButton createButton;
    private JPanel panel;
    private JTextField nameField;
    private JLabel label1;
    private JTextField numberField;

    private String inputName = "";
    private String logText;
    Saver saver;

    public MainForm(final Saver saver){
        this.saver = saver;
        String[] items = {
                "Lv_",
                "pak_",
                "Lv_pak_",
                "doc_",
                ""
        };
        comboBox1.setEditable(true);
        for(String s: items) {
            comboBox1.addItem(s);
        }
        comboBox1.setEditable(false);
        //System.out.println(comboBox1.getItemCount());
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label1.setText("В обработке");
                MainForm.super.repaint();
                try {
                    File drWebCommonLogFile = FileFinder.fileFinder(saver.getPathFrom());
                    String[] newFile = FileParser.findLastCheckLog(drWebCommonLogFile);
                    logText = newFile[0];
                }
                catch (NullPointerException e1){
                    label1.setText("Нет файлов в исходном каталоге");
                }
                catch (FileNotFoundException e1){
                    label1.setText("Исходный файл не найден");
                }
                inputName = comboBox1.getSelectedItem() + nameField.getText();
                createLogFile();
            }
        });
        configButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfigForm configForm = new ConfigForm(saver);
            }
        }));
        setVisible(true);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //saver.serialization();
                System.exit(0);
            }
        });
    }

    public void createLogFile(){
        try{
            FileCreator.createLogFile(saver.getPathTo(), inputName, logText);
            label1.setText("Готово. " + "Создан файл: " + inputName + ".txt");
        }
        catch(FileNotFoundException e){
            label1.setText("Не существует путь назначения");
        }
    }
}
