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
import vulpy.core.Tag;

/**
 * TagsHBox-luokka tarjoaa raportin tagista.
 */

public class TagsHBox {

    private Tag tag;
    private Tags tags;
    private HBox hBox;

    /**
     * Konstruktorissa otetaan luokalle sisään tagi mistä raportti tehdään, sekä tags-olio, jotta voimme päivittää näkymää.
     * @param tag tagi mille tehdään reportti.
     * @param tags tags olio mitä päivitetään.
     */

    public TagsHBox(Tag tag, Tags tags) {
        this.tag = tag;
        this.tags = tags;
        this.hBox = new HBox();
        greateHBox();
    }

    /**
     * Metodi greateHBox luo boxin missä on melkeen kaikki tieto raportista.
     */

    public void greateHBox() {
        this.hBox.setId("tagHBox");
        VBox vbox = new VBox(20);
        Text projectName = new Text(tag.getName());
        projectName.setId("tagName");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setAnimated(true);
        xAxis.setLabel("Dates");
        yAxis.setLabel("Minutes");
        bc.setMaxHeight(250);
        bc.getData().addAll(addData());
        bc.setLegendVisible(false);
        vbox.getChildren().addAll(back(), projectName, editingBox(), bc);
        this.hBox.getChildren().addAll(vbox);
    }

    /**
     * Metodi editingBox rakentaa HBoxin joka sisältää kaksi VBoxia.
     * Vasen on textBox ja oikea sisältää datan vaihtoon liittyviä asioita.
     * @return HBox jossa on reportin tietoja.
     */

    public HBox editingBox() {
        HBox hBox = new HBox(10);
        VBox left = textBox();
        VBox right = new VBox();
        right.getChildren().addAll(buttons());
        hBox.getChildren().addAll(left, right);
        return hBox;
    }

    /**
     * Metodi buttons tarjoaa reportissa olevat mahdollisuudet datan muuttamiseen.
     * Metodi luo yhden napin, ja yhden tekstikentän. Näiden avulla on mahdollista lähettää json-posteja.
     * @return VBox jossa nappi sekä tekstikenttä sijaitsee.
     */

    public VBox buttons() {
        VBox vBox = new VBox(10);
        HBox json = new HBox(10);
        TextArea postUrl = new TextArea("Add url");
        postUrl.setPrefRowCount(1);
        postUrl.setMaxWidth(200);
        TextArea newHourlyWage = new TextArea("Add new hourly wage");
        newHourlyWage.setPrefRowCount(1);
        newHourlyWage.setMaxWidth(200);
        Button jsonButton = new Button("Send Json");
        jsonButton.setPrefWidth(160);
        jsonButton.setId("reportWindowButtons");
        json.getChildren().addAll(postUrl, jsonButton);
        vBox.getChildren().addAll(json);
        return vBox;
    }

    /**
     * Metodi textBox luo VBoxin jossa on kaikki tärkeimmät tiedot tagista.
     * VBoxiin lisätään nimi ja käytetty aika.
     * @return VBox jossa nimi ja käytetty aika.
     */

    public VBox textBox() {
        VBox vbox = new VBox(10);
        Text workingTime = new Text("Working time: " + this.tag.getReport().getHoursMinutesAndSeconds());
        workingTime.setId("reportTextBox");
        vbox.getChildren().addAll(workingTime);
        return vbox;
    }

    /**
     * Metodi back luo Buttonin jossa on takaisin meno vaihtoehto edelliseen näkymään.
     * @return button edelliseen näkymään
     */

    public Button back() {
        Button button = new Button("Back");
        button.setId("back");
        button.setOnAction(event -> {
            this.tags.getContent().setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            this.tags.getContent().setContent(this.tags.getBox());
        });
        return button;
    }

    /**
     * Metodi addData antaa kuvaajan datan ulos jokaista projektissa mitattavana olevaa päivää kohden.
     * @return kuvaajan data.
     */

    public XYChart.Series addData() {
        XYChart.Series series = new XYChart.Series();
        for (String date:tag.getCalendar().getDates().keySet()) {
            series.getData().add(new XYChart.Data(date, tag.getCalendar().getDates().get(date).getMinutes()));
        }
        return series;
    }

    public HBox getBox() {
        return this.hBox;
    }
}
