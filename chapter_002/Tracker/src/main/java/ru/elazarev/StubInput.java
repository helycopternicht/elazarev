package ru.elazarev;

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
}
