package vulpy.core;
import com.google.gson.JsonObject;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

/**
 * RerortJson luokka tarjoaa Json muotoisen raportin projektista.
 */

public class ReportJson {

    private Measurable measurable;

    /**
     * Konstruktorissa alustetaan alkio mistä alkiosta json-reportti tehdään.
     * @param measurable reporttia kaipaava alkio.
     */

    public ReportJson(Measurable measurable) {
        this.measurable = measurable;
    }

    /**
     * Metodi getJson tarjoaa json muotoisen raportin tietystä mitatusta oliosta.
     * @return json raportti.
     */

    public String getJson() {
        JsonObject innerObject = new JsonObject();
        for (String date:measurable.getCalendar().getDates().keySet()) {
            innerObject.addProperty(date, measurable.getCalendar().getDates().get(date).getSeconds());
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(measurable.getName(), innerObject);
        return jsonObject.toString();
    }

    /**
     * Metodi copyJsonToClipboard tarjoaa toiminnallisuuden json-reportin kopioimiseen leikepyödälle.
     */

    public void copyJsonToClipboard() {
        String jsonString = getJson();
        StringSelection stringSelection = new StringSelection(jsonString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
