package ru.elazarev;

import org.junit.Test;
import ru.elazarev.input.StubInput;
import ru.elazarev.model.Tracker;
import ru.elazarev.model.TrackerItem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class for test Tracker app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 11.04.17
 */
public class StubInputTest {

    /**
     * Test to add method.
     */
    @Test
    public void testAddMethod() {
        String[] answers = {"1", "new name", "new description", "y"};
        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("1");
        assertNotNull(item);
    }

    /**
     * Test to find all method.
     */
    @Test
    public void testFindAllMethod() {
        String[] answers = {"1", "New name", "New descriptions", "n",
                "1", "Another name", "Big description", "y"};
        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Test to edit method.
     */
    @Test
    public void testEditItemMethod() {

        String[] answers = {"1", "New name", "New description", "n",
                "1", "Another name", "Description of task", "n",
                "2", "1", "Test task", "Test description", "y"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("1");
        assertThat(item.getName(), is("Test task"));
        assertThat(item.getDescription(), is("Test description"));
    }

    /**
     * Test to delete method.
     */
    @Test
    public void testDeleteItemMethod() {

        String[] answers = {"1", "New name", "New description", "n",
                "1", "Another name", "Description of task", "n",
                "3", "1", "y"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();

        assertThat(tracker.findAll().length, is(1));
        assertNull(tracker.findById("1"));
    }

    /**
     * Test to comment item method.
     */
    @Test
    public void commentItemTest() {
        String[] answers = {"1", "New name", "New description", "n",
                "1", "Another name", "desc", "n",
                "5", "1", "TEST_COMMENT", "n", "5", "1", "TEST_COMMENT2", "y"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("1");
        assertNotNull(item);
        assertThat(item.getComments().size(), is(2));
        assertThat(item.getComments().get(0), is("TEST_COMMENT"));
        assertThat(item.getComments().get(1), is("TEST_COMMENT2"));
    }

}
