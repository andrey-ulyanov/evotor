package org.demo.evotor.domain;

/**
 * 
 * @author Andrey Ulyanov
 *
 */
public enum Currency {

	RUB(2), USD(2);

	private final int point;

	Currency(int point) {
		this.point = point;
	}

	/**
	 * Count of numbers after decimal point.
	 * 
	 * @return 
	 */
	public int getPoint() {
		return point;
	}

}
