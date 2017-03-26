package ru.job4j.array;

/**
* Class Turn to change arrays.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 26.03.2017
*/
public class Turn {
	/**
	* Method reverse source array.
	* @param arr - source array
	* @return result reversed array
	*/
	public int[] back(int[] arr) {
		int i = 0;
		int j = arr.length - 1;
		for (; i < (arr.length / 2); i++, j--) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return arr;
	}
}