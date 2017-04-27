package vulpy.core;

import vulpy.core.tracker.Calendar;

/**
 * Measurable-rajapinta tarkojaa metodit ajankäytön laskemiseen.
 * Käytetään esimerkiksi Tag ja Project luokissa.
 */

public interface Measurable {
    String getName();
    void startTracking();
    void stopTracking();
    Calendar getCalendar();
    long getTime();
}
