package core;

import core.tracker.Tracker;
import java.util.ArrayList;

public class Project {

    private Tracker tracer;
    private String name;
    private ArrayList<String> tags;

    public Project(String name, ArrayList<String> tags){
        this.tracer = new Tracker();
        this.name = name;
        this.tags = tags;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public void startTracking(){
        this.tracer.startTracking();
    }

    public void stopTracking(){
        this.tracer.stopTracking();
    }

    public void getTime(){
        this.tracer.getSeconds();
    }

}
