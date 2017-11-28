package office;

import office.openbet.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import util.Timer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link BackOfficeOxifeed} is able to automate different OpenBet back office
 * tasks like events, markets, selections creation sending xml requests through
 * Oxifeed
 *
 * @author israel.lozano@williamhill.com
 *
 */
public class BackOfficeOxifeed {

	private static BackOfficeOxifeed instance = null;

	private String user;

	private String password;

	private String endPoint;

	private static final Logger logger = LogManager.getRootLogger();

	public static BackOfficeOxifeed getInstance() {
		if(instance == null) {
			instance = new BackOfficeOxifeed();
		}
		return instance;
	}

	public BackOfficeOxifeed initialize (String endPoint, String user, String password) {
		this.endPoint = endPoint;
		this.user = user;
		this.password = password;
		return null;
	}

	/**
	 * Adds an event to the office.
	 *
	 * @param event
	 *            The event to add.
	 * @return a {@link Event} with all attributes set.
	 */
	public Event addEvent(Event event) {
		String eventInsertRequest = String.format(
				"<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
						+ "<auth username='%s' password='%s'/>"
						+ "<eventInsert>"
						+ "<typeId><openbetId>%s</openbetId></typeId>"
						+ "<eventName>%s</eventName>"
						+ "<homeTeam>IFFK</homeTeam>"
						+ "<awayTeam>ARC</awayTeam>"
						+ "<sort>%s</sort>"
						+ "<startTime isOff='%s'>%s</startTime>"
						+ "<status>%s</status>" + "<display displayed='%s'/>"
						+ "</eventInsert></oxiFeedRequest>", this.user,
				this.password, event.competitionId, event.name, event.sort,
				event.offFlag.toLowerCase(), event.startTime,
				event.status.toLowerCase(), event.displayed.toLowerCase());

		event.setId(sendRequest(eventInsertRequest));
		//logger.info(String.format("event with id:%s added", event.id));

		return event;
	}

	/**
	 * Adds a market to the event with the current eventId.
	 *
	 * @param eventId
	 *            The parent event reference.
	 * @param market
	 *            The market to add.
	 * @return a {@link office.openbet.model.Market} with all attributes set.
	 */
	public Market addMarket(Integer eventId, Market market) {
		StringBuffer marketInsertRequest = new StringBuffer();

		// build market request header
		marketInsertRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
		marketInsertRequest.append("<oxiFeedRequest version='1.0'>");
		marketInsertRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
		marketInsertRequest.append("<marketInsert>");

		// set mandatory fields
		marketInsertRequest.append(String.format("<eventId><openbetId>%s</openbetId></eventId>", eventId));
		marketInsertRequest.append(String.format("<marketTemplate>%s</marketTemplate>", market.marketGroup));

		// set optional fields
		if (!market.name.isEmpty())
			marketInsertRequest.append(String.format("<marketName>%s</marketName>", market.name));
		if (!market.status.isEmpty())
			marketInsertRequest.append(String.format("<status>%s</status>", market.status.toLowerCase()));
		if (!market.displayed.isEmpty())
			marketInsertRequest.append(String.format("<display displayed='%s' order='%s'/>",
					market.displayed.toLowerCase(), market.getOrder()));
		if (!market.bir.isEmpty())
			marketInsertRequest.append(String.format("<bettingInRunning active='%s'/>", market.bir.toLowerCase()));
		if (!market.handicap.isEmpty())
			marketInsertRequest.append(String.format("<handicapValue>%s</handicapValue>", market.handicap));
		if (!market.antepost.isEmpty())
			marketInsertRequest.append(String.format("<isApMarket>%s</isApMarket>", market.antepost.toLowerCase()));
		if (!market.getStartPrice().isEmpty())
			marketInsertRequest.append(String.format("<pricing live='%s' starting='%s'/>",
					market.livePrice.toLowerCase(), market.startPrice.toLowerCase()));
		if (!market.getGuaranteedPrice().isEmpty())
			marketInsertRequest.append(String.format("<gpAvail>%s</gpAvail>",
					market.guaranteedPrice.toLowerCase()));
		if (!market.getEarlyPrices().isEmpty())
			marketInsertRequest.append(String.format("<epActive>%s</epActive>",
					market.earlyPrices.toLowerCase()));
		if (!market.getEachWay().isEmpty())
			marketInsertRequest.append(String.format("<ewAvail>%s</ewAvail>",
					market.eachWay.toLowerCase()));
		if (!market.getEachWayPlaces().isEmpty())
			marketInsertRequest.append(String.format("<ewPlaces>%s</ewPlaces>",
					market.eachWayPlaces));
		if (!market.getEachWayPlacesAt().isEmpty()) {
			String[] placesAt = market.getEachWayPlacesAt().trim().split("/");
			marketInsertRequest.append(String.format("<ewFacNum>%s</ewFacNum>", placesAt[0]));
			marketInsertRequest.append(String.format("<ewFacDen>%s</ewFacDen>", placesAt[1]));
		}
		marketInsertRequest.append("<fcAvail>yes</fcAvail>");
		marketInsertRequest.append("<tcAvail>yes</tcAvail>");

		// close market request headers
		marketInsertRequest.append("</marketInsert>");
		marketInsertRequest.append("</oxiFeedRequest>");

		market.setId(sendRequest(marketInsertRequest.toString()));
		//logger.info(String.format("market with id:%s and name:%s added", market.id, market.name));

		return market;
	}

