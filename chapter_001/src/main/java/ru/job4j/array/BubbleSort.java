package ru.job4j.array;

/**
* Class BubbpleSort ti sort some arrays.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 27.03.2017
*/
public class BubbleSort {
	/**
	* Method returns sorted array.
	* @param source - source array
	* @return sorded source array
	*/
	public int[] sort(int[] source) {
		boolean sorted = false;
		int intend = 0;
		while (!sorted) {
			sorted = true;
			for (int i = 1; i < source.length - intend; i++) {
				if (source[i - 1] > source[i]) {
					int temp = source[i - 1];
					source[i - 1] = source[i];
					source[i] = temp;
					sorted = false;
				}
			}
			intend++;
		}
		return source;
	}
}