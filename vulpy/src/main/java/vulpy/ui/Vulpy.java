package vulpy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Vulpy extends Application {

    Stage window;
    Scene projects, reports, tags;

    public static void main(String[] args) {
        try {
            // Won't work on Windows or Linux. Only for Mac dock
            URL iconURL  = Vulpy.class.getClassLoader().getResource("ui/logo.png");
            Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {}
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        BorderPane layout = new BorderPane();

        layout.setStyle("-fx-background-color: #FAFBFC;");

        Sidebar sidebar = new Sidebar();

        layout.setRight(sidebar.getSidebar());
        layout.setMaxHeight(500);
        layout.setMinHeight(400);
        layout.setMaxWidth(800);
        layout.setMinWidth(700);

        this.projects = new Scene(layout,800,500);
        this.projects.getStylesheets().add(getClass().getResource("/vulpy.css").toExternalForm());

        window.setTitle("Vulpy");
        window.setScene(this.projects);
        window.setResizable(false);
        window.show();
    }

}
