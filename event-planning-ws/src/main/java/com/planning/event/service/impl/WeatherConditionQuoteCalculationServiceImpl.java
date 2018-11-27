package com.planning.event.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.planning.event.domain.EventQuoteRequest;
import com.planning.event.domain.yahooweather.Forecast;
import com.planning.event.domain.yahooweather.YahooWeatherResponse;
import com.planning.event.service.QuoteCalculationService;

/**
 * TODO
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
@Service
@ConfigurationProperties("weather-condition-quote-config")
public class WeatherConditionQuoteCalculationServiceImpl implements QuoteCalculationService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherConditionQuoteCalculationServiceImpl.class);

	private Set<String> conditionList;

	private BigDecimal quotePrice;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * {@inheritDoc}
	 * 
	 * Implementation Notes: TODO
	 */
	@Override
	public BigDecimal calculateQuotePrice(EventQuoteRequest request) {

		BigDecimal price = BigDecimal.ZERO;

		// Yahoo weather returns a forecast of only 10 days
		// So if the requested date is within those 10 days then call yahoo
		// weather api to check the weather

		if (ChronoUnit.DAYS.between(LocalDate.now(), request.getEventDate()) > 10) {
			// We cannot estimate the quote price for weather conditions if it
			// is greater than 10 days
			return price;
		}

		YahooWeatherResponse weatherForecastResponse = callYahooWeatherAPI(request);

		// Iterate through forecast and check the forecast of the requested day
		if (weatherForecastResponse != null && weatherForecastResponse.getQuery() != null
				&& weatherForecastResponse.getQuery().getResults() != null
				&& weatherForecastResponse.getQuery().getResults().getChannel() != null
				&& weatherForecastResponse.getQuery().getResults().getChannel().getItem() != null
				&& weatherForecastResponse.getQuery().getResults().getChannel().getItem().getForecast() != null) {
			for (Forecast forecast : weatherForecastResponse.getQuery().getResults().getChannel().getItem()
					.getForecast()) {
				if (forecast.getDate().isEqual(request.getEventDate())
						&& conditionList.contains(forecast.getText().toLowerCase())) {
					price = quotePrice;
					break;
				}
			}
		}

		return price;

	}

	private YahooWeatherResponse callYahooWeatherAPI(EventQuoteRequest request) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromUriString("https://query.yahooapis.com/v1/public/yql").queryParam("format", "json")
					.queryParam("q",
							"select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\" "
									+ request.getCity() + "\")")
					.queryParam("env", "store://datatables.org/alltableswithkeys");
			ResponseEntity<YahooWeatherResponse> response = restTemplate.exchange(builder.build().toUriString(),
					HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), YahooWeatherResponse.class);
			return response.getBody();

		} catch (Exception e) {
			// Catching all types of exceptions
			logger.error("Exception while calling yahoo weather API");
			logger.debug(e.getMessage());
		}
		return null;
	}

	/**
	 * @return the conditionList
	 */
	public Set<String> getConditionList() {
		return conditionList;
	}

	/**
	 * @param conditionList
	 *            the conditionList to set
	 */
	public void setConditionList(Set<String> conditionList) {
		this.conditionList = conditionList;
	}

	public BigDecimal getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}

}
