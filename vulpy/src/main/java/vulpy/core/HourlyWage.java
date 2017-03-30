package vulpy.core;

public class HourlyWage {

    private static int EURO = 100000;
    private static int DOLLAR = 107275;
    private static int CNY = 739180;

    private int wage;
    private String unit;

    public HourlyWage(int wage, String unit){
        refreshHourlyWage(wage,unit);
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
        this.unit = unit;
    }

    public void refreshHourlyWage(int wage, String unit){

    }


}
