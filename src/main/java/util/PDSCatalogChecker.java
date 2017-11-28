package util;

import office.openbet.model.Event;
import office.openbet.model.Market;
import office.openbet.model.Selection;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class PDSCatalogChecker {

	private static PDSCatalogChecker instance = null;

	private String cmpBaseUrl;

	private String eventUrl;

	private static final Logger logger = Logger
			.getLogger(PDSCatalogChecker.class.getName());

	public static PDSCatalogChecker getInstance() {
		if(instance == null) {
			instance = new PDSCatalogChecker();
		}
		return instance;
	}

	public void initialize (String pdsCmpIpAddress) {
		this.cmpBaseUrl =pdsCmpIpAddress;
	}

	public boolean isEventInCatalog(Integer eventId) {

		CloseableHttpResponse httpResponse = null;
		try {
			eventUrl = cmpBaseUrl  + "/events/OB_EV" + eventId + "?settled=either";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(new HttpGet(eventUrl));
			StatusLine  statusLine = httpResponse.getStatusLine();

			switch (statusLine.getStatusCode()) {
				case 404:
					return false;
				default:
					return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpResponse.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	public boolean isElementInCatalog(Integer eventId, String elementId) {

		CloseableHttpResponse httpResponse = null;
		try {
			eventUrl = cmpBaseUrl  + "/events/OB_EV" + eventId + "?settled=either";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(new HttpGet(eventUrl));
			String response = EntityUtils.toString(httpResponse.getEntity());

			return response.contains(elementId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpResponse.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * Check if an event is present in the catalog up to n times, polling every m seconds.
	 * @param eventId
	 * @param attempts Number of attempts
	 * @param pollInterval Polling every m seconds
	 * @return boolean if the event is present
	 */
	public boolean isEventInCatalog(Integer eventId, int attempts, int pollInterval) {
		boolean eventPresent = false;
		int trials = attempts;
		do {
			if(!isEventInCatalog(eventId)) {
				trials--;
				Timer.sleep(pollInterval, TimeUnit.SECONDS);
			} else
				eventPresent = true;

		} while(trials > 0 && !eventPresent);

		if(!eventPresent) {
			logger.warning(String.format("The event with id:%s was not found in %s after %s seconds.",
					eventId, eventUrl, attempts*pollInterval));
		} else
			logger.info(String.format("The event with id:%s was found in %s", eventId, eventUrl));

		return eventPresent;
	}

	/**
	 * Check if an event is present in the catalog up to n times, polling every m seconds.
	 * @param eventId
	 * @param attempts Number of attempts
	 * @param pollInterval Polling every m seconds
	 * @return boolean if the event is present
	 */
	public boolean isElementInCatalog(Integer eventId, String elementId, int attempts, int pollInterval) {
		boolean eventPresent = false;
		int trials = attempts;
		do {
			if(!isElementInCatalog(eventId, elementId)) {
				trials--;
				Timer.sleep(pollInterval, TimeUnit.SECONDS);
			} else
				eventPresent = true;

		} while(trials > 0 && !eventPresent);

		if(!eventPresent)
			logger.warning(String.format("%s was not found in %s after %s seconds.",
					elementId, eventUrl, attempts*pollInterval));

		return eventPresent;
	}

	/**
	 * Check if an event is present in the catalog up to n times, polling every second.
	 * @param eventId
	 * @param timeout in seconds
	 * @return boolean if the event is present
	 */
	public boolean isEventInCatalog(Integer eventId, int timeout) {
		return isEventInCatalog(eventId, timeout, 1);
	}

	/**
	 * Check if a list of events are present in the catalog up to n times, polling every second.
	 * @param eventList
	 * @param timeout in seconds
	 * @return boolean if the events are present
	 */
	public boolean areEventsInCatalog(ArrayList<Event> eventList, int timeout) {
		boolean contentIndexed = true;
		for (Event event : eventList) {
			if(!isEventInCatalog(event.getId(), timeout, 1))
				contentIndexed = false;
			if(contentIndexed) {
				for (Market market : event.getMarkets()) {
					if(!isElementInCatalog(event.getId(), "OB_MA" + market.getId(), timeout, 1))
						contentIndexed = false;
					if(contentIndexed) {
						for (Selection selection : market.getSelections())
							if(!isElementInCatalog(event.getId(), "OB_OU" + selection.getId(), timeout, 1))
								contentIndexed = false;
					}
				}
			}
		}

		return contentIndexed;
	}

}
