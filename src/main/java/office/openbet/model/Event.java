package office.openbet.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import util.Timer;
import util.Timer.DayFilter;

import java.util.ArrayList;

/**
* @author israel.lozano@williamhill.com
*/
public class Event {

	public Integer id;

	public String localId;

	public String pdsId;

	public String externalId;

	public String sport;

	public String competition;

	public Integer competitionId;

	public String name;

	public String status;

	public String startTime;

	public String offFlag;

	public String displayed;

	public Commentary commentary;

	public ArrayList<Market> markets;

	public String sort;

	private static final String PREFIX = "OB_EV";

	/**
	 *
	 * @param competitionId is the competition number where 0 is the primary competition.
	 */
	public Event(Integer competitionId) {
		this("event_" + RandomStringUtils.randomAlphabetic(8), competitionId);
	}

	public Event() {
		this.id = -1;
		this.pdsId = StringUtils.EMPTY;
		this.name = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.status = StringUtils.EMPTY;
		this.offFlag = StringUtils.EMPTY;
		this.sort = StringUtils.EMPTY;
		this.localId = StringUtils.EMPTY;
		this.competitionId = -1;
		this.competition = StringUtils.EMPTY;
		this.startTime = StringUtils.EMPTY;
		markets = new ArrayList<Market>();
	}

	public Event(String name, Integer competitionId) {
		this.id = -1;
		this.name = name;
		this.displayed = BackOfficeConstants.EVENT_DISPLAYED_YES;
		this.status = BackOfficeConstants.EVENT_STATUS_ACTIVE;
		this.offFlag = BackOfficeConstants.EVENT_OFF_FLAG_NA;
		this.sort = BackOfficeConstants.EVENT_TYPE_MATCH;
		this.localId = "01";
        this.competitionId = competitionId;
        this.competition = CompetitionIds.getCompetitionName(competitionId);

                // by default set event start time for today
        this.startTime = Timer.getDate(DayFilter.Today);
		markets = new ArrayList<Market>();
	}

	public Event(final String pdsId, final String name) {
		this.id = Integer.parseInt(pdsId.replace(PREFIX,StringUtils.EMPTY));
		this.pdsId = pdsId;
		this.name = name;
		this.displayed = BackOfficeConstants.EVENT_DISPLAYED_YES;
		this.status = BackOfficeConstants.EVENT_STATUS_ACTIVE;
		this.offFlag = BackOfficeConstants.EVENT_OFF_FLAG_NA;
		this.sort = BackOfficeConstants.EVENT_TYPE_MATCH;
		this.localId = "01";
		this.competitionId = -1;
		this.competition = StringUtils.EMPTY;

		// by default set event start time for today
		this.startTime = Timer.getDate(DayFilter.Today);
		markets = new ArrayList<Market>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.pdsId = PREFIX.concat(String.valueOf(id));
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public Integer getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	public String getOffFlag() {
		return offFlag;
	}

	public void setOffFlag(String offFlag) {
		this.offFlag = offFlag;
	}

	public Commentary getCommentary() {
		return commentary;
	}

	public void setCommentary(Commentary commentary) {
		this.commentary = commentary;
	}

	public String getExternal_id() {
		return externalId;
	}

	public void setExternal_id(String externalId) {
		this.externalId = externalId;
	}

	public ArrayList<Market> getMarkets() {
		return markets;
	}

	public void addMarket(Market market) {
		markets.add(market);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getPdsId() {
		return pdsId.isEmpty() ? "OB_EV" + id : pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
		this.id = Integer.parseInt(pdsId.replace(PREFIX, StringUtils.EMPTY));
	}

	public boolean isInPlay() {
		if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_YES) &&
				hasInPlayMarkets())
			return true;

		else if (Timer.getDate(startTime).before(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NA) &&
				hasInPlayMarkets())
			return true;

		else if (Timer.getDate(startTime).before(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_YES) &&
				hasInPlayMarkets())
			return true;

		else
			return false;
	}

	public boolean isPreMatch() {
		if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NO) &&
				hasPreMatchMarkets())
			return true;

		else if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NA) &&
				hasPreMatchMarkets())
			return true;

		else
			return false;

	}

	public boolean hasInPlayMarkets() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_YES))
				return true;

		return false;
	}

	public boolean hasPreMatchMarkets() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_NO))
				return true;

		return false;
	}

	public Market getFirstPreMatchMarket() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_NO))
				return market;

		throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any pre-match market.",
				this.getId()));
	}

	public Market getFirstInPlayMarket() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_YES))
				return market;

		throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any in-play market.",
				this.getId()));
	}

	public Market getFirstMarket() {
		if(isPreMatch())
			return getFirstPreMatchMarket();
		else if(isInPlay())
			return getFirstInPlayMarket();
		else if(!markets.isEmpty())
			return markets.get(0);
		else
			throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any markets.",
				this.getId()));
	}

    public Market getMarket(String marketName) {
        for (Market market : markets)
            if (market.getName().equalsIgnoreCase(marketName))
                return market;

        throw new IllegalArgumentException(String.format(
                "The event %s doesn't contain any market called %s",
                this.getId(), marketName));
    }

}
