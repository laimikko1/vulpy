package vulpy.core;

import vulpy.core.tracker.Calendar;

/**
 * Tag-luokka tarjoaa tagin, jota voi hyödyntää samalla tavalla kuin projektia.
 * Kuitenkin yhdellä projektilla voi olla useita tagejä.
 */

public class Tag implements Measurable {

    private String name;
    private Calendar calendar;
    private Report report;
    private int howManyProjectsAreOn;

    /**
     * Konstruktorissa alustetaan Tag-olio.
     * @param name tagin nimi.
     */

    public Tag(String name) {
        this.name = name;
        this.calendar = new Calendar();
        this.report = new Report(this);
        this.howManyProjectsAreOn = 0;
    }

    /**
     * Metodi startTracking tarjoaa tagin ajanlaskun aloituksen.
     */

    @Override
    public void startTracking() {
        this.calendar.start();
        this.howManyProjectsAreOn++;
    }

    /**
     * Metodi stopTracking tarjoaa tagin ajanlaskun lopettamisen.
     */

    @Override
    public void stopTracking() {
        this.howManyProjectsAreOn--;
        if(this.howManyProjectsAreOn < 1){
            this.calendar.stop();
            this.howManyProjectsAreOn = 0;
        }
    }

    /**
     * Metodi getTime tarjoaa tietylle tagille mitatun ajan senttisekuntteina.
     * @return tagille mitattu aika senttisekuntteina.
     */

    @Override
    public long getTime() {
        return this.calendar.getSeconds();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Report getReport() {
        return report;
    }

    public String getName() {
        return name;
    }
}
