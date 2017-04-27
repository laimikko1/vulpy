package vulpy.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Project;

import java.util.List;

public class Reports {

    private VBox box;
    private VBox right;
    private VBox left;
    private List<Project> projects;
    private Projects project;
    private ScrollPane content;
    private HBox subBox;

    public Reports(Projects project, ScrollPane content) {
        this.content = content;
        formatReports(project);
        addAllReports();
    }

    public void formatReports(Projects project) {
        this.projects = project.getCollector().getProjectList();
        this.project = project;
        this.box = new VBox(10);
        this.box.setId("reportBox");
        this.subBox = new HBox(10);
        this.right = new VBox(10);
        this.left = new VBox(10);
        this.right.setId("reportSubBox");
        this.left.setId("reportSubBox");
        Text text = new Text("Your projects!");
        text.setId("yourProjects");
        this.subBox.getChildren().addAll(right, left);
        this.box.getChildren().addAll(text, subBox);
    }

    public void addAllReports() {
        for (int i = 0; i < projects.size(); i++) {
            if (i % 2 == 0) {
                this.right.getChildren().addAll(addReportButton(i));
            } else {
                this.left.getChildren().addAll(addReportButton(i));
            }
        }
    }

    public Button addReportButton(int i) {
        Button reportButton = new Button();
        addButtonFunctions(reportButton, i);
        reportButton.setPrefWidth(250);
        reportButton.setPrefHeight(30);
        reportButton.setId("reportButton");
        reportButton.setText(this.projects.get(i).getName() + "    " + this.projects.get(i).getTagsString());
        addButtonFunctions(reportButton, i);
        return reportButton;
    }

    public void addButtonFunctions(Button button, int i) {
        ReportHBox reportHBox = new ReportHBox(this.projects.get(i), this);
        HBox hbox = reportHBox.getBox();
        button.setOnAction(e -> {
            this.content.setContent(hbox);
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
    }

    public VBox getBox() {
        formatReports(this.project);
        addAllReports();
        return this.box;
    }

    public ScrollPane getContent() {
        return content;
    }
}
