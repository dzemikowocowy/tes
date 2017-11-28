package office.openbet.model;

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
 *
 * {@link FootballServiceHelper} is able to automate  updates for goal score use  score boards endpoitns
 *
 *
 * @author michal.koza@williamhill.com
 *
 */
public class FootballServiceHelper {

	private static FootballServiceHelper instance = null;


	private String endPoint;

	private static final Logger logger = LogManager.getRootLogger();

	public static FootballServiceHelper getInstance() {
		if(instance == null) {
			instance = new FootballServiceHelper();
		}
		return instance;
	}

	public void initialize (String endPoint) {
		this.endPoint = endPoint;

	}


	public String updateScore(String EventID) {
		String eventInsertRequest = String.format(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<incidentUpdate timeStamp=\"2016-04-25T13:43:05.613+01:00\" id=\"cfb69604-833a-4326-8fc1-fc999f9c7c52\" source=\"Running Ball\" xmlns=\"http://caerus.williamhill.com/scoreboards/incident\">\n" +
						" <sport name=\"football\">\n" +
						"  <event id=\"%s\" type=\"English Premier League\" name=\"Bahama Animations\" startTime=\"2016-03-03T13:23:29.341+01:00\" sequence=\"0\">\n" +
						"   <footballSummary>\n" +
						"    <team side=\"home\" name=\"Bahama Home\" goals=\"0\" corners=\"0\" yellow_cards=\"0\" red_cards=\"0\" freekicks=\"0\" penalties=\"0\" shots_on_target=\"0\" shots_on_woodwork=\"0\" throw_ins=\"0\" />\n" +
						"    <team side=\"away\" name=\"Bahama Away\" goals=\"0\" corners=\"0\" yellow_cards=\"0\" red_cards=\"0\" freekicks=\"0\" penalties=\"0\" shots_on_target=\"0\" shots_on_woodwork=\"0\" throw_ins=\"0\" />        \n" +
						"   </footballSummary>\n" +
						"   <incident operation=\"Add\">    \n" +
						"    <id>\"incident_2\"</id>\n" +
						"    <type>Goal</type>\n" +
						"    <period>1st Half</period>\n" +
						"    <time>67</time>\n" +
						"    <scope>Home</scope>    \n" +
						"    <incidentDetail type=\"Home Team\" value=\"Bahama Home\"/>\n" +
						"    <incidentDetail type=\"Away Team\" value=\"Bahama Away\"/>\n" +
						"   </incident>\n" +
						"  </event>\n" +
						" </sport>\n" +
						"</incidentUpdate>",
				EventID);

	return eventInsertRequest;
	}






	private Integer sendRequest(String request) {

		try {
			HttpPost postRequest = new HttpPost(this.endPoint );
			StringEntity entity = new StringEntity(request,
					ContentType.create("text/xml", "UTF-8"));
			postRequest.setEntity(entity);

			CloseableHttpResponse response;
			int retries = 5;
			int statusCode;
			do {
				response = HttpClients.createDefault().execute(postRequest);
				statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == 500) {
					Timer.sleep(200, TimeUnit.MILLISECONDS);
					retries--;
				}

			} while(statusCode == 500 && retries > 0);
			if(statusCode == 500)
				throw new RuntimeException("Failed to send the request. The footbal service service is unavailable.");
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
			int statusCode = 200;
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
