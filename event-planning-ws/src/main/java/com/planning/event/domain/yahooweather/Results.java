package com.planning.event.domain.yahooweather;

/**
 * Domain class representing the response from Yahoo Weather API
 * 
 * @author hatrivedi
 * @date Nov 26, 2018
 */
public class Results {
	private Channel channel;

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
