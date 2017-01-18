package digitalaxom.asm.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heerok on 16-01-2017.
 */
public class ProductDTO {

    private String code;
    private List<ProductPrice> productPrices = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void addProductPrice(ProductPrice price){
        productPrices.add(price);
    }

    public void setProductPrices(List<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }
}
