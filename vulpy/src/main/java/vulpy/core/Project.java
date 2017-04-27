package vulpy.core;

import vulpy.core.tracker.Calendar;
import java.util.List;

/**
 * Project-luokka tarjoaa projektin luontia varten tärkeimmät metodit.
 */

public class Project implements Measurable{

    private String name;
    private Calendar calendar;
    private HourlyWage hourlyWage;
    private List<String> tags;
    private Report report;

    /**
     * Konstruktori jota käytetään normaalisti ohjelmassa.
     * @param name projektin nimi.
     * @param tags Lista tageja jota projektilla on.
     */

    public Project(String name, List<String> tags) {
        this.hourlyWage = new HourlyWage(0, "Euro");
        this.name = name;
        this.calendar = new Calendar();
        this.tags = tags;
        this.report = new Report(this);
    }

    /**
     * Konstruktori jota käytetään testejen kanssa.
     * @param name projektin nimi.
     * @param tags Lista tageja jota projektilla on.
     * @param calendar Calendar olio jota yleensä puukotetaan testeissä.
     */

    public Project(String name, List<String> tags, Calendar calendar) {
        this.hourlyWage = new HourlyWage(0, "Euro");
        this.name = name;
        this.calendar = calendar;
        this.tags = tags;
        this.report = new Report(this);
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

    public String getTagsString() {
        String tagStrign = "Tags: ";
        for (int i = 0; i < getTags().size(); i++) {
            if (i == getTags().size() - 1) {
                tagStrign += getTags().get(i);
            } else {
                tagStrign += getTags().get(i) +  ", ";
            }
        }
        return tagStrign;
    }

    /**
     * addTag metodi tarjoaa toiminnon yhden tagin lisäämiseen.
     * @param tag annetaan String muodossa tag.
     */

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public List<String> getTags() {
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

    public Calendar getCalendar(){
        return this.calendar;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
