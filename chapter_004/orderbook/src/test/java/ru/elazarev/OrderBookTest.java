package ru.elazarev;

import org.junit.Test;
import ru.elazarev.model.Order;
import ru.elazarev.model.OrderBook;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Order book class test.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.06.17
 */
public class OrderBookTest {
    /**
     * getName() method test.
     */
    @Test
    public void whenCreateOrderWithNameThanCanGetItName() {
        OrderBook ob = new OrderBook("book1");

        assertThat(ob.getName(), is("book1"));
    }

    /**
     * addOrder() method test.
     */
    @Test
    public void whenAddedThreeSellOrderAndThreeBayOrderWithNotMatchingPriceThenAllOrderAllSaved() {
        OrderBook ob = new OrderBook("book1");

        ob.addOrder(new Order("SELL", 1, 100.5, 81));
        ob.addOrder(new Order("SELL", 2, 100., 80));
        ob.addOrder(new Order("SELL", 3, 100.5, 79));

        ob.addOrder(new Order("BUY", 4, 99, 86));
        ob.addOrder(new Order("BUY", 5, 99, 16));
        ob.addOrder(new Order("BUY", 6, 99, 78));

        assertThat(ob.getBids().size(), is(3));
        assertThat(ob.getAsks().size(), is(3));
    }

    /**
     * addOrder() method test.
     */
    @Test
    public void whenAddedCounterOrdersThenTheAreExecute() {
        OrderBook ob = new OrderBook("book1");

        ob.addOrder(new Order("SELL", 1, 99, 10));
        ob.addOrder(new Order("SELL", 2, 100, 10));
        ob.addOrder(new Order("BUY", 4, 100, 15));

        assertThat(ob.getAsks().size(), is(1));
        assertThat(ob.getBids().size(), is(0));

        Iterator<Order> it = ob.getAsks().iterator();
        Order o = it.next();
        assertThat(o.getId(), is(2));
        assertThat(o.getPrice(), is(100.));
        assertThat(o.getVolume(), is(5));
    }

    /**
     * deleteOrder() method test.
     */
    @Test
    public void whenRemoveOrderThanOrderIsRemoved() {
        OrderBook ob = new OrderBook("book1");

        ob.addOrder(new Order("SELL", 1, 100.5, 81));
        ob.addOrder(new Order("SELL", 2, 100., 80));
        ob.addOrder(new Order("BUY", 5, 99, 16));
        ob.addOrder(new Order("BUY", 6, 99, 78));

        assertThat(ob.deleteOrderWithId(1), is(true));
        assertThat(ob.deleteOrderWithId(5), is(true));
        assertThat(ob.deleteOrderWithId(7), is(false));

        assertThat(ob.getBids().size(), is(1));
        assertThat(ob.getAsks().size(), is(1));
        assertThat(ob.getAsks().iterator().next().getId(), is(2));
        assertThat(ob.getBids().iterator().next().getId(), is(6));
    }

    /**
     * toString() method test.
     */
    @Test
    public void toStringMethodTest() {
        OrderBook ob = new OrderBook("book1");

        ob.addOrder(new Order("SELL", 1, 99, 10));
        ob.addOrder(new Order("SELL", 2, 99, 10));
        ob.addOrder(new Order("SELL", 3, 101.1, 10));
        ob.addOrder(new Order("SELL", 4, 100, 10));
        ob.addOrder(new Order("BUY", 5, 98, 15));
        ob.addOrder(new Order("BUY", 6, 98.5, 15));
        ob.addOrder(new Order("BUY", 7, 98.5, 15));

        String exp = "Order book: book1\n"
                   + "BID                ASK\n"
                   + "Volume@Price – Volume@Price\n"
                   + "15@98.5 - 10@99.0\n"
                   + "15@98.0 - 10@100.0\n"
                   + "----------- - 10@101.1\n";

        assertThat(ob.print(), is(exp));
    }
}