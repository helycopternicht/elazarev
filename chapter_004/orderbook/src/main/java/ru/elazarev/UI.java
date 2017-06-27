package ru.elazarev;

import ru.elazarev.util.OrderParser;

import java.io.File;

/**
 * UI class of order book.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 23.06.17
 */
public class UI {

    /**
     * application instance.
     */
    private App orderBook;

    /**
     * Default constructor.
     */
    public UI() {
        orderBook = new App();
    }

    /**
     * Start application.
     */
    public void start() {

        String fileName = "/Volumes/localDrive/orders.xml";

        OrderParser parser = new OrderParser(new File(fileName), orderBook);
        parser.parse();
    }

    /**
     * Main method.
     * @param args params to start application.
     */
    public static void main(String[] args) {
        new UI().start();
    }
}
