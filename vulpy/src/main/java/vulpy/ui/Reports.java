package vulpy.ui;

import javafx.scene.layout.VBox;

public class Reports {

    private Projects projects;
    private VBox box;

    public Reports(Projects projects) {
        this.projects = projects;
        this.box = new VBox();
    }

    public VBox getBox() {
        return this.box;
    }
}
