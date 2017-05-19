package ru.elazarev.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to convert user lists
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 19.05.17
 */
public class UserConvert {
    /**
     * Method converts list of Users to Map, where key is User id and value is User.
     * @param list - to convert
     * @return - Map
     */
    public Map<Integer, User> process(List<User> list) {
        Map<Integer, User> map = new HashMap<>(list.size());
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }

}
