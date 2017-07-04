package ru.elazarev.threads;

import org.junit.Test;

/**
 * Test for WordSpacesFinder class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 04.07.17
 */
public class WordSpacesFinderTest {
    /**
     * Starts finder.
     */
    @Test
    public void start() {
        // three words and three spaces
        new WordSpacesFinder(" one two three").printResult();
    }
}