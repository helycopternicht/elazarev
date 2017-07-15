package ru.elazarev.jmm;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 15.07.17
 */
public class DeadLock {

    /**
     * Friend class.
     */
    static class Friend {
        /**
         * Name of friend.
         */
        private final String name;

        /**
         * Default constructor.
         * @param name name of friend
         */
        Friend(String name) {
            this.name = name;
        }

        /**
         * Getter for friend field.
         * @return name of friend
         */
        public String getName() {
            return this.name;
        }

        /**
         * Friend bow to this friend.
         * @param bower another friend.
         */
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }

        /**
         * This friend bow to bower.
         * @param bower aother friend.
         */
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    /**
     * Start this method you get deadlock.
     * @param args args of app
     */
    public static void main(String[] args) {
        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }
}
