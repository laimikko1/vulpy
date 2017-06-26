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

    /**
     * Konstruktorissa luodaan kaikki vasemmalle puolelle ruutua scrollPane,
     * ja oikealle puolelle alustetaan createSidebar komennolla valikko.
     * @param layout borderpane näkymä, johon lisätään elementtejä.
     * @param scene tämänhetkinen ohjelman näkymä.
     * @param window koko ohjelman ikkuna.
     */

    public Menu(Stage window, BorderPane layout, Scene scene) {
        this.window = window;
        this.layout = layout;
        this.content = new ScrollPane();
        this.content.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.layout.setCenter(content);
        this.scene = scene;
        createSidebar();
    }

    /**
     * Metodi createSidebar tarjoaa ominaisuuden menun luontiin.
     */

    public void createSidebar() {
        Button toProjects = new Button("Projects");
        Projects projects = new Projects();
        Reports reports = new Reports(projects, this.content);
        Tags tags = new Tags(projects.getCollector(), this.content);

        toProjects.setMinWidth(200);
        toProjects.setOnAction(e -> {
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            this.content.setContent(projects.getProjectsSection());
            layout.setBottom(projects.writingSection());
        });

        Button toReports = new Button("Reports");
        toReports.setMinWidth(200);
        toReports.setOnAction(e -> {
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            this.content.setContent(reports.getBox());
            this.layout.setBottom(null);
        });

        Button toTags = new Button("Tags");
        toTags.setMinWidth(200);
        toTags.setOnAction(e -> {
            this.content.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            this.content.setContent(tags.getBox());
            this.layout.setBottom(null);
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
