import java.io.Serializable;

public class PathSaver implements Serializable{
    private String pathFrom;
    private String pathTo;

    public PathSaver(String pathF, String pathT){
        pathFrom = pathF;
        pathTo = pathT;
    }

    public String getPathFrom() {
        return pathFrom;
    }

    public String getPathTo() {
        return pathTo;
    }
}
