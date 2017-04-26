package vulpy.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

    public Projects() {
        this.projects = new ArrayList<>();
        this.projectsSection = new VBox(10);
        this.projectsSection.setId("projectsSection");
    }

    public VBox writingSection() {
        Text addNew = new Text("Add new project");
        addNew.setId("addNew");
        HBox textArea = textArea();
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 30, 10, 10));
        vBox.getChildren().addAll(addNew, textArea);
        return vBox;
    }

    public HBox textArea() {
        HBox writingSection = new HBox(10);
        writingSection.setMaxWidth(700);
        TextArea name = new TextArea("");
        TextArea tags = new TextArea("");
        Button add = new Button("Add");
        add.setMinHeight(30);
        add.setId("addButton");
        add.setMinWidth(120);
        add.setOnAction(e -> {
            if(name.getText().equals("Set name!") || name.getText().equals("Name is too long!") || tags.getText().equals("Set at least one tag!") || tags.getText().equals("Too much tags!")){
                name.setText("");
                tags.setText("");
            } else if(name.getText().isEmpty()){
                name.setText("Set name!");
            } else if(name.getText().length() > 10){
                name.setText("Name is too long!");
            } else if(tags.getText().isEmpty()){
                tags.setText("Set at least one tag!");
            } else if(tags.getText().length() > 20){
                tags.setText("Too much tags!");
            } else {
                addProject(name, tags);
                ProjectHBox hBox = new ProjectHBox(projects.get(projects.size() - 1));
                projectsSection.getChildren().add(hBox.getHbox());
            }
        });

        VBox vBoxAdd = new VBox(10);
        vBoxAdd.getChildren().addAll(new Text(),add);

        writingSection.setMaxHeight(70);
        writingSection.getChildren().
                addAll(overText(name, "Name"),
                       overText(tags,  "Tags, separate with comma (max 2)"), vBoxAdd);
        return writingSection;
    }

    public VBox overText(TextArea textArea, String addText) {
        VBox vBox = new VBox(10);
        Text text = new Text(addText);
        text.setId("addNew");
        vBox.getChildren().addAll(text, textArea);
        return vBox;
    }

    public void addProject(TextArea textAreaName, TextArea textAreaTags) {
        String name = textAreaName.getText();
        ArrayList<String> tags = separate(textAreaTags.getText());
        projects.add(new Project(name, tags));
        textAreaName.clear();
        textAreaTags.clear();
    }

    public ArrayList<String> separate(String stringTags) {
        ArrayList<String> tags = new ArrayList<>();
        if (!stringTags.isEmpty()) {
            String[] list = stringTags.split(",");
            Arrays.stream(list).forEach(e -> tags.add(e.replaceAll("\\s+", "")));
        }
        return tags;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public VBox getProjectsSection() {
        return projectsSection;
    }
}
