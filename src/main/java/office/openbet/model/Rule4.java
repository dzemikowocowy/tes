package office.openbet.model;

/**
 * Created by israel on 02/06/2015.
 */
public class Rule4 {

    public String priceType;

    public String valid;

    public String fromTime;

    public String toTime;

    public String reduction;

    public String reason;

    public Rule4() {
        this.priceType = BackOfficeConstants.RULE4_PRICE_LIVE;
        this.valid = BackOfficeConstants.RULE4_VALID_YES;
        this.fromTime = "2015-01-01 20:05:00";
        this.toTime = "2015-01-01 20:59:59";
        this.reduction = "10";
        this.reason = "";
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
