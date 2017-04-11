import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 * Test class for Tracker class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 08.04.17
 */
public class TrackerTest {

    /**
     * Array of track items for tests.
     */
    private static final TrackerItem[] SOURCE = new TrackerItem[5];

    /**
     * Init of source array.
     */
    static {
        SOURCE[0] = new TrackerItem("id#1", "Test task 1", "Created for testing", 123L, null);
        SOURCE[1] = new TrackerItem("id#2", "Test task 2", "Created for testing", 125L, null);
        SOURCE[2] = new TrackerItem("id#3", "Test task 3", "Created for testing", 123L, null);
        SOURCE[3] = new TrackerItem("id#4", "Test task 4", "Created for testing", 143L, null);
        SOURCE[4] = new TrackerItem("id#5", "Test task 5", "Created for testing", 123L, null);
    }

    /**
     * add and findAll methods test.
     */
    @Test
    public void testAddAndFindAllMethodsTest() {
        Tracker tracker = getTestTracker();
        TrackerItem[] source = SOURCE.clone();
        assertThat(tracker.findAll(), is(source));
    }

    /**
     * update and findById methods test.
     */
    @Test
    public void updateAndFindByIdTest() {
        Tracker tracker = getTestTracker();
        TrackerItem newItem = new TrackerItem("id#1", "updated task  #1", "Created for testing", 123L, null);
        tracker.update(newItem);
        assertThat(tracker.findById("id#1"), is(newItem));
    }

    /**
     * findByName and delete methods test.
     */
    @Test
    public void findByNameAndDeleteTest() {
        Tracker tracker = getTestTracker();
        TrackerItem[] source = SOURCE.clone();

        TrackerItem firstItem = tracker.findByName("Test task 1");
        assertThat(firstItem, is(source[0]));

        tracker.delete(firstItem);
        assertNull(tracker.findByName("Test task 1"));
    }

    /**
     * Method for init test tracker.
     * @return - test tracker object
     */
    private Tracker getTestTracker() {
        Tracker tracker = new Tracker();
        TrackerItem[] source = SOURCE.clone();
        for (TrackerItem item: source) {
            tracker.add(item);
        }
        return tracker;
    }
}
