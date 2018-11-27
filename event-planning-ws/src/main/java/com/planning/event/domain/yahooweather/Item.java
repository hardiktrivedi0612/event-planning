package com.planning.event.domain.yahooweather;

import java.util.List;

/**
 * Domain class representing the response from Yahoo Weather API
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class Item {
	private List<Forecast> forecast;

	/**
	 * @return the forecast
	 */
	public List<Forecast> getForecast() {
		return forecast;
	}

	/**
	 * @param forecast
	 *            the forecast to set
	 */
	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}
}
