package project.vm0915.drweb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmallForm extends JFrame {
    private JTextField textField1;
    private JButton createButton;
    private JPanel panel;

    public SmallForm(String presetName, final MainForm mainForm) {
        textField1.setText(presetName);
        this.setContentPane(panel);
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрывает приложение или только окно?
        this.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainForm.setInputName(textField1.getText());
                mainForm.createLogFile();
                dispose();
            }
        });
    }
}
