package ru.elazarev.input;

import java.util.Scanner;

/**
 * Class to getting answers from user.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 10.04.17
 */
public class ConsoleInput implements Input {

    /**
     * Method to get answer from user.
     * @param question - text of question
     * @return
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
