package digitalaxom.asm.view;

/**
 * Created by Heerok on 16-01-2017.
 */
public class ProductPrice {
    private String vendor;
    private String url;
    private String price;
    private String date;

    public ProductPrice(String vendor, String url, String price, String date) {
        this.vendor = vendor;
        this.url = url;
        this.price = price;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
