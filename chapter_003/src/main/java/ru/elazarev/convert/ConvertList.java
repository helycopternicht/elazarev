package ru.elazarev.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Class converts two-dimension array to List.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 17.05.17
 */
public class ConvertList {

    /**
     * Method converts two-dimensional array to one-dimensional list.
     * @param array - array to convert.
     * @return - one-dimensional list
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int cell : row) {
                list.add(cell);
            }
        }
        return list;
    }

    /**
     * Method converts list to two-dimension array with rows count of rows.
     * @param list - list to convert
     * @param rows - count of rows in result array
     * @return - two-dimension array
     */
    public int[][] toArray(List<Integer> list, int rows) {

        int count = (int) Math.ceil((double) list.size() / rows);
        int columns = (int) (Math.ceil((double) list.size() / count));
        int[][] array = new int[count][];

        Iterator<Integer> iter = list.iterator();

        int[] innerArray;
        for (int i = 0; i < array.length; i++) {
            innerArray = new int[columns];
            for (int j = 0; j < innerArray.length && iter.hasNext(); j++) {
                innerArray[j] = iter.next();
            }
            array[i] = innerArray;
        }
        return array;
    }

    /**
     * Method convert List of int arrays to one-dimension list.
     * @param list - list to convert.
     * @return - one-dimension list.
     */
    public List<Integer> convert(List<int[]> list) {
        if (list == null) {
            throw new IllegalArgumentException("List is null!");
        }

        List<Integer> result = new ArrayList<>();
        for (int[] ints : list) {
            for (int i = 0; i < ints.length; i++) {
                result.add(ints[i]);
            }
        }
        return result;
    }

    /**
     * Method converts list of Users to Map, where key is User id and value is User.
     * @param list - to convert
     * @return - Map
     */
    public Map<Integer, User> listToMap(List<User> list) {
        Map<Integer, User> map = new HashMap<>(list.size());
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