	/**
	 * Adds a selection to the market with the current marketId.
	 *
	 * @param marketId
	 *            The parent market reference.
	 * @param selection
	 *            The selection to add.
	 * @return a {@link office.openbet.model.Selection} with all attributes set.
	 */
	public Selection addSelection(Integer marketId, Selection selection) {
		StringBuffer selectionInsertRequest = new StringBuffer();

		// build selection request header
		selectionInsertRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
		selectionInsertRequest.append("<oxiFeedRequest version='1.0'>");
		selectionInsertRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
		selectionInsertRequest.append("<selectionInsert>");

		// set mandatory fields
		selectionInsertRequest.append(String.format("<marketId><openbetId>%s</openbetId></marketId>", marketId));
		selectionInsertRequest.append(String.format("<selectionName>%s</selectionName>", selection.name));
		selectionInsertRequest.append(String.format("<price type='fractional'>%s</price>", selection.price));

		// set optional fields
		if (!selection.status.isEmpty())
			selectionInsertRequest.append(String.format("<status>%s</status>", selection.status.toLowerCase()));
		if (!selection.displayed.isEmpty())
			selectionInsertRequest.append(String.format("<display displayed='%s' order='%s'/>",
					selection.displayed.toLowerCase(), selection.getOrder()));
		if (!selection.type.isEmpty())
			selectionInsertRequest.append(String.format("<selectionType>%s</selectionType>", selection.type));
		if(!selection.runner_order.isEmpty())
			selectionInsertRequest.append(String.format("<stallNo>%s</stallNo>", selection.runner_order));

		// close selection request headers
		selectionInsertRequest.append("</selectionInsert>");
		selectionInsertRequest.append("</oxiFeedRequest>");

		selection.setId(sendRequest(selectionInsertRequest.toString()));
		//logger.info(String.format("selection with id:%s added", selection.id));

		return selection;
	}

	public Selection addScoreSelection(Integer marketId, Selection selection) {
		String[] scrore = StringUtils.substringAfterLast(selection.getName(), " ").split("-");
		String scoreHome = scrore[0];
		String scoreAway = scrore[1];
		String selectionInsertRequest = String.format(
				"<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
						+ "<auth username='%s' password='%s'/>"
						+ "<selectionInsert>"
						+ "<marketId><openbetId>%s</openbetId></marketId>"
						+ "<selectionName>%s</selectionName>"
						+ "<score home='%s' away='%s'/>"
						+ "<price type='fractional'>%s</price>"
						+ "<status>%s</status>"
						+ "<display displayed='%s' order='%s'/>"
						+ "<selectionType>score</selectionType>"
						+ "</selectionInsert></oxiFeedRequest>", this.user,
				this.password, marketId, selection.name, scoreHome, scoreAway, selection.price,
				selection.status.toLowerCase(),
				selection.displayed.toLowerCase(), selection.getOrder());

		selection.setId(sendRequest(selectionInsertRequest));
		//logger.info(String.format("selection with id:%s added", selection.id));

		return selection;
	}

