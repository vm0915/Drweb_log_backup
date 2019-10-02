package project.vm0915.drweb;

public class Main{
    public static void main(String []args){
        //ConfigForm form = new ConfigForm();
        Saver saver = new Saver("","");
        saver.deserialization();
        MainForm form = new MainForm(saver);
    }
}
