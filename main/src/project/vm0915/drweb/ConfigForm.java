package project.vm0915.drweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class ConfigForm extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private String pathFrom = "";
    private String pathTo = "";
    private String inputName = "";
    private String logText;


    public ConfigForm(final Saver saver){
        textField1.setText(saver.getPathFrom());
        textField2.setText(saver.getPathTo());
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /**
                pathFrom = textField1.getText();
                pathTo = textField2.getText();
                label3.setText("В обработке");
                ConfigForm.super.repaint();
                try {
                    File drWebCommonLogFile = FileFinder.fileFinder(pathFrom);
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
                }*/
                saver.setPathFrom(textField1.getText());
                saver.setPathTo(textField2.getText());
            }
        });
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                saver.serialization();
                dispose();
                //System.exit(0);
            }
        });

        this.setContentPane(panel1);
        this.pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,190));
        this.setSize(400,190);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,
                         dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }

    public void setInputName(String name){
        inputName = name;
    }
}
