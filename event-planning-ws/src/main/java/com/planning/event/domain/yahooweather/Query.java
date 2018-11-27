package com.planning.event.domain.yahooweather;

/**
 * Domain class representing the response from Yahoo Weather API
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class Query {
	private Results results;

	/**
	 * @return the results
	 */
	public Results getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(Results results) {
		this.results = results;
	}
}
