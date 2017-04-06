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
        if (!(unit.equals("Euro") || unit.equals("Dollar") || unit.equals("CNY"))) {
            this.unit = "Euro";
            this.wage = 0;
        } else {
            this.unit = unit;
            this.wage = wage;
        }
    }

    public void changeCurrency(String unit) {
        if (this.unit.equals(unit)) {
            return;
        }
        if (this.unit.equals("Euro")) {
            changeEuroToUnit(unit);
        } else if (this.unit.equals("Dollar")) {
            changeDollarToUnit(unit);
        } else if (this.unit.equals("CNY")) {
            changeCNYToUnit(unit);
        }
    }

    public void changeEuroToUnit(String unit){

    }

    public void changeDollarToUnit(String unit){

    }

    public void changeCNYToUnit(String unit){

    }

    public String getSymbol() {
        if (this.unit.equals("Dollar")) {
            return "$";
        } else if (this.unit.equals("CNY")) {
            return "¥";
        }
        return "€";
    }

    public int getSalary(int hours) {
        return hours * this.wage;
    }
}
