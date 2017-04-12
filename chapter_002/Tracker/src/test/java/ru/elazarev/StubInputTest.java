package ru.elazarev;

import org.junit.Test;

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
        String[] answers = {"0", "new name", "newID", "newDescriptions", "6"};
        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("newID");
        assertNotNull(item);
    }

    /**
     * Test to find all method.
     */
    @Test
    public void testFindAllMethod() {
        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc", "6"};
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

        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc",
                "2", "newID", "test task", "test description", "6"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("newID");
        assertThat(item.getName(), is("test task"));
        assertThat(item.getDescription(), is("test description"));
    }

    /**
     * Test to delete method.
     */
    @Test
    public void testDeleteItemMethod() {

        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc",
                "3", "newID", "6"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();

        assertThat(tracker.findAll().length, is(1));
        assertNull(tracker.findById("newID"));
    }

    /**
     * Test to findByIdMethod.
     */
    @Test
    public void testFindItemByIdAndEdit() {
        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc",
                "4", "newID", "1", "TEST_NAME", "TEST_DESCRIPTION", "3", "6"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("newID");
        assertNotNull(item);
        assertThat(item.getName(), is("TEST_NAME"));
        assertThat(item.getDescription(), is("TEST_DESCRIPTION"));
    }

    /**
     * Test to findByName and comment method.
     */
    @Test
    public void testFindItemByIdAndComment() {
        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc",
                "4", "newID", "2", "TEST_COMMENT", "2", "TEST_COMMENT2", "3", "6"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findById("newID");
        assertNotNull(item);
        assertThat(item.getComments().size(), is(2));
        assertThat(item.getComments().get(0), is("TEST_COMMENT"));
        assertThat(item.getComments().get(1), is("TEST_COMMENT2"));
    }

    /**
     * Test to find item and edit method.
     */
    @Test
    public void testFindItemByNameAndEdit() {
        String[] answers = {"0", "new name", "newID", "newDescriptions",
                "0", "another name", "someID", "desc",
                "5", "new name", "1", "TEST_NAME", "TEST_DESCRIPTION", "3", "6"};

        StartUI app = new StartUI(new StubInput(answers));
        Tracker tracker = app.getTracker();
        app.init();
        TrackerItem item = tracker.findByName("TEST_NAME");
        assertNotNull(item);
    }

}
