package com.planning.event.domain.yahooweather;

/**
 * Domain class representing the response from Yahoo Weather API
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class YahooWeatherResponse {
	private Query query;

	/**
	 * @return the query
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(Query query) {
		this.query = query;
	}
}
