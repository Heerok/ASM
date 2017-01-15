package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Heerok on 13-01-2017.
 */
@Entity
public class ScrappingResult extends BaseModel{

    @ManyToOne
    private WebScraping webScraping;

    private String price;

    public WebScraping getWebScraping() {
        return webScraping;
    }

    public void setWebScraping(WebScraping webScraping) {
        this.webScraping = webScraping;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
