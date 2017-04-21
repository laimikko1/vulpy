package vulpy.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Menu-luokka tarjoaa menun, sekä scenejen vaihto ominaisuudet UI:hin.
 */

public class Menu {

    private Stage window;
    private Scene projects;
    private Scene reports;
    private Scene tags;
    private VBox sidebar;
    private BorderPane layout;

    public Menu(Stage window, BorderPane layout, Scene projects, Scene reports, Scene tags) {
        this.window = window;
        this.layout = layout;
        this.projects = projects;
        this.reports = reports;
        this.tags = tags;
        createSidebar();
    }

    public void createSidebar() {
        Button toProjects = new Button("Projects");
        Projects projectsWindow = new Projects();
        Reports reportsWindow = new Reports(projectsWindow);
        Tags tagsWindow = new Tags(projectsWindow);

        Projects projects1 = new Projects();
        toProjects.setMinWidth(200);
        toProjects.setOnAction(e -> layout.setLeft(projects1.getBox()));


        Button toReports = new Button("Reports");
        toReports.setMinWidth(200);
        toReports.setOnAction(e -> layout.setLeft(reportsWindow.));

        Button toTags = new Button("Tags");
        toTags.setMinWidth(200);
        toTags.setOnAction(e -> layout.setLeft(new VBox()));

        this.sidebar = new VBox(10);
        this.sidebar.setPadding(new Insets(50, 10, 0, 10));
        this.sidebar.setStyle("-fx-background-color: #323232;");
        this.sidebar.getChildren().addAll(toProjects, toReports, toTags);
    }

    public VBox getSidebar() {
        return this.sidebar;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Scene getProjects() {
        return projects;
    }

    public void setProjects(Scene projects) {
        this.projects = projects;
    }

    public Scene getReports() {
        return reports;
    }

    public void setReports(Scene reports) {
        this.reports = reports;
    }

    public Scene getTags() {
        return tags;
    }

    public void setTags(Scene tags) {
        this.tags = tags;
    }
}
