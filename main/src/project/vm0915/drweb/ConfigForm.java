package project.vm0915.drweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigForm extends JFrame {
    /**
     * TODO:
     * запретить переход к главному окну пока это открыто
     */
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public ConfigForm(final Saver saver){
        textField1.setText(saver.getPathFrom());
        textField2.setText(saver.getPathTo());
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
}
