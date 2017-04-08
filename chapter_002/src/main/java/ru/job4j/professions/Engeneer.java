package ru.job4j.professions;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Engeneer extends Profession {
    /**
     * Name of working place of engeneer.
     */
    private String workPlace;

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
    public String developDevice() {
        return "In developing...";
    }

    /**
     * Engeneer drinking cofee.
     * @return String
     */
    public String drinkCofee() {
        return "Woooow";
    }

    /**
     * Setter for working place field.
     * @param workPlace - work place name
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    /**
     * Setter for project name field.
     * @param projectName - String project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Result workin place name or default string.
     * @return String
     */
    public String whereAreYouWork() {
        String result;
        if (this.workPlace == null) {
            result = "no working";
        } else {
            result = this.workPlace;
        }
        return result;
    }

    /**
     * Returns project name or default value.
     * @return String
     */
    public String whatAreYouWorkingOn() {
        String result;
        if (this.projectName == null) {
            result = "I have no project";
        } else {
            result = this.projectName;
        }
        return result;
    }
}
