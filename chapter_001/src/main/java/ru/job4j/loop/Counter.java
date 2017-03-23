package ru.job4j.loop;

/**
* Class for generate different counters.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 23.03.2017
*/
public class Counter {
	/**
	* Method returns summ of even numbers in range
	* between start and finish numers include finish.
	* @param start - begin of range
	* @param finish - end of range
	* @return int summ of all even numbers in range
	*/
	public int add(int start, int finish) {
		int summ = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				summ += i;
			}			
		}
		return summ;
	}
}