package digitalaxom.asm.view;

import digitalaxom.asm.db.Article;
import digitalaxom.asm.db.BaseModel;
import digitalaxom.asm.db.SattrasList;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by Heerok on 11-10-2016.
 */
public class SattrasListDTO{

    private String sattraName;
    private String sattradhikarName;
    private String phoneNo;
    private String contactDetails;
    private String district;
    private String state;
    private Long Id;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public SattrasListDTO(SattrasList sl) {
        this.sattraName = sl.getSattraName();
        this.sattradhikarName = sl.getSattradhikarName();
        this.phoneNo = sl.getPhoneNo();
        this.contactDetails = sl.getContactDetails();
        this.district = sl.getDistrict();
        this.state = sl.getState();
        this.articles = sl.getArticles();
        this.Id = sl.getId();
    }
}
