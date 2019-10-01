package project.vm0915.drweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SmallForm extends JFrame {
    private JTextField textField1;
    private JButton createButton;
    private JPanel panel;

    public SmallForm(String presetName, final MainForm mainForm) {
        textField1.setText(presetName);
        System.out.println(textField1.getPreferredSize());
        this.setMinimumSize(textField1.getPreferredSize());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainForm.setInputName(textField1.getText());
                mainForm.createLogFile();
                dispose();
            }
        });
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //Serialization
                mainForm.serialization();
                System.exit(0);
            }
        });
    }
}
