package vulpy.core;

import vulpy.core.tracker.Calendar;

import java.util.ArrayList;

/**
 * Project luokka tarjoaa projektin luontia varten tärkeimmät metodit.
 */

public class Project {

    private String name;
    private Calendar calendar;
    private HourlyWage hourlyWage;
    private ArrayList<Tag> tags;

    public Project(String name, ArrayList<String> tags) {
        this.hourlyWage = new HourlyWage(0,"Euro");
        this.name = name;
        this.calendar = new Calendar();
        this.tags = new ArrayList<>();
        wordToTags(tags);
    }

    public Project(String name, ArrayList<String> tags, Calendar calendar) {
        this.name = name;
        this.calendar = calendar;
        this.tags = new ArrayList<>();
        wordToTags(tags);
    }

    public String getName() {
        return this.name;
    }

    public void addTag(String tag) {
        this.tags.add(new Tag(tag));
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

    public void startTracking() {
        this.calendar.start();
    }

    public void stopTracking() {
        this.calendar.stop();
    }

    public long getTime() {
        return this.calendar.getSeconds();
    }

    public void wordToTags(ArrayList<String> words) {
        words.stream().forEach(o -> this.tags.add(new Tag(o)));
    }

    public HourlyWage getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(HourlyWage hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
