package vulpy.core.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Calendar {

    Map<String, Tracker> dates;

    public Calendar(){
        this.dates = new HashMap<>();
    }

    public void start(){
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).startTracking();
    }

    public void stop(){
        String currentDate = getCurrentDate();
        ifNotContainsCurrentDate(currentDate);
        dates.get(currentDate).stopTracking();
    }

    public long getSeconds(){
        return this.dates.values().stream().mapToLong(Tracker::getSeconds).sum();
    }

    public void ifNotContainsCurrentDate(String currentDate){
        if(!dates.containsKey(currentDate)){
            dates.put(currentDate, new Tracker());
        }
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public ArrayList<String> getStringDates(){
        ArrayList<String> stringDates = new ArrayList<>();
        this.dates.keySet().stream().forEach(o -> stringDates.add(o));
        return stringDates;
    }

    public void putOneDate(String date){
        ifNotContainsCurrentDate(date);
    }
}
