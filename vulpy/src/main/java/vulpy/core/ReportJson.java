package vulpy.core;
import com.google.gson.JsonObject;

/**
 * RerortJson luokka tarjoaa Json muotoisen raportin projektista
 * Sekä metodit sen lähettämiseen Post komennolla serverille.
 */

public class ReportJson {

    private Measurable measurable;
    private String url;

    /**
     * Konstruktorissa alustetaan alkio mistä alkiosta json-reportti tehdään.
     * @param measurable reporttia kaipaava alkio.
     */

    public ReportJson(Measurable measurable) {
        this.measurable = measurable;
    }

    /**
     * Metodi getJson tarjoaa json muotoisen rapostin tietystä mitatusta oliosta.
     * @return json raportti.
     */

    public String getJson() {
        return "";
    }

    /**
     * Metodi setUrl tarjoaa mahdollisuuden asettaa osoite mihin json-tiedosto lähetetään.
     * @param url url-osoite joka halutaan lisätä oliolle.
     */

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
