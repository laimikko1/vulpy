package vulpy.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Collector;
import vulpy.core.Tag;

import java.util.Map;

/**
 * Tags-luokka tarjoaa lista näkymän jossa on kaikki tagit listattuna.
 */

public class Tags {

    private Collector collector;
    private VBox box;
    private ScrollPane content;
    private VBox right;
    private VBox left;

    /**
     * Konstruktorissa otetaan Projects olio vastaan jossa on kaikista projekteista viite.
     * Myös päivitettävä ScrollPane olio otetaan sisälle luokkaan.
     * @param content ScrollPane jota päivitetään.
     * @param collector collector, josta saadaan tagit joille näkymä tehdään.
     */

    public Tags(Collector collector, ScrollPane content) {
        this.content = content;
        this.collector = collector;
        this.box = new VBox();
        formatTags();
        addAllTags();
    }

    /**
     * Metodi formatReports luo koko näkymän ja alustaa oliot uusiksi.
     */

    public void formatTags() {
        Map<String, Tag> tagMap = this.collector.getTagMap();
        this.box = new VBox(10);
        this.box.setId("tagBox");
        HBox subBox = new HBox(10);
        this.right = new VBox(10);
        this.left = new VBox(10);
        right.setId("tagSubBox");
        left.setId("tagSubBox");
        Text text = new Text("Your tags!");
        text.setId("yourTags");
        subBox.getChildren().addAll(right, left);
        this.box.getChildren().addAll(text, subBox);
    }

    /**
     * Metodi addAllTags jaoittelee tagit niiden määrän mukaan kahteen osioon, vasempaan ja oikeaan.
     */

    public void addAllTags() {
        int i = 0;
        for (String name:this.collector.getTagMap().keySet()) {
            if (i % 2 == 0) {
                this.right.getChildren().addAll(addTagButton(name));
            } else {
                this.left.getChildren().addAll(addTagButton(name));
            }
            i++;
        }
    }

    /**
     * Metodi addTagButton luo tagButtonin eli jokaisesta tagista tehdään nappi.
     * @param name nimi mikä tagi on kyseessä.
     * @return nappi jokaista tagia kohti.
     */

    public Button addTagButton(String name) {
        Button tagButton = new Button();
        addButtonFunctions(tagButton, name);
        tagButton.setPrefWidth(250);
        tagButton.setPrefHeight(30);
        tagButton.setId("reportButton");
        tagButton.setText(name);
        addButtonFunctions(tagButton, name);
        return tagButton;
    }

    /**
     * Metodi addButtonFunctions lisää napille funktion että saadaan ulos raportti.
     * @param button nappi mihin funktio lisätään.
     * @param name nimi mikä tagi on kyseessä.
     */

    public void addButtonFunctions(Button button, String name) {
        TagsHBox tagsHbox = new TagsHBox(this.collector.getTag(name), this);
        HBox hbox = tagsHbox.getBox();
        button.setOnAction(e -> {
            this.content.setContent(hbox);
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
    }

    /**
     * Metodi getBox tarjoaa tagienlistaus näkymän boxin.
     * Tätä ennen metodi kumminkin päivittää tagilistauksen uusimpaan versioon.
     * @return VBox jossa tagien raporttilistaus sijaitsee.
     */

    public VBox getBox() {
        formatTags();
        addAllTags();
        return this.box;
    }

    public ScrollPane getContent() {
        return content;
    }
}
