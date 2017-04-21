package vulpy.ui;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Projects {

    private List<Project> projects;
    private VBox projectsSection;

    public Projects(){
        this.projects = new ArrayList<>();
        this.projectsSection = new VBox();
    }

    public HBox makeProject(Project project){
        HBox hBox = new HBox();
        Text name = new Text(project.getName());
        Text tags = new Text(getTagsString(project));
        Text hourlyWage = new Text(project.getHourlyWage().getWage() + "/" + project.getHourlyWage().getSymbol());
        Timeline timeline = new Timeline();
        Label myLabel = new Label(project.getTime() + "");

        Button startButton = new Button("");
        hBox.getChildren().addAll(name,tags,hourlyWage,startButton);

        
        return hBox;
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
        System.out.println(tagStrign);
        return tagStrign;
    }

    public VBox writingSection(){
        Text addNew = new Text("Add new");
        HBox textArea = textArea();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(addNew,textArea);
        return vBox;
    }

    public HBox textArea(){
        HBox writingSection = new HBox();
        writingSection.setMaxWidth(600);
        TextArea name = new TextArea("");
        TextArea tags = new TextArea("");

        Button add = new Button("Add");
        add.setMinWidth(120);
        add.setOnAction(e -> {
            addProject(name,tags);
            projectsSection.getChildren().add(makeProject(projects.get(projects.size() - 1)));
        });

        writingSection.setMaxHeight(50);
        writingSection.getChildren().
                addAll(overText(name,"Name"),overText(tags, "Tags, separate with comma"),add);
        return writingSection;
    }

    public VBox overText(TextArea textArea, String addText){
        VBox vBox = new VBox();
        Text text = new Text(addText);
        vBox.getChildren().addAll(text,textArea);
        return vBox;
    }

    public void addProject(TextArea textAreaName, TextArea textAreaTags){
        String name = textAreaName.getText();
        ArrayList<String> tags = seperate(textAreaTags.getText());
        projects.add(new Project(name,tags));
        textAreaName.clear();
        textAreaTags.clear();
    }

    public ArrayList<String> seperate(String stringTags){
        ArrayList<String> tags = new ArrayList<>();
        if(!stringTags.isEmpty()){
            String[] list = stringTags.split(",");
            Arrays.stream(list).forEach(e -> tags.add(e.replaceAll("\\s+","")));
        }
        return tags;
    }

    public List<Project> getProjects(){
        return this.projects;
    }

    public VBox getProjectsSection() {
        return projectsSection;
    }
}
