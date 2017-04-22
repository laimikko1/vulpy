package vulpy.core;

import vulpy.core.tracker.Tracker;

import java.util.ArrayList;
import java.util.Map;

/**
 * Luokka tarjoaa reportin jokaisesta projektista, pahasti keskeneräinen.
 */

public class Report {

    private Project project;
    private Map<String,Tracker> dates;
    private ReportJson json;
    private ReportPdf pdf;

    public Report(Project project){
        this.project = project;
        this.dates = project.getCalendar().getDates();
        this.json = new ReportJson(project);
        this.pdf = new ReportPdf(project);
    }

    public ArrayList<String[]> getDatesArray(){
        ArrayList<String[]> datesArray = new ArrayList<>();
        for (String dates:dates.keySet()) {
            String[] date = new String[2];
            date[0] = dates;
            date[1] = this.dates.get(dates).getCentiseconds() + "";
            datesArray.add(date);
        }
        return datesArray;
    }
}
