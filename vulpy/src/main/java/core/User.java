package core;

import java.util.HashMap;

public class User {
    private String name;
    private HashMap<String, Theme> themes;

    public User(String name){
        this.name = name;
        this.themes = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTheme(Theme theme){
        themes.put(theme.getName(),theme);
    }
}