	/**
	 * Updates the attributes for the given {@link Event}.
	 *
	 * @param event
	 * @return an {@link Event} with the updated attributes.
	 */
	public Event updateEvent(Event event) {
		String eventUpdateRequest = String.format(
				"<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
						+ "<auth username='%s' password='%s'/>"
						+ "<eventUpdate>"
						+ "<eventId><openbetId>%s</openbetId></eventId>"
						+ "<eventName>%s</eventName>"
						+ "<sort>%s</sort>"
						+ "<startTime isOff='%s'>%s</startTime>"
						+ "<status>%s</status>"
						+ "<display displayed='%s'/>"
						+ "</eventUpdate></oxiFeedRequest>", this.user,
				this.password, event.id, event.name, event.sort,
				event.offFlag.toLowerCase(), event.startTime,
				event.status.toLowerCase(), event.displayed.toLowerCase());

		sendRequest(eventUpdateRequest);
		//logger.info(String.format("event with id:%s updated", event.id));

		return event;
	}

	/**
	 * Updates the attributes for the given {@link office.openbet.model.Market}.
	 *
	 * @param market
	 * @return a {@link office.openbet.model.Market} with the updated attributes.
	 */
	public Market updateMarket(Market market) {
		StringBuffer marketUpdateRequest = new StringBuffer();

		// build market request header
		marketUpdateRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
		marketUpdateRequest.append("<oxiFeedRequest version='1.0'>");
		marketUpdateRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
		marketUpdateRequest.append("<marketUpdate>");

		// set mandatory fields
		marketUpdateRequest.append(String.format("<marketId><openbetId>%s</openbetId></marketId>", market.getId()));

		// set optional fields
		if (!market.name.isEmpty())
			marketUpdateRequest.append(String.format("<marketName>%s</marketName>", market.name));
		if (!market.status.isEmpty())
			marketUpdateRequest.append(String.format("<status>%s</status>", market.status.toLowerCase()));
		if (!market.displayed.isEmpty())
			marketUpdateRequest.append(String.format("<display displayed='%s' order='%s'/>",
					market.displayed.toLowerCase(), market.getOrder()));
		if (!market.bir.isEmpty())
			marketUpdateRequest.append(String.format("<bettingInRunning active='%s'/>", market.bir.toLowerCase()));
		if (!market.handicap.isEmpty())
			marketUpdateRequest.append(String.format("<handicapValue>%s</handicapValue>", market.handicap));
		if (!market.antepost.isEmpty())
			marketUpdateRequest.append(String.format("<isApMarket>%s</isApMarket>", market.antepost.toLowerCase()));
		if (!market.getStartPrice().isEmpty())
			marketUpdateRequest.append(String.format("<pricing live='%s' starting='%s'/>",
					market.livePrice.toLowerCase(), market.startPrice.toLowerCase()));
		if (!market.getGuaranteedPrice().isEmpty())
			marketUpdateRequest.append(String.format("<gpAvail>%s</gpAvail>",
					market.guaranteedPrice.toLowerCase()));
		if (!market.getEarlyPrices().isEmpty())
			marketUpdateRequest.append(String.format("<epActive>%s</epActive>",
					market.earlyPrices.toLowerCase()));
		if (!market.getEachWay().isEmpty())
			marketUpdateRequest.append(String.format("<ewAvail>%s</ewAvail>",
					market.eachWay.toLowerCase()));
		if (!market.getEachWayPlaces().isEmpty())
			marketUpdateRequest.append(String.format("<ewPlaces>%s</ewPlaces>",
					market.eachWayPlaces));
		if (!market.getEachWayPlacesAt().isEmpty()) {
			String[] placesAt = market.getEachWayPlacesAt().trim().split("/");
			marketUpdateRequest.append(String.format("<ewFacNum>%s</ewFacNum>", placesAt[0]));
			marketUpdateRequest.append(String.format("<ewFacDen>%s</ewFacDen>", placesAt[1]));
		}
		marketUpdateRequest.append("<fcAvail>yes</fcAvail>");
		marketUpdateRequest.append("<tcAvail>yes</tcAvail>");

		// close market request headers
		marketUpdateRequest.append("</marketUpdate>");
		marketUpdateRequest.append("</oxiFeedRequest>");

		sendRequest(marketUpdateRequest.toString());
		//logger.info(String.format("market with id:%s updated", market.id));

		return market;
	}

