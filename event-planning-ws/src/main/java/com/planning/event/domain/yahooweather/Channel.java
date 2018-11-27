package com.planning.event.domain.yahooweather;

/**
 * Domain class representing the response from Yahoo Weather API
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class Channel {
	private Item item;

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
}
