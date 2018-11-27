package com.planning.event.domain.yahooweather;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class Forecast {
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd MMM yyyy")
	private LocalDate date;
	private String text;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
