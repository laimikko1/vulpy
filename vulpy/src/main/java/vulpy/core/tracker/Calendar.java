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

    /**
     * Konstruktorissa luodaan uusi Calendar-olio.
     */

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

    public long getCentiSeconds() {
        return this.dates.values().stream().mapToLong(Tracker::getCentiseconds).sum();
    }

    /**
     * ifNotContainsCurrentDate metodi katsoo onko kalenterissa olemassa jo kyseistä päivää.
     * Jos ei niin luodaan uusi päivä.
     * @param currentDate nykyinen päivä.
     */

    private void ifNotContainsCurrentDate(String currentDate) {
        if (!dates.containsKey(currentDate)) {
            dates.put(currentDate, new Tracker());
        }
    }

    /**
     * getCurrentDate metodi tarjoaa tämän päivän.
     * @return tämänhetkinen päivä.
     */

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

    /**
     * putOneDate metodi antaa mahdollisuuden lisätä kalenteriin yhden päivän.
     * @param date päivä.
     */

    public void putOneDate(String date) {
        ifNotContainsCurrentDate(date);
    }

    /**
     * putOneDateAndTracker metodi antaa mahdollisuuden lisätä kalenteriin yhden päivän ja trackerin.
     * @param date päivä.
     * @param tracker tracker.
     */

    public void putOneDateAndTracker(String date, Tracker tracker) {
        if (!dates.containsKey(date)) {
            dates.put(date, tracker);
        }
    }

    /**
     * getCalendarSize metodi palauttaa kalenterin tämänhetkisen koon.
     * @return kalenterin tämänhetkinen koko.
     */

    public int getCalendarSize() {
        return this.dates.size();
    }
}
