package ru.elazarev.extratask;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for sort departments array.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.05.17
 */
public class DepartmentSort {

    /**
     * Method to sort departments names in asc direction.
     * @param departments - array of departments names.
     * @return - sorted array
     */
    public String[] sortAsc(String[] departments) {

        Set<String> set = new TreeSet<>(new ASCComparator());
        for (String name : departments) {

            do {
                set.add(name);
                name = getParentUnit(name);
            } while (name != null);
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * Method to sort departments names in desc direction.
     * @param departments - array of departments names.
     * @return - sorted array
     */
    public String[] sortDesc(String[] departments) {

        Set<String> set = new TreeSet<>(new DESCComparator());
        for (String name : departments) {

            do {
                set.add(name);
                name = getParentUnit(name);
            } while (name != null);
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * Method to get parent unit name from department name.
     * @param depName - department name
     * @return - parent unit name ro null if there isn't parent
     */
    private String getParentUnit(String depName) {
        int lastIndex =  depName.lastIndexOf("\\");
        if (lastIndex != -1) {
            return depName.substring(0, lastIndex);
        }
        return null;
    }

    /**
     * Comparator for sort departments names in asc direction.
     */
    static class ASCComparator implements Comparator<String> {

        /**
         * Compare departments name with its unit hierarchy.
         * @param first - first name
         * @param second - second name
         * @return
         */
        @Override
        public int compare(String first, String second) {
            String[] firstArr = first.split("\\\\");
            String[] secondArr = second.split("\\\\");

            for (int i = 0; i < Math.min(firstArr.length, secondArr.length); i++) {
                int result = firstArr[i].compareTo(secondArr[i]);
                if (result != 0) {
                    return result;
                }
            }

            return firstArr.length - secondArr.length;
        }
    }

    /**
     * Comparator for sort departments names in desc direction.
     */
    static class DESCComparator implements Comparator<String> {

        /**
         * Compare departments name with its unit hierarchy.
         * @param first - first name
         * @param second - second name
         * @return
         */
        @Override
        public int compare(String first, String second) {
            String[] firstArr = first.split("\\\\");
            String[] secondArr = second.split("\\\\");

            for (int i = 0; i < Math.min(firstArr.length, secondArr.length); i++) {
                int result = firstArr[i].compareTo(secondArr[i]) * -1;
                if (result != 0) {
                    return result;
                }
            }

            return (firstArr.length - secondArr.length);
        }
    }
}
