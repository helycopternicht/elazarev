package ru.job4j.professions;

/**
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Engeneer extends Profession {
    /**
     * Name of working place of engeneer.
     */
    private String workPlece;

    /**
     * Name of project of engeneer.
     */
    private String projectName;

    /**
     * Eingeneer is working.
     * @return Sting Working
     */
    public String work() {
        return "Working...";
    }

    /**
     * Engenee developing device.
     * @return String
     */
    public String developDivice() {
        return "In developing...";
    }

    /**
     * Engeneer drinking cofee.
     * @return String
     */
    public String drinkCofee() {
        return "Woooow";
    }
}
