package vulpy.core.tracker;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Calendar-luokka tarjoaa ajanlaskuun tarvittavat metodit päivien tasolla.
 * Jokaisella projektilla on yksi Calendar-olio, jolla on jokaista mitattua päivää kohden yksi Tracker-olio.
 */

public class Calendar {

    Map<String, Tracker> dates;
    ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> future;
    private boolean on;

    /**
     * Konstruktorissa luodaan uusi Calendar-olio.
     */

    public Calendar() {
        this.dates = new HashMap<>();
        this.on = false;
    }

    /**
     * Metodi start aloittaa juuri tämän päivän ajanlaskemisen.
     * Metodi käyttää stopAllOthers metodia varmistaakseen, ettei mikään muu tracker ole päällä.
     */

    public void start() {
        this.on = true;
        stopAllOthers();
        future = scheduler.scheduleAtFixedRate(refreshingTask, 1, 1, TimeUnit.SECONDS);
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).startTracking();
    }

    /**
     * Metodi stop lopettaa kaikkien laskureiden ajanlaskemisen.
     */

    public void stop() {
        this.on = false;
        stopAllOthers();
        future.cancel(false);
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).stopTracking();
    }

    private void stopAllOthers() {
        String currentDate = getCurrentDate();
        for (String date:this.dates.keySet()) {
            if (!date.equals(currentDate)) {
                this.dates.get(date).stopTracking();
            }
        }
    }

    /**
     * Metodi getMilliSeconds käy koko hashmapin läpi ja laskee koko projektiin käytetyn ajan millisekuntteina.
     * @return koko projektiin yhteensä käytetty aika senttisekuntteina.
     */

    public long getMilliSeconds() {
        return this.dates.values().stream().mapToLong(Tracker::getMilliseconds).sum();
    }

    /**
     * Metodi getSeconds käy koko hashmapin läpi ja laskee koko projektiin käytetyn ajan millisekuntteina.
     * @return koko projektiin yhteensä käytetty aika senttisekuntteina.
     */

    public long getSeconds() {
        return this.dates.values().stream().mapToLong(Tracker::getSeconds).sum();
    }

    /**
     * Metodi ifNotContainsCurrentDate metodi tarkistaa onko kalenterissa olemassa jo kyseistä päivää.
     * Jos ei ole niin luodaan uusi päivä.
     * @param currentDate nykyinen päivä.
     */

    private void ifNotContainsCurrentDate(String currentDate) {
        if (!dates.containsKey(currentDate)) {
            dates.put(currentDate, new Tracker(maxTime()));
        }
    }

    /**
     * Metodi maxTime tarjoaa tämän päivän maksimiajan mitä on käytössä.
     * @return aika millisekuntteina mitä on loppupäivänä jäljellä.
     */

    private long maxTime(){
        long fullDayMilliSeconds = 1000 * 60 * 60 * 24;
        long milliTimeNow = DateTime.now().millisOfDay().get();
        return fullDayMilliSeconds - milliTimeNow;
    }

    /**
     * Metodi getCurrentDate tarjoaa tämänhetkisen päivän muodossa dd/MM/YYYY.
     * @return tämänhetkinen päivä muodossa dd/MM/YYYY.
     */

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
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

    public Map<String, Tracker> getDates() {
        return this.dates;
    }

    private void refresh(){
        ifNotContainsCurrentDate(getCurrentDate());
        start();
    }

    Runnable refreshingTask = new Runnable(){
        @Override
        public void run() {
            try{
                if(on){
                    refresh();
                }
            }catch(Exception e){

            }
        }
    };
}
