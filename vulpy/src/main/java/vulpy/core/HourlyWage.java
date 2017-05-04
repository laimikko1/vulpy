package vulpy.core;

/**
 * HourlyWage-luokka tarjoaa toiminnallisuuden palkkojen laskemiseen.
 */

public class HourlyWage {

    private static final int EURO = 1;

    private int wage;
    private String unit;

    /**
     * Konstruktorissa luodaan uusi HourlyWage olio.
     * @param wage tuntipalkka
     * @param unit rahayksikkö
     */

    public HourlyWage(int wage, String unit) {
        setHourlyWage(wage);
    }

    /**
     * Metodin setHourlyWage avulla voi vaihtaa tuntipalkkaa olion sisällä.
     * @param wage tuntipalkka
     */

    public void setHourlyWage(int wage) {
        this.unit = "EURO";
        this.wage = wage;
    }

    /**
     * Metodi getSymbol palauttaa kyseisen rahayksikön symbolin.
     * @return palauttaa kyseisen rahayksikön symbolin.
     */

    public String getSymbol() {
        return "€";
    }

    /**
     * Metodi getSalary antaa projektin kokonaispalkan.
     * @param hours tunnit mitä kyseiseen projektiin on käytetty.
     * @return palauttaa kokonaispalkan. Palkkaa kertyy vain koko tunneista.
     */

    public int getSalary(int hours) {
        return hours * this.wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWage() {
        return wage;
    }

    public String getUnit() {
        return unit;
    }
}
