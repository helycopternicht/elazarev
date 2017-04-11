package ru.job4j.trackerapp;

import java.util.Scanner;

/**
 * Class to getting answers from user.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 10.04.17
 */
public class ConsoleInput implements Input {

    @Override
    public String ask(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
