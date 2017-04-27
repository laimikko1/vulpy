package vulpy.core;

/**
 * HourlyWage-luokka tarjoaa toiminnallisuuden palkkojen laskemiseen.
 */

public class HourlyWage {

    private static final int EURO = 1;

    private int wage;
    private String unit;

    /**
     * luodaan uusi HourlyWage olio.
     * @param wage tuntipalkka
     * @param unit rahayksikkö
     */

    public HourlyWage(int wage, String unit) {
        setHourlyWage(wage);
    }

    /**
     * setHourlyWage metodin avulla voi vaihtaa tuntipalkkaa olion sisällä.
     * @param wage tuntipalkka
     */

    public void setHourlyWage(int wage) {
        this.unit = "EURO";
        this.wage = wage;
    }

    /**
     * getSymbol metodi palauttaa kyseisen yksikön symbolin.
     * @return palauttaa kyseisen yksikön symbolin.
     */

    public String getSymbol() {
        return "€";
    }

    /**
     * getSalary metodi antaa kokonaispalkan. Käytetään projekti-luokasta.
     * @param hours tunnit mitä kyseiseen projektiin on käytetty.
     * @return palauttaa kokonaispalkan.
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
