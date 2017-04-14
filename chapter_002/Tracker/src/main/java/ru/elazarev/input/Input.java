package ru.elazarev.input;

/**
 * Input interface to ask user.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 09.04.17
 */
public interface Input {
    /**
     * Method to ask question and get string answer.
     * @param question - text of question
     * @return - answer
     */
    String ask(String question);

    /**
     * Method to ask question and get int answer.
     * @param question - text of question
     * @param range - answer should be in range
     * @return - answer
     */
     int ask(String question, int[] range);
}
