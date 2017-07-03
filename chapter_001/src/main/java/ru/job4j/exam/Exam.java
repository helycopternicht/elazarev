package ru.job4j.exam;

/**
* Exam task class.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 29.03.2017
*/
public class Exam {
	/**
	* Method returns true if param origin contains param sub and
	* false else.
	* @param origin - string in which the search is performed
	* @param sub - string we are looking for in origin
	* @return true if origin contains sub and false else
	*/
	public boolean contains(String origin, String sub) {

        if ("".equals(origin) || "".equals(sub) || sub.length() > origin.length()) {
            return false;
        }

        boolean isSubstring = false;
        for (int i = 0, j = 0; i < origin.length() && !isSubstring; i++) {

            if (origin.charAt(i) == sub.charAt(j)) {
                j++;
            } else {
                j = 0;
            }

            if (j == sub.length()) {
                isSubstring = true;
            }
        }
        return isSubstring;
    }
}