package ru.elazarev;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for app class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 23.06.17
 */
public class AppTest {

    /**
     * Test for addOrder method.
     */
    @Test
    public void whenNoSuchBookInAppThanBookAdded() {

        App app = new App();

        assertThat(app.getBookList().size(), is(0));

        boolean added = app.addOrder("book1", "SELL", 1, 100., 10);

        assertThat(added, is(true));
        assertThat(app.getBookList().size(), is(1));
    }

    /**
     * Test of addOrder method.
     */
    @Test
    public void whenAddToExistingBookThanBooksCountIsNotIncrease() {
        App app = new App();

        app.addOrder("book1", "SELL", 1, 100., 10);
        app.addOrder("book1", "SELL", 2, 100., 10);

        assertThat(app.getBookList().size(), is(1));
    }

    /**
     * Test of delete order method.
     */
    @Test
    public void whenDeleteExistingOrderFromExistingBookThatTrueAndViceVersa() {
        App app = new App();

        assertThat(app.deleteOrder("book1", 1), is(false));

        app.addOrder("book1", "SELL", 1, 100., 10);
        app.addOrder("book1", "SELL", 2, 100., 10);

        assertThat(app.deleteOrder("book1", 1), is(true));
    }

    /**
     * toString method test.
     */
    @Test
    public void booksStringRepresentationTest() {
        App app = new App();

        app.addOrder("book1", "SELL", 1, 99, 10);
        app.addOrder("book1", "SELL", 2, 99, 10);
        app.addOrder("book1", "SELL", 3, 101.1, 10);
        app.addOrder("book1", "SELL", 4, 100., 10);
        app.addOrder("book1", "BUY", 5, 98., 10);
        app.addOrder("book1", "BUY", 6, 98.5, 10);
        app.addOrder("book1", "BUY", 7, 98.5, 10);


        app.addOrder("book2", "BUY", 0, 98.1, 10);
        app.addOrder("book2", "BUY", 1, 98., 10);
        app.addOrder("book2", "BUY", 2, 98.5, 10);
        app.addOrder("book2", "BUY", 3, 98.5, 10);
        app.addOrder("book2", "BUY", 4, 97, 10);
        app.addOrder("book2", "SELL", 5, 99, 10);
        app.addOrder("book2", "SELL", 6, 101.1, 10);
        app.addOrder("book2", "SELL", 7, 100., 10);
        app.addOrder("book2", "SELL", 8, 100., 10);

        String exp = "Order book: book1\n"
                + "BID                ASK\n"
                + "Volume@Price – Volume@Price\n"
                + "10@98.5 - 10@99.0\n"
                + "10@98.0 - 10@100.0\n"
                + "----------- - 10@101.1\n"
                + "\n"
                + "Order book: book2\n"
                + "BID                ASK\n"
                + "Volume@Price – Volume@Price\n"
                + "10@98.5 - 10@99.0\n"
                + "10@98.1 - 10@100.0\n"
                + "10@98.0 - 10@101.1\n"
                + "10@97.0 - -----------\n\n";

        assertThat(app.booksStringRepresentation(), is(exp));
    }
}