package vulpy.core;

import vulpy.core.tracker.Tracker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Luokka tarjoaa reportin jokaisesta projektista, pahasti keskeneräinen.
 */

public class Report {

    private Measurable measurable;
    private Map<String,Tracker> dates;
    private ReportJson json;

    public Report(Measurable measurable){
        this.measurable = measurable;
        this.dates = measurable.getCalendar().getDates();
        this.json = new ReportJson(measurable);
    }
}
