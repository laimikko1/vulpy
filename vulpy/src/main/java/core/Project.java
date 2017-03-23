package core;

import core.tracker.Calendar;
import java.util.ArrayList;

public class Project {

    private String name;
    private Calendar calendar;
    private ArrayList<Tag> tags;

    public Project(String name, ArrayList<String> tags){
        this.name = name;
        this.calendar = new Calendar();
        this.tags = new ArrayList<>();
        wordToTags(tags);
    }

    public String getName(){
        return this.name;
    }

    public void addTag(String tag){
        this.tags.add(new Tag(tag));
    }

    public ArrayList<Tag> getTags(){
        return this.tags;
    }

    public void startTracking(){
        this.calendar.start();
    }

    public void stopTracking(){
        this.calendar.stop();
    }

    public long getTime(){
        return this.calendar.getSeconds();
    }

    public void wordToTags(ArrayList<String> words){
        words.stream().forEach(o -> this.tags.add(new Tag(o)));
    }
}
