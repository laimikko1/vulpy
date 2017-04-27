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

    public String getHoursMinutesAndSeconds(){
        long longVal = this.measurable.getTime() / 100;
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
}
