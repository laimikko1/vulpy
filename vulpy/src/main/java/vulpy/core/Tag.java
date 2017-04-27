package vulpy.core;

import vulpy.core.tracker.Calendar;

/**
 * Tag-luokka tarjoaa tagin, jota voi hyödyntää niinkuin projektia.
 * Kuitenkin yksi projekti voi käyttää useita tagejä.
 */

public class Tag implements Measurable {
    private String name;
    private Calendar calendar;
    private Report report;

    /**
     * Konstruktorissa luodaan Tag-olio.
     * @param name tagin nimi.
     */

    public Tag(String name) {
        this.name = name;
        this.calendar = new Calendar();
        this.report = new Report(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void startTracking() {
        this.calendar.start();
    }

    @Override
    public void stopTracking() {
        this.calendar.stop();
    }

    @Override
    public long getTime() {
        return this.calendar.getCentiSeconds();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHoursMinutesAndSeconds(){
        long longVal = getTime() / 100;
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        String hoursMinutesAndSeconds = underTen(hours) + "h " + underTen(mins) + "min " + underTen(secs) + "sec";
        return hoursMinutesAndSeconds;
    }

    public String underTen(int n){
        String number = "";
        if(n < 10){
            number += "0";
        }
        number += n;
        return number;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
