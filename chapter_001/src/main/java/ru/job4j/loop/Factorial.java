package ru.job4j.loop;

/**
* Class for calculate factorial of number.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 23.03.2017
*/
public class Factorial {

	/**
	* Method return factorial of number.
	* @param n - natural number
	* @return factorial of n number
	*/
	public int calc(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}