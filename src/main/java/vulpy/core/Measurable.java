package vulpy.core;

import vulpy.core.tracker.Calendar;

/**
 * Measurable-rajapinta tarkojaa metodit ajankäytön laskemiseen.
 * Käytetään esimerkiksi Tag ja Project luokissa.
 */

public interface Measurable {

    /**
     * Metodi getName Palauttaa mitattavan olion nimen.
     * @return palautettava nimi.
     */

    String getName();

    /**
     * Metodi startTracking aloittaa ajan mittauksen.
     */

    void startTracking();

    /**
     * Metodi stopTracking lopettaa ajan mittauksen.
     */

    void stopTracking();

    /**
     * Metodi getCalendar palauttaa mitattavan olion kalenterin.
     * @return mitattavan olion kalenteri.
     */

    Calendar getCalendar();

    /**
     * Metodi getTime palauttaa mitattavan olion mitatun ajan.
     * @return mitattu aika.
     */

    long getTime();
}
