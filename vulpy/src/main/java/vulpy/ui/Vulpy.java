package vulpy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Main-luokka joka tarjoaa ohjelman käynnistämisen ja pitää huolen UI:n pääikkunasta.
 */

public class Vulpy extends Application {

    Stage window;

    public static void main(String[] args) {
        try {
            URL iconURL  = Vulpy.class.getClassLoader().getResource("ui/logo.png");
            Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            //This is for linux and windows
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #FAFBFC;");

        Scene scene = new Scene(layout, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/vulpy.css").toExternalForm());

        Sidebar sidebar = new Sidebar(primaryStage, scene, scene, scene);

        layout.setRight(sidebar.getSidebar());
        layout.setMaxHeight(500);
        layout.setMinHeight(400);
        layout.setMaxWidth(800);
        layout.setMinWidth(700);

        window = sidebar.getWindow();
        window.setTitle("Vulpy");
        window.setScene(sidebar.getProjects());
        window.setResizable(false);
        window.show();
    }

}
