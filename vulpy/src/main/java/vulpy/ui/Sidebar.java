package vulpy.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sidebar-luokka tarjoaa sidebar näkymän UI:hin.
 */

public class Sidebar {

    private Button toProjects;
    private Button toReports;
    private Button toTags;
    private Stage window;
    private Scene projects;
    private Scene reports;
    private Scene tags;

    private VBox sidebar;


    public Sidebar(){
        createSidebar();
    }

    public void createSidebar(){
        Button toProjects = new Button("Projects");
        toProjects.setMinWidth(200);
        toProjects.setOnAction(e -> window.setScene(projects));

        Button toReports = new Button("Reports");
        toReports.setMinWidth(200);
        toReports.setOnAction(e -> window.setScene(reports));

        Button toTags = new Button("Tags");
        toTags.setMinWidth(200);
        toTags.setOnAction(e -> window.setScene(tags));

        this.sidebar = new VBox(10);
        sidebar.setPadding(new Insets(50,10,0,10));
        sidebar.setStyle("-fx-background-color: #323232;");
        sidebar.getChildren().addAll(toProjects,toReports,toTags);
    }

    public VBox getSidebar(){
        return this.sidebar;
    }
}
