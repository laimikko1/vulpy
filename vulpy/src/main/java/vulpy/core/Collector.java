package vulpy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collector {

    /**
     * Collector-luokka tarjoaa yhtymäpinnan projektejen ja tagejen välille.
     */

    private List<Project> projectList;
    private Map<String, Tag> tagMap;

    /**
     * Konstruktorissa alustetaan projektilista, sekä tagMap.
     */

    public Collector() {
        this.projectList = new ArrayList<>();
        this.tagMap = new HashMap<>();
    }

    /**
     * Metodi startTrackingByProject tarjoaa toiminnallisuuden tämän projektin ajanlaskennan aloittamiseen,
     * sekä jokaisen tämän projektin tagin ajanlaskun käynnistämiseen.
     * @param project projekti jonka ajanlasku halutaan aloittaa.
     */

    public void startTrackingByProject(Project project) {
        List<String> tags = project.getTags();
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            if (!this.tagMap.containsKey(tag)) {
                this.tagMap.put(tag, new Tag(tag));
            }
            this.tagMap.get(tags.get(i)).startTracking();
        }
        project.startTracking();
    }

    /**
     * Metodi stopTrackingByProject tarjoaa toiminnallisuuden tämän projektin ajanlaskennan lopettamiseen,
     * sekä jokaisen tämän projektin tagin ajanlaskun lopettamiseen, jos tällähetkellä ei ole muita projekteja
     * päällä laskemassa tagin aikaa.
     * @param project projekti jonka ajanlasku halutaan lopettaa.
     */

    public void stopTrackingByProject(Project project) {
        List<String> tags = project.getTags();
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            if (tagMap.containsKey(tag)) {
                tagMap.get(tag).stopTracking();
            }
            project.stopTracking();
        }
    }

    /**
     * Metodi getProject palauttaa listasta tietyssä indeksissä olevan projektin.
     * @param i indeksi mistä kohtaa listasta halutaan projekti.
     * @return projekti mikä saadaan on i:n kohdalla listassa.
     */

    public Project getProject(int i) {
        return projectList.get(i);
    }

    /**
     * Metodi getProjectListSize palauttaa projekti listauksen koon.
     * @return listan koko.
     */

    public int getProjectListSize() {
        return projectList.size();
    }

    /**
     * Metodi addProject tarjoaa mahdollisuuden lisätä projekteja collectoriin.
     * @param project lisättävä projekti.
     */

    public void addProject(Project project) {
        for (int i = 0; i < project.getTags().size(); i++) {
            if (!this.tagMap.containsKey(project.getTags().get(i))) {
                this.tagMap.put(project.getTags().get(i), new Tag(project.getTags().get(i)));
            }
        }
        this.projectList.add(project);
    }

    /**
     * Metodi getTag palauttaa tag olion, jos sellainen on olemassa.
     * @param tag haettavan olion nimi.
     * @return Tag-olio, jos sellainen on olemassa.
     */

    public Tag getTag(String tag) {
        if (tagMap.containsKey(tag)) {
            return tagMap.get(tag);
        } else {
            return null;
        }
    }

    public Map<String, Tag> getTagMap() {
        return tagMap;
    }

    public List<Project> getProjectList() {
        return projectList;
    }
}
