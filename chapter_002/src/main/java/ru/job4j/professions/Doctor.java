package ru.job4j.professions;

/**
 * Doctor class.
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Doctor extends Profession {

    /**
     * Has doctor his own clinic?
     */
    private boolean ownClinic;

    /**
     * Doctor washing hands.
     * @return String
     */
    public String washHands() {
        return "washing hands";
    }

    /**
     * Doctior doing operation.
     * @return String
     */
    public String doOperation() {
        return "do operation...";
    }

    /**
     * Doctor is interviewing pationt.
     * @return String
     */
    public String interviewPatient() {
        return "What is your name?";
    }

    /**
     * Setter for ownClinic field.
     * @param ownClinic - boolean
     */
    public void setOwnClinic(boolean ownClinic) {
        this.ownClinic = ownClinic;
    }

    /**
     * Returns true if doctor have own clinic and false else.
     * @return - boolean
     */
    public boolean haveUOwnClinic() {
        return this.ownClinic;
    }
}
