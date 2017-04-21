package vulpy.core.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Calendar-luokka tarjoaa ajanlaskuun tarvittavat metodit päivien tasolla.
 * Jokaisella projektilla on yksi Calendar-olio, jokaista mitattua päivää kohden yksi Tracker olio.
 */

public class Calendar {

    Map<String, Tracker> dates;

    public Calendar() {
        this.dates = new HashMap<>();
    }

    /**
     * Metodi aloittaa tämän päivän ajanlaskemisen.
     */

    public void start() {
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).startTracking();
    }

    /**
     * Metodi lopettaaa tämän päivän ajanlaskemisen.
     */

    public void stop() {
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).stopTracking();
    }

    /**
     * Metodi käy koko hashmapin läpi ja laskee koko projektiin käytetyn ajan sekuntteina.
     * @return koko projektiin käytetty aika.
     */

    public long getSeconds() {
        return this.dates.values().stream().mapToLong(Tracker::getCentiseconds).sum();
    }

    private void ifNotContainsCurrentDate(String currentDate) {
        if (!dates.containsKey(currentDate)) {
            dates.put(currentDate, new Tracker());
        }
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Metodi käy läpi kaikki päivät milloin on laskettu aikaa.
     * @return kaikki päivät milloin on mitattu aikaa.
     */

    public ArrayList<String> getStringDates() {
        ArrayList<String> stringDates = new ArrayList<>();
        this.dates.keySet().stream().forEach(o -> stringDates.add(o));
        return stringDates;
    }

    public void putOneDate(String date) {
        ifNotContainsCurrentDate(date);
    }

    public void putOneDateAndTracker(String date, Tracker tracker) {
        if (!dates.containsKey(date)) {
            dates.put(date, tracker);
        }
    }

    public int getCalendarSize() {
        return this.dates.size();
    }
}
