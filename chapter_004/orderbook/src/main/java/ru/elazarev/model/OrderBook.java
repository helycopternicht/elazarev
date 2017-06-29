package ru.elazarev.model;

import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class represent Order book entity.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.06.17
 */
public class OrderBook implements Comparable<OrderBook> {
    /**
     * Name of order book.
     */
    private String name;

    /**
     * Set of bids.
     */
    private Set<Order> bids;

    /**
     * Set of ascs.
     */
    private Set<Order> asks;

    /**
     * Default constructor.
     * @param name name of new book.
     */
    public OrderBook(String name) {
        this.name = name;
        bids = new TreeSet<>();
        asks = new TreeSet<>();
    }

    /**
     * Method adds order to order book.
     * @param o order to add
     * @return true if order added and false else.
     */
    public boolean addOrder(Order o) {
        if (o.getType() == OrderType.BUY) {
            return addBid(o);
        } else {
            return addAsk(o);
        }
    }

    /**
     * Deletes order from order book.
     * @param id id of order to delete
     * @return true if success and false else
     */
    public boolean deleteOrderWithId(int id) {
        for (Order bid : bids) {
            if (bid.getId() == id) {
                return bids.remove(bid);
            }
        }

        for (Order ask : asks) {
            if (ask.getId() == id) {
                return asks.remove(ask);
            }
        }
        return false;
    }

    /**
     * Method equals if they have same name.
     * @param o object to test
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderBook orderBook = (OrderBook) o;
        return name != null ? name.equals(orderBook.name) : orderBook.name == null;
    }

    /**
     * Hashcode based on name.
     * @return int
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * Getter for name field.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for bids field.
     * @return set
     */
    public Set<Order> getBids() {
        return bids;
    }

    /**
     * Getter for asks field.
     * @return set
     */
    public Set<Order> getAsks() {
        return asks;
    }

    /**
     * Returns string representation of order book.
     * @return string representation of object
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order book: " + getName() + "\n");
        sb.append("BID                ASK\n");
        sb.append("Volume@Price – Volume@Price\n");

        double lastBid = 0;
        double lastAsk = 0;

        Iterator<Order> bidIter = bids.iterator();
        Iterator<Order> askIter = asks.iterator();
        for (int i = 0; i < Math.max(bids.size(), asks.size()); i++) {

            Order bid = null;
            while (bidIter.hasNext()) {
                bid = bidIter.next();
                if (lastBid != bid.getPrice()) {
                    lastBid = bid.getPrice();
                    break;
                }
            }

            Order ask = null;
            while (askIter.hasNext()) {
                ask = askIter.next();
                if (lastAsk != ask.getPrice()) {
                    lastAsk = ask.getPrice();
                    break;
                }
            }

            if (bid == null && ask == null) {
                continue;
            }

            if (bid == null) {
                sb.append("-----------");
            } else {
                sb.append(bid.getVolume() + "@" + bid.getPrice());
            }

            sb.append(" - ");

            if (ask == null) {
                sb.append("-----------");
            } else {
                sb.append(ask.getVolume() + "@" + ask.getPrice());
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Adds bid in bids.
     * @param bid bid to add.
     * @return true or false.
     */
    private boolean addBid(Order bid) {
        boolean res = bids.add(bid);

        Collection<Order> toRemove = new ArrayList<>();
        for (Order ask : asks) {
            if (bid.getPrice() < ask.getPrice()) {
                break;
            }

            if (ask.getVolume() <= bid.getVolume()) {
                bid.setVolume(bid.getVolume() - ask.getVolume());
                toRemove.add(ask);
            } else {
                ask.setVolume(ask.getVolume() - bid.getVolume());
                bids.remove(bid);
                break;
            }

            if (bid.getVolume() == 0) {
                bids.remove(bid);
                break;
            }
        }
        if (!toRemove.isEmpty()) {
            asks.removeAll(toRemove);
        }
        return res;
    }

    /**
     * Adds ask to asks.
     * @param ask ask to add
     * @return true or false
     */
    private boolean addAsk(Order ask) {
        boolean res = asks.add(ask);

        Collection<Order> toRemove = new ArrayList<>();
        for (Order bid : bids) {
            if (ask.getPrice() > bid.getPrice()) {
                break;
            }

            if (bid.getVolume() > ask.getVolume()) {
                bid.setVolume(bid.getVolume() - ask.getVolume());
                asks.remove(ask);
                break;
            } else {
                ask.setVolume(ask.getVolume() - bid.getVolume());
                toRemove.add(bid);
            }

            if (ask.getVolume() == 0) {
                asks.remove(ask);
                break;
            }
        }

        if (!toRemove.isEmpty()) {
            asks.removeAll(toRemove);
        }
        return res;
    }

    /**
     * Comparator based on books name.
     * @param o object to test
     * @return int
     */
    @Override
    public int compareTo(OrderBook o) {
        return this.name.compareTo(o.name);
    }
}
