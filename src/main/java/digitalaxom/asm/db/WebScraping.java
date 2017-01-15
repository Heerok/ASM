package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Heerok on 13-01-2017.
 */
@Entity
public class WebScraping extends BaseModel {

    @ManyToOne
    private Product product;
    private String vendor;
    private String url;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
