package vulpy.core;

/**
 * HourlyWage tarjoaa tärkeimmät metodit tuntipalkan laskemiseen kolmella isoimmalla valuutalla.
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
     * setHourlyWage metodin avulla voi vaihtaa HourlyWagea lennosta.
     * Metodi asettaa sekä uuden unitin ja wagen.
     * @param wage tuntipalkka
     */

    public void setHourlyWage(int wage) {
        this.unit = "EURO";
        this.wage = wage;
    }

    /**
     * getSymbol metodi palauttaa kyseisen unitin symbolin.
     * @return palauttaa kyseisen unitin symbolin.
     */

    public String getSymbol() {
        return "€";
    }

    /**
     * getSalary metodi antaa kokonaispalkan. Käytetään projekti-luokasta.
     * @param hours tunnit mitä kyseiseen projektiin on käytetty
     * @return palauttaa kokonaispalkan
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
