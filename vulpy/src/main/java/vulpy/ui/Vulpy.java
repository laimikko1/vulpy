package vulpy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;

/**
 * Main-luokka joka tarjoaa ohjelman käynnistämisen ja pitää huolen UI:n pääikkunasta.
 */

public class Vulpy extends Application {

    public static Stage window;

    /**
     * Metodi main käynnistää ohjelman.
     * @param args args
     */

    public static void main(String[] args) {
        URL iconURL  = Vulpy.class.getClassLoader().getResource("ui/logo.png");
        java.awt.Image image = new ImageIcon(iconURL).getImage();
        //com.apple.eawt.Application.getApplication().setDockIconImage(image);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox layout = new HBox();
        BorderPane borderPane = new BorderPane();

        layout.setStyle("-fx-background-color: #FAFBFC;");
        borderPane.setStyle("-fx-background-color: #FAFBFC;");

        Scene scene = new Scene(layout, 900, 500);
        borderPane.setPrefSize(900, 500);
        Menu menu = new Menu(primaryStage, borderPane, scene);

        scene.getStylesheets().add(getClass().getResource("/vulpy.css").toExternalForm());
        primaryStage.getIcons().add(new Image(Vulpy.class.getClassLoader().getResourceAsStream("ui/logo32.png")));

        layout.getChildren().addAll(borderPane);
        layout.getChildren().addAll(menu.getSidebar());

        window = menu.getWindow();
        window.setTitle("Vulpy");
        window.setScene(menu.getScene());
        window.setResizable(false);
        window.show();
    }
}
