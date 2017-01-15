package digitalaxom.asm.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heerok on 13-01-2017.
 */
@Service
public class BatchImportService extends BatchImportAdapter{

    public void importFile(String filename){
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

        }


    }

}
