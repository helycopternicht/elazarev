package ru.elazarev.input;

import ru.elazarev.exceptions.OutOfMenuException;

/**
 * Class for emulation user input.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 11.04.17
 */
public class StubInput implements Input {

    /**
     * List of answers.
     */
    private String[] answers;

    /**
     * Index of current question.
     */
    private int position;

    /**
     * Default constructor.
     * @param answers - List of answers
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Returns next answer from answers list.
     * @param question - text of question
     * @return
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * Returns next answer from answers list and cast to int.
     * @param question - text of question
     * @return
     */
    @Override
    public int ask(String question, int[] range) {
        System.out.println(question);
        return getIntFromUser(range);
    }

    /**
     * Get user int input.
     * @param range - range of menu keys
     * @return user input
     */
    private int getIntFromUser(int[] range) {
        int result;
        try {
            result = Integer.valueOf(answers[position++]);
            boolean inRange = false;
            for (int i = 0; i < range.length; i++) {
                if (result == range[i]) {
                    inRange = true;
                }
            }

            if (!inRange) {
                throw new OutOfMenuException("Menu dosn't have clause with key " + result);
            }

        } catch (NumberFormatException ex) {
            System.out.println("Incorrect number! Please add correct number...");
            result = getIntFromUser(range);
        } catch (OutOfMenuException e) {
            System.out.println("Incorrect number! Please add number from menu range...");
            result = getIntFromUser(range);
        }

        return result;
    }
}
