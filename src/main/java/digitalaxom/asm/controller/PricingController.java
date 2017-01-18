package digitalaxom.asm.controller;

import digitalaxom.asm.Repository.ProductRepository;
import digitalaxom.asm.Repository.ScrappingResponseRepository;
import digitalaxom.asm.Repository.WebScrapingRepository;
import digitalaxom.asm.db.Product;
import digitalaxom.asm.db.ScrappingResponse;
import digitalaxom.asm.db.ScrappingResult;
import digitalaxom.asm.service.BatchImportService;
import digitalaxom.asm.service.ScraperService;
import digitalaxom.asm.view.Batch;
import digitalaxom.asm.view.ProductDTO;
import digitalaxom.asm.view.ProductPrice;
import digitalaxom.asm.view.ScrapingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Heerok on 16-01-2017.
 */
@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @Value("${asm.filelocation}")
    private String filedir;

    @Autowired
    private BatchImportService batchImportService;

    @Autowired
    private WebScrapingRepository webScrapingRepository;

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private ScrappingResponseRepository scrappingResponseRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/listData")
    public List<ScrapingData> listData(){
        return webScrapingRepository.findAll().stream().map(ScrapingData::new).collect(Collectors.toList());
    }

    @RequestMapping("/scrapAll")
    public void scrapAll(){
        scraperService.scrapAll();
    }

    @RequestMapping("/scrap")
    public void scrap(@RequestParam String productCode){
        scraperService.scrapProduct(productCode);
    }

    @RequestMapping("/listProductPrices")
    public ProductDTO listProductPrices(@RequestParam String productCode){
        return toProductDTO(productRepository.findOneByCode(productCode));
    }


    @RequestMapping("/listAllProductPrices")
    public List<ProductDTO> listAllProductPrices(){
        return toProductDTO(productRepository.findAll());
    }

    @RequestMapping(value ="/upload", method = RequestMethod.POST)
    public Batch importData(@RequestParam("file") MultipartFile file, @RequestParam(required = false) boolean dryrun){
        Path dirpath = Paths.get(filedir);
        Batch batch = new Batch();
        try{
            if (dirpath.toFile().exists()) {
                if (!dirpath.toFile().isDirectory()) {
                    System.out.println("{} is not a dir, cant continue");
                }
            } else {
                dirpath.toFile().mkdirs();
            }
            if (!file.isEmpty()) {
                String filename;
                filename =dirpath.resolve(UUID.randomUUID().toString() + ".xls").toString();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(filename)));
                stream.write(bytes);
                stream.close();
                batch.setFilename(filename);
                batch.setDryrun(dryrun);
                batchImportService.importFile(batch,filename,dryrun);
            }
            return batch;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<ProductDTO> toProductDTO(List<Product> all){
        List<ProductDTO> dtoList = new ArrayList<>();
        for(Product p: all){
            dtoList.add(toProductDTO(p));
        }
        return dtoList;
    }

    private ProductDTO toProductDTO(Product p){
        List<ScrappingResponse> list = scrappingResponseRepository.findByWebScrapingProduct(p);
        ProductDTO dto = new ProductDTO();
        dto.setCode(p.getCode());
        if(list!=null && list.size()>0){
            for(ScrappingResponse sr: list){
                String price="Currency not handled";
                if(sr.getUnit()==null || sr.getUnit().isEmpty())continue;
                if(sr.getUnit().equalsIgnoreCase("SAR") || "SR".equalsIgnoreCase(sr.getUnit()))
                    price = sr.getPrice().toString();
                else if(sr.getUnit().equalsIgnoreCase("AED"))
                    price = String.valueOf(sr.getPrice()*1.02d);
                dto.addProductPrice(new ProductPrice(sr.getWebScraping().getVendor(),sr.getWebScraping().getUrl(),price,sr.getCreatedDate().toString()));
            }
        }
        return dto;
    }

}
