package ru.job4j.array;

/**
* Class RotateArray for rotates square arrays.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 27.03.2017
*/
public class RotateArray {
	/**
	* Method rotate square array clockwise.
	* @param array - array to rotate
	* @return rotated array
	*/
	public int[][] rotate(int[][] array) {
		int[][] result = new int[array.length][array.length];
        int col = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                result[j][col] = array[i][j];
            }
            col--;
        }
		return result;
	}
}