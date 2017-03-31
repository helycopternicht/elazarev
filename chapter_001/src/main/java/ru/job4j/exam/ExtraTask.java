package ru.job4j.exam;

/**
* Class ExtraTask is extra task on exam.
* @author Eugene Lazarev
* @since 31.03.2017
*/
public class ExtraTask {
	/**
	* Method merge make one sorted array from two sorted arrays.
	* @param first - first sorted array;
	* @param second - second sorted array
	* @return sorted array that contain all elemtnts from param arrays
	*/
	public int[] merge(int[] first, int[] second) {
		int[] result = new int[first.length + second.length];
		int r = 0;
		int f = 0;
		int s = 0;

		while (r < result.length) {
			if (f == first.length) {
				result[r++] = second[s++];
			} else if (s == second.length) {
				result[r++] = first[f++];
			} else {
				result[r++] = first[f] > second[s] ? second[s++] : first[f++];
			}
		}
		return result;
	}
}