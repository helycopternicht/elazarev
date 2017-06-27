package ru.elazarev.model;

/**
 * Order representation.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.06.17
 */
public class Order implements Comparable<Order> {
    /**
     * Type of order.
     */
    private OrderType type;
    /**
     * Order id.
     */
    private int id;
    /**
     * Order price.
     */
    private double price;
    /**
     * Order volume.
     */
    private int volume;

    /**
     * Default constructor.
     * @param type type of order.
     * @param id id of new order.
     * @param price price of order.
     * @param volume volume of order.
     */
    public Order(String type, int id, double price, int volume) {
        if ("BUY".equals(type)) {
            this.type = OrderType.BUY;
        } else {
            this.type = OrderType.SELL;
        }

        this.id = id;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Getter for type field.
     * @return order type
     */
    public OrderType getType() {
        return type;
    }

    /**
     * Getter for id field.
     * @return id of order.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for price field.
     * @return price of order.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for volume field.
     * @return volume of order.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Setter for volume field.
     * @param volume new volume.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * If id is same that orders is same.
     * If type of order is buy than desc sort by price else asc sort by price.
     * @param o object to compare.
     * @return int
     */
    @Override
    public int compareTo(Order o) {
        if (getId() == o.getId()) {
            return 0;
        }

        if (OrderType.BUY == type) {
            return getPrice() > o.getPrice() ? -1 : 1;
        } else {
            return getPrice() < o.getPrice() ? -1 : 1;
        }
    }

    /**
     * Equals if same id.
     * @param o object to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;
        return getId() == order.getId();
    }

    /**
     * Based on id field.
     * @return int
     */
    @Override
    public int hashCode() {
        return getId();
    }
}
