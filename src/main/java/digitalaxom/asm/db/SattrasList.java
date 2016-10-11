package digitalaxom.asm.db;

import javax.persistence.Entity;

/**
 * Created by Heerok on 11-10-2016.
 */
@Entity
public class SattrasList extends BaseModel{

    private String sattraName;
    private String sattradhikarName;
    private String phoneNo;
    private String contactDetails;
    private String district;
    private String state;

    public String getSattraName() {
        return sattraName;
    }

    public void setSattraName(String sattraName) {
        this.sattraName = sattraName;
    }

    public String getSattradhikarName() {
        return sattradhikarName;
    }

    public void setSattradhikarName(String sattradhikarName) {
        this.sattradhikarName = sattradhikarName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
