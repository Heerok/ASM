package digitalaxom.asm.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import digitalaxom.asm.Repository.ScrappingResponseRepository;
import digitalaxom.asm.Repository.WebScrapingRepository;
import digitalaxom.asm.db.ScrappingResponse;
import digitalaxom.asm.db.WebScraping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Heerok on 13-01-2017.
 */
@Service
public class ScraperService {

    @Autowired
    private WebScrapingRepository webScrapingRepository;

    @Autowired
    private ScrappingResponseRepository scrappingResponseRepository;

    public void scrap(){
        List<WebScraping> webScrapings = webScrapingRepository.findAll();
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            for(WebScraping webScraping: webScrapings){
                //String searchUrl = "http://www.alshop.com/apple-iphone-6-16gb-lte-smartphone-silver.html";
                HtmlPage page = client.getPage(webScraping.getUrl());
                if(page==null)return;
                ScrappingResponse response = new ScrappingResponse();
                response.setWebScraping(webScraping);
                switch (webScraping.getVendor()){
                    case "souq uae":
                    case "souq":
                        List<HtmlElement> items = (List<HtmlElement>) page.getByXPath(".//h3[@class='price']");
                        /*for(HtmlElement item:items){
                            System.out.println(item.asText());
                        }*/
                        if(items!=null && items.size()>0){
                            response.setResponse(items.get(0).asText());
                            scrappingResponseRepository.save(response);
                        }
                        break;
                    default:
                        break;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
