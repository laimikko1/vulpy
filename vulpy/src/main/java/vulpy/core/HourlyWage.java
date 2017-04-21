package vulpy.core;

/**
 * HourlyWage tarjoaa tärkeimmät metodit tuntipalkan laskemiseen kolmella isoimmalla valuutalla.
 */

public class HourlyWage {

    private static final int EURO = 1000;
    private static final int DOLLAR = 1072;
    private static final int CNY = 7391;

    private int wage;
    private String unit;

    /**
     * luodaan uusi HourlyWage olio.
     * @param wage tuntipalkka
     * @param unit rahayksikkö
     */

    public HourlyWage(int wage, String unit) {
        setHourlyWage(wage, unit);
    }

    /**
     * setHourlyWage metodin avulla voi vaihtaa HourlyWagea lennosta.
     * Metodi asettaa sekä uuden unitin ja wagen.
     * @param wage tuntipalkka
     * @param unit rahayksikkö
     */

    public void setHourlyWage(int wage, String unit) {
        if (!(unit.equals("EURO") || unit.equals("DOLLAR") || unit.equals("CNY"))) {
            this.unit = "EURO";
            this.wage = 0;
        } else {
            this.unit = unit;
            this.wage = wage;
        }
    }

    /**
     * getSymbol metodi palauttaa kyseisen unitin symbolin.
     * @return palauttaa kyseisen unitin symbolin.
     */

    public String getSymbol() {
        if (this.unit.equals("EURO")) {
            return "€";
        } else if (this.unit.equals("CNY")) {
            return "¥";
        }
        return "$";
    }

    /**
     * getSalary metodi antaa kokonaispalkan. Käytetään projekti-luokasta.
     * @param hours tunnit mitä kyseiseen projektiin on käytetty
     * @return palauttaa kokonaispalkan
     */

    public int getSalary(int hours) {
        return hours * this.wage;
    }

    /**
     * setUnit metodi antaa vaihtaa rahayksikköä, jos rahayksikkö ei ole käytössä käytetään euroa.
     * @param unit vaatii rahayksikön, jos kyseinen String ei ole rahayksikkö, käytetään euroa.
     */

    public void setUnit(String unit) {
        setHourlyWage(this.wage, unit);
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