	/**
	 * Updates the attributes for the given {@link office.openbet.model.Selection}.
	 *
	 * @param selection
	 * @return an {@link office.openbet.model.Selection} with the updated attributes.
	 */
	public Selection updateSelection(Selection selection) {
		String selectionUpdateRequest = String
				.format("<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
								+ "<auth username='%s' password='%s'/>"
								+ "<selectionUpdate>"
								+ "<selectionId><openbetId>%s</openbetId></selectionId>"
								+ "<selectionName>%s</selectionName>"
								+ "<price type='fractional'>%s</price>"
								+ "<status>%s</status>"
								+ "<display displayed='%s' order='%s'/>"
								+ "</selectionUpdate></oxiFeedRequest>", this.user,
						this.password, selection.id, selection.name,
						selection.price, selection.status.toLowerCase(),
						selection.displayed.toLowerCase(), selection.getOrder());

		sendRequest(selectionUpdateRequest);
		//logger.info(String.format("selection with id:%s updated", selection.id));

		return selection;
	}

	/**
	 * Settles a market in Back Office. All included selections must have a
	 * confirmed result.
	 *
	 * @param {@link Market} to settle
	 */
	public void settleMarket(Market market) {
		String selectionResultRequest = String.format(
				"<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
						+ "<auth username='%s' password='%s'/>"
						+ "<marketSettle>"
						+ "<marketId><openbetId>%s</openbetId></marketId>"
						+ "</marketSettle></oxiFeedRequest>", this.user,
				this.password, market.id);

		sendRequest(selectionResultRequest);
		//logger.info(String.format("market with id:%s queued for settlement", market.id));
	}

	public void settleMarket(Market market, String forecastDividend, String tricastDividend) {
		String selectionResultRequest = String.format(
				"<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
						+ "<auth username='%s' password='%s'/>"
						+ "<marketSettle>"
						+ "<marketId><openbetId>%s</openbetId></marketId>"
						+ "<fcDividend>%s</fcDividend>"
						+ "<tcDividend>%s</tcDividend>"
						+ "</marketSettle></oxiFeedRequest>", this.user,
				this.password, market.id, forecastDividend, tricastDividend);

		sendRequest(selectionResultRequest);
		//logger.info(String.format("market with id:%s queued for settlement", market.id));
	}

	/**
	 * Set a {@link office.openbet.model.Selection} result to void.
	 *
	 * @param {@link Selection} to void
	 */
	public void voidSelectionResult(Selection selection) {
		String selectionResultRequest = String
				.format("<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'>"
								+ "<auth username='%s' password='%s'/>"
								+ "<selectionResult>"
								+ "<selectionId><openbetId>%s</openbetId></selectionId>"
								+ "<result>void</result>"
								+ "</selectionResult></oxiFeedRequest>", this.user,
						this.password, selection.id);

		sendRequest(selectionResultRequest);
		//logger.info(String.format("selection with id:%s result confirmed", selection.id));
	}

	public void resultSelection(Selection selection) {
		StringBuffer selectionResultRequest = new StringBuffer();

		// build market request header
		selectionResultRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
		selectionResultRequest.append("<oxiFeedRequest version='1.0'>");
		selectionResultRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
		selectionResultRequest.append("<selectionResult>");

		// set mandatory fields
		selectionResultRequest.append(String.format("<selectionId><openbetId>%s</openbetId></selectionId>", selection.id));
		selectionResultRequest.append(String.format("<result>%s</result>", selection.getResult().toLowerCase()));

		// set optional fields
		if (!selection.getPlace().isEmpty())
			selectionResultRequest.append(String.format("<place>%s</place>", selection.getPlace()));


		// close market request headers
		selectionResultRequest.append("</selectionResult>");
		selectionResultRequest.append("</oxiFeedRequest>");

		sendRequest(selectionResultRequest.toString());
	}

