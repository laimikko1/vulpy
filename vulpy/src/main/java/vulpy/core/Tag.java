package vulpy.core;

/**
 * Tag-luokka tarjoaa tagin, jota voi hyödyntää niinkuin projektia.
 * Kuitenkin yksi projekti voi käyttää useita tagejä.
 */

public class Tag {
    private String name;

    /**
     * Konstruktorissa luodaan Tag-olio.
     * @param name tagin nimi.
     */

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
