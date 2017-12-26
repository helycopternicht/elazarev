package ru.elazarev.input;

import ru.elazarev.exceptions.OutOfMenuException;

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

    /**
     * Ask int itput from user.
     * @param question - text of question
     * @param range - answer should be in range
     * @return - user answer
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
            result = Integer.valueOf(new Scanner(System.in).nextLine());
            boolean inRange = false;
            for (int i = 0; i < range.length; i++) {
                if (result == range[i]) {
                    inRange = true;
                }
            }

            if (!inRange) {
                throw new OutOfMenuException("Menu dosen't have clause with key " + result);
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
