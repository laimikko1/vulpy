package vulpy.ui;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Project;

public class ReportHBox {

    private HBox hBox;
    private Project project;
    private Reports reports;

    public ReportHBox(Project project, Reports reports){
        this.hBox = new HBox();
        this.project = project;
        this.reports = reports;
        greateHBox();
    }

    public void refresh(){
        this.hBox = new HBox();
        greateHBox();
        this.reports.getContent().setContent(this.hBox);
    }


    public void greateHBox(){
        this.hBox.setId("reportHBox");
        VBox vbox = new VBox(20);
        Text projectName = new Text(project.getName());
        projectName.setId("projectName");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
        bc.setAnimated(true);
        xAxis.setLabel("Dates");
        yAxis.setLabel("Minutes");
        bc.setMaxHeight(250);
        bc.getData().addAll(addData());
        bc.setLegendVisible(false);
        vbox.getChildren().addAll(back(),projectName,editingBox(),bc);
        this.hBox.getChildren().addAll(vbox);
    }

    public HBox editingBox(){
        HBox hBox = new HBox(10);
        VBox left = textBox();
        VBox right = new VBox();
        right.getChildren().addAll(buttons());
        hBox.getChildren().addAll(left,right);
        return hBox;
    }

    public VBox buttons(){
        VBox vBox = new VBox(10);
        HBox json = new HBox(10);
        HBox hourlyWage = new HBox(10);
        TextArea postUrl = new TextArea("Add url");
        postUrl.setPrefRowCount(1);
        postUrl.setMaxWidth(200);
        TextArea newHourlyWage = new TextArea("Add new hourly wage");
        newHourlyWage.setPrefRowCount(1);
        newHourlyWage.setMaxWidth(200);
        Button jsonButton = new Button("Send Json");
        Button hourlyWageButton = new Button("Change hourly wage");
        jsonButton.setPrefWidth(160);
        hourlyWageButton.setPrefWidth(160);
        jsonButton.setId("reportWindowButtons");
        hourlyWageButton.setId("reportWindowButtons");
        hourlyWageButton.setOnAction(e -> {
            this.project.getHourlyWage().setWage(Integer.parseInt(newHourlyWage.getText()));
            refresh();
        });
        json.getChildren().addAll(postUrl,jsonButton);
        hourlyWage.getChildren().addAll(newHourlyWage,hourlyWageButton);
        vBox.getChildren().addAll(json,hourlyWage);
        return vBox;
    }

    public VBox textBox(){
        VBox vbox = new VBox(10);
        Text workingTime = new Text("Working time: " + this.project.getReport().getHoursMinutesAndSeconds());
        String hourlyWage = "Hourly wage: " + this.project.getHourlyWage().getWage() + " " + this.project.getHourlyWage().getSymbol() + " / h";
        Text hw = new Text(hourlyWage);
        Text salary = new Text("Salary: " + this.project.getHourlyWage().getSalary((int)(this.project.getCalendar().getCentiSeconds() / (6000l * 60))) + " " + this.project.getHourlyWage().getSymbol());
        workingTime.setId("reportTextBox");
        hw.setId("reportTextBox");
        salary.setId("reportTextBox");
        vbox.getChildren().addAll(workingTime,hw,salary);
        return vbox;
    }

    public XYChart.Series addData(){
        XYChart.Series series = new XYChart.Series();
        for (String date:project.getCalendar().getDates().keySet()) {
            series.getData().add(new XYChart.Data(date,project.getCalendar().getDates().get(date).getMinutes()));
        }
        return series;
    }

    public Button back(){
        Button button = new Button("Back");
        button.setId("back");
        button.setOnAction(event -> {
            this.reports.getContent().setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            this.reports.getContent().setContent(this.reports.getBox());
        });
        return button;
    }

    public HBox getBox(){
        return this.hBox;
    }
}
