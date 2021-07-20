package org.demo.evotor.domain;

public enum Currency {

	RUB(2);

	private final int point;

	Currency(int point) {
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

}
