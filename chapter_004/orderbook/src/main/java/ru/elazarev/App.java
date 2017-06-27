package ru.elazarev;

import ru.elazarev.model.Order;
import ru.elazarev.model.OrderBook;

import java.util.Set;
import java.util.TreeSet;

/**
 * Class to manage order books.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.06.17
 */
public class App {
    /**
     * Set of order book list.
     */
    private Set<OrderBook> bookList;

    /**
     * Default constructor.
     */
    public App() {
        bookList = new TreeSet<>();
    }

    /**
     * Adds new order to order book.
     * @param bookName name of book to add order.
     * @param type type of order bay or sell.
     * @param id id of new order.
     * @param price price of order.
     * @param volume volume of order.
     * @return true if order added success and false else
     */
    public boolean addOrder(String bookName, String type, int id, double price, int volume) {
        OrderBook book = getBookWitName(bookName);
        if (book == null) {
            book = new OrderBook(bookName);
            bookList.add(book);
        }

        return book.addOrder(new Order(type, id, price, volume));
    }

    /**
     * Deletes order from order book.
     * @param bookName name of book to delete order.
     * @param id id of order to delete.
     * @return true or false
     */
    public boolean deleteOrder(String bookName, int id) {
        OrderBook book = getBookWitName(bookName);
        if (book == null) {
            return false;
        }
        return book.deleteOrderWithId(id);
    }

    /**
     * Returns set of order books.
     * @return set of order books.
     */
    public Set<OrderBook> getBookList() {
        return bookList;
    }

    /**
     * Returns string representation of all order books.
     * @return String
     */
    public String booksStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (OrderBook book : bookList) {
            sb.append(book.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Method finds and returns order books with specified name.
     * @param name name of book to find
     * @return order book or null
     */
    private OrderBook getBookWitName(String name) {
        for (OrderBook book : bookList) {
            if (name.equals(book.getName())) {
                return book;
            }
        }
        return null;
    }
}
