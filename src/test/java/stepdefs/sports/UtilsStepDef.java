package stepdefs.sports;


import asl.SportsAutomationScriptingLanguage;
import cucumber.api.java.en.Then;
import office.openbet.model.Selection;
import org.apache.commons.lang3.math.Fraction;
import org.json.JSONException;
import org.json.JSONObject;
import util.Jenkins_Inputs;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michal Koza on 09/07/15.
 */
public class UtilsStepDef extends SportsAutomationScriptingLanguage {

    SportsAutomationScriptingLanguage asl = new SportsAutomationScriptingLanguage();
    Jenkins_Inputs jenkinsInputs = new Jenkins_Inputs();

    private List<String> pp1NodesApi(){
        List<String> pp1Nodes = new ArrayList<>();
        pp1Nodes.add("http://10.212.145.53:7271/healthcheck");
        pp1Nodes.add("http://10.212.145.54:7271/healthcheck");
        pp1Nodes.add("http://10.212.145.127:7271/healthcheck");
        pp1Nodes.add("http://10.212.145.128:7271/healthcheck");
        return pp1Nodes;
    }

    private List<String> pp1NodesStaticApi(){
        List<String> pp1Nodes = new ArrayList<>();
        pp1Nodes.add("http://10.212.145.71/healthcheck");
        pp1Nodes.add("http://10.212.145.72/healthcheck");
        return pp1Nodes;
    }

    private List<String> pp2NodesApi(){
        List<String> pp2Nodes = new ArrayList<>();
        pp2Nodes.add("http://10.214.145.53:7271/healthcheck");
        pp2Nodes.add("http://10.214.145.54:7271/healthcheck");
        pp2Nodes.add("http://10.214.145.127:7271/healthcheck");
        pp2Nodes.add("http://10.214.145.128:7271/healthcheck");
        return pp2Nodes;
    }

    private List<String> pp2NodesStaticApi(){
        List<String> pp2Nodes = new ArrayList<>();
        pp2Nodes.add("http://10.214.145.71/healthcheck");
        pp2Nodes.add("http://10.214.145.72/healthcheck");
        return pp2Nodes;
    }

    private List<String> pp3NodesApi(){
        List<String> pp3Nodes = new ArrayList<>();
        pp3Nodes.add("http://10.214.145.53:7271/healthcheck");
        pp3Nodes.add("http://10.214.145.54:7271/healthcheck");
        pp3Nodes.add("http://10.214.145.127:7271/healthcheck");
        pp3Nodes.add("http://10.214.145.128:7271/healthcheck");
        return pp3Nodes;
    }

    private List<String> pp3NodesStaticApi(){
        List<String> pp3Nodes = new ArrayList<>();
        pp3Nodes.add("http://10.214.145.71/healthcheck");
        pp3Nodes.add("http://10.214.145.72/healthcheck");
        return pp3Nodes;
    }

    private List<String> liveNodesApi(){
        List<String> liveNodes = new ArrayList<>();
        liveNodes.add("http://10.120.145.53:7271/healthcheck");
        liveNodes.add("http://10.120.145.54:7271/healthcheck");
        liveNodes.add("http://10.120.145.55:7271/healthcheck");
        liveNodes.add("http://10.120.145.117:7271/healthcheck");
        liveNodes.add("http://10.120.145.118:7271/healthcheck");
        return liveNodes;
    }

