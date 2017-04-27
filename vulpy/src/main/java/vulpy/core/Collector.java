package vulpy.core;

import vulpy.core.tracker.Calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collector {

    private List<Project> projectList;
    private Map<String, Tag> tagMap;

    public Collector(){
        this.projectList = new ArrayList<>();
        this.tagMap = new HashMap<>();
    }

    public void startTracking(int i){
        List<String> tags = this.projectList.get(i).getTags();
        for (int j = 0; j < tags.size(); j++) {
            if(!this.tagMap.containsKey(tags.get(j))){
                System.out.println(tags.get(j));
                this.tagMap.put(tags.get(j),new Tag(tags.get(j)));
            }
            this.tagMap.get(tags.get(j)).startTracking();
        }
        this.projectList.get(i).startTracking();
    }

    public void stopTracking(int i){
        List<String> tags = this.projectList.get(i).getTags();
        for (int j = 0; j < tags.size(); j++) {
            if(!this.tagMap.containsKey(tags.get(j))){
                this.tagMap.put(tags.get(j),new Tag(tags.get(j)));
            }
            this.tagMap.get(tags.get(j)).stopTracking();
        }
        this.projectList.get(i).stopTracking();
    }

    public void startTrackingByProject(Project project){
        List<String> tags = project.getTags();
        for (int j = 0; j < tags.size(); j++) {
            if(!this.tagMap.containsKey(tags.get(j))){
                this.tagMap.put(tags.get(j),new Tag(tags.get(j)));
            }
            this.tagMap.get(tags.get(j)).startTracking();
        }
        project.startTracking();
    }

    public void stopTrackingByProject(Project project){
        List<String> tags = project.getTags();
        for (int j = 0; j < tags.size(); j++) {
            if(!this.tagMap.containsKey(tags.get(j))){
                this.tagMap.put(tags.get(j),new Tag(tags.get(j)));
            }
            this.tagMap.get(tags.get(j)).stopTracking();
        }
        project.stopTracking();
    }

    public Project getProject(int i){
        return projectList.get(i);
    }

    public int getProjectListSize(){
        return projectList.size();
    }

    public void addProject(Project project){
        for (int i = 0; i < project.getTags().size(); i++) {
            if(!this.tagMap.containsKey(project.getTags().get(i))){
                this.tagMap.put(project.getTags().get(i),new Tag(project.getTags().get(i)));
            }
        }
        this.projectList.add(project);
    }

    public void addTag(String tag){
        if(!tagMap.containsKey(tag)){
            tagMap.put(tag ,new Tag(tag));
        }
    }

    public Tag getTag(String tag){
        if(tagMap.containsKey(tag)){
            return tagMap.get(tag);
        } else {
            return null;
        }
    }

    public Map<String, Tag> getTagMap(){
        return tagMap;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
