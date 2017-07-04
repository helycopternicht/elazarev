package ru.elazarev.threads;

/**
 * Class to find count of word and count of spaces in text.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 04.07.17
 */
public class WordSpacesFinder {
    /**
     * Text to find counts.
     */
    private String text;

    /**
     * Default constructor.
     * @param text text to find counts.
     */
    public WordSpacesFinder(String text) {
        this.text = text;
    }

    /**
     * Method calculate counts and prints result.
     */
    public void printResult() {

        new Thread(new SpaceFinder(text)).start();
        new Thread(new WordFinder(text)).start();
    }

    /**
     * Runnable class to calculate count of words in text.
     */
    private static final class WordFinder implements Runnable {
        /**
         * Text to calculate.
         */
        private String text;

        /**
         * Default constructor.
         * @param text text to find word count.
         */
        WordFinder(String text) {
            this.text = text;
        }

        /**
         * Calculate and print count of words in text.
         */
        @Override
        public void run() {
            int count = 0;
            String[] arr = text.split(" ");
            for (String st : arr) {
                if ("".equals(st)) {
                    continue;
                }
                count++;
            }
            System.out.println(String.format("Words count is %d", count));
        }
    }

    /**
     * Runnable class to calculate count of spaces in text.
     */
    private static final class SpaceFinder implements Runnable {
        /**
         * Text to calculate.
         */
        private String text;

        /**
         * Default constructor.
         * @param text text to find word count.
         */
        SpaceFinder(String text) {
            this.text = text;
        }

        /**
         * Calculate and print count of spaces in text.
         */
        @Override
        public void run() {
            int count = 0;
            int idx = -1;
            while ((idx = text.indexOf(" ", idx + 1)) != -1) {
                count++;
            }
            System.out.println(String.format("Spaces count is %d", count));
        }
    }
}
