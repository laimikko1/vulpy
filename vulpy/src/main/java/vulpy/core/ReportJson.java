package vulpy.core;

import com.google.gson.JsonObject;

/**
 * RerortJson luokka tarjoaa Json muotoisen raportin projektista
 */

public class ReportJson {

    private Project project;

    public ReportJson(Project project){
        this.project = project;
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
