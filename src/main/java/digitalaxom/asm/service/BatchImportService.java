package digitalaxom.asm.service;

import digitalaxom.asm.Repository.ProductRepository;
import digitalaxom.asm.Repository.ScrappingResponseRepository;
import digitalaxom.asm.Repository.WebScrapingRepository;
import digitalaxom.asm.db.Product;
import digitalaxom.asm.db.WebScraping;
import digitalaxom.asm.view.BatchImportDataMapping;
import digitalaxom.asm.view.Batch;
import digitalaxom.asm.view.BatchResult;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heerok on 13-01-2017.
 */
@Service
public class BatchImportService extends BatchImportAdapter{

    @Autowired
    private ScrappingResponseRepository scrappingResponseRepository;

    @Autowired
    private WebScrapingRepository webScrapingRepository;

    @Autowired
    private ProductRepository productRepository;

    public Batch importFile(Batch batch,String filename, boolean dryrun){
        List<String> columns = new ArrayList<>();
        List<String[]> params = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(filename));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet.getRow(0) == null) {
            batch.setStatus("ERROR");
            batch.setComments("Header missing at first row!!!");
            return batch;
        }
        for (Row row : sheet) {
            List<String> cells = collectRow(row, columns.size());
            if (row.getRowNum() == 0) {
                prepareHeader(cells, columns);
            } else {
                params.add(cells.toArray(new String[columns.size()]));
            }
        }
        if (params.size() == 0) {
            batch.setStatus("ERROR");
            batch.setComments("Data not found!!!");
            return batch;
        }

        Map<String, String> fieldmap = BatchImportDataMapping.getFieldmap();
        for (String[] row : params) {
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                if (fieldmap.containsKey(columns.get(i)))
                    map.put(fieldmap.get(columns.get(i)), row[i]);
            }
            importBatch(map, batch, dryrun);
        }
        return batch;
    }

    private void importBatch(HashMap<String, String> dataRow,Batch batch, boolean dryrun){
        if (StringUtils.isEmpty(dataRow.get("vendor"))) {
            batch.addResult(new BatchResult(
                    dataRow.get("vendor"),
                    dataRow.get("bids"),
                    "vendor is empty"
            ));
            return;
        }
        if (StringUtils.isEmpty(dataRow.get("bids"))) {
            batch.addResult(new BatchResult(
                    dataRow.get("vendor"),
                    dataRow.get("bids"),
                    "bids is empty"
            ));
            return;
        }
        if (StringUtils.isEmpty(dataRow.get("url"))) {
            batch.addResult(new BatchResult(
                    dataRow.get("vendor"),
                    dataRow.get("bids"),
                    "url is empty"
            ));
            return;
        }

        if(webScrapingRepository.findByProductCodeAndVendorAndUrl(dataRow.get("bids"),dataRow.get("vendor"),dataRow.get("url"))!=null){
            batch.addResult(new BatchResult(
                    dataRow.get("vendor"),
                    dataRow.get("bids"),
                    "Duplicate data"
            ));
            return;
        }

        WebScraping webScraping = new WebScraping();
        webScraping.setVendor(dataRow.get("vendor"));
        webScraping.setUrl(dataRow.get("url"));

        Product product = productRepository.findOneByCode(dataRow.get("bids"));
        if(product==null){
            product = new Product();
            product.setCode(dataRow.get("bids"));
            product.setName(dataRow.get("bids"));
            if(!dryrun)
            productRepository.save(product);
        }
        webScraping.setProduct(product);
        batch.addResult(new BatchResult(
                dataRow.get("vendor"),
                dataRow.get("bids"),
                "ok"));
        if(!dryrun){
            webScrapingRepository.save(webScraping);
        }

    }

}
