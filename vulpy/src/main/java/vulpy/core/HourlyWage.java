package vulpy.core;

public class HourlyWage {

    private static int EURO = 100000;
    private static int DOLLAR = 107275;
    private static int CNY = 739180;

    private int wage;
    private String unit;

    public HourlyWage(int wage, String unit){
        setHourlyWage(wage,unit);
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

    public void setHourlyWage(int wage, String unit) {
        if (!(this.unit.equals("Euro") || this.unit.equals("Dollar") || this.unit.equals("CNY"))) {
            this.unit = "Euro";
            this.wage = 0;
        } else {
            this.unit = unit;
            this.wage = wage;
        }
    }

    public String getSymbol(){
        if(this.unit.equals("Dollar")){
            return "$";
        } else if(this.unit.equals("CNY")){
            return "¥";
        }
        return "€";
    }

    public void convertUnit(String unit){
        if(!(this.unit.equals("Euro") || this.unit.equals("Dollar") || this.unit.equals("CNY"))){
            return;
        } else if(this.unit.equals(unit)){
            return;
        } else if (this.unit.equals("Euro")){
            convertEuro(unit);
        } else if (this.unit.equals("Dollar")){
            convertDollar(unit);
        } else (this.unit.equals("CNY")){
            convertCNY(unit);
        }
    }

    public void convertEuro(String unit){
        if(unit.equals("Dollar")){
            this.wage = this.wage * DOLLAR;
        } else if(this.equals("CNY")){
            this.wage = this.wage * CNY;
        }
        this.unit = unit;
    }

    public void convertDollar(String unit){
        if(unit.equals("Euro")){
            this.wage = this.wage / DOLLAR;
        } else if(this.equals("CNY")){
            this.wage = (this.wage / DOLLAR) * CNY;
        }
        this.unit = unit;
    }

    public void convertCNY(String unit){
        if(unit.equals("Euro")){
            this.wage = this.wage / CNY;
        } else if(this.equals("Dollar")){
            this.wage = (this.wage / CNY) * DOLLAR;
        }
        this.unit = unit;
    }
}
