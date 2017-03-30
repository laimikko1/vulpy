package vulpy.core;

public class HourlyWage {

    private static final int EURO = 1000;
    private static final int DOLLAR = 1072;
    private static final int CNY = 7391;

    private int wage;
    private String unit;

    public HourlyWage(int wage, String unit) {
        setHourlyWage(wage, unit);
    }

    public int getWage() {
        return wage;
    }

    public String getUnit() {
        return unit;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setUnit(String unit) {
        setHourlyWage(this.wage, unit);
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
