package vulpy.core;

import vulpy.core.tracker.Calendar;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Project-luokka tarjoaa projektin luontia varten tärkeimmät metodit.
 */

public class Project {

    private String name;
    private Calendar calendar;
    private HourlyWage hourlyWage;
    private ArrayList<Tag> tags;

    /**
     * Konstruktori jota käytetään normaalisti ohjelmassa.
     * @param name projektin nimi.
     * @param tags Lista tageja jota projektilla on.
     */

    public Project(String name, ArrayList<String> tags) {
        this.hourlyWage = new HourlyWage(0, "Euro");
        this.name = name;
        this.calendar = new Calendar();
        this.tags = new ArrayList<>();
        wordToTags(tags);
    }

    /**
     * Konstruktori jota käytetään testejen kanssa.
     * @param name projektin nimi.
     * @param tags Lista tageja jota projektilla on.
     * @param calendar Calendar olio jota yleensä puukotetaan testeissä.
     */

    public Project(String name, ArrayList<String> tags, Calendar calendar) {
        this.hourlyWage = new HourlyWage(0, "Euro");
        this.name = name;
        this.calendar = calendar;
        this.tags = new ArrayList<>();
        wordToTags(tags);
    }

    /**
     * startTracking metodi aloittaa ajan laskemisen kyseiselle projektille.
     */

    public void startTracking() {
        this.calendar.start();
    }

    /**
     * stopTracking metodi lopettaa ajan laskemisen kyseiseltä projektilta.
     */

    public void stopTracking() {
        this.calendar.stop();
    }

    /**
     * wordToTags metodi tarjoaa toiminnon tagi olioiden tekemiseen.
     * @param words lista tagejä String muodossa
     */

    private void wordToTags(ArrayList<String> words) {
        words.stream().forEach(o -> this.tags.add(new Tag(o)));
    }

    public LocalTime getHoursMinutesAndSeconds(){
        long seconds = getTime() / 100;
        LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
        return timeOfDay;
    }

    /**
     * addTag metodi tarjoaa toiminnon yhden tagin lisäämiseen.
     * @param tag annetaan String muodossa tag.
     */

    public void addTag(String tag) {
        this.tags.add(new Tag(tag));
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

    public long getTime() {
        return this.calendar.getCentiSeconds();
    }

    public HourlyWage getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(HourlyWage hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public String getName() {
        return this.name;
    }
}
