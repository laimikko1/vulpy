package vulpy.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Collector;
import vulpy.core.Tag;

import java.util.Map;

public class Tags {

    private Collector collector;
    private VBox box;
    private ScrollPane content;
    private VBox right;
    private VBox left;

    public Tags(Collector collector, ScrollPane content) {
        this.content = content;
        this.collector = collector;
        this.box = new VBox();
        formatTags();
        addAllTags();
    }

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

    public Button addTagButton(String name) {
        Button reportButton = new Button();
        addButtonFunctions(reportButton, name);
        reportButton.setPrefWidth(250);
        reportButton.setPrefHeight(30);
        reportButton.setId("reportButton");
        reportButton.setText(name);
        addButtonFunctions(reportButton, name);
        return reportButton;
    }

    public void addButtonFunctions(Button button, String name) {
        TagsHBox tagsHbox = new TagsHBox(this.collector.getTag(name), this);
        HBox hbox = tagsHbox.getBox();
        button.setOnAction(e -> {
            this.content.setContent(hbox);
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
    }

    public VBox getBox() {
        formatTags();
        addAllTags();
        return this.box;
    }

    public ScrollPane getContent() {
        return content;
    }
}
