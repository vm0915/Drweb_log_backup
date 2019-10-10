package project.vm0915.drweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MainForm extends JFrame {
    /**
     * TODO:
     * цвет выводимых ошибок
     * заменить надпись "параметры" на иконку
     */
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
                if (numberField.getText().equals("") || nameField.getText().equals("")) {
                    //label1.setForeground(Color.RED);
                    label1.setText("Заполните все поля");
                } else {
                    if (numberField.getText().equals("0")){
                        label1.setText("Количество проверок не может быть равно 0");
                        MainForm.this.pack();
                    }
                    else{
                        label1.setText("В обработке");
                        MainForm.super.repaint();
                        try {
                            File drWebCommonLogFile = FileFinder.fileFinder(saver.getPathFrom());
                            String[] newFile = FileParser.findLastCheckLog(drWebCommonLogFile, Integer.parseInt(numberField.getText()));
                            logText = newFile[0];
                            inputName = comboBox1.getSelectedItem() + nameField.getText();
                            createLogFile();
                        } catch (NullPointerException e1) {
                            label1.setText("Нет файлов в исходном каталоге");
                        } catch (FileNotFoundException e1) {
                            label1.setText("Исходный файл не найден");
                        }
                        catch (IndexOutOfBoundsException e1){
                            label1.setText("В исходном файле меньше проверок, чем Вы указали");
                            MainForm.super.pack();
                        }
                        catch (IllegalArgumentException e1){
                            label1.setText("Файл с таким именем уже существует");
                        }

                    }

                }
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
                System.exit(0);
            }
        });
        numberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
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
