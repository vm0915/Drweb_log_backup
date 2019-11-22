package project.vm0915.drweb;

import java.io.*;

public class Saver implements Serializable{
    private String pathFrom;
    private String pathTo;
    private String serializationPath = "save.ser";

    public Saver(String pathF, String pathT){
        pathFrom = pathF;
        pathTo = pathT;
    }

    public String getPathFrom() {
        return pathFrom;
    }

    public String getPathTo() {
        return pathTo;
    }
    public void setPathFrom(String pf){
        pathFrom = pf;
    }
    public void setPathTo(String pt){
        pathTo = pt;
    }

    public void serialization(){
        try {
            FileOutputStream file = new FileOutputStream(serializationPath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("\nObject has been serialized");
            System.out.println("Source DrWeb log directory: " + getPathFrom());
            System.out.println("Destination log copy directory: " + getPathTo() + "\n");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
    }

    public void deserialization(){
        try {
            FileInputStream file = new FileInputStream(serializationPath);
            ObjectInputStream in = new ObjectInputStream(file);
            Saver saver = (Saver) in.readObject();
            //System.out.println(saver);
            pathFrom = saver.getPathFrom();
            pathTo = saver.getPathTo();
            in.close();
            file.close();
            System.out.println("\nObject has been deserialized");
            System.out.println("Source DrWeb log directory: " + saver.getPathFrom());
            System.out.println("Destination log copy directory: " + saver.getPathTo() + "\n");
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
    }
}
