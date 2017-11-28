package office.openbet.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/** 
* @author israel.lozano@williamhill.com
*/
public class Market {

	public Integer id;
	
	public String localId;
	
	public String name;
	
	public String displayed;
	
	public Integer order;
	
	public String status;
	
	public String bir;
	
	public String blurb;
	
	public String starting;
	
	public String marketGroup;
	
	public String handicap;

    public String antepost;

    public String startPrice;

    public String livePrice;

    public String guaranteedPrice;

    public String earlyPrices;

    public String eachWay;

    public String eachWayPlaces;

    public String eachWayPlacesAt;
	
	public ArrayList<Selection> selections;

	public String pdsId;
	
	
	public Market(String marketGroup) {
		this.id = -1;
		this.localId = "01";
		this.name = marketGroup;
		this.displayed = BackOfficeConstants.MARKET_DISPLAYED_YES;
		this.order = 0;
		this.status = BackOfficeConstants.MARKET_STATUS_ACTIVE;
		this.bir = BackOfficeConstants.MARKET_BIR_NO;
		this.blurb = "";
		this.selections = new ArrayList<Selection>();
		this.marketGroup = marketGroup;
		this.handicap = "0.0";
        this.antepost = "";
        this.startPrice = "";
        this.livePrice = BackOfficeConstants.MARKET_LP_YES;
        this.guaranteedPrice = "";
        this.earlyPrices = "";
        this.eachWay = "";
        this.eachWayPlaces = "";
        this.eachWayPlacesAt = "";

	}

	public Market() {
		this.id = -1;
		this.localId = StringUtils.EMPTY;
		this.pdsId = StringUtils.EMPTY;
		this.name = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.order = 0;
		this.status = StringUtils.EMPTY;
		this.bir = StringUtils.EMPTY;
		this.blurb = StringUtils.EMPTY;
		this.selections = new ArrayList<Selection>();
		this.marketGroup = StringUtils.EMPTY;
		this.handicap = StringUtils.EMPTY;
		this.antepost = StringUtils.EMPTY;
		this.startPrice = StringUtils.EMPTY;
		this.livePrice = StringUtils.EMPTY;
		this.guaranteedPrice = StringUtils.EMPTY;
		this.earlyPrices = StringUtils.EMPTY;
		this.eachWay = StringUtils.EMPTY;
		this.eachWayPlaces = StringUtils.EMPTY;
		this.eachWayPlacesAt = StringUtils.EMPTY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBir() {
		return bir;
	}

	public void setBir(String bir) {
		this.bir = bir;
	}
	
	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public ArrayList<Selection> getSelections() {
		return selections;
	}

	public void setSelections(ArrayList<Selection> selections) {
		this.selections = selections;
	}
	
	public void addSelection(Selection selection) {
		this.selections.add(selection);
	}
	
	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = Integer.valueOf(order);
	}
	
	public String getMarketGroup() {
		return marketGroup;
	}

	public void setMarketGroup(String marketGroup) {
		this.marketGroup = marketGroup;
	}

	public String getHandicap() {
		return handicap;
	}

	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}

    public String getAntepost() {
        return antepost;
    }

    public void setAntepost(String antepost) {
        this.antepost = antepost;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getLivePrice() {
        return livePrice;
    }

    public void setLivePrice(String livePrice) {
        this.livePrice = livePrice;
    }

    public String getGuaranteedPrice() {
        return guaranteedPrice;
    }

    public void setGuaranteedPrice(String guaranteedPrice) {
        this.guaranteedPrice = guaranteedPrice;
    }

    public String getEarlyPrices() {
        return earlyPrices;
    }

    public void setEarlyPrices(String earlyPrices) {
        this.earlyPrices = earlyPrices;
    }

    public String getEachWay() {
        return eachWay;
    }

    public void setEachWay(String eachWay) {
        this.eachWay = eachWay;
    }

    public String getEachWayPlaces() {
        return eachWayPlaces;
    }

    public void setEachWayPlaces(String eachWayPlaces) {
        this.eachWayPlaces = eachWayPlaces;
    }

    public String getEachWayPlacesAt() {
        return eachWayPlacesAt;
    }

    public void setEachWayPlacesAt(String eachWayPlacesAt) {
        this.eachWayPlacesAt = eachWayPlacesAt;
    }

    public Selection getFirstSelection() {
		if (!selections.isEmpty())
				return selections.get(0);
		
		throw new IllegalArgumentException(String.format(
				"The market %s doesn't contain any selection.",
				this.getId()));
	}
	public Selection getSelectionByIndex(int arg) {
		if (!selections.isEmpty())
			return selections.get(arg);

		throw new IllegalArgumentException(String.format(
				"The market %s doesn't contain any selection.",
				this.getId()));
	}

    public Selection getSelection(String selectionName) {
        for (Selection selection : selections)
            if (selection.getName().equalsIgnoreCase(selectionName))
                return selection;

        throw new IllegalArgumentException(String.format(
                "The market %s doesn't contain any selection called %s",
                this.getId(), selectionName));
    }
	
}
