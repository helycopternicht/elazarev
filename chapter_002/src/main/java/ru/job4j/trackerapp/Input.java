package ru.job4j.trackerapp;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 09.04.17
 */
public interface Input {
    /**
     * Method to ask question.
     * @param question - text of question
     * @return - answer
     */
    String ask(String question);
}
