package digitalaxom.asm.db;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Heerok on 13-01-2017.
 */
@Entity
public class ScrappingResponse extends BaseModel{

    @ManyToOne
    private WebScraping webScraping;

    public WebScraping getWebScraping() {
        return webScraping;
    }

    public void setWebScraping(WebScraping webScraping) {
        this.webScraping = webScraping;
    }

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
