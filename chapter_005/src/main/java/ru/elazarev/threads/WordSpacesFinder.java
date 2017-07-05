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
     * @throws InterruptedException NOP
     */
    public void printResult() throws InterruptedException {

        System.out.println("Application find and print all spaces and words in text.");

        SpaceFinder sp = new SpaceFinder(text);
        WordFinder wd = new WordFinder(text);

        sp.getThread().join(1000);
        wd.getThread().join(1000);

        System.out.println("Calculation ends.");
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
         * Word finder thread.
         */
        private Thread thread;

        /**
         * Default constructor.
         * @param text text to find word count.
         */
        WordFinder(String text) {
            this.text = text;
            this.thread = new Thread(this);
            this.thread.start();
        }

        /**
         * Calculate and print count of words in text.
         */
        @Override
        public void run() {
            int count = 0;
            String[] arr = text.split(" ");

            for (String st : arr) {
                if (thread.isInterrupted()) {
                    return;
                }
                if ("".equals(st)) {
                    continue;
                }
                count++;
            }
            System.out.println(String.format("Words count is %d", count));
        }

        /**
         * Getter for thread field.
         * @return thread
         */
        public Thread getThread() {
            return thread;
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
         * Space finder thread.
         */
        private Thread thread;

        /**
         * Default constructor.
         * @param text text to find word count.
         */
        SpaceFinder(String text) {
            this.text = text;
            this.thread = new Thread(this);
            this.thread.start();
        }

        /**
         * Calculate and print count of spaces in text.
         */
        @Override
        public void run() {

            int count = 0;
            int idx = -1;
            while ((idx = text.indexOf(" ", idx + 1)) != -1) {
                if (thread.isInterrupted()) {
                    return;
                }
                count++;
            }
            System.out.println(String.format("Spaces count is %d", count));
        }

        /**
         * Getter for thread field.
         * @return thread
         */
        public Thread getThread() {
            return thread;
        }
    }
}
