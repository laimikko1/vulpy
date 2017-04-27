package vulpy.ui;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;
import vulpy.core.Collector;
import vulpy.core.Project;

import javafx.animation.Timeline;
import javafx.scene.layout.HBox;

public class ProjectHBox {

    private Collector collector;
    private Project project;
    private HBox hbox;
    private Timeline timeline;
    private Text time;
    private Text hourlyWage;
    private boolean onOff;

    public ProjectHBox(Collector collector) {
        this.project = collector.getProject(collector.getProjectListSize() - 1);
        this.hbox = new HBox(30);
        this.hbox.setPrefWidth(640);
        this.hbox.setMinWidth(640);
        this.hbox.setId("projectBox");
        this.hbox.setAlignment(Pos.CENTER);
        this.timeline = new Timeline();
        this.collector = collector;
        this.time = new Text(project.getReport().getHoursMinutesAndSeconds());
        this.time.setId("timerText");
        this.onOff = false;
        makeProjectHbox();
    }

    public void makeProjectHbox() {
        Text name = new Text(this.project.getName());
        Text tags = new Text(this.project.getTagsString());
        this.hourlyWage = new Text(project.getHourlyWage().getWage() + " " + project.getHourlyWage().getSymbol() + " / hour");
        name.setId("projectText");
        tags.setId("projectText");
        name.setWrappingWidth(105);
        tags.setWrappingWidth(195);
        this.hourlyWage.setId("projectText");
        Button startButton = greateButton();
        startButton.setAlignment(Pos.CENTER_LEFT);
        this.hbox.getChildren().addAll(name, tags, this.hourlyWage, this.time, startButton);
    }

    public Button greateButton() {
        Button button = new Button();
        button.setId("projectButton");
        button.setText("S");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (onOff) {
                    onOff = false;
                    collector.stopTrackingByProject(project);
                    timeline.stop();
                } else {
                        onOff = true;
                        collector.startTrackingByProject(project);
                        timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000),
                            new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent t) {
                                        time.setText(project.getReport().getHoursMinutesAndSeconds());
                                    }
                                })
                        );
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                    }
                }
            });
        return button;
    }

    public void refresh(){
        this.hourlyWage.setText(project.getHourlyWage().getWage() + " " + project.getHourlyWage().getSymbol() + " / hour");
    }


    public HBox getHbox() {
        return hbox;
    }
}
