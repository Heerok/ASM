package digitalaxom.asm.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import digitalaxom.asm.Repository.ScrappingResponseRepository;
import digitalaxom.asm.Repository.WebScrapingRepository;
import digitalaxom.asm.db.ScrappingResponse;
import digitalaxom.asm.db.WebScraping;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    private static int count;

    public void scrapAll(){
        List<WebScraping> webScrapings = webScrapingRepository.findAll();
        System.out.println("Total urls: "+webScrapings.size());
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            for(WebScraping webScraping: webScrapings){
                //String searchUrl = "http://www.alshop.com/apple-iphone-6-16gb-lte-smartphone-silver.html";
                ScrappingResponse lastResonse = scrappingResponseRepository.findTopByWebScrapingOrderByCreatedDateDesc(webScraping);
                if(lastResonse!=null && !StringUtils.isEmpty(lastResonse.getUnit()) && lastResonse.getPrice()!=null && lastResonse.getCreatedDate().after(DateTime.now().withTimeAtStartOfDay().toDate()))
                    continue;
                try{
                    scrap(client,webScraping);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void scrapProduct(String code){
        List<WebScraping> webScrapings = webScrapingRepository.findByProductCode(code);
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            for(WebScraping webScraping: webScrapings){
                //String searchUrl = "http://www.alshop.com/apple-iphone-6-16gb-lte-smartphone-silver.html";
                /*if(scrappingResponseRepository.countByWebScraping(webScraping)>0)
                    continue;*/
                ScrappingResponse lastResonse = scrappingResponseRepository.findTopByWebScrapingOrderByCreatedDateDesc(webScraping);
                if(!StringUtils.isEmpty(lastResonse.getUnit()) && lastResonse.getPrice()!=null && lastResonse.getCreatedDate().after(DateTime.now().withTimeAtStartOfDay().toDate()))
                    continue;
                try{
                    scrap(client,webScraping);
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void scrap(WebClient client,WebScraping webScraping){
        ScrappingResponse response = new ScrappingResponse();
        response.setWebScraping(webScraping);
        HtmlPage page;
        List<HtmlElement> items;
        switch (webScraping.getVendor()){
            case "souq uae":
            case "souq":
                try{
                    page = client.getPage(webScraping.getUrl());
                }catch (Exception e){
                    e.printStackTrace();
                    webScraping.setStatus("Failed");
                    webScrapingRepository.save(webScraping);
                    break;
                }
                items = (List<HtmlElement>) page.getByXPath(".//h3[@class='price']");
                if(items!=null && items.size()>0){
                    String[] responses = items.get(0).asText().split("\\s+");
                    response.setPrice(Double.parseDouble(responses[0].replace(",","")));
                    response.setUnit(responses[1]);
                    response.setResponse(items.get(0).asText());
                    System.out.println(items.get(0).asText());
                    scrappingResponseRepository.save(response);
                }
                break;
            case "jarir":
                try{
                    page = client.getPage(webScraping.getUrl());
                }catch (Exception e){
                    e.printStackTrace();
                    webScraping.setStatus("Failed");
                    webScrapingRepository.save(webScraping);
                    break;
                }
                items = (List<HtmlElement>) page.getByXPath(".//span[@class='price']");
                if(items!=null && items.size()>0){
                    response.setResponse(items.get(0).asText());
                    response.setPrice(Double.parseDouble(items.get(0).asText().replace(",","")));
                    response.setUnit("SAR");
                    System.out.println(items.get(0).asText());
                    scrappingResponseRepository.save(response);
                }
                break;
            case "extrastores":
                try {
                    page = client.getPage(webScraping.getUrl());
                }catch (Exception e){
                    e.printStackTrace();
                    webScraping.setStatus("Failed");
                    webScrapingRepository.save(webScraping);
                    break;
                }
                if (page == null) break;
                items = (List<HtmlElement>) page.getByXPath(".//li[@class='price2']");
                if (items != null && items.size() > 0) {
                    String[] responses = items.get(0).asText().split("\\s+");
                    response.setPrice(Double.parseDouble(responses[1].replace(",","")));
                    response.setUnit(responses[0]);
                    response.setResponse(items.get(0).asText());
                    System.out.println(items.get(0).asText());
                    scrappingResponseRepository.save(response);
                }
                break;

            default:
                break;
        }

    }

    @Scheduled(cron="0 23 17 * * *")
    public void dailyRun(){
        System.out.println("Starting scheduler..");
        scrapAll();
        System.out.println("--End scheduler..");
    }

}
