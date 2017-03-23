package core;

import core.tracker.Calendar;
import java.util.ArrayList;

public class Project {

    private String name;
    private ArrayList<Tag> tags;
    private Calendar calendar;

    public Project(String name, ArrayList<String> tags){
        this.name = name;
        this.tags = makeTags(tags);
        this.calendar = new Calendar();
    }

    public void addTag(String tag){
        this.tags.add(new Tag(tag));
    }

    public void startTracking(){
        this.calendar.start();
    }

    public void stopTracking(){
        this.calendar.stop();
    }

    public void getTime(){
        this.calendar.getSeconds();
    }

    public ArrayList<Tag> makeTags(ArrayList<String> words){
        ArrayList<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            tags.add(new Tag(words.get(i)));
        }
        return tags;
    }
}
