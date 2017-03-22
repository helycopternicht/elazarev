package ru.job4j.calculator;

/**
* Simple Calculator class.
* @author Eugene Lazarev
* @since 22.03.2017
*/
public class Calculator {
	/**
	* Field for result value.
	*/
	private double result;

	/**
	* Method for add fist and second numbers.
	* @param first - first number for addition
	* @param second - second number for addition
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	* Method for substruct second from fist numbers.
	* @param first - first number
	* @param second - second number
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}

	/**
	* Method for divide first number on second number.
	* @param first - first number
	* @param second - second number
	*/
	public void divade(double first, double second) {
		this.result = first / second;
	}

	/**
	* Method for multiply first number on second number.
	* @param first - first number
	* @param second - second number
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}

	/**
	* Returns result of last computation.
	* @return double
	*/
	public double getResult() {
		return this.result;
	}
}