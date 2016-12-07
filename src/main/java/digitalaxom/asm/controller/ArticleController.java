package digitalaxom.asm.controller;

import digitalaxom.asm.db.Article;
import digitalaxom.asm.service.ArticleService;
import digitalaxom.asm.view.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Heerok on 04-10-2016.
 */
@RestController
@RequestMapping(value = "/admin/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/findAll")
    public List<ArticleDTO> findAll(){
        return toDTO(articleService.findAll());
    }

    @RequestMapping(value = "/findAllActive")
    public List<Article> findAllActive(){
        return articleService.findAllActive();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestParam(required = false) String name, @RequestParam(required = false) String author,
                     @RequestParam(required = false) MultipartFile file,
                     HttpServletRequest request, @RequestParam(required = false) boolean active,
                     @RequestParam(required = false) String language,
                     @RequestParam(required = false) String articleString){
        articleService.save(name,author,file,request,active,language,articleString);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestParam(required = false) String name, @RequestParam(required = false) String author,
                       @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(required = false) Long Id,
                     HttpServletRequest request, @RequestParam(required = false) boolean active, @RequestParam String language){
        articleService.update(Id,name,author,file,request,active,language);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id){
        articleService.delete(id);
    }


    private List<ArticleDTO> toDTO(List<Article> all){
        return all.stream().map(ArticleDTO::new).collect(Collectors.toList());
    }

}
