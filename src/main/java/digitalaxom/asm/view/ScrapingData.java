package digitalaxom.asm.view;

import digitalaxom.asm.db.WebScraping;

/**
 * Created by Heerok on 16-01-2017.
 */
public class ScrapingData {

    private String code;
    private String vendor;
    private String url;
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public ScrapingData(WebScraping scraping) {
        this.code = scraping.getProduct().getCode();
        this.vendor = scraping.getVendor();
        this.url = scraping.getUrl();
        this.status=scraping.getStatus();
    }
}
