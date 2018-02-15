package digitalaxom.asm.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import digitalaxom.asm.Repository.ArticleRepository;
import digitalaxom.asm.db.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * Created by Heerok on 04-10-2016.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Value("${asm.website.filelocation}")
    private String filedirW;

    @Value("${asm.admin.filelocation}")
    private String filedirA;

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public Article save(String name, String author, MultipartFile file, HttpServletRequest request, boolean active,String language,String articleString){
        Article article = new Article();
        article.setAuthor(author);
        article.setName(name);
        article.setActive(active);
        article.setLanguage(language);
        article.setArticleString(articleString);

        if(!file.isEmpty()){
            String fname = "";
            String[] fileName = file.getOriginalFilename().split("\\.");
            if(fileName.length>1){
                try {
                    String filename=request.getServletContext().getRealPath("/articles/")+file.getOriginalFilename();

                    Path dirpath = Paths.get(filedirA);
                    if (dirpath.toFile().exists()) {
                        if (!dirpath.toFile().isDirectory()) {
                            System.out.println("{} is not a dir, cant continue");
                        }
                    } else {
                        dirpath.toFile().mkdirs();
                    }
                    fname= dirpath.resolve(file.getOriginalFilename()).toString();


                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(fname)));
                    stream.write(bytes);
                    stream.close();

                    Path dirpath1 = Paths.get(filedirW);
                    if (dirpath1.toFile().exists()) {
                        if (!dirpath1.toFile().isDirectory()) {
                            System.out.println("{} is not a dir, cant continue");
                        }
                    } else {
                        dirpath1.toFile().mkdirs();
                    }
                    String fname1= dirpath1.resolve(file.getOriginalFilename()).toString();


                    byte[] bytes1 = file.getBytes();
                    BufferedOutputStream stream1 =
                            new BufferedOutputStream(new FileOutputStream(new File(fname1)));
                    stream1.write(bytes1);
                    stream1.close();
                    AmazonS3 s3Client=new AmazonS3Client(new BasicAWSCredentials("", ""));

                    ObjectMetadata objectMetadata = new ObjectMetadata();
                    objectMetadata.setContentLength(file.getSize());
                    PutObjectRequest putObjectRequest = new PutObjectRequest("", filename, file.getInputStream(), objectMetadata);
                    s3Client.putObject(putObjectRequest);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                article.setFilePath(file.getOriginalFilename());
            }
        }
        return articleRepository.save(article);
    }

    public Article update(Long Id, String name, String author, MultipartFile file, HttpServletRequest request, boolean active, String language){
        Article article = articleRepository.findOne(Id);
        if(!StringUtils.isEmpty(author))
        article.setAuthor(author);
        if(!StringUtils.isEmpty(name))
        article.setName(name);
        article.setActive(active);
        article.setLanguage(language);

        if(file!=null && !file.isEmpty()){
            String[] fileName = file.getOriginalFilename().split("\\.");
            if(fileName.length>1){
                try {
                    String filename=request.getServletContext().getRealPath("/articles/")+file.getOriginalFilename();

                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(filename)));
                    stream.write(bytes);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                article.setFilePath(file.getOriginalFilename());
            }
        }
        return articleRepository.save(article);
    }


    public void delete(Long id){
        articleRepository.delete(id);
    }

    public List<Article> findAllActive(){
        return articleRepository.findByActiveTrue();
    }

    public List<Article> findAllActive(String lang){
        return articleRepository.findByActiveTrueAndLanguage(lang);
    }

}