	/**
	 * Adds a rule4 to the market with the current marketId.
	 *
	 * @param marketId
	 *            The parent market reference.
	 * @param rule4
	 *            The rule4 to add.
	 */
	public void addRule4(Integer marketId, Rule4 rule4) {
		StringBuffer rule4InsertRequest = new StringBuffer();

		// build market request header
		rule4InsertRequest.append("<?xml version='1.0' encoding='UTF-8'?>");
		rule4InsertRequest.append("<oxiFeedRequest version='1.0'>");
		rule4InsertRequest.append(String.format("<auth username='%s' password='%s'/>", this.user, this.password));
		rule4InsertRequest.append("<rule4Insert>");

		// set mandatory fields
		rule4InsertRequest.append(String.format("<marketId><openbetId>%s</openbetId></marketId>", marketId));
		rule4InsertRequest.append(String.format("<priceType>%s</priceType>", rule4.priceType.toLowerCase()));
		rule4InsertRequest.append(String.format("<valid>%s</valid>", rule4.valid.toLowerCase()));
		rule4InsertRequest.append(String.format("<fromTime>%s</fromTime>", rule4.fromTime.trim()));
		rule4InsertRequest.append(String.format("<toTime>%s</toTime>", rule4.toTime.trim()));
		rule4InsertRequest.append(String.format("<reduction>%s</reduction>", rule4.reduction));

		// set optional fields
		if (!rule4.reason.isEmpty())
			rule4InsertRequest.append(String.format("<reason>%s</reason>", rule4.reason));

		// close market request headers
		rule4InsertRequest.append("</rule4Insert>");
		rule4InsertRequest.append("</oxiFeedRequest>");

		sendRequestNoResponse(rule4InsertRequest.toString());
		logger.info(String.format("rule4 added to market with id:%s", marketId));

	}

	/*private void checkConnection() {
		int statusCode = 200;
		try {
			String connectionRequest = "<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'></oxiFeedRequest>";
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost postRequest = new HttpPost(this.endPoint);
			postRequest.setEntity(new StringEntity(connectionRequest));
			CloseableHttpResponse response = httpclient.execute(postRequest);
			statusCode = response.getStatusLine().getStatusCode();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if(statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE)
			throw new RuntimeException("The Back Office Oxifeed server is temporarily unable to service your request "
					+ "due to maintenance downtime or capacity problems. Please try again later.");


	}*/

	private Integer sendRequest(String request) {

		try {
			HttpPost postRequest = new HttpPost(this.endPoint);
			StringEntity entity = new StringEntity(request,
					ContentType.create("text/xml", "UTF-8"));
			postRequest.setEntity(entity);

			CloseableHttpResponse response;
			int retries = 5;
			int statusCode;
			do {
				response = HttpClients.createDefault().execute(postRequest);
				statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == 503) {
					// oxifeed service unavailable
					Timer.sleep(200, TimeUnit.MILLISECONDS);
					retries--;
				}

			} while(statusCode == 503 && retries > 0);
			if(statusCode == 503)
				throw new RuntimeException("Failed to send the request. The Oxifeed service is unavailable.");
			else {
				String responseBody = EntityUtils.toString(response.getEntity());
				return getOpenbetID(responseBody);
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	private void sendRequestNoResponse(String request) {

		try {
			HttpPost postRequest = new HttpPost(this.endPoint);
			StringEntity entity = new StringEntity(request,
					ContentType.create("text/xml", "UTF-8"));
			postRequest.setEntity(entity);

			CloseableHttpResponse response;
			int retries = 5;
			int statusCode;
			do {
				response = HttpClients.createDefault().execute(postRequest);
				statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == 503) {
					// oxifeed service unavailable
					Timer.sleep(200, TimeUnit.MILLISECONDS);
					retries--;
				}

			} while(statusCode == 503 && retries > 0);
			if(statusCode == 503)
				throw new RuntimeException("Failed to send the request. The Oxifeed service is unavailable.");


		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Integer getOpenbetID(String response) {
		Integer openbetId = -1;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(response));

			Pattern pattern = Pattern.compile("<debug>.*");
			Matcher matcher = pattern.matcher(response);
			if (matcher.find()) {
				throw new RuntimeException(String.format(
						"Failed to POST Oxifeed with message: %s",
						matcher.group(0)));
			}

			Document doc = db.parse(inStream);

			NodeList nl = doc.getElementsByTagName("openbetId");
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element nameElement = (Element) nl.item(i);
					openbetId = Integer.valueOf(nameElement.getFirstChild().getNodeValue().trim());
				}
			}

		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return openbetId;

	}

}
