package ru.elazarev.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.elazarev.App;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Class to parse xml file.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 23.06.17
 */
public class OrderParser {
    /**
     * XML file to parse.
     */
    private File file;
    /**
     * app class.
     */
    private App app;

    /**
     * Default constructor.
     * @param file file field
     * @param app app field
     */
    public OrderParser(File file, App app) {
        this.file = file;
        this.app = app;
    }

    /**
     * Parse file and add orders to app.
     */
    public void parse() {
        SAXParser sax = null;
        try {
            sax = SAXParserFactory.newInstance().newSAXParser();
            sax.parse(file, new OrderHandler(app));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

/**
 * Handler for handle order file.
 */
class OrderHandler extends DefaultHandler {
    /**
     * App to add orders.
     */
    private App app;

    /**
     * Default constructor.
     * @param app app instance.
     */
    OrderHandler(App app) {
        this.app = app;
    }

    /**
     * Receive notification of the start of an element.
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException if file is incorrect.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("AddOrder".equals(qName)) {
            String bookName = attributes.getValue("book");
            String type = attributes.getValue("operation");
            int id = Integer.valueOf(attributes.getValue("orderId"));
            int volume = Integer.valueOf(attributes.getValue("volume"));
            double price = Double.valueOf(attributes.getValue("price"));
            app.addOrder(bookName, type, id, price, volume);
            return;
        }

        if ("DeleteOrder".equals(qName)) {
            String bookName = attributes.getValue("book");
            int id = Integer.valueOf(attributes.getValue("orderId"));
            app.deleteOrder(bookName, id);
        }
    }
}
