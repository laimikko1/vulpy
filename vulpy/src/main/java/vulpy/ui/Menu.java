package vulpy.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Menu-luokka tarjoaa menun, sekä scenejen vaihto ominaisuudet UI:hin.
 */

public class Menu {

    private Stage window;
    private Scene scene;
    private VBox sidebar;
    private BorderPane layout;
    private ScrollPane content;

    public Menu(Stage window, BorderPane layout, Scene scene) {
        this.window = window;
        this.layout = layout;
        this.content = new ScrollPane();
        this.layout.setCenter(content);
        this.scene = scene;
        createSidebar();
    }

    public void createSidebar() {
        Button toProjects = new Button("Projects");
        Projects projects = new Projects();
        Reports reports = new Reports(projects);
        Tags tags = new Tags(projects);

        toProjects.setMinWidth(200);
        toProjects.setOnAction(e -> {
            content.setContent(projects.getProjectsSection());
            layout.setBottom(projects.writingSection());
        });

        Button toReports = new Button("Reports");
        toReports.setMinWidth(200);
        toReports.setOnAction(e -> {
            content.setContent(reports.getBox());
            layout.setBottom(null);
        });

        Button toTags = new Button("Tags");
        toTags.setMinWidth(200);
        toTags.setOnAction(e -> {
            content.setContent(tags.getBox());
            layout.setBottom(null);
        });

        toProjects.fire();

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

    public Scene getScene() {
        return scene;
    }

}
