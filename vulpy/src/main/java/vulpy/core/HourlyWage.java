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

    public HourlyWage(int wage, String unit) {
        setHourlyWage(wage, unit);
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWage() {
        return wage;
    }

    public void setUnit(String unit) {
        setHourlyWage(this.wage, unit);
    }

    public String getUnit() {
        return unit;
    }

    public void setHourlyWage(int wage, String unit) {
        if (!(unit.equals("EURO") || unit.equals("DOLLAR") || unit.equals("CNY"))) {
            this.unit = "EURO";
            this.wage = 0;
        } else {
            this.unit = unit;
            this.wage = wage;
        }
    }

    public String getSymbol() {
        if (this.unit.equals("EURO")) {
            return "€";
        } else if (this.unit.equals("CNY")) {
            return "¥";
        }
        return "$";
    }

    public int getSalary(int hours) {
        return hours * this.wage;
    }
}
