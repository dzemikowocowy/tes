package office.openbet.model;

import office.openbet.model.BackOfficeConstants.SelectionTypes;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/** 
* @author israel.lozano@williamhill.com
*/
public class Selection {

	public String pdsId;

	public Integer id;
	
	public String name;
	
	public String price;

	public String status;
	
	public String displayed;
	
	public String type;
	
	public Integer order;

    public String runner_order;

    public String result;

    public String place;

    public String SP;
	
	
	public Selection() {
		this.id = -1;
		this.name = "selection_" + RandomStringUtils.randomAlphabetic(8);
		this.price = "1/" + String.valueOf(new Random().nextInt(100) + 2);
		this.status = BackOfficeConstants.SELECTION_STATUS_ACTIVE;
		this.displayed = BackOfficeConstants.SELECTION_DISPLAYED_YES;
		this.type = SelectionTypes.home.toString();
		this.order = 0;
        this.runner_order = "";
        this.result = "";
        this.place = "";
        this.SP = "";
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = Integer.valueOf(order);
	}

    public String getRunnerOrder() {
        return runner_order;
    }

    public void setRunnerOrder(String runner_order) {
        this.runner_order = runner_order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSP() {
        return SP;
    }

    public void setSP(String SP) {
        this.SP = SP;
    }

	public String getPdsId() {
		return pdsId.isEmpty() ? "OB_OU" + id : pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
	}
}