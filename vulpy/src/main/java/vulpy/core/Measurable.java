package vulpy.core;

import vulpy.core.tracker.Calendar;

public interface Measurable {
    String getName();
    void startTracking();
    void stopTracking();
    Calendar getCalendar();
    long getTime();
}
