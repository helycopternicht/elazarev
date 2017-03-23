package ru.job4j.max;

/**
* Class Max for detect maximum value.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 22.03.2017
*/
public class Max {
	/**
	* Method returns maximum from first and second.
	* @param first - first value
	* @param second - second value
	* @return maximimem from first and second
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}

	/**
	* Method returns maximum from three numbers.
	* @param first - first value
	* @param second - second value
	* @param third - third value
	* @return maximum from three numbers
	*/
	public int max(int first, int second, int third) {
		return this.max(first, this.max(second, third));
	}
}