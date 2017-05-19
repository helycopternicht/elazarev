package ru.elazarev.convert;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Class to test UserCovert class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.05.17
 */
public class UserConvertTest {

    /**
     * listToMap method test.
     */
    @Test
    public void convertToMapTest() {

        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1, "Vasja"), new User(2, "Petja"), new User(3, "Masha")));

        Map<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "Vasja"));
        expected.put(2, new User(2, "Petja"));
        expected.put(3, new User(3, "Masha"));
        assertEquals(expected, new UserConvert().process(users));
    }
}
