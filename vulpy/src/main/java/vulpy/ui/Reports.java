package vulpy.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vulpy.core.Project;

import java.util.List;

public class Reports {

    private HBox box;
    private VBox right;
    private VBox left;
    private List<Project> projects;
    private Projects project;
    private BorderPane layout;

    public Reports(Projects project, BorderPane layout) {
        this.layout = layout;
        formatReports(project);
        addAllReports();
    }

    public void formatReports(Projects project){
        this.projects = project.getProjects();
        this.project = project;
        this.box = new HBox(10);
        this.right = new VBox(10);
        this.left = new VBox(10);
        this.left.setId("reportBox");
        this.right.setId("reportBox");
        this.box.getChildren().addAll(right,left);
    }

    public void addAllReports(){
        for (int i = 0; i < projects.size(); i++) {
            if(i % 2 == 0){
                this.right.getChildren().addAll(addReportButton(i));
            } else {
                this.left.getChildren().addAll(addReportButton(i));
            }
        }
    }

    public Button addReportButton(int i){
        Button reportButton = new Button();
        addButtonFunctions(reportButton,i);
        reportButton.setPrefWidth(300);
        reportButton.setId("reportButton");
        reportButton.setText(this.projects.get(i).getName() + "\t" + this.projects.get(i).getTagsString());
        addButtonFunctions(reportButton,i);
        return reportButton;
    }

    public void addButtonFunctions(Button button, int i){
        ReportHBox reportHBox = new ReportHBox(this.projects.get(i));
        HBox hbox = reportHBox.getBox();
        button.setOnAction(e -> {

        });
    }

    public HBox getBox() {
        formatReports(this.project);
        addAllReports();
        return this.box;
    }
}
