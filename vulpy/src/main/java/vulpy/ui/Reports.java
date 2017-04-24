package vulpy.ui;

import javafx.scene.control.Button;
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

    public Reports(Projects project) {
        formatReports(project);
        addAllReports();
    }

    public void formatReports(Projects project){
        this.projects = project.getProjects();
        this.project = project;
        this.box = new HBox(10);
        this.right = new VBox(10);
        this.left = new VBox(10);
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
        reportButton.setPrefWidth(250);
        reportButton.setId("reportButton");
        reportButton.setText(this.projects.get(i).getName() + "\t" + this.projects.get(i).getTagsString());
        return reportButton;
    }

    public HBox getBox() {
        formatReports(this.project);
        addAllReports();
        return this.box;
    }
}
