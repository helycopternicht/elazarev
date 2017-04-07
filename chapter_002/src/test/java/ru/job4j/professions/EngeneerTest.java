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
     * DevelopDivice method test.
     */
    @Test
    public void whenDevelopDiviceThenDeveloping() {
        Engeneer eng = new Engeneer();
        assertThat(eng.developDivice(), is("In developing..."));
    }

    /**
     * DrinkCofee method test.
     */
    @Test
    public void whenDrinkCofeeThenDrink() {
        Engeneer eng = new Engeneer();
        assertThat(eng.drinkCofee(), is("Woooow"));
    }
}
