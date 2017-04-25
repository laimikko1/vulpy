package vulpy.ui;

import javafx.scene.layout.HBox;
import vulpy.core.Project;

public class ReportHBox {

    private HBox hBox;

    public ReportHBox(Project project){
        this.hBox = new HBox();
    }

    public HBox getBox(){
        return this.hBox;
    }
}
