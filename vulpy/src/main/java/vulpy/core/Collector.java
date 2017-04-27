package vulpy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collector {

    /**
     * Collector-luokka tarjoaa yhtymäpinnan projektejen ja tagejen välille.
     * Luokalle kuuluu vastuu siitä että miten tagejen ja projektejen ajastimet on päällä tai pois päältä.
     */

    private List<Project> projectList;
    private Map<String, Tag> tagMap;
    private Map<String, Integer> howManyTagsIsOnSameTime;

    public Collector(){
        this.projectList = new ArrayList<>();
        this.tagMap = new HashMap<>();
        this.howManyTagsIsOnSameTime = new HashMap<>();
    }

    public void startTrackingByProject(Project project){
        List<String> tags = project.getTags();
        for (int j = 0; j < tags.size(); j++) {
            if(!this.howManyTagsIsOnSameTime.containsKey(tags.get(j))){
                this.howManyTagsIsOnSameTime.put(tags.get(j), 1);
            } else {
                this.howManyTagsIsOnSameTime.put(tags.get(j), this.howManyTagsIsOnSameTime.get(tags.get(j)) + 1);
            }
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
            if(this.howManyTagsIsOnSameTime.containsKey(tags.get(j)) && this.howManyTagsIsOnSameTime.get(tags.get(j)) == 1){
                this.howManyTagsIsOnSameTime.remove(tags.get(j));
                if(!this.tagMap.containsKey(tags.get(j))){
                    this.tagMap.put(tags.get(j),new Tag(tags.get(j)));
                }
                this.tagMap.get(tags.get(j)).stopTracking();
            } else {
                this.howManyTagsIsOnSameTime.put(tags.get(j), this.howManyTagsIsOnSameTime.get(tags.get(j)) - 1);
            }
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
