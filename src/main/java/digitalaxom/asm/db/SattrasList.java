package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name="sattra_articles", joinColumns = {@JoinColumn(name = "sattra_id")},
    inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private Set<Article> articles;

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

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
