package vulpy.ui;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;
import vulpy.core.Project;

import javafx.animation.Timeline;
import javafx.scene.layout.HBox;


public class ProjectHBox {
    private HBox hbox;
    private Timeline timeline;
    private Project project;
    private Text time;
    private boolean onOff;

    public ProjectHBox (Project project){
        this.hbox = new HBox();
        this.timeline = new Timeline();
        this.project = project;
        this.time = new Text("" + this.project.getTime());
        this.onOff = false;

        makeProjectHbox();
    }

    public void makeProjectHbox(){
        Text name = new Text(this.project.getName());
        Text tags = new Text(getTagsString(this.project));
        Text hourlyWage = new Text(project.getHourlyWage().getWage() + "/" + project.getHourlyWage().getSymbol());
        Button startButton = greateButton();
        this.hbox.getChildren().addAll(name,tags,hourlyWage,this.time,startButton);
    }

    public Button greateButton(){
        Button button = new Button();
        button.setText("Start / Stop");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if(onOff){
                    onOff = false;
                    project.stopTracking();
                    timeline.stop();
                } else {
                        onOff = true;
                        project.startTracking();
                        timeline = new Timeline(
                            new KeyFrame(Duration.millis(100),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    time.setText("" + project.getTime());
                                    System.out.println(project.getTime());
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

    public String getTagsString(Project project){
        String tagStrign = "Tags: ";
        for (int i = 0; i < project.getTags().size(); i++) {
            if(i == project.getTags().size() - 1){
                tagStrign += project.getTags().get(i).getName();
            } else {
                tagStrign += project.getTags().get(i).getName() +  ", ";
            }
        }
        return tagStrign;
    }

    public HBox getHbox() {
        return hbox;
    }
}
