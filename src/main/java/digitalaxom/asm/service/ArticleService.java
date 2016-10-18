package digitalaxom.asm.service;

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

    @Value("${asm.filelocation}")
    private String filedir;

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public Article save(String name, String author, MultipartFile file, HttpServletRequest request, boolean active,String language){
        Article article = new Article();
        article.setAuthor(author);
        article.setName(name);
        article.setActive(active);
        article.setLanguage(language);

        if(!file.isEmpty()){
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

}
