package ru.job4j.array;

import java.util.Arrays;

/**
* ArrayDuplicate class.
* @author Eugene Lazarev mailto(helycoptrenicht@rambler.ru)
* @since 29.03.2017
*/
public class ArrayDuplicate {
	/**
	* Method returns new array without duplicates.
	* @param array - source array
	* @return new array without duplicates
	*/
	public String[] remove(String[] array) {
		int lastUniqueIdx = 0;
		for (int cursor = 1; cursor < array.length; cursor++) {
			boolean unique = true;
			for (int j = cursor - 1; j >= 0; j--) {
				if (array[cursor].equals(array[j])) {
					unique = false;
					break;
				}
			}

			if (unique) {
				array[++lastUniqueIdx] = array[cursor];
			}
		}
		return Arrays.copyOf(array, lastUniqueIdx + 1);
	}
}