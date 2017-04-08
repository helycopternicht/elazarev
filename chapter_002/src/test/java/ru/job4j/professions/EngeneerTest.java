package ru.job4j.professions;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Engeneer test class.
 * @author Eugeme Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.04.17
 */
public class EngeneerTest {
    /**
     * Work method test.
     */
    @Test
    public void whenWorkThenWorking() {
        Engeneer eng = new Engeneer();
        assertThat(eng.work(), is("Working..."));
    }

    /**
     * DevelopDevice method test.
     */
    @Test
    public void whenDevelopDiviceThenDeveloping() {
        Engeneer eng = new Engeneer();
        assertThat(eng.developDevice(), is("In developing..."));
    }

    /**
     * DrinkCofee method test.
     */
    @Test
    public void whenDrinkCofeeThenDrink() {
        Engeneer eng = new Engeneer();
        assertThat(eng.drinkCofee(), is("Woooow"));
    }

    /**
     * setWorkPlace and whereAreYouWork methods tests.
     */
    @Test
    public void setWorkPlaceAndWhereAruWorkTests() {
        Engeneer eng = new Engeneer();
        assertThat(eng.whereAreYouWork(), is("no working"));
        eng.setWorkPlace("Google");
        assertThat(eng.whereAreYouWork(), is("Google"));
    }
    /**
     * setProjectName and whatAreYouWorkingOn methods test.
     */
    @Test
    public void setProjectNameAndWhatAreYouWorkingOnTest() {
        Engeneer eng = new Engeneer();
        assertThat(eng.whatAreYouWorkingOn(), is("I have no project"));
        eng.setProjectName("GoogleDocs project");
        assertThat(eng.whatAreYouWorkingOn(), is("GoogleDocs project"));
    }
}
