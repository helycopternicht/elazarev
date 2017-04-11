package ru.elazarev;

/**
 * Profession class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class Profession {
    /**
     * Name of professional.
     */
    private String name;

    /**
     * Age of professional.
     */
    private int age;

    /**
     * Years of experiance of professional.
     */
    private int yearsOfExpirience;

    /**
     * Specialization of professional.
     */
    private int specialization;

    /**
     * Name getter.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name - name field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Age getter.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Age setter.
     * @param age - age field
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * YearsOfExpirience getter.
     * @return - yearsOfExpirience field
     */
    public int getYearsOfExpirience() {
        return yearsOfExpirience;
    }

    /**
     * YearsOfExpirience setter.
     * @param yearsOfExpirience - yearsOfExpirience field
     */
    public void setYearsOfExpirience(int yearsOfExpirience) {
        this.yearsOfExpirience = yearsOfExpirience;
    }

    /**
     * Specialization getter.
     * @return specialization field
     */
    public int getSpecialization() {
        return specialization;
    }

    /**
     * Specialization setter.
     * @param specialization - specialization field
     */
    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }
}
