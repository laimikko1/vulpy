package core;

import java.util.HashMap;

public class Theme {
    private HashMap<String,Project> projects;
    private String name;

    public Theme(String name){
        this.name = name;
        this.projects = new HashMap<>();
    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    public void setProjects(HashMap<String, Project> projects) {
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
