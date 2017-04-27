package vulpy.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vulpy.core.Collector;
import vulpy.core.Project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Luokka-projects tarjoaa projekti näkymän UI:hin.
 */

public class Projects {

    private Collector collector;
    private List<Project> projects;
    private VBox projectsSection;
    private List<ProjectHBox> projectHBoxes;

    /**
     * Konstruktorissa alustetaan collector-luokka logiikan puolelta, ja sen avulla saadaan kaikki tarvittava tieto logiikan puolelta.
     */

    public Projects() {
        this.collector = new Collector();
        this.projects = this.collector.getProjectList();
        this.projectsSection = new VBox(10);
        this.projectsSection.setId("projectsSection");
        this.projectHBoxes = new ArrayList<>();
    }

    /**
     * Metodi writingSection luo projektin luonnille välttämättömät boxit.
     * @return projektin luonnille välttämättömän boxin.
     */

    public VBox writingSection() {
        Text addNew = new Text("Add new project");
        addNew.setId("addNew");
        HBox textArea = textArea();
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 30, 10, 10));
        vBox.getChildren().addAll(addNew, textArea);
        return vBox;
    }

    /**
     * Metodi refresh päivittää kaikki sellaiset kentät joiden tieto on voinut muuttua ohjelman käytön aikana.
     */

    public void refresh() {
        for (int i = 0; i < projectHBoxes.size(); i++) {
            projectHBoxes.get(i).refresh();
        }
    }

    /**
     * Metodi textArea luo tekstiboxit joihin voi kirjoittaa.
     * @return tekstiboksit joihin voi kirjoittaa.
     */

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
            if (name.getText().equals("Set name!") || name.getText().equals("Name is too long!") || tags.getText().equals("Set at least one tag!") || tags.getText().equals("Too much tags!")) {
                name.setText("");
                tags.setText("");
            } else if (name.getText().isEmpty()) {
                name.setText("Set name!");
            } else if (name.getText().length() > 10) {
                name.setText("Name is too long!");
            } else if (tags.getText().isEmpty()) {
                tags.setText("Set at least one tag!");
            } else if (tags.getText().length() > 20) {
                tags.setText("Too much tags!");
            } else {
                addProject(name, tags);
                ProjectHBox hBox = new ProjectHBox(collector);
                projectHBoxes.add(hBox);
                projectsSection.getChildren().add(hBox.getHbox());
            }
        });

        VBox vBoxAdd = new VBox(10);
        vBoxAdd.getChildren().addAll(new Text(), add);

        writingSection.setMaxHeight(70);
        writingSection.getChildren().
                addAll(overText(name, "Name"),
                       overText(tags,  "Tags, separate with comma (max 2)"), vBoxAdd);
        return writingSection;
    }

    /**
     * Metodi overText luo TekstiArean yläpuolle info-tekstin.
     * @param textArea haluttu tekstiArea.
     * @param addText haluttu teksti.
     * @return boxi missä on tarvittava tieto.
     */

    public VBox overText(TextArea textArea, String addText) {
        VBox vBox = new VBox(10);
        Text text = new Text(addText);
        text.setId("addNew");
        vBox.getChildren().addAll(text, textArea);
        return vBox;
    }

    /**
     * Metodi addProject lisää projektin ohjelmaan.
     * @param textAreaName nimikenttä.
     * @param textAreaTags tagikenttä.
     */

    public void addProject(TextArea textAreaName, TextArea textAreaTags) {
        String name = textAreaName.getText();
        List<String> tags = separate(textAreaTags.getText());
        this.collector.addProject(new Project(name, tags));
        textAreaName.clear();
        textAreaTags.clear();
    }

    /**
     * Metodi seperate luo listan tageistä, jotka ohjelma on saanut pilkulla eroteltuna.
     * @param stringTags käyttäjän antama lista tageista pilkulla eroteltuna.
     * @return lista tageistä.
     */

    public ArrayList<String> separate(String stringTags) {
        ArrayList<String> tags = new ArrayList<>();
        if (!stringTags.isEmpty()) {
            String[] list = stringTags.split(",");
            Arrays.stream(list).forEach(e -> tags.add(e.replaceAll("\\s+", "")));
        }
        return tags;
    }

    public Collector getCollector() {
        return this.collector;
    }

    public VBox getProjectsSection() {
        refresh();
        return projectsSection;
    }
}
