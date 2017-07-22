package ru.elazarev.waitnotify.consumerproducer;

/**
 * Simple Producer Consumer pattern implementation.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.07.17
 */
public class ProducerConsumer {
    /**
     * Blocking queue for storing data.
     */
    private SimpleBlockingQueue<Integer> queue;

    /**
     * Default constructor. Starts threads.
     */
    public ProducerConsumer() {
        queue = new SimpleBlockingQueue<>();
        Thread producer = new Producer("Producer");
        Thread consumer1 = new Consumer("Consumer1");
        Thread consumer2 = new Consumer("Consumer2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }

    /**
     * Main to start app.
     * @param args list of args
     */
    public static void main(String[] args) {
        new ProducerConsumer();
    }

    /**
     * Producer class to produce ints and put in queue.
     */
    class Producer extends Thread {
        /**
         * Constructor with name of thread.
         * @param name name of thread
         */
        Producer(String name) {
            super(name);
        }

        /**
         * Run stack.
         */
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Produced :" + i);
                queue.push(i);
            }
        }
    }

    /**
     * Consumer class to consume produced ints.
     */
    class Consumer extends Thread {
        /**
         * Constructor with name.
         * @param name name of thread.
         */
        Consumer(String name) {
            super(name);
        }

        /**
         * Run stack.
         */
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                int tmp = queue.pop();
                System.out.println(Thread.currentThread().getName() + " consumed: " + tmp);
            }
        }
    }
}


