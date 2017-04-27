package vulpy.core;

import vulpy.core.tracker.Tracker;
import java.util.Map;

/**
 * Report-luokka tarjoaa peruselementit raporttejen luomiseen.
 */

public class Report {

    private Measurable measurable;
    private Map<String,Tracker> dates;
    private ReportJson json;

    /**
     * Konstruktorissa alustetaan ReportJson-olio, sekä yksilöidään mistä projektista halutaan raportti.
     * @param measurable reporttia kaipaava mitattu olio.
     */

    public Report(Measurable measurable){
        this.measurable = measurable;
        this.dates = measurable.getCalendar().getDates();
        this.json = new ReportJson(measurable);
    }

    /**
     * Metodi getHoursMinutesAndSeconds tarjoaa string muodossa olevan merkkijonon mitattavaan kohteesee käytetystä ajasta.
     * @return string muodossa oleva projektiin käytetty aika.
     */

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

    private String underTen(int n){
        String number = "";
        if(n < 10){
            number += "0";
        }
        number += n;
        return number;
    }
}