    private List<String> liveNodesStaticApi(){
        List<String> liveNodes = new ArrayList<>();
        liveNodes.add("http://10.120.145.71/healthcheck");
        liveNodes.add("http://10.120.145.72/healthcheck");
        liveNodes.add("http://10.120.145.73/healthcheck");
        liveNodes.add("http://10.120.145.74/healthcheck");
        liveNodes.add("http://10.120.145.75/healthcheck");
        liveNodes.add("http://10.120.145.76/healthcheck");
        liveNodes.add("http://10.120.145.77/healthcheck");
        liveNodes.add("http://10.120.145.78/healthcheck");
        return liveNodes;
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment the Sportsbook API.
     * Note: We set the Version from the Run Configurations.
     *
     * @throws Throwable
     */
    @Then("^user verify that the version for the Sportsbook API is the correct one$")
    public void verifySportsbookVersionApi() throws Throwable {

        String version = jenkinsInputs.getVersionOfTheApplicationAPI();
        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {
            String response = readJsonFromUrl(url).getJSONObject("info").getJSONObject("version").get("version").toString();
            assertEquals(response, version);
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the PDS service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the PDS service is working as expected$")
    public void verifyPDSservices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the PDS service
            String responsePDS = readJsonFromUrl(url + "/full").getJSONObject("pds").getJSONObject("status").get("info").toString();
            assertEquals(responsePDS, "OK");

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the SQUIZ service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the SQUIZ service is working as expected$")
    public void verifySquizServices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the SQUIZ service
            String responseSquiz = readJsonFromUrl(url + "/full").getJSONObject("squiz").getJSONObject("status").get("info").toString();
            assertEquals(responseSquiz, "OK");

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Top Bets service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Top Bets service is working as expected$")
    public void verifyTopBetsServices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the Top Bets service
            String responseTopBets = readJsonFromUrl(url + "/full").getJSONObject("topbets").getJSONObject("status").get("info").toString();
            assertEquals(responseTopBets, "OK");

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Funnelback service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Funnelback service is working as expected$")
    public void verifyFunnelbackServices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the Funnelback service
            String responseFunnelback = readJsonFromUrl(url + "/full").getJSONObject("funnelback").getJSONObject("status").get("info").toString();
            assertEquals(responseFunnelback, "OK");

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Betslip service is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Betslip service is working as expected$")
    public void verifyBetslipServices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the Betslip service
            String responseBetslip = readJsonFromUrl(url + "/full").getJSONObject("betslip").getJSONObject("status").get("info").toString();
            assertEquals(responseBetslip, "OK");

        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the Coupons service Health is Ok,
     * Status is Up and Count is Ok.
     * @throws Throwable
     */
    @Then("^user verify that the Coupons service is working as expected$")
    public void verifyCouponsServices() throws Throwable {

        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesApi();
        List<String> pp2Nodes = pp2NodesApi();
        List<String> pp3Nodes = pp3NodesApi();
        List<String> liveNodes = liveNodesApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {

            // Here we check the Coupons service
            String responseCouponsHealth = readJsonFromUrl(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("health").get("info").toString();
            assertEquals(responseCouponsHealth, "OK");

            String responseCouponsStatus = readJsonFromUrl(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("health").getJSONObject("detail").get("status").toString();
            assertEquals(responseCouponsStatus, "UP");

            String responseCouponsCount = readJsonFromUrl(url + "/full").getJSONObject("coupons").getJSONObject("coupons").getJSONObject("count").get("info").toString();
            assertEquals(responseCouponsCount, "OK");
        }
    }

    /**
     * On this Step we will verify on all the nodes of the current chosen environment if the API Static version .
     * Note: We set the Version from the Run Configurations.
     * @throws Throwable
     */
    @Then("^user verify that the version for the Sportsbook Static is the correct one$")
    public void verifySportsbookVersionStatic() throws Throwable {

        String version = jenkinsInputs.getVersionOfTheApplicationStatic();
        String envionment = jenkinsInputs.getEnvironmentBeingRun();

        Map<String, List<String>> environmentUrlForGettingVersion = new HashMap<>();

        List<String> pp1Nodes = pp1NodesStaticApi();
        List<String> pp2Nodes = pp2NodesStaticApi();
        List<String> pp3Nodes = pp3NodesStaticApi();
        List<String> liveNodes = liveNodesStaticApi();

        environmentUrlForGettingVersion.put("pp1", pp1Nodes);
        environmentUrlForGettingVersion.put("pp2", pp2Nodes);
        environmentUrlForGettingVersion.put("pp3", pp3Nodes);
        environmentUrlForGettingVersion.put("live", liveNodes);

        for (String url : environmentUrlForGettingVersion.get(envionment)) {
            String response = readJsonFromUrl(url).getJSONObject("info").get("version").toString();
            assertEquals(response, version);
        }
    }

    /**
     * This method will return the whole HTML code in Text
     * For the JSONObject reader below.
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * This method given a String URL will read the page HTML and return a JSON object.
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * This method will modify the Fractional Price of a Selection for the desired one.
     *
     * Note: We have to send the name of the Selection, otherwise this one will be modified.
     *
     * @param name
     * @param selectionId
     * @param price
     */
    public void modifySelectionFractionPrice(String name, String selectionId, String price) {

        if(price.equals("EVS")){
            price = "0.0";
        }

        Selection selection = new Selection();
        selection.setId(Integer.valueOf(selectionId.replaceAll("[\\D]", "")));

        // calculate new price
        Fraction fraction = Fraction.getFraction(price);
        String newPrice = fraction.getNumerator() + 1 + "/" + fraction.getDenominator();

        selection.setPrice(newPrice);
        selection.setName(name);

        getOxifeedHelper().updateSelection(selection);


    }
}