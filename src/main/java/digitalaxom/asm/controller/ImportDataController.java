package digitalaxom.asm.controller;

import digitalaxom.asm.service.BatchImportService;
import digitalaxom.asm.view.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Heerok on 16-01-2017.
 */
@RestController
@RequestMapping("/api/batch")
public class ImportDataController {

    @Value("${asm.filelocation}")
    private String filedir;

    @Autowired
    private BatchImportService batchImportService;

    @RequestMapping("/upload")
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
}
