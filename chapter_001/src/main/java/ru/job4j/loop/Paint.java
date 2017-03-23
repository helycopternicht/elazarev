package ru.job4j.loop;

/**
* Class Paint for generating some figures.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 23.03.2017
*/
public class Paint {
	/**
	* Method returns piramid figure with height h.
	* @param h - height of piramid
	* @return String pyramid view
	*/
	public String piramid(int h) {
		StringBuilder sb = new StringBuilder();
		int width = h - 1 + h;
		for (int rowNumber = 0; rowNumber < h; rowNumber++) {
			int startWith = (width / 2) - rowNumber;
			int endOn = (width / 2) + rowNumber;
			for (int ceilNumber = 0; ceilNumber < width; ceilNumber++) {
				if (ceilNumber >= startWith && ceilNumber <= endOn) {
					sb.append("^");
				} else {
					sb.append(" ");
				}
			}
			if (rowNumber != h - 1) {
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
}