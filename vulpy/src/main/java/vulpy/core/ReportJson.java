package vulpy.core;

import com.google.gson.JsonObject;

/**
 * RerortJson luokka tarjoaa Json muotoisen raportin projektista
 */

public class ReportJson {

    private Measurable measurable;

    public ReportJson(Measurable measurable){
        this.measurable = measurable;
    }

    public String getJson(){
        return "";
    }

    public String getJsonSeconds(){
        return "";
    }

    public String getJsonDays(){
        return "";
    }
}
