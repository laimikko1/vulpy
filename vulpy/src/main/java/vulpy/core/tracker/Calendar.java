package vulpy.core.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Calendar-luokka tarjoaa ajanlaskuun tarvittavat metodit päivien tasolla.
 * Jokaisella projektilla on yksi Calendar-olio, jokaista mitattua päivää kohden yksi Tracker-olio.
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
     * Metodi start aloittaa tämän päivän ajanlaskemisen.
     * Metodi käyttää stopAllOthers metodia varmistaakseen, ettei mikään muu tracker ole päällä.
     */

    public void start() {
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        stopAllOthers();
        dates.get(currentDate).startTracking();
    }

    /**
     * Metodi stop lopettaaa tämän päivän ajanlaskemisen.
     */

    public void stop() {
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).stopTracking();
    }

    /**
     * Metodi stopAllOthers varmistaa sen että ainoastaan yksi tracker on päällä yhtäaikaa yhdessä projektissa.
     * Metodi stoppaa kaikki muut trackerit kuin tämän päivän tracker.
     */

    public void stopAllOthers(){
        String currentDate = getCurrentDate();
        for (String date:this.dates.keySet()) {
            if(!date.equals(currentDate)){
                this.dates.get(date).stopTracking();
            }
        }
    }

    /**
     * Metodi getCentiSeconds käy koko hashmapin läpi ja laskee koko projektiin käytetyn ajan sekuntteina.
     * @return koko projektiin yhteensä käytetty aika.
     */

    public long getCentiSeconds() {
        return this.dates.values().stream().mapToLong(Tracker::getCentiseconds).sum();
    }

    /**
     * Metodi ifNotContainsCurrentDate metodi katsoo onko kalenterissa olemassa jo kyseistä päivää.
     * Jos ei niin luodaan uusi päivä.
     * @param currentDate nykyinen päivä.
     */

    private void ifNotContainsCurrentDate(String currentDate) {
        if (!dates.containsKey(currentDate)) {
            dates.put(currentDate, new Tracker());
        }
    }

    /**
     * Metodi getCurrentDate metodi tarjoaa tämän päivän.
     * @return tämänhetkinen päivä.
     */

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Metodi getStringList kerää string listan kaikista mitatuista päivistä.
     * @return kaikki päivät milloin on mitattu aikaa.
     */

    public List<String> getStringDates() {
        List<String> stringDates = new ArrayList<>();
        this.dates.keySet().stream().forEach(o -> stringDates.add(o));
        return stringDates;
    }

    /**
     * Metodi putOneDate antaa mahdollisuuden lisätä kalenteriin yhden päivän.
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

    public Map<String,Tracker> getDates(){
        return this.dates;
    }
}
